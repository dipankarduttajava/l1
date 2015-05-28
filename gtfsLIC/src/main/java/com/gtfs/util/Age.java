package com.gtfs.util;

import java.io.Serializable;

public class Age implements Serializable{
	private Integer day;
	private Integer month;
	private Integer year;
	
	/* GETTER SETTER */
	public Integer getDay() {
		return day;
	}
	public void setDay(Integer day) {
		this.day = day;
	}
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
}
