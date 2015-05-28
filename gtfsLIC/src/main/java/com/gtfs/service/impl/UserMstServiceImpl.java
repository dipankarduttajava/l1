package com.gtfs.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.gtfs.bean.UserLoginDtls;
import com.gtfs.bean.UserMst;
import com.gtfs.dao.interfaces.UserLoginDtlsDao;
import com.gtfs.dao.interfaces.UserMstDao;
import com.gtfs.service.interfaces.UserMstService;

@Service
public class UserMstServiceImpl implements UserMstService, Serializable{
	@Autowired
	private UserMstDao userMstDao;
	@Autowired
	private UserLoginDtlsDao userLoginDtlsDao;
	
	 public List<UserMst> login(String loginId, String password) {
	        return userMstDao.login(loginId,password);
	 }
	 
	 public Boolean doChangePasswordProcess(Long userId, Long branchId, String newPassword) {

	        UserLoginDtls userLoginDtls = new UserLoginDtls();
	        userLoginDtls.setUserid(userId);
	        userLoginDtls.setLoginTime(new Date());
	        userLoginDtls.setBranchId(branchId);
	        
	        UserMst userMst = userMstDao.findById(userId);
	        userMst.setPasswd(newPassword);
	       
	        Boolean updateStatus = userMstDao.update(userMst);
	        Long uldId = userLoginDtlsDao.save(userLoginDtls);

	        if (updateStatus && uldId > 0) {
	            return true;
	        } else {
	            return false;
	        }

	    }
	 
	 public List<UserMst> findActiveUserByUserId(Long userId) {
		 return userMstDao.findActiveUserByUserId(userId);
	 }
	 
	 public List<UserMst> findActiveUserInfoByUserId(Long userId){
		 return userMstDao.findActiveUserInfoByUserId(userId);
	 }
	 
	 public List<UserMst> findActiveUserInfoByUserName(String userName) {
		 return userMstDao.findActiveUserInfoByUserName(userName);
	 }
	 
	 public List<UserMst> findAllActiveUserInfo() {
		 return userMstDao.findAllActiveUserInfo();
	 }
	 
	 public UserMst findById(Long userId) {
		 return userMstDao.findById(userId);
	 }
	 
	 public Boolean deleteUserMst(Long userId, Long userLoginId){
			return userMstDao.deleteUserMst(userId, userLoginId);
		}
	 
}
