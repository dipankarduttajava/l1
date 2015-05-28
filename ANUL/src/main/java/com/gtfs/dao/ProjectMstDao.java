package com.gtfs.dao;

import java.util.List;

import com.gtfs.dto.ProjectMstDto;
import com.gtfs.pojo.ProjectMst;

public interface ProjectMstDao {
	List<ProjectMstDto> findAll();
	ProjectMst findById(Long id);
	Long insert(ProjectMst projectMst); // return autoincremented value
	Integer update(ProjectMst projectMst); // return no of rows updated
	Integer delete(ProjectMst projectMst);
	Boolean saveOrUpdate(ProjectMst projectMst);
}
