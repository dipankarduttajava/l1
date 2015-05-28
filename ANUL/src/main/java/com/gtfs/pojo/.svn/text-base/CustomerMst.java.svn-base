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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "CUSTOMER_MST")
public class CustomerMst implements Serializable {
	private Long id;
	private String name1;
	private String name2;
	private String name3;
	private Integer custType;
	private String contancPerson;
	private String commAddress;
	private String permAddress;
	private String email;
	private String mobile1;
	private String mobile2;
	private String mobile3;
	private String pan;
	private Long createdBy;
	private Long modifiedBy;
	private Long deletedBy;
	private Date createdDate;
	private Date modifiedDate;
	private Date deletedDate;
	private String deleteFlag;
	
	private List<PaymentFollowupCombo> paymentFollowupCombos = new ArrayList<PaymentFollowupCombo>();
	private List<FlatMst> flatMsts = new ArrayList<FlatMst>();
	private List<FlatPaySchd> FlatPaySchds = new ArrayList<FlatPaySchd>();
	
	@Id
	@Column(name = "ID", unique = true, nullable = false, precision = 22, scale = 0)
	@SequenceGenerator(name="CUSTOMER_MST_SEQ",sequenceName="CUSTOMER_MST_SEQ")
	@GeneratedValue(generator="CUSTOMER_MST_SEQ",strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}	
	
	
	@Column(name="NAME1",length=50)
	public String getName1() {
		return name1;
	}
	public void setName1(String name1) {
		this.name1 = name1;
	}
	@Column(name="NAME2",length=50)
	public String getName2() {
		return name2;
	}
	public void setName2(String name2) {
		this.name2 = name2;
	}
	@Column(name="NAME3",length=50)
	public String getName3() {
		return name3;
	}
	public void setName3(String name3) {
		this.name3 = name3;
	}
	@Column(name = "CUST_TYPE", precision = 22, scale = 0)
	public Integer getCustType() {
		return custType;
	}
	public void setCustType(Integer custType) {
		this.custType = custType;
	}
	@Column(name="CONTANC_PERSON",length=50)
	public String getContancPerson() {
		return contancPerson;
	}
	public void setContancPerson(String contancPerson) {
		this.contancPerson = contancPerson;
	}
	@Column(name="COMM_ADDRESS",length=200)
	public String getCommAddress() {
		return commAddress;
	}
	public void setCommAddress(String commAddress) {
		this.commAddress = commAddress;
	}
	@Column(name="PERM_ADDRESS",length=200)
	public String getPermAddress() {
		return permAddress;
	}
	public void setPermAddress(String permAddress) {
		this.permAddress = permAddress;
	}
	@Column(name="EMAIL",length=30)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name="MOBILE1",length=15)
	public String getMobile1() {
		return mobile1;
	}
	public void setMobile1(String mobile1) {
		this.mobile1 = mobile1;
	}
	@Column(name="MOBILE2",length=15)
	public String getMobile2() {
		return mobile2;
	}
	public void setMobile2(String mobile2) {
		this.mobile2 = mobile2;
	}
	@Column(name="MOBILE3",length=15)
	public String getMobile3() {
		return mobile3;
	}
	public void setMobile3(String mobile3) {
		this.mobile3 = mobile3;
	}
	@Column(name="PAN",length=10)
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
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
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customerMst")
	public List<PaymentFollowupCombo> getPaymentFollowupCombos() {
		return paymentFollowupCombos;
	}
	public void setPaymentFollowupCombos(
			List<PaymentFollowupCombo> paymentFollowupCombos) {
		this.paymentFollowupCombos = paymentFollowupCombos;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customerMst")
	public List<FlatMst> getFlatMsts() {
		return flatMsts;
	}
	public void setFlatMsts(List<FlatMst> flatMsts) {
		this.flatMsts = flatMsts;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customerMst")
	public List<FlatPaySchd> getFlatPaySchds() {
		return FlatPaySchds;
	}
	public void setFlatPaySchds(List<FlatPaySchd> flatPaySchds) {
		FlatPaySchds = flatPaySchds;
	}
}
