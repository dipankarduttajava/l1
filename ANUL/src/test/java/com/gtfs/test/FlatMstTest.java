package com.gtfs.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.gtfs.dao.impl.FlatMstDaoImpl;
import com.gtfs.dto.FlatMstDto;
import com.gtfs.service.impl.FlatMstServiceImpl;
@Ignore
public class FlatMstTest {
	ApplicationContext ac;
	
	@Before
	public void setUp()
	{
		ac = new FileSystemXmlApplicationContext("file:src/main/webapp/WEB-INF/applicationContext.xml");
	}
	
	@Test
	public void findAllInDao(){
		FlatMstDaoImpl flatMstDaoImpl = (FlatMstDaoImpl) ac.getBean("flatMstDaoImpl");
		
		Assert.assertTrue(flatMstDaoImpl.findAll().size()>= 1);
		
		Assert.assertEquals(flatMstDaoImpl.findAll().get(0).getClass(), FlatMstDto.class);
	}
	
	@Test
	public void findByIdInDao(){
		
		FlatMstDaoImpl flatMstDaoImpl = (FlatMstDaoImpl) ac.getBean("flatMstDaoImpl");

		Assert.assertNotEquals(flatMstDaoImpl.findById(1l), null);
	}
	
	
	
	@Test
	public void findAllInService(){
		FlatMstServiceImpl flatMstServiceImpl = (FlatMstServiceImpl) ac.getBean("flatMstServiceImpl");
		
		Assert.assertTrue(flatMstServiceImpl.findAll().size()>= 1);
		
		Assert.assertEquals(flatMstServiceImpl.findAll().get(0).getClass(), FlatMstDto.class);
	}
	
	
	
	@Test
	public void findByIdInService(){
		
		FlatMstServiceImpl flatMstServiceImpl = (FlatMstServiceImpl) ac.getBean("flatMstServiceImpl");

		Assert.assertNotEquals(flatMstServiceImpl.findById(1l), null);
		
		Assert.assertEquals(flatMstServiceImpl.findById(1l).getClass(), FlatMstDto.class);
	}
	
	@Test
	public void findByProjectIdFlatNoInDao(){
		FlatMstDaoImpl flatMstDaoImpl = (FlatMstDaoImpl) ac.getBean("flatMstDaoImpl");
		
		Assert.assertEquals(flatMstDaoImpl.findByProjectIdFlatNo(null, null).size(), flatMstDaoImpl.findAll().size());
		
		Assert.assertEquals(flatMstDaoImpl.findByProjectIdFlatNo(null, "").size(), flatMstDaoImpl.findAll().size());
		
		Assert.assertEquals(flatMstDaoImpl.findByProjectIdFlatNo(1l, "1A").size(), 1);
		Assert.assertTrue(flatMstDaoImpl.findByProjectIdFlatNo(1l, "1AA") == null || flatMstDaoImpl.findByProjectIdFlatNo(1l, "1AA").size()==0);
		Assert.assertTrue(flatMstDaoImpl.findByProjectIdFlatNo(3l, "1A") == null || flatMstDaoImpl.findByProjectIdFlatNo(3l, "1A").size()==0);
		Assert.assertEquals(flatMstDaoImpl.findByProjectIdFlatNo(1l, "1A").get(0).getProjectName(), "52 B , RAJA BASANTA ROY ROAD" );
	}
	
	
	
}
