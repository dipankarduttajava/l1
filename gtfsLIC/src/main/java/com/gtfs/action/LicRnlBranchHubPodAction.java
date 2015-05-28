package com.gtfs.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gtfs.bean.LicBrnhHubPicPodDtls;
import com.gtfs.bean.LicPolicyDtls;
import com.gtfs.bean.UserMst;
import com.gtfs.dto.LicPolicyDtlsDto;
import com.gtfs.service.interfaces.LicBrnhHubPicPodDtlsService;
import com.gtfs.service.interfaces.LicPolicyDtlsService;
import com.gtfs.service.interfaces.UserMstService;

@Component
@Scope("session")
public class LicRnlBranchHubPodAction implements Serializable{
	private Logger log = Logger.getLogger(LicRnlBranchHubPodAction.class);
	
	@Autowired
	private LoginAction loginAction;
	@Autowired
	private UserMstService userMstService;
	@Autowired
	private LicPolicyDtlsService licPolicyDtlsService;
	@Autowired
	private LicBrnhHubPicPodDtlsService licBrnhHubPicPodDtlsService;
	
	
	private String deliveryMode;
	private Long dispatchListNo;
	private Long empCode;
	private String empName;
	private String podNo;
	private String courierName;
	private UserMst employee;	
	private Boolean courierFlag;
	private Boolean handFlag;
	private Boolean renderedListPanel;
	
	private List<Long> dispatchList =  new ArrayList<Long>();
	private List<LicPolicyDtlsDto> licPolicyDtlsDtoList = new ArrayList<LicPolicyDtlsDto>();
	//private List<LicPolicyDtlsDto> selectedList = new ArrayList<LicPolicyDtls>();
	
	
	public void refresh(){
		try{
			courierFlag = false;
			handFlag = false;
			renderedListPanel = false;
			dispatchListNo = null;
			deliveryMode = null;
			empCode = null;
			empName = null;
			podNo = null;
			courierName = null;
			
			if(licPolicyDtlsDtoList != null){
				licPolicyDtlsDtoList.clear();
			}
			
			dispatchList = licPolicyDtlsService.findPodApplicationsForRenewal(loginAction.getUserList().get(0).getBranchMst().getBranchId());
		}catch(Exception e){
			log.info("LicRnlBranchHubPodAction refresh Error : ", e);
		}
	}
	
	public String onLoad(){
		refresh();
		return "/licBranchRenewalActivity/licRnlBranchHubPod.xhtml";
	}
	
	public void deliveryModeChange(){
		empCode = null;
		empName = null;
		podNo = null;
		courierName = null;
		if(deliveryMode.equals("H")){
			handFlag = true;
			courierFlag = false;
		}else if(deliveryMode.equals("P")){
			handFlag = false;
			courierFlag = true;
		}else{
			handFlag = false;
			courierFlag = false;
		}
	}
	
	public void findEmployee(){
		try{
			List<UserMst> list = userMstService.findActiveUserByUserId(empCode);
			
			if(!(list==null || list.size()==0)){
				employee = list.get(0);
				empName = list.get(0).getUserName();
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Error : ", "No Employee Found"));
				empName = null;
				return;
			}
		}catch(Exception e){
			log.info("LicRnlBranchHubPodAction findEmployee Error : ", e);
		}
	}
	
	public void searchForPod(){
		try{
			
			if(licPolicyDtlsDtoList!=null){
				licPolicyDtlsDtoList.clear();
			}
			
			if(deliveryMode.equals("H") && empName == null){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Error : ", "Please Enter a Valid Employee ID"));
				return;
			}
			
			List<Object> list = licPolicyDtlsService.findPolicyDtlsByDispatchIdAndBranchForPod(dispatchListNo, loginAction.getUserList().get(0).getBranchMst().getBranchId());
			
			if(list == null || list.size()==0 || list.contains(null)){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Error : ", "No Data is found"));
				return;
			}
			
			Iterator<Object> listIterator = list.iterator();
			while(listIterator.hasNext()){
				Object[] objects = (Object[]) listIterator.next();
				LicPolicyDtlsDto licPolicyDtlsDto = new LicPolicyDtlsDto();
				licPolicyDtlsDto.setPolicyNo((String) objects[0]);
				licPolicyDtlsDto.setPayDate((Date) objects[1]);
				licPolicyDtlsDto.setInsuredName((String) objects[2]);
				licPolicyDtlsDto.setProposerName((String) objects[3]);
				licPolicyDtlsDto.setProduct((String) objects[4]);
				licPolicyDtlsDto.setHealthFlag((String) objects[5]);
				licPolicyDtlsDto.setPayMode((String) objects[6]);
				licPolicyDtlsDto.setDueYears((Long) objects[7]);
				licPolicyDtlsDto.setFromDueDate((Date) objects[8]);
				licPolicyDtlsDto.setToDueDate((Date) objects[9]);
				licPolicyDtlsDto.setPayNature((String) objects[10]);
				licPolicyDtlsDtoList.add(licPolicyDtlsDto);				
			}
			renderedListPanel = true;
		}catch(Exception e){
			log.info("LicRnlBranchHubPodAction searchForPod Error : ", e);
		}
	}
	
	public void onSave(){
		try{
			Date now = new Date();			
			List<LicPolicyDtls> licPolicyDtlsToSave = new ArrayList<LicPolicyDtls>();
			
			LicBrnhHubPicPodDtls licBrnhHubPicPodDtls = new LicBrnhHubPicPodDtls();
			licBrnhHubPicPodDtls.setBranchHubPicFlag("B2H");
			licBrnhHubPicPodDtls.setPodNo(podNo != null ? podNo: (now.getTime() + loginAction.getUserList().get(0).getUserid() + ""));
			licBrnhHubPicPodDtls.setPodDate(now);
			licBrnhHubPicPodDtls.setEmployee(employee);
			licBrnhHubPicPodDtls.setCourierName(courierName);
			licBrnhHubPicPodDtls.setCreatedBy(loginAction.getUserList().get(0).getUserid());
			licBrnhHubPicPodDtls.setModifiedBy(loginAction.getUserList().get(0).getUserid());
			licBrnhHubPicPodDtls.setCreatedDate(now);
			licBrnhHubPicPodDtls.setModifiedDate(now);
			licBrnhHubPicPodDtls.setDeleteFlag("N");

			for(LicPolicyDtlsDto obj : licPolicyDtlsDtoList){
				List<LicPolicyDtls> dtls = licPolicyDtlsService.findPolicyDtlsByPolicyNoAndDueDateRange(obj.getPolicyNo(),obj.getFromDueDate(),obj.getToDueDate());
				Iterator<LicPolicyDtls> iterator = dtls.iterator();
				while(iterator.hasNext()){
					LicPolicyDtls policyDtls = iterator.next();
					policyDtls.setBrnhHubPodDtls(licBrnhHubPicPodDtls);					
					licPolicyDtlsToSave.add(policyDtls);
				}
			}

			Boolean status = licPolicyDtlsService.saveBrnhHubPodDtlsForRenewal(licPolicyDtlsToSave);
			
			if(status){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
	                    "Save Successful : ", "Branch To HUB Renewal POD Generated Successfully"));
				refresh();
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Save Unsuccessful : ", "Branch To HUB Renewal POD Generation Unsuccessful"));
			}
		}catch(Exception e){
			log.info("LicRnlBranchHubPodAction save Error : ", e);
		}
	}

	
	/* GETTER SETTER */
	public String getDeliveryMode() {
		return deliveryMode;
	}

	public void setDeliveryMode(String deliveryMode) {
		this.deliveryMode = deliveryMode;
	}

	public Long getDispatchListNo() {
		return dispatchListNo;
	}

	public void setDispatchListNo(Long dispatchListNo) {
		this.dispatchListNo = dispatchListNo;
	}

	public Long getEmpCode() {
		return empCode;
	}

	public void setEmpCode(Long empCode) {
		this.empCode = empCode;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getPodNo() {
		return podNo;
	}

	public void setPodNo(String podNo) {
		this.podNo = podNo;
	}

	public String getCourierName() {
		return courierName;
	}

	public void setCourierName(String courierName) {
		this.courierName = courierName;
	}

	public UserMst getEmployee() {
		return employee;
	}

	public void setEmployee(UserMst employee) {
		this.employee = employee;
	}

	public Boolean getCourierFlag() {
		return courierFlag;
	}

	public void setCourierFlag(Boolean courierFlag) {
		this.courierFlag = courierFlag;
	}

	public Boolean getHandFlag() {
		return handFlag;
	}

	public void setHandFlag(Boolean handFlag) {
		this.handFlag = handFlag;
	}

	public Boolean getRenderedListPanel() {
		return renderedListPanel;
	}

	public void setRenderedListPanel(Boolean renderedListPanel) {
		this.renderedListPanel = renderedListPanel;
	}

	public List<Long> getDispatchList() {
		return dispatchList;
	}

	public void setDispatchList(List<Long> dispatchList) {
		this.dispatchList = dispatchList;
	}

	public List<LicPolicyDtlsDto> getLicPolicyDtlsDtoList() {
		return licPolicyDtlsDtoList;
	}

	public void setLicPolicyDtlsDtoList(List<LicPolicyDtlsDto> licPolicyDtlsDtoList) {
		this.licPolicyDtlsDtoList = licPolicyDtlsDtoList;
	}

	
	
}
