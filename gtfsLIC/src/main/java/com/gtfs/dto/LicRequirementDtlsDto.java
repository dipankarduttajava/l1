package com.gtfs.dto;

import java.io.Serializable;
import java.util.Date;

public class LicRequirementDtlsDto implements Serializable{
	private String applicationNo;
	private Double shortAmt;
	private Long entryBy;
	private Date entryDate;
	private String document;
	
	public String getApplicationNo() {
		return applicationNo;
	}
	public void setApplicationNo(String applicationNo) {
		this.applicationNo = applicationNo;
	}
	public Double getShortAmt() {
		return shortAmt;
	}
	public void setShortAmt(Double shortAmt) {
		this.shortAmt = shortAmt;
	}
	public Long getEntryBy() {
		return entryBy;
	}
	public void setEntryBy(Long entryBy) {
		this.entryBy = entryBy;
	}
	public Date getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}
	public String getDocument() {
		return document;
	}
	public void setDocument(String document) {
		this.document = document;
	}
	
}
