package com.gtfs.controller;

import java.io.Serializable;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/pro")
public class FlatNegotiationController implements Serializable {

	@RequestMapping(value="/flatNegotiation")
	 public String findAll(Model model){
		 return "flatNegotiation";
	 }
}
