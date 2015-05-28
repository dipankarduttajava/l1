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
import com.gtfs.bean.UserMst;
import com.gtfs.dto.LicOblApplicationMstDto;
import com.gtfs.service.interfaces.LicBrnhHubPicPodDtlsService;
import com.gtfs.service.interfaces.LicOblApplicationMstService;
import com.gtfs.service.interfaces.UserMstService;

@Component
@Scope("session")
public class BranchHubLicPodAction implements Serializable{
	private Logger log = Logger.getLogger(BranchHubLicPodAction.class);
	
	@Autowired
	private UserMstService userMstService;
	@Autowired
	private LicOblApplicationMstService licOblApplicationMstService;
	@Autowired
	private LoginAction loginAction;
	@Autowired
	private LicBrnhHubPicPodDtlsService licBrnhHubPicPodDtlsService;
	
	private String deliveryMode;
	private Long dispatchListNo;
	private Long empCode;
	private String empName;
	private String podNo;
	private String courierName;
	private Date businessFromDate;
	private Date businessToDate;
	private Boolean courierFlag;
	private Boolean handFlag;
	private Boolean renderedListPanel;
	
	private UserMst employee;
	private List<Long> dispatchLists =  new ArrayList<Long>();
	private List<LicOblApplicationMstDto> licOblApplicationMstDtoList = new ArrayList<LicOblApplicationMstDto>();
	private List<LicOblApplicationMstDto> selectedList = new ArrayList<LicOblApplicationMstDto>();
	
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
			businessFromDate = null;
			businessToDate = null;
			
			if(licOblApplicationMstDtoList != null){
				licOblApplicationMstDtoList.clear();
			}
			
			if(selectedList != null){
				selectedList.clear();
			}
			
			if(dispatchLists != null){
				dispatchLists.clear();
			}
			
			dispatchLists = licOblApplicationMstService.findPodApplications(loginAction.getUserList().get(0).getBranchMst().getBranchId());

		}catch(Exception e){
			log.info("BranchHubLicPodAction refresh Error : ", e);
		}
	}
	public String onLoad(){
		refresh();
		return "/licBranchActivity/branchHubLicPod.xhtml";
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
			if(!(list == null || list.size() == 0)){
				employee = list.get(0);
				empName = list.get(0).getUserName();
			}else{
				empName = null;
			}
		}catch(Exception e){
			log.info("BranchHubLicPodAction findEmployee Error : ", e);
		}
	}
	
	public void searchForPod(){		
		try{
			if(deliveryMode.equals("H") && empName == null){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Error : ", "Please Enter a Valid Employee ID"));
				return;
			}
			
			licOblApplicationMstDtoList = licOblApplicationMstService.findApplicationByDispatchListAndBusinessDate(businessFromDate, businessToDate, dispatchListNo, loginAction.getUserList().get(0).getBranchMst());
			
			if(licOblApplicationMstDtoList == null || licOblApplicationMstDtoList.size() == 0 || licOblApplicationMstDtoList.contains(null)){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Error : ", "No Record(s) Found"));
				renderedListPanel = false;
				return;
			}
			
			renderedListPanel = true;
		}catch(Exception e){
			log.info("BranchHubLicPodAction searchForPod Error : ", e);
		}
	}
	
	
	public void save(){
		try{
			if(selectedList == null || selectedList.size() == 0){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Save Unsuccessful : ", "Please Select an Application to Save"));
				return;
			}
			
			Date now = new Date();
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
			
			for(LicOblApplicationMstDto obj : selectedList){
				LicOblApplicationMst applicationMst = licOblApplicationMstService.findById(obj.getId());
				applicationMst.setBrnhHubPodDtls(licBrnhHubPicPodDtls);
				licBrnhHubPicPodDtls.getLicOblApplicationMsts().add(applicationMst);
			}
			Long id = licBrnhHubPicPodDtlsService.saveForBranchHubPodDtls(licBrnhHubPicPodDtls);
			
			if(id > 0){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
	                    "Save Successful : ", "Branch To HUB POD Generated Successfully, Your POD ID is : " + licBrnhHubPicPodDtls.getId()));
				onLoad();
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Save Unsuccessful : ", "Branch To HUB POD Generation Unsuccessful"));
			}
		}catch(Exception e){
			log.info("BranchHubLicPodAction Save Error : ", e);
		}
	}

	
	/* GETTER SETTER */
	public List<Long> getDispatchLists() {
		return dispatchLists;
	}

	public void setDispatchLists(List<Long> dispatchLists) {
		this.dispatchLists = dispatchLists;
	}

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

	

	public UserMst getEmployee() {
		return employee;
	}

	public void setEmployee(UserMst employee) {
		this.employee = employee;
	}

	public Boolean getRenderedListPanel() {
		return renderedListPanel;
	}

	public void setRenderedListPanel(Boolean renderedListPanel) {
		this.renderedListPanel = renderedListPanel;
	}

	
	public Date getBusinessFromDate() {
		return businessFromDate;
	}
	public void setBusinessFromDate(Date businessFromDate) {
		this.businessFromDate = businessFromDate;
	}
	public Date getBusinessToDate() {
		return businessToDate;
	}
	public void setBusinessToDate(Date businessToDate) {
		this.businessToDate = businessToDate;
	}
	public List<LicOblApplicationMstDto> getLicOblApplicationMstDtoList() {
		return licOblApplicationMstDtoList;
	}
	public void setLicOblApplicationMstDtoList(
			List<LicOblApplicationMstDto> licOblApplicationMstDtoList) {
		this.licOblApplicationMstDtoList = licOblApplicationMstDtoList;
	}
	public List<LicOblApplicationMstDto> getSelectedList() {
		return selectedList;
	}
	public void setSelectedList(List<LicOblApplicationMstDto> selectedList) {
		this.selectedList = selectedList;
	}

}
