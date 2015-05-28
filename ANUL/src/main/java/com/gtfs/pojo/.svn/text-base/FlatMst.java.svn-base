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
@Table(name = "FLAT_MST")
public class FlatMst implements Serializable {
	
	private Long id;
	private ProjectMst projectMst;
	private String flatNo;
	private String flatDesc;
	private String flatFacing;
	private String floorName;
	private Double builtUpArea;
	private Double sprBuiltUpArea;
	private Double carpetArea;
	private String areaFlag;
	private Double pricePerSqft;
	private Double flatPrice;
	private Double otherChrgs;
	private Integer noOfCarPark;
	private Double carParkValue;
	private Double totalPrice;
	private String bookingStatus;
	private Date bookingDate;
	private CustomerMst customerMst;
	private Long agentId;
	private Date agrmntFSaleDate;
	private Long payScheduleFileId;
	private Long createdBy;
	private Long modifiedBy;
	private Long deletedBy;
	private Date createdDate;
	private Date modifiedDate;
	private Date deletedDate;
	private String deleteFlag;
	private Double finalFlatPrice;
	private Double finalCarParkPrice;
	private String paySchdType;
	
	private List<FlatPaySchd> flatPaySchds = new ArrayList<FlatPaySchd>();
	private List<FlatLoanDtls> flatLoanDtlss = new ArrayList<FlatLoanDtls>();
	private List<FlatOthrChrgCombo> flatOthrChrgCombos = new ArrayList<FlatOthrChrgCombo>();
	private List<FlatTaxCombo> flatTaxCombos = new ArrayList<FlatTaxCombo>();
	
	
	
	/* GETTER SETTER */
	@Id
	@SequenceGenerator(name="FLAT_MST_SEQ" , sequenceName="FLAT_MST_SEQ")
	@GeneratedValue(generator="FLAT_MST_SEQ" , strategy = GenerationType.AUTO)
	@Column(name = "ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PROJECT_MST_ID")
	public ProjectMst getProjectMst() {
		return projectMst;
	}
	public void setProjectMst(ProjectMst projectMst) {
		this.projectMst = projectMst;
	}
	
	@Column(name="FLAT_NO" , length=20)
	public String getFlatNo() {
		return flatNo;
	}
	public void setFlatNo(String flatNo) {
		this.flatNo = flatNo;
	}
	
	@Column(name="FLAT_DESC" , length=20)
	public String getFlatDesc() {
		return flatDesc;
	}
	public void setFlatDesc(String flatDesc) {
		this.flatDesc = flatDesc;
	}
	
	@Column(name="FLAT_FACING" , length=20)
	public String getFlatFacing() {
		return flatFacing;
	}
	public void setFlatFacing(String flatFacing) {
		this.flatFacing = flatFacing;
	}
	
	@Column(name="FLOOR_NAME" , length=20)
	public String getFloorName() {
		return floorName;
	}
	public void setFloorName(String floorName) {
		this.floorName = floorName;
	}
	
	@Column(name = "BUILT_UP_AREA", precision = 22, scale = 0)
	public Double getBuiltUpArea() {
		return builtUpArea;
	}
	public void setBuiltUpArea(Double builtUpArea) {
		this.builtUpArea = builtUpArea;
	}
	
	@Column(name = "SPR_BUILT_UP_AREA", precision = 22, scale = 0)
	public Double getSprBuiltUpArea() {
		return sprBuiltUpArea;
	}
	public void setSprBuiltUpArea(Double sprBuiltUpArea) {
		this.sprBuiltUpArea = sprBuiltUpArea;
	}
	
	@Column(name = "CARPET_AREA", precision = 22, scale = 0)
	public Double getCarpetArea() {
		return carpetArea;
	}
	public void setCarpetArea(Double carpetArea) {
		this.carpetArea = carpetArea;
	}
	
	@Column(name="AREA_FLAG" , length=1)
	public String getAreaFlag() {
		return areaFlag;
	}	
	public void setAreaFlag(String areaFlag) {
		this.areaFlag = areaFlag;
	}
	
	@Column(name = "PRICE_PER_SQFT", precision = 22, scale = 0)
	public Double getPricePerSqft() {
		return pricePerSqft;
	}
	public void setPricePerSqft(Double pricePerSqft) {
		this.pricePerSqft = pricePerSqft;
	}
	
	@Column(name = "FTAT_PRICE", precision = 22, scale = 0)
	public Double getFlatPrice() {
		return flatPrice;
	}
	public void setFlatPrice(Double flatPrice) {
		this.flatPrice = flatPrice;
	}
	
	@Column(name = "OTHER_CHRGS", precision = 22, scale = 0)
	public Double getOtherChrgs() {
		return otherChrgs;
	}
	public void setOtherChrgs(Double otherChrgs) {
		this.otherChrgs = otherChrgs;
	}
	
	@Column(name = "NO_OF_CAR_PARK", precision = 22, scale = 0)
	public Integer getNoOfCarPark() {
		return noOfCarPark;
	}
	public void setNoOfCarPark(Integer noOfCarPark) {
		this.noOfCarPark = noOfCarPark;
	}
	
	@Column(name = "CAR_PARK_VALUE", precision = 22, scale = 0)
	public Double getCarParkValue() {
		return carParkValue;
	}
	public void setCarParkValue(Double carParkValue) {
		this.carParkValue = carParkValue;
	}
	
	@Column(name = "TOTAL_PRICE", precision = 22, scale = 0)
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	@Column(name="BOOKING_STATUS" , length=20)
	public String getBookingStatus() {
		return bookingStatus;
	}
	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "BOOKING_DATE", length = 7)
	public Date getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CUSTOMER_ID")
	public CustomerMst getCustomerMst() {
		return customerMst;
	}
	public void setCustomerMst(CustomerMst customerMst) {
		this.customerMst = customerMst;
	}
	@Column(name = "AGENT_ID", precision = 22, scale = 0)
	public Long getAgentId() {
		return agentId;
	}
	public void setAgentId(Long agentId) {
		this.agentId = agentId;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "AGRMNT_F_SALE_DATE", length = 7)
	public Date getAgrmntFSaleDate() {
		return agrmntFSaleDate;
	}
	public void setAgrmntFSaleDate(Date agrmntFSaleDate) {
		this.agrmntFSaleDate = agrmntFSaleDate;
	}
	
	@Column(name = "PAY_SCHEDULE_FILE_ID", precision = 22, scale = 0)
	public Long getPayScheduleFileId() {
		return payScheduleFileId;
	}
	public void setPayScheduleFileId(Long payScheduleFileId) {
		this.payScheduleFileId = payScheduleFileId;
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
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "flatMst")
	public List<FlatPaySchd> getFlatPaySchds() {
		return flatPaySchds;
	}
	public void setFlatPaySchds(List<FlatPaySchd> flatPaySchds) {
		this.flatPaySchds = flatPaySchds;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "flatMst")
	public List<FlatLoanDtls> getFlatLoanDtlss() {
		return flatLoanDtlss;
	}
	public void setFlatLoanDtlss(List<FlatLoanDtls> flatLoanDtlss) {
		this.flatLoanDtlss = flatLoanDtlss;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "flatMst")
	public List<FlatOthrChrgCombo> getFlatOthrChrgCombos() {
		return flatOthrChrgCombos;
	}
	public void setFlatOthrChrgCombos(List<FlatOthrChrgCombo> flatOthrChrgCombos) {
		this.flatOthrChrgCombos = flatOthrChrgCombos;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "flatMst")
	public List<FlatTaxCombo> getFlatTaxCombos() {
		return flatTaxCombos;
	}
	public void setFlatTaxCombos(List<FlatTaxCombo> flatTaxCombos) {
		this.flatTaxCombos = flatTaxCombos;
	}
	
	@Column(name = "FINAL_FLAT_PRICE", precision = 22, scale = 0)
	public Double getFinalFlatPrice() {
		return finalFlatPrice;
	}
	public void setFinalFlatPrice(Double finalFlatPrice) {
		this.finalFlatPrice = finalFlatPrice;
	}
	
	@Column(name = "FINAL_CAR_PARK_PRICE", precision = 22, scale = 0)
	public Double getFinalCarParkPrice() {
		return finalCarParkPrice;
	}
	public void setFinalCarParkPrice(Double finalCarParkPrice) {
		this.finalCarParkPrice = finalCarParkPrice;
	}
	
	@Column(name = "PAY_SCHD_TYPE", length = 1)
	public String getPaySchdType() {
		return paySchdType;
	}
	public void setPaySchdType(String paySchdType) {
		this.paySchdType = paySchdType;
	}
}
