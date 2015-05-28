package com.gtfs.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "LIC_RNL_BUSINESS_TXN")
public class LicRnlBusinessTxn implements Serializable{
	private Long id;
	private Date transDate;
	private PhaseMst phaseMst;
	private AgentMst agentMst;
	private LicProductMst licProductMst;
	private Double businessValue;
	private String frozenFlag;
	private String transferFlag;
	private Date transferDate;
	private String transStatus;
	private String acceptedFlag;
	private Date acceptedOn;
	private Date rejectOn;
	private AgentRlns agentRlns;
	private String freeQtyFlag;
	private Double receivable;
	private Double Received;
	private Long createdBy;
	private Long modifiedBy;
	private Long deletedBy;
	private Date createdDate;
	private Date modifiedDate;
	private Date deletedDate;
	private String deleteFlag;
	
	private LicPolicyDtls licPolicyDtls;
	
	
	@Id
	@GenericGenerator(name = "rnlBusinessGenerator", strategy = "foreign", parameters = @Parameter(name = "property", value = "licPolicyDtls"))
	@GeneratedValue(generator = "rnlBusinessGenerator")
	@Column(name = "ID", nullable = false, precision = 22, scale = 0)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	@Temporal(TemporalType.DATE)
	@Column(name = "TRANS_DATE", length = 7)
	public Date getTransDate() {
		return transDate;
	}
	public void setTransDate(Date transDate) {
		this.transDate = transDate;
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
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LIC_PRODUCT_MST_ID")
	public LicProductMst getLicProductMst() {
		return licProductMst;
	}
	public void setLicProductMst(LicProductMst licProductMst) {
		this.licProductMst = licProductMst;
	}
	
	
	@Column(name = "BUSINESS_VALUE", precision = 22, scale = 0)
	public Double getBusinessValue() {
		return businessValue;
	}
	public void setBusinessValue(Double businessValue) {
		this.businessValue = businessValue;
	}
	
	
	@Column(name = "FROZEN_FLAG" ,length = 1)
	public String getFrozenFlag() {
		return frozenFlag;
	}
	public void setFrozenFlag(String frozenFlag) {
		this.frozenFlag = frozenFlag;
	}
	
	
	@Column(name = "TRANSFER_FLAG" ,length = 1)
	public String getTransferFlag() {
		return transferFlag;
	}
	public void setTransferFlag(String transferFlag) {
		this.transferFlag = transferFlag;
	}
	
	
	@Temporal(TemporalType.DATE)
	@Column(name = "TRANSFER_DATE" ,length = 7)
	public Date getTransferDate() {
		return transferDate;
	}
	public void setTransferDate(Date transferDate) {
		this.transferDate = transferDate;
	}
	
	
	@Column(name = "TRANS_STATUS" ,length = 1)
	public String getTransStatus() {
		return transStatus;
	}
	public void setTransStatus(String transStatus) {
		this.transStatus = transStatus;
	}
	
	
	@Column(name = "ACCEPTED_FLAG" ,length = 1)
	public String getAcceptedFlag() {
		return acceptedFlag;
	}
	public void setAcceptedFlag(String acceptedFlag) {
		this.acceptedFlag = acceptedFlag;
	}
	
	
	@Temporal(TemporalType.DATE)
	@Column(name = "ACCEPTED_ON" ,length = 7)
	public Date getAcceptedOn() {
		return acceptedOn;
	}
	public void setAcceptedOn(Date acceptedOn) {
		this.acceptedOn = acceptedOn;
	}
	
	
	@Temporal(TemporalType.DATE)
	@Column(name = "REJECT_ON" ,length = 7)
	public Date getRejectOn() {
		return rejectOn;
	}
	public void setRejectOn(Date rejectOn) {
		this.rejectOn = rejectOn;
	}
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AG_REL_ID")
	public AgentRlns getAgentRlns() {
		return agentRlns;
	}
	public void setAgentRlns(AgentRlns agentRlns) {
		this.agentRlns = agentRlns;
	}
	
	
	@Column(name = "FREE_QTY_FLAG" ,length = 1)
	public String getFreeQtyFlag() {
		return freeQtyFlag;
	}
	public void setFreeQtyFlag(String freeQtyFlag) {
		this.freeQtyFlag = freeQtyFlag;
	}
	
	
	@Column(name = "RECEIVABLE", precision = 22, scale = 0)
	public Double getReceivable() {
		return receivable;
	}
	public void setReceivable(Double receivable) {
		this.receivable = receivable;
	}
	
	
	@Column(name = "RECEIVED", precision = 22, scale = 0)
	public Double getReceived() {
		return Received;
	}
	public void setReceived(Double received) {
		Received = received;
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
	
	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public LicPolicyDtls getLicPolicyDtls() {
		return licPolicyDtls;
	}
	public void setLicPolicyDtls(LicPolicyDtls licPolicyDtls) {
		this.licPolicyDtls = licPolicyDtls;
	}
	
}
