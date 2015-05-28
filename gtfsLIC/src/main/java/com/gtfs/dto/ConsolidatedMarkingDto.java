package com.gtfs.dto;

import java.io.Serializable;
import java.util.Date;

public class ConsolidatedMarkingDto implements Serializable{
	private Long id;
	private String podNo;
	private Date podDate;
	private Long userid;
	private String userName;
	private String dropdownString;
	private String courierName;
	
	
	public String getPodNo() {
		return podNo;
	}
	public void setPodNo(String podNo) {
		this.podNo = podNo;
	}
	public Date getPodDate() {
		return podDate;
	}
	public void setPodDate(Date podDate) {
		this.podDate = podDate;
	}
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getDropdownString() {
		return dropdownString;
	}
	public void setDropdownString(String dropdownString) {
		this.dropdownString = dropdownString;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCourierName() {
		return courierName;
	}
	public void setCourierName(String courierName) {
		this.courierName = courierName;
	}
	
	
}
