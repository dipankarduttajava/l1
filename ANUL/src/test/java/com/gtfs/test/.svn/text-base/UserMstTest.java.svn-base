package com.gtfs.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.gtfs.dao.impl.UserMstDaoImpl;
import com.gtfs.dto.UserMstDto;
import com.gtfs.service.impl.UserMstServiceImpl;

@Ignore
public class UserMstTest {
	ApplicationContext ac;
	
	@Before
	public void setUp()
	{
		ac = new FileSystemXmlApplicationContext("file:src/main/webapp/WEB-INF/applicationContext.xml");
	}
	
	@Test
	public void findAllInDao(){
		UserMstDaoImpl userMstDaoImpl = (UserMstDaoImpl) ac.getBean("userMstDaoImpl");
		
		Assert.assertNotEquals(userMstDaoImpl.findAll(), null);
		
		Assert.assertEquals(userMstDaoImpl.findAll().get(0).getClass(), UserMstDto.class);
	}
	
	@Test
	public void findByIdInDao(){
		
		UserMstDaoImpl userMstDaoImpl = (UserMstDaoImpl) ac.getBean("userMstDaoImpl");
		
		Assert.assertNotEquals(userMstDaoImpl.findById(1l), null);
	}
	
	@Test
	public void findAllInService(){
		UserMstServiceImpl userMstServiceImpl = (UserMstServiceImpl) ac.getBean("userMstServiceImpl");
		
		Assert.assertNotEquals(userMstServiceImpl.findAll(), null);
		
		Assert.assertEquals(userMstServiceImpl.findAll().get(0).getClass(), UserMstDto.class);
	}
	
	@Test
	public void findByIdInService(){
		
		UserMstServiceImpl userMstServiceImpl = (UserMstServiceImpl) ac.getBean("userMstServiceImpl");
		
		Assert.assertNotEquals(userMstServiceImpl.findById(1l), null);
		
		Assert.assertEquals(userMstServiceImpl.findById(1l).getClass(), UserMstDto.class);
	}
}
