package com.gtfs.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.gtfs.dao.impl.UserRoleComboDaoImpl;
import com.gtfs.dto.UserRoleComboDto;
import com.gtfs.service.impl.UserRoleComboServiceImpl;

@Ignore
public class UserRoleComboTest {
	ApplicationContext ac;
	
	@Before
	public void setUp(){
		ac = new FileSystemXmlApplicationContext("file:src/main/webapp/WEB-INF/applicationContext.xml");
	}
	
	@Test
	public void findAllInDao(){
		UserRoleComboDaoImpl userRoleComboDaoImpl = (UserRoleComboDaoImpl) ac.getBean("userRoleComboDaoImpl");
		
		Assert.assertNotEquals(userRoleComboDaoImpl.findAll(), null);
		
		Assert.assertEquals(userRoleComboDaoImpl.findAll().get(0).getClass(), UserRoleComboDto.class);
	}
	
	@Test
	public void findByIdInDao(){
		
		UserRoleComboDaoImpl userRoleComboDaoImpl = (UserRoleComboDaoImpl) ac.getBean("userRoleComboDaoImpl");

		Assert.assertNotEquals(userRoleComboDaoImpl.findById(1l), null);
	}
	
	
	@Test
	public void findAllInService(){
		UserRoleComboServiceImpl userRoleComboServiceImpl = (UserRoleComboServiceImpl) ac.getBean("userRoleComboServiceImpl");
		
		Assert.assertNotEquals(userRoleComboServiceImpl.findAll(), null);
		
		Assert.assertEquals(userRoleComboServiceImpl.findAll().get(0).getClass(), UserRoleComboDto.class);
	}
	
	@Test
	public void findByIdInService(){
		
		UserRoleComboServiceImpl userRoleComboServiceImpl = (UserRoleComboServiceImpl) ac.getBean("userRoleComboServiceImpl");

		Assert.assertNotEquals(userRoleComboServiceImpl.findById(1l), null);
		
		Assert.assertEquals(userRoleComboServiceImpl.findById(1l).getClass(), UserRoleComboDto.class);
	}
}
