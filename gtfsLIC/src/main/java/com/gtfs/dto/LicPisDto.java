package com.gtfs.dto;

import java.io.Serializable;
import java.util.Date;

public class LicPisDto implements Serializable{
	private Long applicationMstId;
	private Long requirementId;
	private String applicationNo;
	private Date businessDate;
	private String draftChqNo;
	private Double draftChqInsAmt;
	private Double draftChqTieAmt;
	private Double cashAmt;
	private Double totalAmt;
	
	public Long getApplicationMstId() {
		return applicationMstId;
	}
	public void setApplicationMstId(Long applicationMstId) {
		this.applicationMstId = applicationMstId;
	}
	public String getApplicationNo() {
		return applicationNo;
	}
	public void setApplicationNo(String applicationNo) {
		this.applicationNo = applicationNo;
	}
	public Date getBusinessDate() {
		return businessDate;
	}
	public void setBusinessDate(Date businessDate) {
		this.businessDate = businessDate;
	}
	public String getDraftChqNo() {
		return draftChqNo;
	}
	public void setDraftChqNo(String draftChqNo) {
		this.draftChqNo = draftChqNo;
	}
	public Double getDraftChqInsAmt() {
		return draftChqInsAmt;
	}
	public void setDraftChqInsAmt(Double draftChqInsAmt) {
		this.draftChqInsAmt = draftChqInsAmt;
	}
	public Double getDraftChqTieAmt() {
		return draftChqTieAmt;
	}
	public void setDraftChqTieAmt(Double draftChqTieAmt) {
		this.draftChqTieAmt = draftChqTieAmt;
	}
	public Double getCashAmt() {
		return cashAmt;
	}
	public void setCashAmt(Double cashAmt) {
		this.cashAmt = cashAmt;
	}
	public Double getTotalAmt() {
		return totalAmt;
	}
	public void setTotalAmt(Double totalAmt) {
		this.totalAmt = totalAmt;
	}
	public Long getRequirementId() {
		return requirementId;
	}
	public void setRequirementId(Long requirementId) {
		this.requirementId = requirementId;
	}
	
	
	
}
