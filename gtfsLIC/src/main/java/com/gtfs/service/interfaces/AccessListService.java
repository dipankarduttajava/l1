package com.gtfs.service.interfaces;

import java.io.Serializable;
import java.util.List;

import com.gtfs.bean.AccessList;


public interface AccessListService extends Serializable{
	List<Object> findAccessListByUserId(Long userId);
    List<AccessList> findAll();
    List<Object> findAllActiveAccessList();
    List<Object> findAllActiveAccessListByAccessName(String accessName);
    Boolean saveAccessList(List<AccessList> accessList);
    Boolean deleteAccessList(Long accessId,Long userId);
    AccessList findById(Long accessId);
    
}
