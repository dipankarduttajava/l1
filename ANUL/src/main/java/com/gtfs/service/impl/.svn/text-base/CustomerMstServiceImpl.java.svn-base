package com.gtfs.service.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtfs.dao.CustomerMstDao;
import com.gtfs.dto.CustomerMstDto;
import com.gtfs.pojo.CustomerMst;
import com.gtfs.service.CustomerMstService;

@Service
public class CustomerMstServiceImpl implements CustomerMstService, Serializable {
	private Logger log = Logger.getLogger(CustomerMstServiceImpl.class);
	
	@Autowired
	private CustomerMstDao customerMstDao;
	
	@Override
	public List<CustomerMstDto> findAll() {
		return customerMstDao.findAll();
	}

	@Override
	public CustomerMstDto findById(Long id) {
		CustomerMst customerMst = customerMstDao.findById(id);
		CustomerMstDto customerMstDto = new CustomerMstDto();
		customerMstDto.setId(customerMst.getId());
		customerMstDto.setName1(customerMst.getName1());
		customerMstDto.setName2(customerMst.getName2());
		customerMstDto.setName3(customerMst.getName3());
		customerMstDto.setCustType(customerMst.getCustType());
		customerMstDto.setContancPerson(customerMst.getContancPerson());
		customerMstDto.setCommAddress(customerMst.getCommAddress());
		customerMstDto.setPermAddress(customerMst.getPermAddress());
		customerMstDto.setEmail(customerMst.getEmail());
		customerMstDto.setMobile1(customerMst.getMobile1());
		customerMstDto.setMobile2(customerMst.getMobile2());
		customerMstDto.setMobile3(customerMst.getMobile3());
		customerMstDto.setPan(customerMst.getPan());
		customerMstDto.setCreatedBy(customerMst.getCreatedBy());
		customerMstDto.setModifiedBy(customerMst.getModifiedBy());
		customerMstDto.setDeletedBy(customerMst.getDeletedBy());
		customerMstDto.setCreatedDate(customerMst.getCreatedDate());
		customerMstDto.setModifiedDate(customerMst.getModifiedDate());
		customerMstDto.setDeletedDate(customerMst.getDeletedDate());
		customerMstDto.setDeleteFlag(customerMst.getDeleteFlag());
		return customerMstDto;
	}

	@Override
	public List<CustomerMstDto> findCustomerByNameMobilePan(String name,String mobile, String pan) {
		return customerMstDao.findCustomerByNameMobilePan(name, mobile, pan);
	}

	@Override
	public String saveCustomerBooking(Long customerId, Long flatId, Long projectId) {
		return customerMstDao.saveCustomerBooking(customerId, flatId, projectId);
	}

	@Override
	public List<CustomerMstDto> findCustomerForAggrementByNameMobilePan(String name, String mobile, String pan) {
		return customerMstDao.findCustomerForAggrementByNameMobilePan(name, mobile, pan);
	}

	@Override
	public String saveCustomerAggrement(Long customerId, Long flatId, Long projectId) {
		return customerMstDao.saveCustomerAggrement(customerId,flatId, projectId);
	}

	@Override
	public String saveOrUpdate(CustomerMstDto customerMstDto) {		
		try{
			CustomerMst customerMst = new CustomerMst();
			customerMst.setId(customerMstDto.getId());
			customerMst.setName1(customerMstDto.getName1());
			customerMst.setName2(customerMstDto.getName2());
			customerMst.setName3(customerMstDto.getName3());
			customerMst.setContancPerson(customerMstDto.getContancPerson());
			customerMst.setCommAddress(customerMstDto.getCommAddress());
			customerMst.setPermAddress(customerMstDto.getPermAddress());
			customerMst.setEmail(customerMstDto.getEmail());
			customerMst.setMobile1(customerMstDto.getMobile1());
			customerMst.setMobile2(customerMstDto.getMobile2());
			customerMst.setMobile3(customerMstDto.getMobile3());
			customerMst.setPan(customerMstDto.getPan());
			customerMst.setCreatedBy(customerMstDto.getCreatedBy());
			customerMst.setModifiedBy(customerMstDto.getModifiedBy());
			customerMst.setCreatedDate(customerMstDto.getCreatedDate());
			customerMst.setModifiedDate(customerMstDto.getModifiedDate());
			customerMst.setDeleteFlag(customerMstDto.getDeleteFlag());
			return customerMstDao.saveOrUpdate(customerMst);
		}catch(Exception e){
			log.info("Error, e");
			return null;
		}
	}

	@Override
	public String delete(Long customerId,Long deletedBy) {
		return customerMstDao.delete(customerId, deletedBy);
	}

	@Override
	public String updateMilesoneAfterBooking(Long customerId, Long flatId,Long projectId) {
		return customerMstDao.updateMilesoneAfterBooking(customerId,flatId,projectId);
	}
}
