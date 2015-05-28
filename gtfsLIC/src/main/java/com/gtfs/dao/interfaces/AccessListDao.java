package com.gtfs.dao.interfaces;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.gtfs.bean.AccessList;

public interface AccessListDao {
	Boolean saveAccessList(List<AccessList> accessList);
	AccessList findById(Long accessId);
	List<Object> findAccessListByUserId(Long userId);
	List<AccessList> findAll();
	List<Object> findAllActiveAccessList();
	Boolean deleteAccessList(Long accessId,Long userId);
	List<Object> findAllActiveAccessListByAccessName(String accessName);
	
}
