package com.gtfs.service;

import java.util.List;

import com.gtfs.dto.CustomerMstDto;

public interface CustomerMstService {
	List<CustomerMstDto> findAll();
	CustomerMstDto findById(Long id);
	List<CustomerMstDto> findCustomerByNameMobilePan(String name,String mobile,String pan);
	String saveCustomerBooking(Long customerId, Long flatId, Long projectId);
	List<CustomerMstDto> findCustomerForAggrementByNameMobilePan(String name,String mobile, String pan);
	String saveCustomerAggrement(Long customerId, Long flatId, Long projectId);
	String saveOrUpdate(CustomerMstDto customerMstDto);
	String delete(Long customerId, Long deletedBy);
	String updateMilesoneAfterBooking(Long customerId, Long flatId,Long projectId);
	
}
