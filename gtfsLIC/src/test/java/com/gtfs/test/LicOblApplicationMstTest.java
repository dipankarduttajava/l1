package com.gtfs.test;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.gtfs.dao.impl.LicOblApplicationMstDaoImpl;
import com.gtfs.dto.LicOblApplicationMstDto;

public class LicOblApplicationMstTest {
	
	private ApplicationContext ac;
	
	
	@Before
	public void before(){
		ac = new FileSystemXmlApplicationContext("file:src/main/webapp/WEB-INF/applicationContext.xml");
	}
	
	
	@Test
	public void findBusinessReportByBusinessDate(){
		
		Calendar from = Calendar.getInstance();
		from.set(Calendar.YEAR,2015);
		from.set(Calendar.MONTH,4);
		from.set(Calendar.DATE,17);
		
		Calendar to = Calendar.getInstance();
		to.set(Calendar.YEAR,2015);
		to.set(Calendar.MONTH,4);
		to.set(Calendar.DATE,20);
		
		
		LicOblApplicationMstDaoImpl licOblApplicationMstDaoImpl = (LicOblApplicationMstDaoImpl) ac.getBean("licOblApplicationMstDaoImpl");
		
		Assert.assertEquals(licOblApplicationMstDaoImpl.findBusinessReportByBusinessDate(from.getTime(),to.getTime()).size(),1);
		
		Assert.assertEquals(licOblApplicationMstDaoImpl.findBusinessReportByBusinessDate(from.getTime(),to.getTime()).get(0).getClass(),
				LicOblApplicationMstDto.class);
	}
}
