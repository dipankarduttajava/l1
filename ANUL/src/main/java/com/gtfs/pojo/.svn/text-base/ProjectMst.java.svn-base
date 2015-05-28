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
@Table(name = "PROJECT_MST")
public class ProjectMst implements Serializable {
	
	private Long id;
	private String projectName;
	private String address;
	private Integer noOfFloor;
	private Integer noOfFlat;
	private Integer noOfCarPark;
	private Integer noOfFlatAvlbl;
	private Integer noOfCarParkAvlbl;
	private Integer milestoneCompltd;
	private String chqInFavour;
	private Long createdBy;
	private Long modifiedBy;
	private Long deletedBy;
	private Date createdDate;
	private Date modifiedDate;
	private Date deletedDate;
	private String deleteFlag;
	private Double carParkListedValue;
	
	private List<FlatMst> flatMsts = new ArrayList<FlatMst>();
	private List<ProjectMilestone> projectMilestones = new ArrayList<ProjectMilestone>();
	
	/* GETTER SETTER */	
	@Id
	@SequenceGenerator(name="PROJECT_MST_SEQ" , sequenceName="PROJECT_MST_SEQ")
	@GeneratedValue(generator="PROJECT_MST_SEQ" , strategy = GenerationType.AUTO)
	@Column(name = "ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}	
	
	@Column(name="PROJECT_NAME" , length=100)
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	@Column(name="ADDRESS" , length=500)
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Column(name = "NO_OF_FLOOR" , precision = 22, scale = 0)
	public Integer getNoOfFloor() {
		return noOfFloor;
	}
	public void setNoOfFloor(Integer noOfFloor) {
		this.noOfFloor = noOfFloor;
	}
	
	@Column(name = "NO_OF_FLAT" , precision = 22, scale = 0)
	public Integer getNoOfFlat() {
		return noOfFlat;
	}
	public void setNoOfFlat(Integer noOfFlat) {
		this.noOfFlat = noOfFlat;
	}
	@Column(name = "NO_OF_CAR_PARK" , precision = 22, scale = 0)
	public Integer getNoOfCarPark() {
		return noOfCarPark;
	}
	public void setNoOfCarPark(Integer noOfCarPark) {
		this.noOfCarPark = noOfCarPark;
	}
	
	@Column(name = "NO_OF_FLAT_AVLBL" , precision = 22, scale = 0)
	public Integer getNoOfFlatAvlbl() {
		return noOfFlatAvlbl;
	}
	public void setNoOfFlatAvlbl(Integer noOfFlatAvlbl) {
		this.noOfFlatAvlbl = noOfFlatAvlbl;
	}
	
	@Column(name = "NO_OF_CAR_PARK_AVLBL" , precision = 22, scale = 0)
	public Integer getNoOfCarParkAvlbl() {
		return noOfCarParkAvlbl;
	}
	public void setNoOfCarParkAvlbl(Integer noOfCarParkAvlbl) {
		this.noOfCarParkAvlbl = noOfCarParkAvlbl;
	}
	
	@Column(name = "MILESTONE_COMPLTD" , precision = 22, scale = 0)
	public Integer getMilestoneCompltd() {
		return milestoneCompltd;
	}
	public void setMilestoneCompltd(Integer milestoneCompltd) {
		this.milestoneCompltd = milestoneCompltd;
	}
	
	@Column(name = "CHQ_IN_FAVOUR" , precision = 22, scale = 0)
	public String getChqInFavour() {
		return chqInFavour;
	}
	public void setChqInFavour(String chqInFavour) {
		this.chqInFavour = chqInFavour;
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
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "projectMst")
	public List<FlatMst> getFlatMsts() {
		return flatMsts;
	}
	public void setFlatMsts(List<FlatMst> flatMsts) {
		this.flatMsts = flatMsts;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "projectMst")
	public List<ProjectMilestone> getProjectMilestones() {
		return projectMilestones;
	}
	public void setProjectMilestones(List<ProjectMilestone> projectMilestones) {
		this.projectMilestones = projectMilestones;
	}
	
	@Column(name = "CAR_PARK_LISTED_VALUE" , precision = 22, scale = 0)
	public Double getCarParkListedValue() {
		return carParkListedValue;
	}
	public void setCarParkListedValue(Double carParkListedValue) {
		this.carParkListedValue = carParkListedValue;
	}
}
