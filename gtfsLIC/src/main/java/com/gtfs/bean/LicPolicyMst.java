package com.gtfs.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;


@Entity
@Table(name = "LIC_POLICY_MST")
public class LicPolicyMst implements Serializable{
	private Long id;
	private String policyStatus;
	private String policyNo;
	private Double modalPrem;
	private String proposalNo;
	private Date acceptDate;
	private Date riskStartDate;
	private String fprFlag;
	private String bondFlag;
	private String dispatchNo;
	private Date dispatchDate;
	private String remarks;
	private LicHubMst sentHub;
	private PicBranchMst picBranchMst;
	private Long createdBy;
	private Long modifiedBy;
	private Long deletedBy;
	private Date createdDate;
	private Date modifiedDate;
	private Date deletedDate;
	private String deleteFlag;
	private LicOblApplicationMst licOblApplicationMst;
	private List<LicPolicyDtls> licPolicyDtlses = new ArrayList<LicPolicyDtls>();
	private AgentMst agentMst;
	private String legacyAgentCode;
	private String migrationFlag;
	private Date lastPaidDueDate;

	
	@Id
	@SequenceGenerator(name="LIC_POLICY_MST_SEQ",sequenceName="LIC_POLICY_MST_SEQ")
	@GeneratedValue(generator="LIC_POLICY_MST_SEQ")
	@Column(name = "ID", nullable = false, precision = 22, scale = 0)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	@Column(name="POLICY_STATUS",length=1)
	public String getPolicyStatus() {
		return policyStatus;
	}
	public void setPolicyStatus(String policyStatus) {
		this.policyStatus = policyStatus;
	}
	
	
	@Column(name="POLICY_NO",length=20, unique = true, nullable = false)
	public String getPolicyNo() {
		return policyNo;
	}
	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}
	
	
	@Column(name = "MODAL_PREM", precision = 22, scale = 0)
	public Double getModalPrem() {
		return modalPrem;
	}
	public void setModalPrem(Double modalPrem) {
		this.modalPrem = modalPrem;
	}
	
	
	@Column(name="PROPOSAL_NO",length=20)
	public String getProposalNo() {
		return proposalNo;
	}
	public void setProposalNo(String proposalNo) {
		this.proposalNo = proposalNo;
	}
	
	
	@Temporal(TemporalType.DATE)
	@Column(name = "ACCEPT_DATE", length = 7)
	public Date getAcceptDate() {
		return acceptDate;
	}
	public void setAcceptDate(Date acceptDate) {
		this.acceptDate = acceptDate;
	}
	
	
	@Temporal(TemporalType.DATE)
	@Column(name = "RISK_START_DATE", length = 7)
	public Date getRiskStartDate() {
		return riskStartDate;
	}
	public void setRiskStartDate(Date riskStartDate) {
		this.riskStartDate = riskStartDate;
	}

	
	@Column(name = "CREATED_BY", precision = 22, scale = 0)
	public Long getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	
	@Column(name = "MODIFIED_BY", precision = 22, scale = 0)
	public Long getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	
	@Column(name = "DELETED_BY", precision = 22, scale = 0)
	public Long getDeletedBy() {
		return this.deletedBy;
	}

	public void setDeletedBy(Long deletedBy) {
		this.deletedBy = deletedBy;
	}

	
	@Temporal(TemporalType.DATE)
	@Column(name = "CREATED_DATE", length = 7)
	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	@Column(name="MIGRATION_FLAG",length=1)
	public String getMigrationFlag() {
		return migrationFlag;
	}

	public void setMigrationFlag(String migrationFlag) {
		this.migrationFlag = migrationFlag;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "MODIFIED_DATE", length = 7)
	public Date getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	
	@Temporal(TemporalType.DATE)
	@Column(name = "DELETED_DATE", length = 7)
	public Date getDeletedDate() {
		return this.deletedDate;
	}

	public void setDeletedDate(Date deletedDate) {
		this.deletedDate = deletedDate;
	}

	
	@Column(name = "DELETE_FLAG", length = 1)
	public String getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="APPL_MST_ID")
	@Cascade({CascadeType.SAVE_UPDATE})
	public LicOblApplicationMst getLicOblApplicationMst() {
		return licOblApplicationMst;
	}
	public void setLicOblApplicationMst(LicOblApplicationMst licOblApplicationMst) {
		this.licOblApplicationMst = licOblApplicationMst;
	}
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "licPolicyMst")
	@Cascade({CascadeType.SAVE_UPDATE})
	public List<LicPolicyDtls> getLicPolicyDtlses() {
		return licPolicyDtlses;
	}
	public void setLicPolicyDtlses(List<LicPolicyDtls> licPolicyDtlses) {
		this.licPolicyDtlses = licPolicyDtlses;
	}
	
	
	@Column(name="FPR_FLAG",length=1)
	public String getFprFlag() {
		return fprFlag;
	}
	public void setFprFlag(String fprFlag) {
		this.fprFlag = fprFlag;
	}
	
	
	@Column(name="BOND_FLAG",length=1)
	public String getBondFlag() {
		return bondFlag;
	}
	public void setBondFlag(String bondFlag) {
		this.bondFlag = bondFlag;
	}
	
	
	@Column(name="REMARKS",length=500)
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
	@Column(name="DISPATCH_NO",length=50)
	public String getDispatchNo() {
		return dispatchNo;
	}
	public void setDispatchNo(String dispatchNo) {
		this.dispatchNo = dispatchNo;
	}
	
	
	@Temporal(TemporalType.DATE)
	@Column(name = "DISPATCH_DATE", length = 7)
	public Date getDispatchDate() {
		return dispatchDate;
	}
	public void setDispatchDate(Date dispatchDate) {
		this.dispatchDate = dispatchDate;
	}
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SENT_HUB_MST_ID")
	public LicHubMst getSentHub() {
		return sentHub;
	}
	public void setSentHub(LicHubMst sentHub) {
		this.sentHub = sentHub;
	}
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SENT_PIC_MST_ID")
	public PicBranchMst getPicBranchMst() {
		return picBranchMst;
	}
	public void setPicBranchMst(PicBranchMst picBranchMst) {
		this.picBranchMst = picBranchMst;
	}
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AGENT_MST_ID")
	@Cascade({CascadeType.SAVE_UPDATE})
	public AgentMst getAgentMst() {
		return agentMst;
	}
	public void setAgentMst(AgentMst agentMst) {
		this.agentMst = agentMst;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "LAST_PAID_DUE_DATE", length = 7)
	public Date getLastPaidDueDate() {
		return lastPaidDueDate;
	}
	public void setLastPaidDueDate(Date lastPaidDueDate) {
		this.lastPaidDueDate = lastPaidDueDate;
	}
	@Column(name="LEGACY_AG_CODE",length=50)
	public String getLegacyAgentCode() {
		return legacyAgentCode;
	}
	public void setLegacyAgentCode(String legacyAgentCode) {
		this.legacyAgentCode = legacyAgentCode;
	}
}
