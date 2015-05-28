package com.gtfs.dao;

import java.util.List;

import com.gtfs.dto.CustomerMstDto;
import com.gtfs.pojo.CustomerMst;

public interface CustomerMstDao {
	List<CustomerMstDto> findAll();
	CustomerMst findById(Long id);
	List<CustomerMstDto> findCustomerByNameMobilePan(String name,String mobile,String pan);
	String saveCustomerBooking(Long customerId, Long flatId, Long projectId);
	List<CustomerMstDto> findCustomerForAggrementByNameMobilePan(String name,String mobile, String pan);
	String saveCustomerAggrement(Long customerId, Long flatId, Long projectId);
	String saveOrUpdate(CustomerMst customerMst);
	String delete(Long customerId,Long deletedby);
	String updateMilesoneAfterBooking(Long customerId, Long flatId,Long projectId);
}
