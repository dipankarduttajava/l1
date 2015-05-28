package com.gtfs.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.gtfs.dao.impl.AgentMstDaoImpl;

@Ignore
public class AgentMstTest {
private ApplicationContext ac;
	
	
	@Before
	public void before(){
		ac = new FileSystemXmlApplicationContext("file:src/main/webapp/WEB-INF/applicationContext.xml");
	}
	
	@Test
	public void findByName(){
		AgentMstDaoImpl agentMstDaoImpl = (AgentMstDaoImpl) ac.getBean("agentMstDaoImpl");
		
		Assert.assertEquals(agentMstDaoImpl.findByName("ABCD").size(),2);
		
	}
	
	
	public void findByAgCode(){
		AgentMstDaoImpl agentMstDaoImpl = (AgentMstDaoImpl) ac.getBean("agentMstDaoImpl");
		
		Assert.assertTrue(agentMstDaoImpl.findByAgCode(2580l) !=null);
		
		
	}
	
}
