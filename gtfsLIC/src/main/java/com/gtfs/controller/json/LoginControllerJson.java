package com.gtfs.controller.json;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gtfs.dto.LoginDto;

@Controller
public class LoginControllerJson {
	
	
	@RequestMapping(value = "/login/validate", method = RequestMethod.POST)
	public @ResponseBody Boolean validateUser(@RequestBody LoginDto loginDto){
		 System.out.println("dd "+loginDto.getUsername());
		return true;
	}
}
