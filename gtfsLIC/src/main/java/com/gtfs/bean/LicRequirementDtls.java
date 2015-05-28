package com.gtfs.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "LIC_REQUIREMENT_DTLS")
public class LicRequirementDtls implements Serializable{
	private Long id;
	private String reqType; // for doc or short prem
	private String document; 
	private Double amount; // short amt
	private String actionType; // SFCL or CFSL or DR or DNR
	private Long actionBy; 
	private Date actionDate;
	private String listGenFlag; // prem or doc list gen flag 
	private String colFlag; 
	private Date colDate;
	private Long colBy;
	private String processType; // OBL or RNL
	private LicPremiumListDtls licPremiumListDtls;
	private LicPolicyDtls licPolicyDtls; // only for RNL reqirement update here 
	private LicOblApplicationMst licOblApplicationMst; // only for obl reqirement update here
	private String dispatchReadyFlag;
	private String branchRcvFlag;
	private LicPisMst licPisMst;
	private LicBrnhHubPicMapDtls licBrnhHubPicMapDtls;
	private LicBrnhHubPicPodDtls licBrnhHubPicPodDtls;
	private LicHubMst licHubMst;
	private String remarks;
	private Long createdBy;
	private Long modifiedBy;
	private Long deletedBy;
	private Date createdDate;
	private Date modifiedDate;
	private Date deletedDate;
	private String deleteFlag;
	private String branchHubPodFlag;
	private PicBranchMst picBranchMst;
	private LicMarkingToQcDtls licMarkingToQcDtls;
	private String printFlag;
	private String receiptNo;
	
	private List<LicBranchReqRcvMst> licBranchReqRcvMsts = new ArrayList<LicBranchReqRcvMst>();
	private List<LicPaymentDtls> licPaymentDtlses = new ArrayList<LicPaymentDtls>();
	private List<LicPremReqMapping> licPremReqMappings = new ArrayList<LicPremReqMapping>();
	private List<LicReqBocMapping> licReqBocMappings = new ArrayList<LicReqBocMapping>();
	
	
	@Id
	@Column(name = "ID", nullable = false, precision = 22, scale = 0)
	@SequenceGenerator(name="LIC_OBL_REQUIREMENT_DTLS_SEQ",sequenceName="LIC_OBL_REQUIREMENT_DTLS_SEQ")
	@GeneratedValue(generator="LIC_OBL_REQUIREMENT_DTLS_SEQ",strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	@Column(name="REQ_TYPE",length=1)
	public String getReqType() {
		return reqType;
	}
	public void setReqType(String reqType) {
		this.reqType = reqType;
	}
	
	
	@Column(name="DOCUMENT",length=500)
	public String getDocument() {
		return document;
	}
	public void setDocument(String document) {
		this.document = document;
	}
	
	
	@Column(name = "AMOUNT", precision = 22, scale = 0)
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	
	@Column(name="ACTION_TYPE",length=5)
	public String getActionType() {
		return actionType;
	}
	
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	
	
	@Column(name = "ACTION_BY", precision = 22, scale = 0)
	public Long getActionBy() {
		return actionBy;
	}
	public void setActionBy(Long actionBy) {
		this.actionBy = actionBy;
	}
	
	
	@Column(name="LIST_GEN_FLAG",length=5)
	public String getListGenFlag() {
		return listGenFlag;
	}
	public void setListGenFlag(String listGenFlag) {
		this.listGenFlag = listGenFlag;
	}
	
	
	@Column(name="COL_FLAG",length=5)
	public String getColFlag() {
		return colFlag;
	}
	public void setColFlag(String colFlag) {
		this.colFlag = colFlag;
	}
	
	
	@Temporal(TemporalType.DATE)
	@Column(name = "COL_DATE", length = 7)
	public Date getColDate() {
		return colDate;
	}
	public void setColDate(Date colDate) {
		this.colDate = colDate;
	}
	
	
	@Column(name = "COL_BY", precision = 22, scale = 0)
	public Long getColBy() {
		return colBy;
	}
	public void setColBy(Long colBy) {
		this.colBy = colBy;
	}
	
	
	@Column(name="PROCESS_TYPE",length=5)
	public String getProcessType() {
		return processType;
	}
	public void setProcessType(String processType) {
		this.processType = processType;
	}
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LIC_OBL_APPL_ID")
	@Cascade({CascadeType.SAVE_UPDATE})
	public LicOblApplicationMst getLicOblApplicationMst() {
		return licOblApplicationMst;
	}
	public void setLicOblApplicationMst(LicOblApplicationMst licOblApplicationMst) {
		this.licOblApplicationMst = licOblApplicationMst;
	}
	
	
	@Column(name="BRANCH_RCV_FLAG",length=1)
	public String getBranchRcvFlag() {
		return branchRcvFlag;
	}
	public void setBranchRcvFlag(String branchRcvFlag) {
		this.branchRcvFlag = branchRcvFlag;
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
	@Column(name = "ACTION_DATE", length = 7)
	public Date getActionDate() {
		return actionDate;
	}
	public void setActionDate(Date actionDate) {
		this.actionDate = actionDate;
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
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LIC_PREM_LIST_ID")
	public LicPremiumListDtls getLicPremiumListDtls() {
		return licPremiumListDtls;
	}
	public void setLicPremiumListDtls(LicPremiumListDtls licPremiumListDtls) {
		this.licPremiumListDtls = licPremiumListDtls;
	}
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LIC_POLICY_DTLS_ID")
	@Cascade({CascadeType.SAVE_UPDATE,CascadeType.MERGE})
	public LicPolicyDtls getLicPolicyDtls() {
		return licPolicyDtls;
	}
	public void setLicPolicyDtls(LicPolicyDtls licPolicyDtls) {
		this.licPolicyDtls = licPolicyDtls;
	}
	
	
	@Column(name="DISPATCH_READY_FLAG",length=1)
	public String getDispatchReadyFlag() {
		return dispatchReadyFlag;
	}
	public void setDispatchReadyFlag(String dispatchReadyFlag) {
		this.dispatchReadyFlag = dispatchReadyFlag;
	}
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "licRequirementDtls")
	@Cascade({CascadeType.SAVE_UPDATE})
	public List<LicBranchReqRcvMst> getLicBranchReqRcvMsts() {
		return licBranchReqRcvMsts;
	}
	public void setLicBranchReqRcvMsts(List<LicBranchReqRcvMst> licBranchReqRcvMsts) {
		this.licBranchReqRcvMsts = licBranchReqRcvMsts;
	}
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LIC_PIS_MST_ID")
	public LicPisMst getLicPisMst() {
		return licPisMst;
	}
	public void setLicPisMst(LicPisMst licPisMst) {
		this.licPisMst = licPisMst;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "licRequirementDtls")
	public List<LicPaymentDtls> getLicPaymentDtlses() {
		return licPaymentDtlses;
	}
	public void setLicPaymentDtlses(List<LicPaymentDtls> licPaymentDtlses) {
		this.licPaymentDtlses = licPaymentDtlses;
	}
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "HUB_PIC_MAP_DTLS_ID")
	@Cascade({CascadeType.SAVE_UPDATE})
	public LicBrnhHubPicMapDtls getLicBrnhHubPicMapDtls() {
		return licBrnhHubPicMapDtls;
	}
	public void setLicBrnhHubPicMapDtls(LicBrnhHubPicMapDtls licBrnhHubPicMapDtls) {
		this.licBrnhHubPicMapDtls = licBrnhHubPicMapDtls;
	}
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "HUB_PIC_POD_DTLS_ID")
	public LicBrnhHubPicPodDtls getLicBrnhHubPicPodDtls() {
		return licBrnhHubPicPodDtls;
	}
	public void setLicBrnhHubPicPodDtls(LicBrnhHubPicPodDtls licBrnhHubPicPodDtls) {
		this.licBrnhHubPicPodDtls = licBrnhHubPicPodDtls;
	}
	
	
	@Column(name="BRANCH_HUB_POD_FLAG", length=1)
	public String getBranchHubPodFlag() {
		return branchHubPodFlag;
	}
	public void setBranchHubPodFlag(String branchHubPodFlag) {
		this.branchHubPodFlag = branchHubPodFlag;
	}
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LIC_MARKING_TO_QC_DTLS_ID")
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.MERGE})
	public LicMarkingToQcDtls getLicMarkingToQcDtls() {
		return licMarkingToQcDtls;
	}
	public void setLicMarkingToQcDtls(LicMarkingToQcDtls licMarkingToQcDtls) {
		this.licMarkingToQcDtls = licMarkingToQcDtls;
	}
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "licRequirementDtls")
	public List<LicPremReqMapping> getLicPremReqMappings() {
		return licPremReqMappings;
	}
	public void setLicPremReqMappings(List<LicPremReqMapping> licPremReqMappings) {
		this.licPremReqMappings = licPremReqMappings;
	}
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PIC_BRANCH_MST_ID")
	@Cascade({CascadeType.SAVE_UPDATE})
	public PicBranchMst getPicBranchMst() {
		return picBranchMst;
	}
	public void setPicBranchMst(PicBranchMst picBranchMst) {
		this.picBranchMst = picBranchMst;
	}
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "licRequirementDtls")
	@Cascade({CascadeType.SAVE_UPDATE})
	public List<LicReqBocMapping> getLicReqBocMappings() {
		return licReqBocMappings;
	}
	public void setLicReqBocMappings(List<LicReqBocMapping> licReqBocMappings) {
		this.licReqBocMappings = licReqBocMappings;
	}
	
	@Column(name="REMARKS",length=500)
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LIC_HUB_MST_ID")
	@Cascade({CascadeType.SAVE_UPDATE})
	public LicHubMst getLicHubMst() {
		return licHubMst;
	}
	public void setLicHubMst(LicHubMst licHubMst) {
		this.licHubMst = licHubMst;
	}
	@Column(name="PRINT_FLAG",length=1)
	public String getPrintFlag() {
		return printFlag;
	}
	public void setPrintFlag(String printFlag) {
		this.printFlag = printFlag;
	}
	@Column(name="RECEIPT_NO",length=100)
	public String getReceiptNo() {
		return receiptNo;
	}
	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo;
	}
}
