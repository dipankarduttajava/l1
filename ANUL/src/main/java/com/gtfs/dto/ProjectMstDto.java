package com.gtfs.dto;

import java.io.Serializable;
import java.util.Date;

public class ProjectMstDto implements Serializable {
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
	
	
	/* GETTER SETTER */
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getNoOfFloor() {
		return noOfFloor;
	}
	public void setNoOfFloor(Integer noOfFloor) {
		this.noOfFloor = noOfFloor;
	}
	public Integer getNoOfFlat() {
		return noOfFlat;
	}
	public void setNoOfFlat(Integer noOfFlat) {
		this.noOfFlat = noOfFlat;
	}
	public Integer getNoOfCarPark() {
		return noOfCarPark;
	}
	public void setNoOfCarPark(Integer noOfCarPark) {
		this.noOfCarPark = noOfCarPark;
	}
	public Integer getNoOfFlatAvlbl() {
		return noOfFlatAvlbl;
	}
	public void setNoOfFlatAvlbl(Integer noOfFlatAvlbl) {
		this.noOfFlatAvlbl = noOfFlatAvlbl;
	}
	public Integer getNoOfCarParkAvlbl() {
		return noOfCarParkAvlbl;
	}
	public void setNoOfCarParkAvlbl(Integer noOfCarParkAvlbl) {
		this.noOfCarParkAvlbl = noOfCarParkAvlbl;
	}
	public Integer getMilestoneCompltd() {
		return milestoneCompltd;
	}
	public void setMilestoneCompltd(Integer milestoneCompltd) {
		this.milestoneCompltd = milestoneCompltd;
	}
	public String getChqInFavour() {
		return chqInFavour;
	}
	public void setChqInFavour(String chqInFavour) {
		this.chqInFavour = chqInFavour;
	}
	public Long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}
	public Long getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public Long getDeletedBy() {
		return deletedBy;
	}
	public void setDeletedBy(Long deletedBy) {
		this.deletedBy = deletedBy;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public Date getDeletedDate() {
		return deletedDate;
	}
	public void setDeletedDate(Date deletedDate) {
		this.deletedDate = deletedDate;
	}
	public String getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
}
