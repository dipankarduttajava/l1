package com.gtfs.service.interfaces;

import java.io.Serializable;
import java.util.List;

import com.gtfs.bean.RoleMst;


public interface RoleMstService extends Serializable{
	List<RoleMst> findAllActiveRole();
	RoleMst findRoleById(Long roleId);
	List<RoleMst> findRoleByRoleName(String roleName);
	Boolean deleteRoleMst(Long roleId, Long userId);
	Boolean saveRoleMst(List<RoleMst> roleMstList);
	
}
