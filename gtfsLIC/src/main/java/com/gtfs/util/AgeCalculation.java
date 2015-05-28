package com.gtfs.util;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class AgeCalculation implements Serializable{
	
	public static Age calculateAge(Date dob){
		
		if(dob == null){
			Age age = new Age();
			age.setDay(null);
			age.setMonth(null);
			age.setYear(null);
			return age;
		}
		
		
		  int years = 0;
	      int months = 0;
	      int days = 0;
	      
	      //create calendar object for birth day
	      Calendar birthDay = Calendar.getInstance();
	      birthDay.setTimeInMillis(dob.getTime());
	      
	      //create calendar object for current day
	      long currentTime = System.currentTimeMillis();
	      Calendar now = Calendar.getInstance();
	      now.setTimeInMillis(currentTime);
	      
	      //Get difference between years
	      years = now.get(Calendar.YEAR) - birthDay.get(Calendar.YEAR);
	      int currMonth = now.get(Calendar.MONTH) + 1;
	      int birthMonth = birthDay.get(Calendar.MONTH) + 1;
	      
	      //Get difference between months
	      months = currMonth - birthMonth;
	      
	      //if month difference is in negative then reduce years by one and calculate the number of months.
	      if (months < 0){
	         years--;
	         months = 12 - birthMonth + currMonth;
	         if (now.get(Calendar.DATE) < birthDay.get(Calendar.DATE))
	            months--;
	      } else if (months == 0 && now.get(Calendar.DATE) < birthDay.get(Calendar.DATE)){
	         years--;
	         months = 11;
	      }
	      
	      //Calculate the days
	      if (now.get(Calendar.DATE) > birthDay.get(Calendar.DATE))
	         days = now.get(Calendar.DATE) - birthDay.get(Calendar.DATE);
	      else if (now.get(Calendar.DATE) < birthDay.get(Calendar.DATE)){
	         int today = now.get(Calendar.DAY_OF_MONTH);
	         now.add(Calendar.MONTH, -1);
	         days = now.getActualMaximum(Calendar.DAY_OF_MONTH) - birthDay.get(Calendar.DAY_OF_MONTH) + today;
	      } else{
	         days = 0;
	         if (months == 12){
	            years++;
	            months = 0;
	         }
	      }
		
	      // Create new Age object
		Age age = new Age();
		age.setDay(days);
		age.setMonth(months);
		age.setYear(years);
		
		return age;
	}
}
