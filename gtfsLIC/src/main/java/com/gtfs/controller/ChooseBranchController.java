package com.gtfs.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gtfs.bean.UserMst;
import com.gtfs.service.interfaces.AccessListService;
import com.gtfs.service.interfaces.BranchMstService;

@Controller
public class ChooseBranchController implements Serializable{
	@Autowired
	private BranchMstService branchMstService;
	@Autowired
	private AccessListService accessListService;
	
	@RequestMapping(value="/chooseBranch", method = RequestMethod.GET)
	public String onLoad(Model model){
		model.addAttribute("branches",branchMstService.findAll());
		return "chooseBranch";	
	}
	
	@RequestMapping(value="/dashboard", method = RequestMethod.GET)
	public String goToDashBoard(Model model){
		return "dashboard";
		
	}
	
	@RequestMapping(value="/chooseBranch", method = RequestMethod.POST)
	public String goToWelcomePage(@RequestParam Long branchId,HttpServletRequest request,Model model){
		
		//System.out.println("dd222222222");
		//UserMst userMst= (UserMst) request.getSession().getAttribute("userMst");
		try{
	    	   /*if (branchId.equals(userMst.getBranchMst().getBranchId())) {
	          	Map<Long,String> map = new HashMap<Long, String>();
	          	List<Object> accessList = accessListService.findAccessListByUserId(userMst.getUserid());
	              for(Object obj:accessList){
	                  Object[] ob=(Object[]) obj;
	                  Long accessId=(Long) ob[0];
	                  String accessName=(String) ob[1];
	                  map.put(accessId, accessName); 
	              }
	              
	            //  licBranchHubMaps = licBranchHubMapService.findBranchHubMapsByBranch(branchMst);
	              
	             
	              
	              return "redirect:dashboard";            
	          } else {
	        	  model.addAttribute("message","Please Contact To Head Office IT");
	        	  request.getSession().invalidate();
	              return "login";
	          }*/
			
			 return "redirect:dashboard";  
	       }catch(Exception e){
	    	   request.getSession().invalidate();
	    	   return "login";
	       }
		
	}
	
	
}
