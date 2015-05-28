package com.gtfs.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.gtfs.dao.impl.CustomerMstDaoImpl;
import com.gtfs.dto.CustomerMstDto;
import com.gtfs.service.impl.CustomerMstServiceImpl;
@Ignore
public class CustomerMstTest {
ApplicationContext ac;
	
	@Before
	public void setUp()
	{
		ac = new FileSystemXmlApplicationContext("file:src/main/webapp/WEB-INF/applicationContext.xml");
	}
	
	@Test
	public void findAllInDao(){
		CustomerMstDaoImpl customerMstDaoImpl = (CustomerMstDaoImpl) ac.getBean("customerMstDaoImpl");
		
		Assert.assertTrue(customerMstDaoImpl.findAll().size()> 1);
		
		Assert.assertEquals(customerMstDaoImpl.findAll().get(0).getClass(), CustomerMstDto.class);
	}
	
	
	@Test
	public void findAllInService(){
		CustomerMstServiceImpl customerMstServiceImpl = (CustomerMstServiceImpl) ac.getBean("customerMstServiceImpl");
		
		Assert.assertTrue(customerMstServiceImpl.findAll().size()>= 1);
		
		Assert.assertEquals(customerMstServiceImpl.findAll().get(0).getClass(), CustomerMstDto.class);
	}
	
	@Test
	public void findByIdInDao(){
		
		CustomerMstDaoImpl customerMstDaoImpl = (CustomerMstDaoImpl) ac.getBean("customerMstDaoImpl");

		Assert.assertNotEquals(customerMstDaoImpl.findById(1l), null);
	}
	
	
	@Test
	public void findCustomerByNameMobilePan(){
		CustomerMstDaoImpl customerMstDaoImpl = (CustomerMstDaoImpl) ac.getBean("customerMstDaoImpl");
		
		List<CustomerMstDto> testedList = customerMstDaoImpl.findAll();
		
		Assert.assertEquals(customerMstDaoImpl.findCustomerByNameMobilePan(null, null, null).size(), testedList.size());
		Assert.assertEquals(customerMstDaoImpl.findCustomerByNameMobilePan("", "", "").size(), testedList.size());
		
		Assert.assertEquals(customerMstDaoImpl.findCustomerByNameMobilePan(null, "9830098300", null).size(), 2);
		Assert.assertEquals(customerMstDaoImpl.findCustomerByNameMobilePan(null, "9830098301", null).size(), 1);
		Assert.assertEquals(customerMstDaoImpl.findCustomerByNameMobilePan("ABC", null , null).size(), 2);
		Assert.assertEquals(customerMstDaoImpl.findCustomerByNameMobilePan("ACDZ", null , null).size(), 1);
		
		List<CustomerMstDto> list = customerMstDaoImpl.findCustomerByNameMobilePan("ACDZX", null , null);
		
		Assert.assertTrue(list == null || list.size() == 0 );
		
		
		Assert.assertEquals(customerMstDaoImpl.findCustomerByNameMobilePan(null, null , "BAZPD3345R").size(), 1);
		
		Assert.assertEquals(customerMstDaoImpl.findCustomerByNameMobilePan("ACD", "9830098300" , "BAZPD3345R").size(), 1);
	}
	
	
	
	@Test
	public void findByIdInService(){
		
		CustomerMstServiceImpl customerMstServiceImpl = (CustomerMstServiceImpl) ac.getBean("customerMstServiceImpl");

		Assert.assertNotEquals(customerMstServiceImpl.findById(1l), null);
		
		Assert.assertEquals(customerMstServiceImpl.findById(1l).getClass(), CustomerMstDto.class);
	}
	
	@Test
	public void saveCustomerBookingInDao(){
		CustomerMstDaoImpl customerMstDaoImpl = (CustomerMstDaoImpl) ac.getBean("customerMstDaoImpl");
		
		//Assert.assertEquals(customerMstDaoImpl.saveCustomerBooking(1l, 1l), "true");
		
		//Assert.assertEquals(customerMstDaoImpl.saveCustomerBooking(3l, 1l), "false");
	}
}
