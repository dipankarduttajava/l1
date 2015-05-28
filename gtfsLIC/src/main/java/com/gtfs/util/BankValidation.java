package com.gtfs.util;

import java.io.Serializable;


public class BankValidation implements Serializable{

	public static Boolean bankAccountValidation(String bankName, String accountNum){
		if(bankName.trim().toUpperCase().equals("AXIS BANK") || bankName.trim().toUpperCase().equals("AXIS BANK LTD")){
			if(accountNum.trim().length() != 15){
				return false;
			}else if(!NumberFormatUtil.isNumber(accountNum)){
				return false;
			}
			
		}else if(bankName.trim().toUpperCase().equals("STATE BANK OF INDIA")){
			if(accountNum.trim().length() != 11){
				return false;
			}else if(!NumberFormatUtil.isNumber(accountNum)){
				return false;
			}else if(accountNum.trim().startsWith("0")){
				return false;
			}

		}else if(bankName.trim().toUpperCase().equals("PUNJAB NATIONAL BANK")){
			if(accountNum.trim().length() != 16){
				return false;
			}else if(!NumberFormatUtil.isNumber(accountNum)){
				return false;
			}
			
		}else{
			if(accountNum.trim().length() > 16){
				return false;
			}else if(!NumberFormatUtil.isNumber(accountNum)){
				return false;
			}
		}
		return true;
	}
	
	
}
