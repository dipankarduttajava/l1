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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "LIC_POLICY_DTLS")
public class LicPolicyDtls implements Serializable{
	private Long id;
	private Date dueDate;
	private Date payDate;
	private Double premAmt;
	private Double serviceTax;
	private Double fineAmt;
	private String hlthReqdFlag;
	private String paidFlag;
	private Long renewalMonth;
	private LicHubMst oblHubMst;
	private LicHubMst processHubMst;
	private PicBranchMst oblPicBranchMst;
	private PicBranchMst processPicBranchMst;
	private BranchMst branchMst;
	private Long createdBy;
	private Long modifiedBy;
	private Long deletedBy;
	private Date createdDate;
	private Date modifiedDate;
	private Date deletedDate;
	private String deleteFlag;
	private LicPolicyMst licPolicyMst;
	private LicRnlBusinessTxn licRnlBusinessTxn;
	private String renewalType;
	private LicPisMst licPisMst;
	private LicBrnhHubPicMapDtls brnhHubMapDtls;
	private LicBrnhHubPicPodDtls brnhHubPodDtls;
	private LicBrnhHubPicMapDtls hubPicMapDtls;
	private LicBrnhHubPicPodDtls hubPicPodDtls;
	private LicMarkingToQcDtls licMarkingToQcDtls;
	private String payMode;
	private String premListReadyFlag;
	private String brnhHubDespFlag;
	private String migrationFlag;
	private AgentMst agentMst;
	private String legacyAgentCode;
	private String healthValidated;
	private String renewalStatus;
	private String rprFlag;
	private String remarks;
	private String printFlag;
	
	private LicPolicyBankDtls licPolicyBankDtls;
	
	private List<LicRequirementDtls> licRequirementDtlses = new ArrayList<LicRequirementDtls>();
	private List<LicPolicyPaymentMapping> licPolicyPaymentMappings = new ArrayList<LicPolicyPaymentMapping>();
	private List<LicPremPolicyMapping> licPremPolicyMappings = new ArrayList<LicPremPolicyMapping>();
	
	
	@Id
	@SequenceGenerator(name="LIC_POLICY_DTLS_SEQ",sequenceName="LIC_POLICY_DTLS_SEQ")
	@GeneratedValue(generator="LIC_POLICY_DTLS_SEQ")
	@Column(name = "ID", nullable = false, precision = 22, scale = 0)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	@Temporal(TemporalType.DATE)
	@Column(name = "DUE_DATE", length = 7)
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	
	
	@Temporal(TemporalType.DATE)
	@Column(name = "PAY_DATE", length = 7)
	public Date getPayDate() {
		return payDate;
	}
	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}
	
	
	@Column(name = "PREM_AMT", precision = 22, scale = 0)
	public Double getPremAmt() {
		return premAmt;
	}
	public void setPremAmt(Double premAmt) {
		this.premAmt = premAmt;
	}
	
	
	@Column(name = "SERVICE_TAX", precision = 22, scale = 0)
	public Double getServiceTax() {
		return serviceTax;
	}
	public void setServiceTax(Double serviceTax) {
		this.serviceTax = serviceTax;
	}
	
	
	@Column(name = "HLTH_DECL_FLAG", length=1)
	public String getHlthReqdFlag() {
		return hlthReqdFlag;
	}
	public void setHlthReqdFlag(String hlthReqdFlag) {
		this.hlthReqdFlag = hlthReqdFlag;
	}
	
	
	@Column(name = "PAID_FLAG", length=1)
	public String getPaidFlag() {
		return paidFlag;
	}
	public void setPaidFlag(String paidFlag) {
		this.paidFlag = paidFlag;
	}
	
	
	@Column(name = "RENEWAL_MONTH", precision = 22, scale = 0)
	public Long getRenewalMonth() {
		return renewalMonth;
	}
	public void setRenewalMonth(Long renewalMonth) {
		this.renewalMonth = renewalMonth;
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
	
	
	@Column(name = "FINE_AMT", precision = 22, scale = 0)
	public Double getFineAmt() {
		return fineAmt;
	}
	public void setFineAmt(Double fineAmt) {
		this.fineAmt = fineAmt;
	}
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "licPolicyDtls")
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.MERGE})
	public List<LicRequirementDtls> getLicRequirementDtlses() {
		return licRequirementDtlses;
	}
	public void setLicRequirementDtlses(
			List<LicRequirementDtls> licRequirementDtlses) {
		this.licRequirementDtlses = licRequirementDtlses;
	}
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="LIC_POLICY_MST_ID")
	public LicPolicyMst getLicPolicyMst() {
		return licPolicyMst;
	}
	public void setLicPolicyMst(LicPolicyMst licPolicyMst) {
		this.licPolicyMst = licPolicyMst;
	}
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "OBL_HUB_MST_ID")
	public LicHubMst getOblHubMst() {
		return oblHubMst;
	}

	public void setOblHubMst(LicHubMst oblHubMst) {
		this.oblHubMst = oblHubMst;
	}
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PROCESS_HUB_MST_ID")
	public LicHubMst getProcessHubMst() {
		return processHubMst;
	}
	public void setProcessHubMst(LicHubMst processHubMst) {
		this.processHubMst = processHubMst;
	}
	
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "licPolicyDtls")
	@Cascade({CascadeType.SAVE_UPDATE})
	public LicRnlBusinessTxn getLicRnlBusinessTxn() {
		return licRnlBusinessTxn;
	}
	public void setLicRnlBusinessTxn(LicRnlBusinessTxn licRnlBusinessTxn) {
		this.licRnlBusinessTxn = licRnlBusinessTxn;
	}
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "licPolicyDtls")
	@Cascade({CascadeType.SAVE_UPDATE})
	public List<LicPolicyPaymentMapping> getLicPolicyPaymentMappings() {
		return licPolicyPaymentMappings;
	}
	public void setLicPolicyPaymentMappings(
			List<LicPolicyPaymentMapping> licPolicyPaymentMappings) {
		this.licPolicyPaymentMappings = licPolicyPaymentMappings;
	}
	
	
	@Column(name="RENEWAL_TYPE",length=20)
	public String getRenewalType() {
		return renewalType;
	}
	public void setRenewalType(String renewalType) {
		this.renewalType = renewalType;
	}
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "OBL_PIC_MST_ID")
	public PicBranchMst getOblPicBranchMst() {
		return oblPicBranchMst;
	}
	public void setOblPicBranchMst(PicBranchMst oblPicBranchMst) {
		this.oblPicBranchMst = oblPicBranchMst;
	}
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PROCESS_PIC_MST_ID")
	public PicBranchMst getProcessPicBranchMst() {
		return processPicBranchMst;
	}
	public void setProcessPicBranchMst(PicBranchMst processPicBranchMst) {
		this.processPicBranchMst = processPicBranchMst;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LIC_PIS_MST_ID")
	public LicPisMst getLicPisMst() {
		return licPisMst;
	}
	public void setLicPisMst(LicPisMst licPisMst) {
		this.licPisMst = licPisMst;
	}
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BRANCH_MST_ID")
	@Cascade({CascadeType.SAVE_UPDATE})
	public BranchMst getBranchMst() {
		return branchMst;
	}
	public void setBranchMst(BranchMst branchMst) {
		this.branchMst = branchMst;
	}
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BRNH_HUB_DESP_ID")
	@Cascade({CascadeType.SAVE_UPDATE})
	public LicBrnhHubPicMapDtls getBrnhHubMapDtls() {
		return brnhHubMapDtls;
	}
	public void setBrnhHubMapDtls(LicBrnhHubPicMapDtls brnhHubMapDtls) {
		this.brnhHubMapDtls = brnhHubMapDtls;
	}
	
	
	@Column(name="PAY_MODE",length=1)
	public String getPayMode() {
		return payMode;
	}
	public void setPayMode(String payMode) {
		this.payMode = payMode;
	}
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BRNH_HUB_POD_ID")
	@Cascade({CascadeType.SAVE_UPDATE})
	public LicBrnhHubPicPodDtls getBrnhHubPodDtls() {
		return brnhHubPodDtls;
	}
	public void setBrnhHubPodDtls(LicBrnhHubPicPodDtls brnhHubPodDtls) {
		this.brnhHubPodDtls = brnhHubPodDtls;
	}
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LIC_MRK_TO_QC_ID")
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.MERGE})
	public LicMarkingToQcDtls getLicMarkingToQcDtls() {
		return licMarkingToQcDtls;
	}
	public void setLicMarkingToQcDtls(LicMarkingToQcDtls licMarkingToQcDtls) {
		this.licMarkingToQcDtls = licMarkingToQcDtls;
	}
	
	
	@Column(name="PREM_LIST_READY_FLAG",length=1)
	public String getPremListReadyFlag() {
		return premListReadyFlag;
	}
	public void setPremListReadyFlag(String premListReadyFlag) {
		this.premListReadyFlag = premListReadyFlag;
	}
	
	
	@Column(name="BRNH_HUB_DESP_FLAG",length=1)
	public String getBrnhHubDespFlag() {
		return brnhHubDespFlag;
	}
	public void setBrnhHubDespFlag(String brnhHubDespFlag) {
		this.brnhHubDespFlag = brnhHubDespFlag;
	}
	
	
	@Column(name="MIGRATION_FLAG",length=1)
	public String getMigrationFlag() {
		return migrationFlag;
	}
	public void setMigrationFlag(String migrationFlag) {
		this.migrationFlag = migrationFlag;
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
	
	
	@Column(name="LEGACY_AG_CODE",length=50)
	public String getLegacyAgentCode() {
		return legacyAgentCode;
	}
	public void setLegacyAgentCode(String legacyAgentCode) {
		this.legacyAgentCode = legacyAgentCode;
	}
	
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="licPolicyDtls")
	public List<LicPremPolicyMapping> getLicPremPolicyMappings() {
		return licPremPolicyMappings;
	}
	public void setLicPremPolicyMappings(
			List<LicPremPolicyMapping> licPremPolicyMappings) {
		this.licPremPolicyMappings = licPremPolicyMappings;
	}
	
	
	@Column(name="HEALTH_VALIDATED",length=1)
	public String getHealthValidated() {
		return healthValidated;
	}
	public void setHealthValidated(String healthValidated) {
		this.healthValidated = healthValidated;
	}
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LIC_BANK_DTLS_ID")
	@Cascade({CascadeType.SAVE_UPDATE})
	public LicPolicyBankDtls getLicPolicyBankDtls() {
		return licPolicyBankDtls;
	}
	public void setLicPolicyBankDtls(LicPolicyBankDtls licPolicyBankDtls) {
		this.licPolicyBankDtls = licPolicyBankDtls;
	}
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "HUB_PIC_DESP_ID")
	@Cascade({CascadeType.SAVE_UPDATE})
	public LicBrnhHubPicMapDtls getHubPicMapDtls() {
		return hubPicMapDtls;
	}
	public void setHubPicMapDtls(LicBrnhHubPicMapDtls hubPicMapDtls) {
		this.hubPicMapDtls = hubPicMapDtls;
	}
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "HUB_PIC_POD_ID")
	@Cascade({CascadeType.SAVE_UPDATE})
	public LicBrnhHubPicPodDtls getHubPicPodDtls() {
		return hubPicPodDtls;
	}
	public void setHubPicPodDtls(LicBrnhHubPicPodDtls hubPicPodDtls) {
		this.hubPicPodDtls = hubPicPodDtls;
	}
	
	
	@Column(name="RENEWAL_STATUS",length=1)
	public String getRenewalStatus() {
		return renewalStatus;
	}
	public void setRenewalStatus(String renewalStatus) {
		this.renewalStatus = renewalStatus;
	}
	
	
	@Column(name="RPR_FLAG",length=1)
	public String getRprFlag() {
		return rprFlag;
	}
	public void setRprFlag(String rprFlag) {
		this.rprFlag = rprFlag;
	}
	
	
	@Column(name="REMARKS",length=500)
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
	@Column(name="PRINT_FLAG",length=1)
	public String getPrintFlag() {
		return printFlag;
	}
	public void setPrintFlag(String printFlag) {
		this.printFlag = printFlag;
	}
	
}
