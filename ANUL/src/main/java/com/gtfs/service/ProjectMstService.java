package com.gtfs.service;

import java.util.List;

import com.gtfs.dto.ProjectMstDto;
import com.gtfs.pojo.ProjectMst;

public interface ProjectMstService {
	List<ProjectMstDto> findAll();
	ProjectMstDto findById(Long id);
	Long insert(ProjectMstDto projectMstDto); // return autoincremented value
	Integer update(ProjectMstDto projectMstDto); // return no of rows updated
	Integer delete(ProjectMstDto projectMstDto);
	Boolean saveOrUpdate(ProjectMstDto projectMstDto);
}
