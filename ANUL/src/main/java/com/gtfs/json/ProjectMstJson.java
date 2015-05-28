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

import com.gtfs.dto.ProjectMstDto;
import com.gtfs.service.ProjectMstService;

@Controller
@RequestMapping("/projectMst")
public class ProjectMstJson implements Serializable{
	
	 @Autowired
	 private ProjectMstService projectMstService;
	 
	 @RequestMapping(value="/findAll")
	 public @ResponseBody List<ProjectMstDto> findAll(Model model){
		 return projectMstService.findAll();
	 }	
	
	 @RequestMapping(value="/findById")
	 public @ResponseBody ProjectMstDto findById(Model model,@RequestParam("id")Long id){
		 return projectMstService.findById(id);
	 }
	 
	 @RequestMapping(value="/insert",method=RequestMethod.POST)
	 public @ResponseBody Long insert(@RequestBody ProjectMstDto projectMstDto, Model model, Principal principal){
		 Date now = new Date();
		 projectMstDto.setDeleteFlag("N");
		 projectMstDto.setCreatedBy(Long.parseLong(principal.getName()));
		 projectMstDto.setModifiedBy(Long.parseLong(principal.getName()));
		 projectMstDto.setCreatedDate(now);
		 projectMstDto.setModifiedDate(now);
		 return projectMstService.insert(projectMstDto);
	 }
	 
	 @RequestMapping(value="/saveOrUpdate",method=RequestMethod.POST)
	 public @ResponseBody Boolean saveOrUpdate(@RequestBody ProjectMstDto projectMstDto, Model model, Principal principal){
		 Date now = new Date();
		
		 if(projectMstDto.getId()!=null){
			 projectMstDto.setDeleteFlag("N"); 	 
			 projectMstDto.setModifiedBy(Long.parseLong(principal.getName()));
			 projectMstDto.setModifiedDate(now);
		 }else{
			 projectMstDto.setDeleteFlag("N"); 
			 projectMstDto.setCreatedBy(Long.parseLong(principal.getName()));
			 projectMstDto.setModifiedBy(Long.parseLong(principal.getName()));
			 projectMstDto.setCreatedDate(now);
			 projectMstDto.setModifiedDate(now);
		 }
		 return projectMstService.saveOrUpdate(projectMstDto);
	 }
	 
	 @RequestMapping(value="/update",method=RequestMethod.POST)
	 public @ResponseBody Integer update(@RequestBody ProjectMstDto projectMstDto, Model model){
		 return projectMstService.update(projectMstDto);
	 }
	 
	 @RequestMapping(value="/delete",method=RequestMethod.POST)
	 public @ResponseBody Integer delete(@RequestBody ProjectMstDto projectMstDto,Model model, Principal principal){
		 Date now = new Date();
		 projectMstDto.setDeletedDate(now);
		 projectMstDto.setDeletedBy(Long.parseLong(principal.getName()));
		 return projectMstService.delete(projectMstDto);
	 }

}
