package com.gtfs.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.gtfs.dao.impl.RoleMstDaoImpl;
import com.gtfs.dto.RoleMstDto;
import com.gtfs.service.impl.RoleMstServiceImpl;

@Ignore
public class RoleMstTest {
	ApplicationContext ac;
	
	@Before
	public void setUp()
	{
		ac = new FileSystemXmlApplicationContext("file:src/main/webapp/WEB-INF/applicationContext.xml");
	}
	
	@Test
	public void findAllInDao(){
		RoleMstDaoImpl roleMstDaoImpl = (RoleMstDaoImpl) ac.getBean("roleMstDaoImpl");
		
		Assert.assertNotEquals(roleMstDaoImpl.findAll(), null);
		
		Assert.assertEquals(roleMstDaoImpl.findAll().get(0).getClass(), RoleMstDto.class);
	}
	
	@Test
	public void findByIdInDao(){
		
		RoleMstDaoImpl roleMstDaoImpl = (RoleMstDaoImpl) ac.getBean("roleMstDaoImpl");

		Assert.assertNotEquals(roleMstDaoImpl.findById(1l), null);
	}
	
	@Test
	public void findAllInService(){
		RoleMstServiceImpl roleMstServiceImpl = (RoleMstServiceImpl) ac.getBean("roleMstServiceImpl");
		
		Assert.assertNotEquals(roleMstServiceImpl.findAll(), null);
		
		Assert.assertEquals(roleMstServiceImpl.findAll().get(0).getClass(), RoleMstDto.class);
	}
	
	@Test
	public void findByIdInService(){
		
		RoleMstServiceImpl roleMstServiceImpl = (RoleMstServiceImpl) ac.getBean("roleMstServiceImpl");

		Assert.assertNotEquals(roleMstServiceImpl.findById(1l), null);
		
		Assert.assertEquals(roleMstServiceImpl.findById(1l).getClass(), RoleMstDto.class);
	}
}
