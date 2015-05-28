package com.gtfs.util;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FinYearCalculation implements Serializable{
	 public static String getFiscalYear(){
			String strFinancialYear="";
			String currentYear="";
			String currentMonth="";
			try
			{
				java.util.Date nowDate = new java.util.Date();
				SimpleDateFormat formatNowYear = new SimpleDateFormat("yyyy");
				currentYear = formatNowYear.format(nowDate);
				
				
				java.sql.Date nowDate2 = new java.sql.Date((new Date()).getTime());
				DateFormat formatMonth = new SimpleDateFormat ("MM");
				currentMonth = formatMonth.format(nowDate2);
				
				Integer intmOnth=Integer.parseInt(currentMonth);
				Integer intYear=Integer.parseInt(currentYear);
				
				if( intmOnth >= 4 )
				{
				    strFinancialYear=currentYear+"-"+(intYear+1);
				}
				else
				{
				    strFinancialYear=(intYear-1)+"-"+currentYear;
				}
			}
			catch(Exception ex)
			{
				strFinancialYear="";
			}
			return strFinancialYear;
		}
}
