package com.gtfs.action;

import java.io.Serializable;
import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class OblBusinessReport implements Serializable {
	
	private Date businessFromDate;
	private Date businessToDate;
	
	public void refresh(){
		businessFromDate=null;
		businessToDate=null;
	}
	
	public String onLoad(){
		refresh();
		return "/report/oblBusinessReport.xhtml";
	}
	
	public void searchBusinessReport(){
		
	}

	public Date getBusinessFromDate() {
		return businessFromDate;
	}

	public void setBusinessFromDate(Date businessFromDate) {
		this.businessFromDate = businessFromDate;
	}

	public Date getBusinessToDate() {
		return businessToDate;
	}

	public void setBusinessToDate(Date businessToDate) {
		this.businessToDate = businessToDate;
	}
	
}
