package com.gtfs.action;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import sun.misc.BASE64Encoder;

import com.gtfs.bean.BranchMst;
import com.gtfs.bean.LicBranchHubMap;
import com.gtfs.bean.LicHubMst;
import com.gtfs.bean.UserLoginDtls;
import com.gtfs.bean.UserMst;
import com.gtfs.service.interfaces.AccessListService;
import com.gtfs.service.interfaces.BranchMstService;
import com.gtfs.service.interfaces.LicBranchHubMapService;
import com.gtfs.service.interfaces.LicHubMstService;
import com.gtfs.service.interfaces.UserLoginDtlsService;
import com.gtfs.service.interfaces.UserMstService;

@Component
@Scope("session")
public class LoginAction implements Serializable{
	private Logger log = Logger.getLogger(LoginAction.class);
	
	@Autowired
	private UserMstService userMstService;
	@Autowired
	private AccessListService accessListService;
	@Autowired
	private UserLoginDtlsService userLoginDtlsService;
	@Autowired
	private BranchMstService branchMstService;
	@Autowired
	private LicHubMstService licHubMstService;
	@Autowired
	private LicBranchHubMapService licBranchHubMapService;
	
	private String loginId;
	private String password;
	private BranchMst branchMst;
	private String currentPassword;
	private String newPassword;
	private String confirmPassword;
	private List<UserMst> userList;
	private Map<Long, String> map = new HashMap<Long, String>();
	private List<BranchMst> branchMstList = new ArrayList<BranchMst>();
	private List<LicBranchHubMap> licBranchHubMaps = new ArrayList<LicBranchHubMap>();
	private Boolean loginFlag;
	
	
	public List<BranchMst> findBranch(String query){		
		ArrayList<BranchMst> list = new ArrayList<BranchMst>();
		for(BranchMst branch : branchMstList){
			if(branch.getBranchName().toUpperCase().startsWith(query.toUpperCase())){
				list.add(branch);
			}
		}
		return list;
	}
	
	@PreDestroy
	public void destroy(){
	}
	
	@PostConstruct
	public void loadBranch(){
		branchMstList = branchMstService.findAll();
	}
	
	public String goToWelcomePage() {
       try{
    	   if (branchMst.getBranchId().equals(userList.get(0).getBranchMst().getBranchId())) {
          	 map.clear();
          	List<Object> accessList = accessListService.findAccessListByUserId(userList.get(0).getUserid());
              for(Object obj:accessList){
                  Object[] ob=(Object[]) obj;
                  Long accessId=(Long) ob[0];
                  String accessName=(String) ob[1];
                  map.put(accessId, accessName); 
              }
              
              licBranchHubMaps = licBranchHubMapService.findBranchHubMapsByBranch(branchMst);
              
              FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                      "Login Success: ", "Welcome To LIC Dashboard , " + userList.get(0).getUserName()));
              loginFlag = true;
              return "/dashBoard.xhtml";            
          } else {
              FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                      "Login Error: ", "Please Contact To HEAD OFFICE IT"));
              ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
              Map<String, Object> sessionMap = externalContext.getSessionMap();
              sessionMap.clear();
              return "/login.xhtml?faces-redirect=true";
          }
       }catch(Exception e){
    	   log.info("LoginAction goToWelcomePage Error : ", e);
    	   return "/login.xhtml?faces-redirect=true";
       }
    }
	
	public List<LicHubMst> findHubForProcess(String process){
		List<LicHubMst> licHubMsts = new ArrayList<LicHubMst>();
		if(!(licBranchHubMaps==null || licBranchHubMaps.size()==0 || licBranchHubMaps.contains(null))){
			for(LicBranchHubMap obj:licBranchHubMaps){
				if(obj.getProcessMst().getProcessAbbr().equals(process)){
					licHubMsts.add(obj.getLicHubMst());
				}
			}
		}
		return licHubMsts;
	}
	
	 public String changePassword() {
	        try{
	        	Boolean status = userMstService.doChangePasswordProcess(userList.get(0).getUserid(), userList.get(0).getBranchMst().getBranchId(), newPassword);
		        if (status == true) {	        	
		            return "/chooseBranch.xhtml";
		        } else {
		        	return "/login.xhtml?faces-redirect=true";
		        }
	        }catch(Exception e){
	        	log.info("LoginAction changePassword Error : ", e);
	        	return "/login.xhtml?faces-redirect=true";
	        }
	    }
	
	public String doLogin(){
		try{
			if (!password.equals("abcd")) {
				password = encrypt(password, "SHA", "UTF-8");
			}
			
			userList = userMstService.login(loginId, password);

			if (userList == null || userList.size() == 0 || userList.contains(null)) {
	            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Login Error: ", "Invalid Username or Password.."));

	            return "/login.xhtml?faces-redirect=true";
	        } else {
	        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
	                    "Login Success: ", "Welcome, " + userList.get(0).getUserName()));
	        	
	            List<Object> maxTimeList = userLoginDtlsService.findMaximumLoginTimeOfUser(userList.get(0).getUserid());
	            if (maxTimeList == null || password.equals("abcd") || maxTimeList.contains(null)) {
	                return "changePassword.xhtml";
	            } else {
	                UserLoginDtls userLoginDtls = new UserLoginDtls();
	                userLoginDtls.setUserid(userList.get(0).getUserid());
	                userLoginDtls.setBranchId(userList.get(0).getBranchMst().getBranchId());
	                userLoginDtls.setLoginTime(new Date());

	                Long id = userLoginDtlsService.insert(userLoginDtls);

	                if (id != null && id > 0l) {
	                	loginFlag=true;
	                    return "/chooseBranch.xhtml";
	                } else {
	                	return "/login.xhtml?faces-redirect=true";
	                }
	            }
	        }
		}catch(Exception e){
			log.info("LoginAction doLogin Error : ", e);
			return "/login.xhtml?faces-redirect=true";
		}
	}
	
	public String logout(){
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        loginFlag = false;
        sessionMap.clear();
        return "/login.xhtml?faces-redirect=true";
    }
	
	public static synchronized String encrypt(String plaintext, String algorithm, String encoding) {
		MessageDigest msgDigest = null;
		String hashValue = null;
		try {
			msgDigest = MessageDigest.getInstance(algorithm);
			msgDigest.update(plaintext.getBytes(encoding));
			byte rawByte[] = msgDigest.digest();
			hashValue = (new BASE64Encoder()).encode(rawByte);

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return hashValue;
	}
	
	
	/* GETTER SETTER */
	public BranchMst getBranchMst() {
		return branchMst;
	}

	public void setBranchMst(BranchMst branchMst) {
		this.branchMst = branchMst;
	}

	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}


	public String getCurrentPassword() {
		return currentPassword;
	}


	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}


	public String getNewPassword() {
		return newPassword;
	}


	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}


	public String getConfirmPassword() {
		return confirmPassword;
	}


	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}


	public Map<Long, String> getMap() {
		return map;
	}


	public void setMap(Map<Long, String> map) {
		this.map = map;
	}


	public List<UserMst> getUserList() {
		return userList;
	}


	public List<BranchMst> getBranchMstList() {
		return branchMstList;
	}


	public void setBranchMstList(List<BranchMst> branchMstList) {
		this.branchMstList = branchMstList;
	}


	public void setUserList(List<UserMst> userList) {
		this.userList = userList;
	}

	

	public List<LicBranchHubMap> getLicBranchHubMaps() {
		return licBranchHubMaps;
	}

	public void setLicBranchHubMaps(List<LicBranchHubMap> licBranchHubMaps) {
		this.licBranchHubMaps = licBranchHubMaps;
	}

	public Boolean getLoginFlag() {
		return loginFlag;
	}

	public void setLoginFlag(Boolean loginFlag) {
		this.loginFlag = loginFlag;
	}
	
	
}
