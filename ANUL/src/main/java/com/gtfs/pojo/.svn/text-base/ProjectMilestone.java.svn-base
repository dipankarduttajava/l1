package com.gtfs.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "PROJECT_MILESTONE")
public class ProjectMilestone implements Serializable {

	private Long id;
	private ProjectMst projectMst;
	private Long milestoneSrlNo;
	private String milestoneDesc;
	private String milestoneScope;
	private String milestoneStatus;
	private Double paymentPerc;
	private Long createdBy;
	private Long modifiedBy;
	private Long deletedBy;
	private Date createdDate;
	private Date modifiedDate;
	private Date deletedDate;
	private String deleteFlag;
	
	private List<FlatPaySchd> flatPaySchds = new ArrayList<FlatPaySchd>();
	
	/* GETTER SETTER */
	@Id
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
	
	@Column(name = "MILESTONE_SRL_NO", precision = 22, scale = 0)
	public Long getMilestoneSrlNo() {
		return milestoneSrlNo;
	}
	public void setMilestoneSrlNo(Long milestoneSrlNo) {
		this.milestoneSrlNo = milestoneSrlNo;
	}
	
	@Column(name="MILESTONE_DESC" , length=200)
	public String getMilestoneDesc() {
		return milestoneDesc;
	}
	public void setMilestoneDesc(String milestoneDesc) {
		this.milestoneDesc = milestoneDesc;
	}
	
	@Column(name="MILESTONE_SCOPE" , length=1)
	public String getMilestoneScope() {
		return milestoneScope;
	}
	public void setMilestoneScope(String milestoneScope) {
		this.milestoneScope = milestoneScope;
	}
	
	@Column(name="MILESTONE_STATUS" , length=10)
	public String getMilestoneStatus() {
		return milestoneStatus;
	}
	public void setMilestoneStatus(String milestoneStatus) {
		this.milestoneStatus = milestoneStatus;
	}
	
	@Column(name = "PAYMENT_PERC", precision = 22, scale = 0)
	public Double getPaymentPerc() {
		return paymentPerc;
	}
	public void setPaymentPerc(Double paymentPerc) {
		this.paymentPerc = paymentPerc;
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
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "projectMilestone")
	public List<FlatPaySchd> getFlatPaySchds() {
		return flatPaySchds;
	}
	public void setFlatPaySchds(List<FlatPaySchd> flatPaySchds) {
		this.flatPaySchds = flatPaySchds;
	}
}
