package com.gtfs.action;

import java.io.Serializable;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gtfs.bean.BranchMst;
import com.gtfs.bean.LicOblApplicationMst;
import com.gtfs.service.interfaces.AgentMstService;
import com.gtfs.service.interfaces.BranchMstService;
import com.gtfs.service.interfaces.LicMarkingToQcDtlsService;

@Component
@Scope("session")
public class IndividualMarkingReportAction implements Serializable{
	private Logger log = Logger.getLogger(IndividualMarkingReportAction.class);
	
	@Autowired
	private LicMarkingToQcDtlsService licMarkingToQcDtlsService;
	@Autowired
	private LoginAction loginAction;
	@Autowired
	private BranchMstService branchMstService;
	@Autowired
	private AgentMstService agentMstService;
	
	private Date businessFromDate;
	private Date businessToDate;
	private Boolean renderedPanel;
	private Long branchId;
	
	private List<LicOblApplicationMst> licOblApplicationMstList = new ArrayList<LicOblApplicationMst>();
	private List<BranchMst> branchList = new ArrayList<BranchMst>();
	
	private LicOblApplicationMst licOblApplicationMst;
	
	public void refresh(){
		renderedPanel = false;
		businessFromDate = null;
		businessToDate = null;
		branchId = null;
		licOblApplicationMstList.clear();
		
		if(branchList != null){
			branchList.clear();
		}
		
		branchList = branchMstService.findAll();
	}
	
	public String onLoad(){
		refresh();
		return "/licHubActivity/individualMarkingReport.xhtml";
	}
	
	public void search(){
		try{
			licOblApplicationMstList = licMarkingToQcDtlsService.findIndividualMarkingDtlsReport(businessFromDate, businessToDate, loginAction.findHubForProcess("OBL"), branchId);

			if(licOblApplicationMstList == null || licOblApplicationMstList.size() == 0 || licOblApplicationMstList.contains(null)){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Error : ", "No Record(s) Found"));
				return;
			}
			
			renderedPanel = true;
		}catch(Exception e){
			log.info("IndividualMarkingAction search Error : ", e);
		}
	}
	
	
	/* GETTER SETTER */
	public List<LicOblApplicationMst> getLicOblApplicationMstList() {
		return licOblApplicationMstList;
	}
	public void setLicOblApplicationMstList(
			List<LicOblApplicationMst> licOblApplicationMstList) {
		this.licOblApplicationMstList = licOblApplicationMstList;
	}
	public List<BranchMst> getBranchList() {
		return branchList;
	}
	public void setBranchList(List<BranchMst> branchList) {
		this.branchList = branchList;
	}

	public Long getBranchId() {
		return branchId;
	}

	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}

	public LicOblApplicationMst getLicOblApplicationMst() {
		return licOblApplicationMst;
	}

	public void setLicOblApplicationMst(LicOblApplicationMst licOblApplicationMst) {
		this.licOblApplicationMst = licOblApplicationMst;
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

	public Boolean getRenderedPanel() {
		return renderedPanel;
	}

	public void setRenderedPanel(Boolean renderedPanel) {
		this.renderedPanel = renderedPanel;
	}
	
}
