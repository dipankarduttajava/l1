package com.gtfs.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtfs.dao.ProjectMilestoneDao;
import com.gtfs.dto.ProjectMilestoneDto;
import com.gtfs.pojo.ProjectMilestone;
import com.gtfs.service.ProjectMilestoneService;

@Service
public class ProjectMilestoneServiceImpl implements ProjectMilestoneService,Serializable {
	
	@Autowired
	private ProjectMilestoneDao projectMilestoneDao;

	@Override
	public List<ProjectMilestoneDto> findAll() {
		return projectMilestoneDao.findAll();
	}

	@Override
	public ProjectMilestoneDto findById(Long id) {
		
		ProjectMilestone projectMilestone = projectMilestoneDao.findById(id);
		
		ProjectMilestoneDto projectMilestoneDto = new ProjectMilestoneDto();
		projectMilestoneDto.setId(projectMilestone.getId());
		projectMilestoneDto.setProjectMst(projectMilestone.getProjectMst().getId());
		projectMilestoneDto.setMilestoneSrlNo(projectMilestone.getMilestoneSrlNo());
		projectMilestoneDto.setMilestoneDesc(projectMilestone.getMilestoneDesc());
		projectMilestoneDto.setMilestoneScope(projectMilestone.getMilestoneScope());
		projectMilestoneDto.setMilestoneStatus(projectMilestone.getMilestoneStatus());
		projectMilestoneDto.setPaymentPerc(projectMilestone.getPaymentPerc());
		projectMilestoneDto.setCreatedBy(projectMilestone.getCreatedBy());
		projectMilestoneDto.setModifiedBy(projectMilestone.getModifiedBy());
		projectMilestoneDto.setDeletedBy(projectMilestone.getDeletedBy());
		projectMilestoneDto.setCreatedDate(projectMilestone.getCreatedDate());
		projectMilestoneDto.setModifiedDate(projectMilestone.getModifiedDate());
		projectMilestoneDto.setDeletedDate(projectMilestone.getDeletedDate());
		projectMilestoneDto.setDeleteFlag(projectMilestone.getDeleteFlag());
		return projectMilestoneDto;
	}

	@Override
	public List<ProjectMilestoneDto> findByProjectId(Long projectId) {
		return projectMilestoneDao.findByProjectId(projectId);
	}

	@Override
	public List<ProjectMilestoneDto> findFlatSpecificByProjectId(Long projectId) {
		return projectMilestoneDao.findFlatSpecificByProjectId(projectId);
	}

	@Override
	public List<ProjectMilestoneDto> findNextProjectSpcMilstoneByProjectId(Long projectId) {
		return projectMilestoneDao.findNextProjectSpcMilstoneByProjectId(projectId);
	}

	@Override
	public String updateProjectMilestone(Long projectId, Long milestoneId,Date dateGiven) {
		return projectMilestoneDao.updateProjectMilestone(projectId,milestoneId,dateGiven);
	}

}
