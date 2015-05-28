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

import com.gtfs.dto.FlatMstDto;
import com.gtfs.service.FlatMstService;

@Controller
@RequestMapping("/flatMst")
public class FlatMstJson implements Serializable {
	
	@Autowired
	private FlatMstService flatMstService;
	
	@RequestMapping(value="/findAll",method=RequestMethod.GET)
	public @ResponseBody List<FlatMstDto> findAll(){
		return flatMstService.findAll();
	}
	
	@RequestMapping(value="/findById",method=RequestMethod.GET)
	public @ResponseBody FlatMstDto findById(@RequestParam("id") Long id){
		return flatMstService.findById(id);
	}
	
	@RequestMapping(value="/findByProjectIdFlatNo",method=RequestMethod.GET)
	public @ResponseBody List<FlatMstDto> findByProjectIdFlatNo(@RequestParam("projectId") Long projectId,@RequestParam("flatNo") String flatNo){
		return flatMstService.findByProjectIdFlatNo(projectId, flatNo);
	}
	
	@RequestMapping(value="/findAvailableByProjectIdFlatNo",method=RequestMethod.GET)
	public @ResponseBody List<FlatMstDto> findAvailableByProjectIdFlatNo(@RequestParam("projectId") Long projectId,@RequestParam("flatNo") String flatNo){
		return flatMstService.findAvailableByProjectIdFlatNo(projectId, flatNo);
	}
	
	@RequestMapping(value="/findForAggrementByCustomerIdFlatNo",method=RequestMethod.GET)
	public @ResponseBody List<FlatMstDto> findForAggrementByCustomerIdFlatNo(@RequestParam("customerId") Long customerId,@RequestParam("flatNo") String flatNo){
		return flatMstService.findForAggrementByCustomerId(customerId,flatNo);
	}
	@RequestMapping(value="/saveOrUpdate",method=RequestMethod.POST)
	public @ResponseBody String saveOrUpdate(@RequestBody FlatMstDto flatMstDto,Model model, Principal principal){
		 Date now = new Date();
			
		 if(flatMstDto.getId()!=null){
			 flatMstDto.setDeleteFlag("N"); 	 
			 flatMstDto.setModifiedBy(Long.parseLong(principal.getName()));
			 flatMstDto.setModifiedDate(now);
		 }else{
			 flatMstDto.setDeleteFlag("N"); 
			 flatMstDto.setCreatedBy(Long.parseLong(principal.getName()));
			 flatMstDto.setModifiedBy(Long.parseLong(principal.getName()));
			 flatMstDto.setCreatedDate(now);
			 flatMstDto.setModifiedDate(now);
		 }
		 return flatMstService.saveOrUpdate(flatMstDto);
	}
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public @ResponseBody String delete(@RequestParam("flatId") Long flatId,Principal principal){
		return flatMstService.delete(flatId,Long.parseLong(principal.getName()));
	}
	
	
	@RequestMapping(value="/saveOrUpdateForFlatEntry",method=RequestMethod.POST)
	public @ResponseBody String saveOrUpdateForFlatEntry(@RequestBody FlatMstDto flatMstDto,Model model, Principal principal){
		 Date now = new Date();
			
		 if(flatMstDto.getId()!=null){
			 flatMstDto.setDeleteFlag("N"); 	 
			 flatMstDto.setModifiedBy(Long.parseLong(principal.getName()));
			 flatMstDto.setModifiedDate(now);
		 }else{
			 flatMstDto.setDeleteFlag("N"); 
			 flatMstDto.setCreatedBy(Long.parseLong(principal.getName()));
			 flatMstDto.setModifiedBy(Long.parseLong(principal.getName()));
			 flatMstDto.setCreatedDate(now);
			 flatMstDto.setModifiedDate(now);
		 }
		 return flatMstService.saveOrUpdateForFlatEntry(flatMstDto);
	}
	
	@RequestMapping(value="/saveOrUpdateForFlatNegotiation",method=RequestMethod.POST)
	public @ResponseBody String saveOrUpdateForFlatNegotiation(@RequestBody FlatMstDto flatMstDto,Model model, Principal principal){
		 Date now = new Date(); 	 
		 flatMstDto.setModifiedBy(Long.parseLong(principal.getName()));
		 flatMstDto.setModifiedDate(now);
		 flatMstDto.setDeleteFlag("N");
		 return flatMstService.saveOrUpdateForFlatNegotiation(flatMstDto);
	}
	
	
	
}
