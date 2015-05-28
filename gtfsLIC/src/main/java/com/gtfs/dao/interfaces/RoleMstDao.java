package com.gtfs.dao.interfaces;

import java.util.List;

import com.gtfs.bean.RoleMst;

public interface RoleMstDao {
	List<RoleMst> findAllActiveRole();
	List<RoleMst> findRoleByRoleName(String roleName);
	RoleMst findRoleById(Long roleId);
	Boolean deleteRoleMst(Long roleId, Long userId);
	Boolean saveRoleMst(List<RoleMst> roleMstList);
}
