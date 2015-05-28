package com.gtfs.util;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

public class CalenderUtil implements Serializable {

	public static Integer monthDiff(Date oldDate, Date newDate) {
		if (oldDate.compareTo(newDate) > 0) {
			return -1;
		}

		
		int months = -1;
		int days = 0;

		Calendar oldCalendar = Calendar.getInstance();
		oldCalendar.setTime(oldDate);
		Calendar newCalendar = Calendar.getInstance();
		newCalendar.setTime(newDate);
		
		do {
			months++;
			oldCalendar.add(Calendar.MONTH, 1);
			
		} while (newCalendar.compareTo(oldCalendar) >= 0);
		
		oldCalendar.add(Calendar.MONTH, -1);
		do {
			days++;
			oldCalendar.add(Calendar.DATE, 1);
		} while (newCalendar.compareTo(oldCalendar) >= 0);

		if (months == 0) {
			return months;
		} else {
			if (days < 15) {
				return months;
			} else {
				return months + 1;
			}
		}
	}

	public static Date incrementDate(Date date, Integer days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, days);
		return cal.getTime();
	}
	
	
	public static void main(String args[]){
		
		Calendar oldCal = Calendar.getInstance();
		
		oldCal.set(Calendar.DATE,5);
		oldCal.set(Calendar.MONTH,3);
		oldCal.set(Calendar.YEAR,2014);
		
		
		Integer fineMnth = CalenderUtil.monthDiff(oldCal.getTime(),new Date());
		
		System.out.print(fineMnth);
	}
	
	
}
