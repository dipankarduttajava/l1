package com.gtfs.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gtfs.bean.LicBrnhHubPicPodDtls;
import com.gtfs.bean.LicOblApplicationMst;
import com.gtfs.bean.LicPolicyDtls;
import com.gtfs.bean.LicPolicyMst;
import com.gtfs.bean.LicRequirementDtls;
import com.gtfs.bean.UserMst;
import com.gtfs.service.interfaces.LicBrnhHubPicPodDtlsService;
import com.gtfs.service.interfaces.LicRequirementDtlsService;
import com.gtfs.service.interfaces.UserMstService;

@Component
@Scope("session")
public class HubPicLicPodForReqAction implements Serializable{
	private Logger log = Logger.getLogger(HubPicLicPodForReqAction.class);
	
	@Autowired
	private LicRequirementDtlsService licRequirementDtlsService;
	@Autowired
	private UserMstService userMstService;
	@Autowired
	private LoginAction loginAction;
	@Autowired
	private LicBrnhHubPicPodDtlsService licBrnhHubPicPodDtlsService;
	
	private List<Long> dispatchLists =  new ArrayList<Long>();
	private Long dispatchListNo;
	private String deliveryMode;
	private Boolean courierFlag;
	private Boolean handFlag;
	private Long empCode;
	private String empName;
	private UserMst employee;
	private String podNo;
	private String courierName;
	private Boolean renderedListPanel;
	private List<LicRequirementDtls> licRequirementDtlsList = new ArrayList<LicRequirementDtls>();
	
	
	public void refresh(){
		try{
			dispatchListNo = null;
			deliveryMode = null;
			empCode = null;
			empName = null;
			handFlag = false;
			courierFlag = false;
			renderedListPanel = false;
			
			if(licRequirementDtlsList!=null){
				licRequirementDtlsList.clear();
			}
			
			if(dispatchLists!=null){
				dispatchLists.clear();
			}
			
			dispatchLists = licRequirementDtlsService.findPodRequirmentForPicDispatch(loginAction.findHubForProcess("POS"));

		}catch(Exception e){
			log.info("HubPicLicPodForReqAction refresh Error : ", e);
		}
	}
	
	public void searchForPod(){
		try{
			licRequirementDtlsList = licRequirementDtlsService.findRequirmentByDispatchListForPicDispatch(dispatchListNo, loginAction.getUserList().get(0).getBranchMst());
			renderedListPanel = true;
		}catch(Exception e){
			log.info("HubPicLicPodForReqAction searchForPod Error : ", e);
		}
	}
	
	public void deliveryModeChange(){
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
			if(!(list == null || list.size()==0)){
				employee = list.get(0);
				empName = list.get(0).getUserName();
			}else{
				licRequirementDtlsList.clear();
				empName = null;
			}
		}catch(Exception e){
			log.info("HubPicLicPodForReqAction findEmployee Error : ", e);
		}
	}
	
	public void save(){
		try{
			Date now = new Date();		
			LicBrnhHubPicPodDtls licBrnhHubPicPodDtls = new LicBrnhHubPicPodDtls();
			licBrnhHubPicPodDtls.setBranchHubPicFlag("H2P");
			licBrnhHubPicPodDtls.setPodNo(podNo!=null ?podNo: (now.getTime()+loginAction.getUserList().get(0).getUserid()+""));
			licBrnhHubPicPodDtls.setPodDate(now);
			licBrnhHubPicPodDtls.setEmployee(employee);
			licBrnhHubPicPodDtls.setCourierName(courierName);
			licBrnhHubPicPodDtls.setCreatedBy(loginAction.getUserList().get(0).getUserid());
			licBrnhHubPicPodDtls.setModifiedBy(loginAction.getUserList().get(0).getUserid());
			licBrnhHubPicPodDtls.setCreatedDate(now);
			licBrnhHubPicPodDtls.setModifiedDate(now);
			licBrnhHubPicPodDtls.setDeleteFlag("N");
			
			licBrnhHubPicPodDtls.setLicRequirementDtlses(licRequirementDtlsList);
			
			for(LicRequirementDtls licRequirementDtls:licRequirementDtlsList){
				licRequirementDtls.setLicBrnhHubPicPodDtls(licBrnhHubPicPodDtls);	
			}
			
			Long id = licBrnhHubPicPodDtlsService.saveForHubPicPodDtls(licBrnhHubPicPodDtls);

			if (id > 0) {
				refresh();
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
								"Save Successful : ", "PID POD Successfully"));
				onLoad();
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
										"Save Unsuccessful : ", "PID POD Unsuccessful"));
			}
		}catch(Exception e){
			log.info("HubPicLicPodForReqAction save Error : ", e);
		}
	}
	
	public String onLoad(){
		refresh();
		return "/licHubActivity/hubPicLicPodForReq.xhtml";
	}

	
	
	/* GETTER SETTER */
	public List<Long> getDispatchLists() {
		return dispatchLists;
	}

	public void setDispatchLists(List<Long> dispatchLists) {
		this.dispatchLists = dispatchLists;
	}

	public Long getDispatchListNo() {
		return dispatchListNo;
	}

	public void setDispatchListNo(Long dispatchListNo) {
		this.dispatchListNo = dispatchListNo;
	}

	public String getDeliveryMode() {
		return deliveryMode;
	}

	public void setDeliveryMode(String deliveryMode) {
		this.deliveryMode = deliveryMode;
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

	public UserMst getEmployee() {
		return employee;
	}

	public void setEmployee(UserMst employee) {
		this.employee = employee;
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

	public List<LicRequirementDtls> getLicRequirementDtlsList() {
		return licRequirementDtlsList;
	}

	public void setLicRequirementDtlsList(
			List<LicRequirementDtls> licRequirementDtlsList) {
		this.licRequirementDtlsList = licRequirementDtlsList;
	}

	public Boolean getRenderedListPanel() {
		return renderedListPanel;
	}

	public void setRenderedListPanel(Boolean renderedListPanel) {
		this.renderedListPanel = renderedListPanel;
	}
	
}
