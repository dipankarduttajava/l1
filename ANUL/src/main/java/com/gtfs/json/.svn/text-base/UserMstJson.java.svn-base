package com.gtfs.json;

import java.io.Serializable;
import java.security.Principal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gtfs.dto.UserMstDto;
import com.gtfs.service.UserMstService;

@Controller
@RequestMapping("/userMst")
public class UserMstJson implements Serializable{
	@Autowired
	private UserMstService userMstService;
	
	@RequestMapping(value="/findAll")
	public @ResponseBody List<UserMstDto> findAll(){
		return userMstService.findAll();
	}	
	@RequestMapping(value="/findById")
	public @ResponseBody UserMstDto findById(Model model,@RequestParam("id")Long id){
		 return userMstService.findById(id);
	}
	@RequestMapping(value="/saveOrUpdate",method=RequestMethod.POST)
	public @ResponseBody Boolean saveOrUpdate(@RequestBody UserMstDto userMstDto, Model model, Principal principal){
		Date now = new Date();
		if(userMstDto.getId() == null){
			userMstDto.setCreatedBy(Long.parseLong(principal.getName()));
			userMstDto.setModifiedBy(Long.parseLong(principal.getName()));
			userMstDto.setCreatedDate(now);
			userMstDto.setModifiedDate(now);
			userMstDto.setDeleteFlag("N");
		}else{
			userMstDto.setModifiedBy(Long.parseLong(principal.getName()));
			userMstDto.setModifiedDate(now);
			userMstDto.setDeleteFlag("N");
		}
		
		return userMstService.saveOrUpdate(userMstDto);
	}
	 
}
