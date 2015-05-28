package com.gtfs.controller;

import java.io.Serializable;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/pro")
public class UserMstController implements Serializable {
	
	@RequestMapping(value="/userMstEntry")
	 public String findAll(Model model){
		 return "userMstEntry";
	 }
}
