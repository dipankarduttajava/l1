package com.gtfs.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.gtfs.dao.impl.RoleAccessComboDaoImpl;
import com.gtfs.dto.RoleAccessComboDto;
import com.gtfs.service.impl.RoleAccessComboServiceImpl;

@Ignore
public class RoleAccessComboTest {
	ApplicationContext ac;
	
	@Before
	public void setUp()
	{
		ac = new FileSystemXmlApplicationContext("file:src/main/webapp/WEB-INF/applicationContext.xml");
	}
	
	@Test
	public void findAllInDao(){
		RoleAccessComboDaoImpl roleAccessComboDaoImpl = (RoleAccessComboDaoImpl) ac.getBean("roleAccessComboDaoImpl");
		
		Assert.assertNotEquals(roleAccessComboDaoImpl.findAll(), null);
		
		Assert.assertEquals(roleAccessComboDaoImpl.findAll().get(0).getClass(), RoleAccessComboDto.class);
	}
	
	@Test
	public void findByIdInDao(){
		
		RoleAccessComboDaoImpl roleAccessComboDaoImpl = (RoleAccessComboDaoImpl) ac.getBean("roleAccessComboDaoImpl");

		Assert.assertNotEquals(roleAccessComboDaoImpl.findById(1l), null);
	}
	
	@Test
	public void findAllInService(){
		RoleAccessComboServiceImpl roleAccessComboServiceImpl = (RoleAccessComboServiceImpl) ac.getBean("roleAccessComboServiceImpl");
		
		Assert.assertNotEquals(roleAccessComboServiceImpl.findAll(), null);
		
		Assert.assertEquals(roleAccessComboServiceImpl.findAll().get(0).getClass(), RoleAccessComboDto.class);
	}
	
	@Test
	public void findByIdInService(){
		
		RoleAccessComboServiceImpl roleAccessComboServiceImpl = (RoleAccessComboServiceImpl) ac.getBean("roleAccessComboServiceImpl");

		Assert.assertNotEquals(roleAccessComboServiceImpl.findById(1l), null);
		
		Assert.assertEquals(roleAccessComboServiceImpl.findById(1l).getClass(), RoleAccessComboDto.class);
	}
}
