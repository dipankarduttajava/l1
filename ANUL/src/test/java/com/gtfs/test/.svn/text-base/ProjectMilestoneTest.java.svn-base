package com.gtfs.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.gtfs.dao.impl.ProjectMilestoneDaoImpl;
import com.gtfs.dto.ProjectMilestoneDto;
import com.gtfs.service.impl.ProjectMilestoneServiceImpl;

public class ProjectMilestoneTest {
	ApplicationContext ac;
	
	@Before
	public void setUp()
	{
		ac = new FileSystemXmlApplicationContext("file:src/main/webapp/WEB-INF/applicationContext.xml");
	}
	
	@Test
	public void findAllInDao(){
		ProjectMilestoneDaoImpl projectMilestoneDaoImpl = (ProjectMilestoneDaoImpl) ac.getBean("projectMilestoneDaoImpl");
		
		Assert.assertTrue(projectMilestoneDaoImpl.findAll().size()>= 1);
		
		Assert.assertEquals(projectMilestoneDaoImpl.findAll().get(0).getClass(), ProjectMilestoneDto.class);
	}
	
	@Test
	public void findByIdInDao(){
		
		ProjectMilestoneDaoImpl projectMilestoneDaoImpl = (ProjectMilestoneDaoImpl) ac.getBean("projectMilestoneDaoImpl");

		Assert.assertNotEquals(projectMilestoneDaoImpl.findById(1l), null);
	}
	
	
	
	@Test
	public void findAllInService(){
		ProjectMilestoneServiceImpl projectMilestoneServiceImpl = (ProjectMilestoneServiceImpl) ac.getBean("projectMilestoneServiceImpl");
		
		Assert.assertTrue(projectMilestoneServiceImpl.findAll().size()>= 1);
		
		Assert.assertEquals(projectMilestoneServiceImpl.findAll().get(0).getClass(), ProjectMilestoneDto.class);
	}
	
	
	
	@Test
	public void findByIdInService(){
		
		ProjectMilestoneServiceImpl projectMilestoneServiceImpl = (ProjectMilestoneServiceImpl) ac.getBean("projectMilestoneServiceImpl");

		Assert.assertNotEquals(projectMilestoneServiceImpl.findById(1l), null);
		
		Assert.assertEquals(projectMilestoneServiceImpl.findById(1l).getClass(), ProjectMilestoneDto.class);
	}
	
	@Test
	public void findByProjectIdInService(){
		ProjectMilestoneServiceImpl projectMilestoneServiceImpl = (ProjectMilestoneServiceImpl) ac.getBean("projectMilestoneServiceImpl");
		
		Assert.assertTrue(projectMilestoneServiceImpl.findByProjectId(1l).size()>= 1);
		
		Assert.assertEquals(projectMilestoneServiceImpl.findByProjectId(1l).get(0).getClass(), ProjectMilestoneDto.class);
		
	}
	
	@Test
	public void findFlatSpecificByProjectIdInService(){
		ProjectMilestoneServiceImpl projectMilestoneServiceImpl = (ProjectMilestoneServiceImpl) ac.getBean("projectMilestoneServiceImpl");
		
		Assert.assertTrue(projectMilestoneServiceImpl.findFlatSpecificByProjectId(1l).size()== 2);
		
		Assert.assertEquals(projectMilestoneServiceImpl.findFlatSpecificByProjectId(1l).get(0).getClass(), ProjectMilestoneDto.class);
	}
	
	@Test
	public void findNextProjectSpcMilstoneByProjectIdInService(){
		ProjectMilestoneServiceImpl projectMilestoneServiceImpl = (ProjectMilestoneServiceImpl) ac.getBean("projectMilestoneServiceImpl");
		
		Assert.assertTrue(projectMilestoneServiceImpl.findNextProjectSpcMilstoneByProjectId(1l).size()== 1);
		Assert.assertEquals(projectMilestoneServiceImpl.findNextProjectSpcMilstoneByProjectId(1l).get(0).getClass(), ProjectMilestoneDto.class);
	}
}
