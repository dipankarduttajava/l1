package com.gtfs.json;

import java.io.Serializable;
import java.security.Principal;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gtfs.dto.CustomerMstDto;
import com.gtfs.service.CustomerMstService;

@Controller
@RequestMapping("/customerMst")
public class CustomerMstJson implements Serializable {
	
	private Logger log = Logger.getLogger(CustomerMstJson.class);
	 
	 @Autowired
	 private CustomerMstService customerMstService;
	 
	 @RequestMapping(value="/findAll",method=RequestMethod.GET)
	 public @ResponseBody List<CustomerMstDto> findAll(Model model){
		 return customerMstService.findAll();
	 }
	
	 @RequestMapping(value="/findById")
	 public @ResponseBody CustomerMstDto findById(Model model,@RequestParam("id")Long id){
		 return customerMstService.findById(id);
	 }
	 
	 @RequestMapping(value="/findCustomerByNameMobilePan",method=RequestMethod.POST)
	 public @ResponseBody List<CustomerMstDto> findCustomerByNameMobilePan(Model model,@RequestParam("name")String name,@RequestParam("mobile")String mobile,@RequestParam("pan")String pan){
		 return customerMstService.findCustomerByNameMobilePan(name, mobile, pan);
	 }
	 
	 @RequestMapping(value="/saveCustomerBooking",method=RequestMethod.GET)
	 public @ResponseBody String saveCustomerBooking(@RequestParam("customerId")Long customerId, @RequestParam("flatId")Long flatId, @RequestParam("projectId")Long projectId ){
		 String response =  customerMstService.saveCustomerBooking(customerId, flatId, projectId);
		 log.info("response "+response);
		 if(response.equals("true")){
			 customerMstService.updateMilesoneAfterBooking(customerId, flatId, projectId);
		 }
		 
		 return response;
	 }
	 
	 @RequestMapping(value="/updateMilesoneAfterBooking",method=RequestMethod.GET)
	 public @ResponseBody String updateMilesoneAfterBooking(@RequestParam("customerId")Long customerId, @RequestParam("flatId")Long flatId, @RequestParam("projectId")Long projectId ){
		 return customerMstService.updateMilesoneAfterBooking(customerId, flatId, projectId);
	 }
	 
	 
	 @RequestMapping(value="/findCustomerForAggrementByNameMobilePan",method=RequestMethod.POST)
	 public @ResponseBody List<CustomerMstDto> findCustomerForAggrementByNameMobilePan(Model model,@RequestParam("name")String name,@RequestParam("mobile")String mobile,@RequestParam("pan")String pan){
		 return customerMstService.findCustomerForAggrementByNameMobilePan(name, mobile, pan);
	 }
	 
	 @RequestMapping(value="/saveCustomerAggrement",method=RequestMethod.GET)
	 public @ResponseBody String saveCustomerAggrement(@RequestParam("customerId")Long customerId,@RequestParam("flatId")Long flatId, @RequestParam("projectId")Long projectId){
		 return customerMstService.saveCustomerAggrement(customerId, flatId, projectId);
	 }
	 
	 @RequestMapping(value="/saveOrUpdate",method=RequestMethod.POST)
	 public @ResponseBody String saveOrUpdate(@RequestBody CustomerMstDto customerMstDto, Model model, Principal principal){
		 Date now = new Date();
		 if(customerMstDto.getId()!=null){
			 customerMstDto.setDeleteFlag("N"); 	 
			 customerMstDto.setModifiedBy(Long.parseLong(principal.getName()));
			 customerMstDto.setModifiedDate(now);
		 }else{
			 customerMstDto.setDeleteFlag("N"); 
			 customerMstDto.setCreatedBy(Long.parseLong(principal.getName()));
			 customerMstDto.setModifiedBy(Long.parseLong(principal.getName()));
			 customerMstDto.setCreatedDate(now);
			 customerMstDto.setModifiedDate(now);
		 }
		 return customerMstService.saveOrUpdate(customerMstDto);
	 }
	 
	 
	 @RequestMapping(value="/delete",method=RequestMethod.GET)
	 public @ResponseBody String delete(@RequestParam("customerId") Long customerId, Model model, Principal principal){
		 return customerMstService.delete(customerId,Long.parseLong(principal.getName()));
	 }
}
