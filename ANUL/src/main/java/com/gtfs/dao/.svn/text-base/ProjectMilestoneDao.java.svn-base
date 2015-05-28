package com.gtfs.dao;

import java.util.Date;
import java.util.List;

import com.gtfs.dto.ProjectMilestoneDto;
import com.gtfs.pojo.ProjectMilestone;

public interface ProjectMilestoneDao {
	List<ProjectMilestoneDto> findAll();
	ProjectMilestone findById(Long id);
	List<ProjectMilestoneDto> findByProjectId(Long projectId);
	List<ProjectMilestoneDto> findFlatSpecificByProjectId(Long projectId);
	List<ProjectMilestoneDto> findNextProjectSpcMilstoneByProjectId(Long projectId);
	String updateProjectMilestone(Long projectId, Long milestoneId,Date dateGiven);
}
