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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "LIC_PAYMENT_MST")
public class LicPaymentMst implements Serializable{
	private Long id;
	private Date payDate;
	private String processName;
	private String payMode;
	private Double totalReceivable;
	private Double totalReceived;
	private Double serviceTax;
	private Double serviceCharge;
	private Double salesTax;
	private Double ddCharges;
	private Double otherCharges;
	private Long createdBy;
	private Long modifiedBy;
	private Long deletedBy;
	private Date createdDate;
	private Date modifiedDate;
	private Date deletedDate;
	private String deleteFlag;
	
	private LicBusinessTxn licBusinessTxn;
	private List<LicPaymentDtls> licPaymentDtlses = new ArrayList<LicPaymentDtls>(0);
	
	
	
	@Id
	@GenericGenerator(name = "paymentGenerator", strategy = "foreign", parameters = @Parameter(name = "property", value = "licBusinessTxn"))
	@GeneratedValue(generator="paymentGenerator")
	@Column(name = "ID", nullable = false, precision = 22, scale = 0)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	@Temporal(TemporalType.DATE)
	@Column(name = "PAY_DATE", length = 7)
	public Date getPayDate() {
		return payDate;
	}
	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}
	
	
	@Column(name = "PROCESS_NAME", length = 10)
	public String getProcessName() {
		return processName;
	}
	public void setProcessName(String processName) {
		this.processName = processName;
	}
	
	
	@Column(name = "PAY_MODE", length = 1)
	public String getPayMode() {
		return payMode;
	}
	public void setPayMode(String payMode) {
		this.payMode = payMode;
	}
	
	
	@Column(name = "SERVICE_TAX", precision = 22, scale = 0)
	public Double getServiceTax() {
		return serviceTax;
	}
	public void setServiceTax(Double serviceTax) {
		this.serviceTax = serviceTax;
	}
	
	
	@Column(name = "TOTAL_RECEIVABLE", precision = 22, scale = 0)
	public Double getTotalReceivable() {
		return totalReceivable;
	}
	public void setTotalReceivable(Double totalReceivable) {
		this.totalReceivable = totalReceivable;
	}
	
	
	@Column(name = "TOTAL_RECEIVED", precision = 22, scale = 0)
	public Double getTotalReceived() {
		return totalReceived;
	}
	public void setTotalReceived(Double totalReceived) {
		this.totalReceived = totalReceived;
	}
	
	
	@Column(name = "SERVICE_CHARGE", precision = 22, scale = 0)
	public Double getServiceCharge() {
		return serviceCharge;
	}
	public void setServiceCharge(Double serviceCharge) {
		this.serviceCharge = serviceCharge;
	}
	
	
	@Column(name = "SALES_TAX", precision = 22, scale = 0)
	public Double getSalesTax() {
		return salesTax;
	}
	public void setSalesTax(Double salesTax) {
		this.salesTax = salesTax;
	}
	
	
	@Column(name = "DD_CHARGES", precision = 22, scale = 0)
	public Double getDdCharges() {
		return ddCharges;
	}
	public void setDdCharges(Double ddCharges) {
		this.ddCharges = ddCharges;
	}
	
	
	@Column(name = "OTHER_CHARGES", precision = 22, scale = 0)
	public Double getOtherCharges() {
		return otherCharges;
	}
	public void setOtherCharges(Double otherCharges) {
		this.otherCharges = otherCharges;
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
	public LicBusinessTxn getLicBusinessTxn() {
		return licBusinessTxn;
	}
	public void setLicBusinessTxn(LicBusinessTxn licBusinessTxn) {
		this.licBusinessTxn = licBusinessTxn;
	}
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "licPaymentMst")
	@Cascade({CascadeType.SAVE_UPDATE})
	public List<LicPaymentDtls> getLicPaymentDtlses() {
		return licPaymentDtlses;
	}
	public void setLicPaymentDtlses(List<LicPaymentDtls> licPaymentDtlses) {
		this.licPaymentDtlses = licPaymentDtlses;
	}
	
}
