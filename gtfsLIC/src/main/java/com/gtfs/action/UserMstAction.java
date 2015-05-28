package com.gtfs.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gtfs.bean.RoleMst;
import com.gtfs.bean.UserMst;
import com.gtfs.bean.UserRoleRlns;
import com.gtfs.dto.UserMstDto;
import com.gtfs.dto.UserRoleRlnsDto;
import com.gtfs.service.interfaces.AccessListService;
import com.gtfs.service.interfaces.BranchMstService;
import com.gtfs.service.interfaces.RoleMstService;
import com.gtfs.service.interfaces.UserMstService;
import com.gtfs.service.interfaces.UserRoleRlnsService;

@Component
@Scope("session")
public class UserMstAction implements Serializable{
	private Logger log = Logger.getLogger(UserMstAction.class);
	
	@Autowired
	private LoginAction loginAction;	
	@Autowired
	private UserMstService userMstService;	
	@Autowired
	private RoleMstService roleMstService;	
	@Autowired
	private AccessListService accessListService;	
	@Autowired
	private UserRoleRlnsService userRoleRlnsService;
	@Autowired
	private BranchMstService branchMstService;
	
	private String userName;
	private Long userId;
	private Long userid;
	private UserMstDto userMstDto;
	private Boolean disabledAction;
	private Boolean disabledRole;
	private Boolean disabledDetails;
	private Boolean disabledUserInfo;
	
	private List<UserMstDto> listOfUsers = new ArrayList<UserMstDto>();
	private List<UserMstDto> userDetails = new ArrayList<UserMstDto>();
	private List<UserRoleRlnsDto> userRoleRlnsDtoList = new ArrayList<UserRoleRlnsDto>();
	private List<RoleMst> roleMstList = new ArrayList<RoleMst>();
	
	@PostConstruct
	public void loadRoleMst(){
		roleMstList = roleMstService.findAllActiveRole();
	}
	
	public void refresh(){
		listOfUsers.clear();
		disabledAction = false;
		disabledRole = false;
		disabledDetails = false;
		disabledUserInfo = true;
	}
	
	public String onLoad(){
		refresh();
		return "/admin/userMst.xhtml";
	}
	
	public void onSearch(){
		try{
			List<UserMst> list = null;
			
			if(userId != null){
				list = userMstService.findActiveUserInfoByUserId(userId);
			}else if(userName != null){
				list = userMstService.findActiveUserInfoByUserName(userName);
			}else {
				list = userMstService.findAllActiveUserInfo();
			}
			
			listOfUsers.clear();
			for(UserMst userMst : list){
				UserMstDto userMstDto = new UserMstDto();
				userMstDto.setUserid(userMst.getUserid());
				userMstDto.setUserName(userMst.getUserName());
				userMstDto.setLogin(userMst.getLogin());
				userMstDto.setPasswd(userMst.getPasswd());
				userMstDto.setDesigName(userMst.getDesignationMst().getDesignation());
				userMstDto.setHoName("NA");
				userMstDto.setRegionName("NA");
				userMstDto.setDivName(userMst.getDivisionMst().getDivName());
				userMstDto.setBranchName(userMst.getBranchMst().getBranchName());
				userMstDto.setLastLogin(userMst.getLastLogin());
				userMstDto.setTieupCompName(userMst.getTieupCompyMst().getTieCompyName());
				userMstDto.setDept(userMst.getDept());
				userMstDto.setDisabledFlag(true);
				listOfUsers.add(userMstDto);
			}
		}catch(Exception e){
			log.info("UserMstAction Search Error : ", e);
		}
	}
	
	public void addList(){
		disabledAction = true;
		disabledRole = true;
		disabledDetails = true;
		listOfUsers.clear();
		UserMstDto userMstDto = new UserMstDto();
		userMstDto.setDisabledFlag(false);
		listOfUsers.add(userMstDto);
	}
	
	public void saveList(){

	}
	
	public void onEdit(){
		disabledUserInfo = false;
	}
	
	public void editRoleForUser(UserMstDto userMstDto){
		try{
			userRoleRlnsDtoList.clear();
			userid = userMstDto.getUserid();
			List<UserRoleRlns> list = userRoleRlnsService.findActiveUserRoleByUserId(userid);
			List<Long> roleIdList = new ArrayList<Long>();

			for(UserRoleRlns userRoleRlns : list){
				roleIdList.add(userRoleRlns.getRoleMst().getRoleId());
			}

			for(RoleMst roleMst : roleMstList){
				UserRoleRlnsDto userRoleRlnsDto = new UserRoleRlnsDto();
				userRoleRlnsDto.setRoleId(roleMst.getRoleId());
				userRoleRlnsDto.setRoleName(roleMst.getRoleName());
				userRoleRlnsDto.setRoleGivenStatus(roleIdList.contains(roleMst.getRoleId()));
				userRoleRlnsDto.setUserid(userid);
				userRoleRlnsDtoList.add(userRoleRlnsDto);
			}
			//RequestContext.getCurrentInstance().openDialog("userRoleDialog");
		}catch(Exception e){
			log.info("UserMstAction editRoleForUser Error : ", e);
		}
	}
	
	public void changeStatusListener(UserRoleRlnsDto userRoleRlnsDto){
		try{
			if(userRoleRlnsDto.getRoleGivenStatus() == true){
				UserRoleRlns userRoleRlns = new UserRoleRlns();
				userRoleRlns.setUserMst(userMstService.findById(userid));
				userRoleRlns.setRoleMst(roleMstService.findRoleById(userRoleRlnsDto.getRoleId()));
				userRoleRlns.setActiveFlag("Y");
				userRoleRlns.setUserId(loginAction.getUserList().get(0).getUserid());
				userRoleRlns.setDateTime(new Date());
				userRoleRlnsService.insertIntoUserRoleRlns(userRoleRlns);
			}else if(userRoleRlnsDto.getRoleGivenStatus() == false){
				userRoleRlnsService.deleteFromUserRoleRlnsByRoleIdAndUserId(userRoleRlnsDto.getRoleId(), userRoleRlnsDto.getUserid(), loginAction.getUserList().get(0).getUserid());
			}
		}catch(Exception e){
			log.info("UserMstAction changeStatusListener Error : ", e);
		}
	}
	
	public void viewDetailUserInfo(UserMstDto userMstDto){
		try{
			List<UserMst> list = userMstService.findActiveUserInfoByUserId(userMstDto.getUserid());
			
			userDetails.clear();
			for(UserMst userMst : list){
				UserMstDto userMstDto1 = new UserMstDto();
				userMstDto1.setUserid(userMst.getUserid());
				userMstDto1.setUserName(userMst.getUserName());
				userMstDto1.setLogin(userMst.getLogin());
				userMstDto1.setPasswd(userMst.getPasswd());
				userMstDto1.setDesigName(userMst.getDesignationMst().getDesignation());
				userMstDto1.setHoName("NA");
				userMstDto1.setRegionName("NA");
				userMstDto1.setDivName(userMst.getDivisionMst().getDivName());
				userMstDto1.setBranchName(userMst.getBranchMst().getBranchName());
				userMstDto1.setLastLogin(userMst.getLastLogin());
				userMstDto1.setTieupCompName(userMst.getTieupCompyMst().getTieCompyName());
				userMstDto1.setDept(userMst.getDept());
				userMstDto1.setDisabledFlag(true);
				userDetails.add(userMstDto);
			}
		}catch(Exception e){
			log.info("UserMstAction viewDetailUserInfo Error : ", e);
		}
	}
	
	public void deleteUserMst(UserMstDto userMstDto) {
		try{
			Long userId = loginAction.getUserList().get(0).getUserid();
			Boolean status = userMstService.deleteUserMst(userMstDto.getUserid(), userId);
			
			if (status) {
				listOfUsers.remove(userMstDto);
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,
						"User Deleted Successfully ", null));
			} else {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Error Occured, Please Try Again ", null));
				
			}
		}catch(Exception e){
			log.info("UserMstAction deleteUserMst Error : ", e);
		}
	}

	
	/* GETTER SETTER */
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public List<UserMstDto> getListOfUsers() {
		return listOfUsers;
	}

	public void setListOfUsers(List<UserMstDto> listOfUsers) {
		this.listOfUsers = listOfUsers;
	}

	public List<RoleMst> getRoleMstList() {
		return roleMstList;
	}

	public void setRoleMstList(List<RoleMst> roleMstList) {
		this.roleMstList = roleMstList;
	}

	public List<UserRoleRlnsDto> getUserRoleRlnsDtoList() {
		return userRoleRlnsDtoList;
	}

	public void setUserRoleRlnsDtoList(List<UserRoleRlnsDto> userRoleRlnsDtoList) {
		this.userRoleRlnsDtoList = userRoleRlnsDtoList;
	}

	public List<UserMstDto> getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(List<UserMstDto> userDetails) {
		this.userDetails = userDetails;
	}

	public UserMstDto getUserMstDto() {
		return userMstDto;
	}

	public void setUserMstDto(UserMstDto userMstDto) {
		this.userMstDto = userMstDto;
	}

	public Boolean getDisabledAction() {
		return disabledAction;
	}

	public void setDisabledAction(Boolean disabledAction) {
		this.disabledAction = disabledAction;
	}

	public Boolean getDisabledRole() {
		return disabledRole;
	}

	public void setDisabledRole(Boolean disabledRole) {
		this.disabledRole = disabledRole;
	}

	public Boolean getDisabledDetails() {
		return disabledDetails;
	}

	public void setDisabledDetails(Boolean disabledDetails) {
		this.disabledDetails = disabledDetails;
	}

	public Boolean getDisabledUserInfo() {
		return disabledUserInfo;
	}

	public void setDisabledUserInfo(Boolean disabledUserInfo) {
		this.disabledUserInfo = disabledUserInfo;
	}
	
}
