package com.gtfs.bean;

// Generated 30 Aug, 2014 4:24:58 PM by Hibernate Tools 3.4.0.CR1

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * CampMst generated by hbm2java
 */
@Entity
@Table(name = "CAMP_MST")
public class CampMst implements java.io.Serializable {

	private Long campId;
	private BranchMst branchMst;
	private StateMst stateMst;
	private String campName;
	private String addr1;
	private String addr2;
	private String addr3;
	private String city;
	private String activeFlag;
	private Long prntBrId;
	private String convertedFlag;
	private String remarks;
	private Long userId;
	private Date dateTime;
	private Set<AgentMst> agentMsts = new HashSet<AgentMst>(0);

	public CampMst() {
	}

	public CampMst(Long campId) {
		this.campId = campId;
	}

	public CampMst(Long campId, BranchMst branchMst, StateMst stateMst,
			String campName, String addr1, String addr2, String addr3,
			String city, String activeFlag, Long prntBrId,
			String convertedFlag, String remarks, Long userId,
			Date dateTime, Set<AgentMst> agentMsts) {
		this.campId = campId;
		this.branchMst = branchMst;
		this.stateMst = stateMst;
		this.campName = campName;
		this.addr1 = addr1;
		this.addr2 = addr2;
		this.addr3 = addr3;
		this.city = city;
		this.activeFlag = activeFlag;
		this.prntBrId = prntBrId;
		this.convertedFlag = convertedFlag;
		this.remarks = remarks;
		this.userId = userId;
		this.dateTime = dateTime;
		this.agentMsts = agentMsts;
	}

	@Id
	@Column(name = "CAMP_ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Long getCampId() {
		return this.campId;
	}

	public void setCampId(Long campId) {
		this.campId = campId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TRNF_LOC")
	public BranchMst getBranchMst() {
		return this.branchMst;
	}

	public void setBranchMst(BranchMst branchMst) {
		this.branchMst = branchMst;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "STATE_ID")
	public StateMst getStateMst() {
		return this.stateMst;
	}

	public void setStateMst(StateMst stateMst) {
		this.stateMst = stateMst;
	}

	@Column(name = "CAMP_NAME", length = 20)
	public String getCampName() {
		return this.campName;
	}

	public void setCampName(String campName) {
		this.campName = campName;
	}

	@Column(name = "ADDR_1", length = 100)
	public String getAddr1() {
		return this.addr1;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	@Column(name = "ADDR_2", length = 100)
	public String getAddr2() {
		return this.addr2;
	}

	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}

	@Column(name = "ADDR_3", length = 100)
	public String getAddr3() {
		return this.addr3;
	}

	public void setAddr3(String addr3) {
		this.addr3 = addr3;
	}

	@Column(name = "CITY", length = 50)
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "ACTIVE_FLAG", length = 1)
	public String getActiveFlag() {
		return this.activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	@Column(name = "PRNT_BR_ID", precision = 22, scale = 0)
	public Long getPrntBrId() {
		return this.prntBrId;
	}

	public void setPrntBrId(Long prntBrId) {
		this.prntBrId = prntBrId;
	}

	@Column(name = "CONVERTED_FLAG", length = 1)
	public String getConvertedFlag() {
		return this.convertedFlag;
	}

	public void setConvertedFlag(String convertedFlag) {
		this.convertedFlag = convertedFlag;
	}

	@Column(name = "REMARKS", length = 500)
	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Column(name = "USER_ID", precision = 22, scale = 0)
	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "DATE_TIME")
	public Date getDateTime() {
		return this.dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "campMst")
	public Set<AgentMst> getAgentMsts() {
		return this.agentMsts;
	}

	public void setAgentMsts(Set<AgentMst> agentMsts) {
		this.agentMsts = agentMsts;
	}

}
