package com.gtfs.controller.json;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gtfs.bean.AccessList;
import com.gtfs.controller.constants.AccessListConstant;
import com.gtfs.dao.interfaces.AccessListDao;

@Controller
public class AccessListControllerJson {
	@Autowired
	private AccessListDao accessListDao;

	
	@RequestMapping(value = AccessListConstant.FIND_ALL, method = RequestMethod.GET)
    public @ResponseBody List<AccessList> findAll() {
			 List<AccessList> list = new ArrayList<AccessList>();
		        
		        for(AccessList obj:accessListDao.findAll()){
		        	AccessList accessList = new AccessList();
		            accessList.setAccessId(obj.getAccessId());
		            accessList.setAccessName(obj.getAccessName());
		            accessList.setUrlName(obj.getUrlName());
		            accessList.setCreatedBy(obj.getCreatedBy());
		            accessList.setModifiedBy(obj.getModifiedBy());
		            accessList.setDeletedBy(obj.getDeletedBy());
		            accessList.setDeleteFlag(obj.getDeleteFlag());
		            accessList.setCreatedDate(obj.getCreatedDate());
		            accessList.setModifiedDate(obj.getModifiedDate());
		            accessList.setDeletedDate(obj.getDeletedDate());
		            list.add(accessList);
		        }

		        return list;
    }
	@RequestMapping(value = AccessListConstant.FIND_BY_ID, method = RequestMethod.GET)
	public @ResponseBody AccessList findById(@PathVariable("id") Long accessId) {
		AccessList obj = accessListDao.findById(accessId);
		AccessList accessList = new AccessList();
		accessList.setAccessId(obj.getAccessId());
        accessList.setAccessName(obj.getAccessName());
        accessList.setUrlName(obj.getUrlName());
        accessList.setCreatedBy(obj.getCreatedBy());
        accessList.setModifiedBy(obj.getModifiedBy());
        accessList.setDeletedBy(obj.getDeletedBy());
        accessList.setDeleteFlag(obj.getDeleteFlag());
        accessList.setCreatedDate(obj.getCreatedDate());
        accessList.setModifiedDate(obj.getModifiedDate());
        accessList.setDeletedDate(obj.getDeletedDate());
         
		return accessList;
	}
}
