package com.gtfs.controller;

import java.io.Serializable;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gtfs.bean.AccessList;

@Controller
public class AccessListController implements Serializable{
	
	@RequestMapping(value="/accesslist/{id}", method = RequestMethod.GET)
	public @ResponseBody AccessList findById(@PathVariable Long accessId) {
		AccessList accessList = new AccessList();
		accessList.setAccessId(accessId);

		
		return accessList;
 
	}
	
	
	
	

}
