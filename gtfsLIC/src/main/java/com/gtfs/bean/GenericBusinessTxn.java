package com.gtfs.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name = "GENERIC_BUSINESS_TXN")
public class GenericBusinessTxn implements Serializable {
	private Long id;
	private PhaseMst phaseMst;
	private AgentMst agentMst;
	private Double businessValue;
	private Date transDate;
	private String transStatus;
	private String tableName;
	private Long tableId;
	private String processName;
	private SchemeMst schemeId;
	private Double agentRank;
	private String processFlag;
	private Date processDate;
	private String name;
	private BranchMst branchMst;
	private Date businessDate;
	private TieupCompyMst tieupCompyMst;
	private Double collBenPct;
	private Double modalPremium;
	private Double basicPremium;
	private Double shortPremium;
	private Double serviceTax;
	private Double totalAmount;
	private Long policyTerm;
	private Long  premiumPayingTerm;
	private Double sumAssured;
	private String payNature;
	private Long createdBy;
	private Long modifiedBy;
	private Long deletedBy;
	private Date createdDate;
	private Date modifiedDate;
	private Date deletedDate;
	private String deleteFlag;
	
	@Id
	@SequenceGenerator(name="GENERIC_BUSINESS_TXN_SEQ",sequenceName="GENERIC_BUSINESS_TXN_SEQ" )
	@GeneratedValue(generator = "GENERIC_BUSINESS_TXN_SEQ",strategy=GenerationType.AUTO)
	@Column(name = "ID", nullable = false, precision = 22, scale = 0)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PHASE_MST_ID")
	public PhaseMst getPhaseMst() {
		return phaseMst;
	}
	public void setPhaseMst(PhaseMst phaseMst) {
		this.phaseMst = phaseMst;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AGENT_CODE")
	public AgentMst getAgentMst() {
		return agentMst;
	}
	public void setAgentMst(AgentMst agentMst) {
		this.agentMst = agentMst;
	}
	@Column(name = "BUSINESS_VALUE", precision = 22, scale = 0)
	public Double getBusinessValue() {
		return businessValue;
	}
	public void setBusinessValue(Double businessValue) {
		this.businessValue = businessValue;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "TRANS_DATE", length = 7)
	public Date getTransDate() {
		return transDate;
	}
	public void setTransDate(Date transDate) {
		this.transDate = transDate;
	}
	@Column(name = "TRANS_STATUS" ,length = 1)
	public String getTransStatus() {
		return transStatus;
	}
	public void setTransStatus(String transStatus) {
		this.transStatus = transStatus;
	}
	@Column(name = "TABLE_NAME" ,length = 50)
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	@Column(name = "TABLE_ID", precision = 22, scale = 0)
	public Long getTableId() {
		return tableId;
	}
	public void setTableId(Long tableId) {
		this.tableId = tableId;
	}
	@Column(name="PROCESS_NAME",length=10)
	public String getProcessName() {
		return processName;
	}
	public void setProcessName(String processName) {
		this.processName = processName;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SCHEME_ID")
	public SchemeMst getSchemeId() {
		return schemeId;
	}
	public void setSchemeId(SchemeMst schemeId) {
		this.schemeId = schemeId;
	}
	@Column(name = "AGENT_RANK", precision = 22, scale = 0)
	public Double getAgentRank() {
		return agentRank;
	}
	public void setAgentRank(Double agentRank) {
		this.agentRank = agentRank;
	}
	@Column(name="PROCESS_FLAG",length=1)
	public String getProcessFlag() {
		return processFlag;
	}
	public void setProcessFlag(String processFlag) {
		this.processFlag = processFlag;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "PROCESS_DATE", length = 7)
	public Date getProcessDate() {
		return processDate;
	}
	public void setProcessDate(Date processDate) {
		this.processDate = processDate;
	}
	@Column(name="NAME",length=50)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BRANCH_ID")
	public BranchMst getBranchMst() {
		return branchMst;
	}
	public void setBranchMst(BranchMst branchMst) {
		this.branchMst = branchMst;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "BUSINESS_DATE", length = 7)
	public Date getBusinessDate() {
		return businessDate;
	}
	public void setBusinessDate(Date businessDate) {
		this.businessDate = businessDate;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TIEUP_COMPY_ID")
	public TieupCompyMst getTieupCompyMst() {
		return tieupCompyMst;
	}
	public void setTieupCompyMst(TieupCompyMst tieupCompyMst) {
		this.tieupCompyMst = tieupCompyMst;
	}
	@Column(name = "COLL_BEN_PCT", precision = 22, scale = 0)
	public Double getCollBenPct() {
		return collBenPct;
	}
	public void setCollBenPct(Double collBenPct) {
		this.collBenPct = collBenPct;
	}
	@Column(name = "MODAL_PREMIUM", precision = 22, scale = 0)
	public Double getModalPremium() {
		return modalPremium;
	}
	public void setModalPremium(Double modalPremium) {
		this.modalPremium = modalPremium;
	}
	@Column(name = "BASIC_PREMIUM", precision = 22, scale = 0)
	public Double getBasicPremium() {
		return basicPremium;
	}
	public void setBasicPremium(Double basicPremium) {
		this.basicPremium = basicPremium;
	}
	@Column(name = "SHORT_PREMIUM", precision = 22, scale = 0)
	public Double getShortPremium() {
		return shortPremium;
	}
	public void setShortPremium(Double shortPremium) {
		this.shortPremium = shortPremium;
	}
	@Column(name = "SERVICE_TAX", precision = 22, scale = 0)
	public Double getServiceTax() {
		return serviceTax;
	}
	public void setServiceTax(Double serviceTax) {
		this.serviceTax = serviceTax;
	}
	@Column(name = "TOTAL_AMOUNT", precision = 22, scale = 0)
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	@Column(name = "POLICY_TERM", precision = 22, scale = 0)
	public Long getPolicyTerm() {
		return policyTerm;
	}
	public void setPolicyTerm(Long policyTerm) {
		this.policyTerm = policyTerm;
	}
	@Column(name = "PREMIUM_PAYING_TERM", precision = 22, scale = 0)
	public Long getPremiumPayingTerm() {
		return premiumPayingTerm;
	}
	public void setPremiumPayingTerm(Long premiumPayingTerm) {
		this.premiumPayingTerm = premiumPayingTerm;
	}
	@Column(name = "SUM_ASSURED", precision = 22, scale = 0)
	public Double getSumAssured() {
		return sumAssured;
	}
	public void setSumAssured(Double sumAssured) {
		this.sumAssured = sumAssured;
	}
	@Column(name = "PAY_NATURE", length=1)
	public String getPayNature() {
		return payNature;
	}
	public void setPayNature(String payNature) {
		this.payNature = payNature;
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
	
	
	
}
