package com.gtfs.test;

import java.nio.charset.Charset;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.gtfs.dao.impl.ProjectMstDaoImpl;
import com.gtfs.dto.ProjectMstDto;
import com.gtfs.json.ProjectMstJson;
import com.gtfs.pojo.ProjectMst;
import com.gtfs.service.impl.ProjectMstServiceImpl;
@Ignore
public class ProjectMstTest {
	
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
				MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8")); 
	
	private final String DATA_REQUEST = "{\"projectName\":\"XYZ\",\"deleteFlag\":\"N\"}";
	private MockMvc mockMvc;
	ApplicationContext ac;
	
	@Before
	public void setUp(){
		ac = new FileSystemXmlApplicationContext("file:src/main/webapp/WEB-INF/applicationContext.xml");
	}
	
	@Ignore
	@Test
	public void insertInJson()throws Exception{
		ProjectMstJson projectMstJson = (ProjectMstJson) ac.getBean("projectMstJson");
		this.mockMvc = MockMvcBuilders.standaloneSetup(projectMstJson).build(); 
		
		this.mockMvc.perform(MockMvcRequestBuilders.post("/projectMst/insert.html").content(DATA_REQUEST).contentType(MediaType.ALL).accept(MediaType.ALL))
		.andExpect(MockMvcResultMatchers.status().isOk());
//		.andExpect(jsonPath("$id", is("e1")))
//		.andExpect(jsonPath("$name", is("ename"))); 
	}
	
	@Ignore
	@Test
	public void findAllInDao(){
		ProjectMstDaoImpl projectMstDaoImpl = (ProjectMstDaoImpl) ac.getBean("projectMstDaoImpl");
		
		Assert.assertTrue(projectMstDaoImpl.findAll().size() >= 1);
		
		Assert.assertEquals(projectMstDaoImpl.findAll().get(0).getClass(), ProjectMstDto.class);
	}
	@Ignore
	@Test
	public void insertInDao(){
		ProjectMstDaoImpl projectMstDaoImpl = (ProjectMstDaoImpl) ac.getBean("projectMstDaoImpl");
		
		ProjectMst projectMst = new ProjectMst();
		projectMst.setProjectName("Test");
		projectMst.setAddress("Kolkata");
		projectMst.setDeleteFlag("N");
		
		
		Long row = projectMstDaoImpl.insert(projectMst);
		Assert.assertTrue(row >= 1);
	}
	
	@Ignore
	@Test
	public void updateInDao(){
		ProjectMstDaoImpl projectMstDaoImpl = (ProjectMstDaoImpl) ac.getBean("projectMstDaoImpl");
		
		ProjectMst projectMst = new ProjectMst();
		projectMst.setId(1l);;
		projectMst.setProjectName("Updated1 Test");
		projectMst.setAddress("Kolkata");
		projectMst.setDeleteFlag("N");
		
		
		Integer row = projectMstDaoImpl.update(projectMst);
		Assert.assertTrue(row >= 1);
	}
	
	
	@Test
	public void deleteInDao(){
		ProjectMstDaoImpl projectMstDaoImpl = (ProjectMstDaoImpl) ac.getBean("projectMstDaoImpl");		
		ProjectMst projectMst = new ProjectMst();
		projectMst.setId(1l);	
		
		Integer row = projectMstDaoImpl.delete(projectMst);
		Assert.assertTrue(row >= 1);
	}
	
	
	
	
	/*@Test
	public void insertInService(){
		ProjectMstServiceImpl projectMstServiceImpl = (ProjectMstServiceImpl) ac.getBean("projectMstServiceImpl");
		
		ProjectMstDto projectMstDto = new ProjectMstDto();
		projectMstDto.setProjectName("Test");
		projectMstDto.setAddress("Kolkata");
		projectMstDto.setDeleteFlag("N");
		
		
		Long row = projectMstServiceImpl.insert(projectMst);
		Assert.assertTrue(row >= 1);
	}*/
	
	
	@Ignore
	@Test
	public void findAllInService(){
		ProjectMstServiceImpl projectMstServiceImpl = (ProjectMstServiceImpl) ac.getBean("projectMstServiceImpl");
		
		Assert.assertTrue(projectMstServiceImpl.findAll().size() >= 1);
		
		Assert.assertEquals(projectMstServiceImpl.findAll().get(0).getClass(), ProjectMstDto.class);
	}
	@Ignore
	@Test
	public void findByIdInDao(){		
		ProjectMstDaoImpl projectMstDaoImpl = (ProjectMstDaoImpl) ac.getBean("projectMstDaoImpl");
		Assert.assertNotEquals(projectMstDaoImpl.findById(1l), null);		
		Assert.assertEquals(projectMstDaoImpl.findById(1l).getClass(), ProjectMst.class);
	}
	
	@Ignore
	@Test
	public void findByIdInService(){		
		ProjectMstServiceImpl projectMstServiceImpl = (ProjectMstServiceImpl) ac.getBean("projectMstServiceImpl");
		Assert.assertNotEquals(projectMstServiceImpl.findById(1l), null);		
		Assert.assertEquals(projectMstServiceImpl.findById(1l).getClass(), ProjectMstDto.class);
	}
}
