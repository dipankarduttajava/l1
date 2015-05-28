package com.gtfs.dto;

import java.io.Serializable;

import com.gtfs.bean.ProcessMst;

public class BranchHubPicMapDto implements Serializable{
	private Long id;
	private String sourceType;
	private String destinationType;
	private Long sourceId;
	private Long destionId;
	private String processName;
	private String processAbbr;
	private ProcessMst processMst;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSourceType() {
		return sourceType;
	}
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}
	public String getDestinationType() {
		return destinationType;
	}
	public void setDestinationType(String destinationType) {
		this.destinationType = destinationType;
	}
	public Long getSourceId() {
		return sourceId;
	}
	public void setSourceId(Long sourceId) {
		this.sourceId = sourceId;
	}
	public Long getDestionId() {
		return destionId;
	}
	public void setDestionId(Long destionId) {
		this.destionId = destionId;
	}
	public String getProcessName() {
		return processName;
	}
	public void setProcessName(String processName) {
		this.processName = processName;
	}
	public ProcessMst getProcessMst() {
		return processMst;
	}
	public void setProcessMst(ProcessMst processMst) {
		this.processMst = processMst;
	}
	public String getProcessAbbr() {
		return processAbbr;
	}
	public void setProcessAbbr(String processAbbr) {
		this.processAbbr = processAbbr;
	}
	
	
}
