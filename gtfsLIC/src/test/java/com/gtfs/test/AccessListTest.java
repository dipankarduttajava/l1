package com.gtfs.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

@Ignore
public class AccessListTest {
	
	private ApplicationContext ac;
	
	
	@Before
	public void before(){
		ac = new FileSystemXmlApplicationContext("file:src/main/webapp/WEB-INF/applicationContext.xml");
	}
	
	
	@Test
	public void multiply(){
		
	}
	
	@Test
	public void findById(){
		assertEquals("Not Equals", 2,2);
	}
}
