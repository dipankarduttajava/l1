package com.gtfs.pojo;

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

@Entity
@Table(name = "FLAT_PAY_SCHD")
public class FlatPaySchd implements Serializable {

	private Long id;
	private FlatMst flatMst;
	private String flatPaySchdType;
	private ProjectMilestone projectMilestone;
	private String eventTimeFlag;
	private Date dueDate;
	private Double paymentRcvbl;
	private String paymentStatus;
	private Long createdBy;
	private Long modifiedBy;
	private Long deletedBy;
	private Date createdDate;
	private Date modifiedDate;
	private Date deletedDate;
	private String deleteFlag;
	private CustomerMst customerMst;
	private String payScheType;
	
	private List<FlatInvoiceCombo> flatInvoiceCombos = new ArrayList<FlatInvoiceCombo>();
	private List<PaymentFollowupCombo> paymentFollowupCombos = new ArrayList<PaymentFollowupCombo>();
	private List<ReceiptMst> receiptMsts = new ArrayList<ReceiptMst>();
	private List<FlatTaxApplicableCombo> flatTaxApplicableCombos = new ArrayList<FlatTaxApplicableCombo>();
	
	/* GETTER SETTER */
	@Id
	@Column(name = "ID", unique = true, nullable = false, precision = 22, scale = 0)
	@SequenceGenerator(name="FLAT_PAY_SCHD_SEQ",sequenceName="FLAT_PAY_SCHD_SEQ")
	@GeneratedValue(generator="FLAT_PAY_SCHD_SEQ",strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FLAT_MST_ID")
	public FlatMst getFlatMst() {
		return flatMst;
	}
	public void setFlatMst(FlatMst flatMst) {
		this.flatMst = flatMst;
	}
	
	@Column(name="FLAT_PAY_SCHD_TYPE" , length=1)
	public String getFlatPaySchdType() {
		return flatPaySchdType;
	}
	public void setFlatPaySchdType(String flatPaySchdType) {
		this.flatPaySchdType = flatPaySchdType;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PROJECT_MILESTONE_ID")
	public ProjectMilestone getProjectMilestone() {
		return projectMilestone;
	}
	public void setProjectMilestone(ProjectMilestone projectMilestone) {
		this.projectMilestone = projectMilestone;
	}
	
	@Column(name="EVENT_TIME_FLAG" , length=1)
	public String getEventTimeFlag() {
		return eventTimeFlag;
	}
	public void setEventTimeFlag(String eventTimeFlag) {
		this.eventTimeFlag = eventTimeFlag;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "DUE_DATE", length = 7)
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	
	@Column(name = "PAYMENT_RCVBL", precision = 22, scale = 0)
	public Double getPaymentRcvbl() {
		return paymentRcvbl;
	}
	public void setPaymentRcvbl(Double paymentRcvbl) {
		this.paymentRcvbl = paymentRcvbl;
	}
	
	@Column(name="PAYMENT_STATUS" , length=10)
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	
	@Column(name = "CREATED_BY", precision = 22, scale = 0)
	public Long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}
	
	@Column(name = "MODIFIED_BY", precision = 22, scale = 0)
	public Long getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	
	@Column(name = "DELETED_BY", precision = 22, scale = 0)
	public Long getDeletedBy() {
		return deletedBy;
	}
	public void setDeletedBy(Long deletedBy) {
		this.deletedBy = deletedBy;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "CREATED_DATE", length = 7)
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "MODIFIED_DATE", length = 7)
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "DELETED_DATE", length = 7)
	public Date getDeletedDate() {
		return deletedDate;
	}
	public void setDeletedDate(Date deletedDate) {
		this.deletedDate = deletedDate;
	}
	
	@Column(name = "DELETE_FLAG", length = 1)
	public String getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "flatPaySchd")
	public List<FlatInvoiceCombo> getFlatInvoiceCombos() {
		return flatInvoiceCombos;
	}
	public void setFlatInvoiceCombos(List<FlatInvoiceCombo> flatInvoiceCombos) {
		this.flatInvoiceCombos = flatInvoiceCombos;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "flatPaySchd")
	public List<PaymentFollowupCombo> getPaymentFollowupCombos() {
		return paymentFollowupCombos;
	}
	public void setPaymentFollowupCombos(List<PaymentFollowupCombo> paymentFollowupCombos) {
		this.paymentFollowupCombos = paymentFollowupCombos;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "flatPaySchd")
	public List<ReceiptMst> getReceiptMsts() {
		return receiptMsts;
	}
	public void setReceiptMsts(List<ReceiptMst> receiptMsts) {
		this.receiptMsts = receiptMsts;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CUSTOMER_ID")
	public CustomerMst getCustomerMst() {
		return customerMst;
	}
	public void setCustomerMst(CustomerMst customerMst) {
		this.customerMst = customerMst;
	}
	
	@Column(name="PAY_SCHE_TYPE",length=1)
	public String getPayScheType() {
		return payScheType;
	}
	public void setPayScheType(String payScheType) {
		this.payScheType = payScheType;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "flatPaySchd")
	public List<FlatTaxApplicableCombo> getFlatTaxApplicableCombos() {
		return flatTaxApplicableCombos;
	}
	public void setFlatTaxApplicableCombos(
			List<FlatTaxApplicableCombo> flatTaxApplicableCombos) {
		this.flatTaxApplicableCombos = flatTaxApplicableCombos;
	}
}
