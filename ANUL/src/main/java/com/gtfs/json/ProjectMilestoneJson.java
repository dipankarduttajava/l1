package com.gtfs.json;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gtfs.dao.impl.CustomerMstDaoImpl;
import com.gtfs.dto.ProjectMilestoneDto;
import com.gtfs.service.ProjectMilestoneService;

@Controller
@RequestMapping("/projectMilestone")
public class ProjectMilestoneJson implements Serializable{
	private Logger log = Logger.getLogger(ProjectMilestoneJson.class);
	
	 @Autowired
	 private ProjectMilestoneService ProjectMilestoneService;
	 
	 @RequestMapping(value="/findAll",method=RequestMethod.GET)
	 public @ResponseBody List<ProjectMilestoneDto> findAll(Model model){
		 return ProjectMilestoneService.findAll();
	 }
	
	 @RequestMapping(value="/findById")
	 public @ResponseBody ProjectMilestoneDto findById(Model model,@RequestParam("id")Long id){
		 return ProjectMilestoneService.findById(id);
	 }
	 
	 @RequestMapping(value="/findByProjectId",method=RequestMethod.GET)
	 public @ResponseBody List<ProjectMilestoneDto> findByProjectId(Model model,@RequestParam("projectId")Long projectId){
		 return ProjectMilestoneService.findByProjectId(projectId);
	 }
	 
	 @RequestMapping(value="/findFlatSpecificByProjectId",method=RequestMethod.GET)
	 public @ResponseBody List<ProjectMilestoneDto> findFlatSpecificByProjectId(Model model,@RequestParam("projectId")Long projectId){
		 return ProjectMilestoneService.findFlatSpecificByProjectId(projectId);
	 }
	 
	 @RequestMapping(value="/findNextProjectSpcMilstoneByProjectId",method=RequestMethod.GET)
	 public @ResponseBody List<ProjectMilestoneDto> findNextProjectSpcMilstoneByProjectId(Model model,@RequestParam("projectId")Long projectId){
		 return ProjectMilestoneService.findNextProjectSpcMilstoneByProjectId(projectId);
	 }
	 
	 @RequestMapping(value="/findFlatNextProjectSpcMilstoneByProjectId",method=RequestMethod.GET)
	 public @ResponseBody List<ProjectMilestoneDto> findFlatNextProjectSpcMilstoneByProjectId(Model model,@RequestParam("projectId")Long projectId){
		 List<ProjectMilestoneDto> list = new ArrayList<ProjectMilestoneDto>();
		 
		 list.addAll(ProjectMilestoneService.findFlatSpecificByProjectId(projectId));
		 list.addAll(ProjectMilestoneService.findNextProjectSpcMilstoneByProjectId(projectId));
		 return list;
	 }
	 
	 @RequestMapping(value="/updateProjectMilestone",method=RequestMethod.GET)
	 public @ResponseBody String updateProjectMilestone(Model model,@RequestParam("projectId")Long projectId,@RequestParam("milestoneId")Long milestoneId,@RequestParam("dateGiven")  @DateTimeFormat(pattern = "dd/MM/yyyy") Date dateGiven){
//		 log.info("XXXXXXXXXXXXXXXXXXXXXXXX : " + dateGiven);
//		 return "true";
		 return ProjectMilestoneService.updateProjectMilestone(projectId,milestoneId,dateGiven);
	 }
}
