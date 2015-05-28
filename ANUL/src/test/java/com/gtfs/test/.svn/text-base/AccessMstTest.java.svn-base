package com.gtfs.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.gtfs.dao.impl.AccessMstDaoImpl;
import com.gtfs.dto.AccessMstDto;
import com.gtfs.service.impl.AccessMstServiceImpl;

@Ignore
public class AccessMstTest {
	
	ApplicationContext ac;
	
	@Before
	public void setUp()
	{
		ac = new FileSystemXmlApplicationContext("file:src/main/webapp/WEB-INF/applicationContext.xml");
	}
	
	@Test
	public void findAllInDao(){
		AccessMstDaoImpl accessMstDaoImpl = (AccessMstDaoImpl) ac.getBean("accessMstDaoImpl");
		
		Assert.assertEquals(accessMstDaoImpl.findAll().size(), 1);
		
		Assert.assertEquals(accessMstDaoImpl.findAll().get(0).getClass(), AccessMstDto.class);
	}
	
	@Test
	public void findAllInService(){
		AccessMstServiceImpl accessMstServiceImpl = (AccessMstServiceImpl) ac.getBean("accessMstServiceImpl");
		
		Assert.assertEquals(accessMstServiceImpl.findAll().size(), 1);
		
		Assert.assertEquals(accessMstServiceImpl.findAll().get(0).getClass(), AccessMstDto.class);
	}
	
	@Test
	public void findByIdInDao(){
		
		AccessMstDaoImpl accessMstDaoImpl = (AccessMstDaoImpl) ac.getBean("accessMstDaoImpl");

		Assert.assertNotEquals(accessMstDaoImpl.findById(1l), null);
	}
	
	
	@Test
	public void findByIdInService(){
		
		AccessMstServiceImpl accessMstServiceImpl = (AccessMstServiceImpl) ac.getBean("accessMstServiceImpl");

		Assert.assertNotEquals(accessMstServiceImpl.findById(1l), null);
		
		Assert.assertEquals(accessMstServiceImpl.findById(1l).getClass(), AccessMstDto.class);
	}
}
