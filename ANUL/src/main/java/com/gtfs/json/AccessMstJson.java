package com.gtfs.json;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gtfs.dto.AccessMstDto;
import com.gtfs.service.AccessMstService;

@Controller
@RequestMapping("/accessMst")
public class AccessMstJson implements Serializable{

	 
	 @Autowired
	 private AccessMstService accessMstService;
	 
	 @RequestMapping(value="/findAll")
	 public @ResponseBody List<AccessMstDto> findAll(Model model){
		 return accessMstService.findAll();
	 }
	
	 @RequestMapping(value="/findById")
	 public @ResponseBody AccessMstDto findById(Model model,@RequestParam("id")Long id){
		 return accessMstService.findById(id);
	 }
}
