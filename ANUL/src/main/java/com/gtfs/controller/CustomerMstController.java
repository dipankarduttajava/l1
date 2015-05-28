package com.gtfs.controller;

import java.io.Serializable;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/pro")
public class CustomerMstController implements Serializable {

	@RequestMapping(value="/customerMstEntry")
	 public String findAll(Model model){
		 return "customerMstEntry";
	 }
}
