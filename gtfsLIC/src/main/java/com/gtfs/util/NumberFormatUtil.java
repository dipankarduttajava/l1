package com.gtfs.util;

import java.io.Serializable;

public class NumberFormatUtil implements Serializable{

	public static Boolean isNumber(String num){
		try{
			Long.parseLong(num);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
}
