package com.gtfs.service;

import java.util.Date;
import java.util.List;

import com.gtfs.dto.ProjectMilestoneDto;

public interface ProjectMilestoneService {
	List<ProjectMilestoneDto> findAll();
	ProjectMilestoneDto findById(Long id);
	List<ProjectMilestoneDto> findByProjectId(Long projectId);
	List<ProjectMilestoneDto> findFlatSpecificByProjectId(Long projectId);
	List<ProjectMilestoneDto> findNextProjectSpcMilstoneByProjectId(Long projectId);
	String updateProjectMilestone(Long projectId, Long milestoneId,Date dateGiven);

}
