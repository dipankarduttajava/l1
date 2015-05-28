package com.gtfs.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtfs.dao.ProjectMstDao;
import com.gtfs.dto.ProjectMstDto;
import com.gtfs.pojo.ProjectMst;
import com.gtfs.service.ProjectMstService;

@Service
public class ProjectMstServiceImpl implements ProjectMstService, Serializable {

	@Autowired
	private ProjectMstDao projectMstDao;
	
	@Override
	public List<ProjectMstDto> findAll() {
		return projectMstDao.findAll();
	}

	@Override
	public ProjectMstDto findById(Long id) {
		ProjectMst projectMst = projectMstDao.findById(id);
		ProjectMstDto projectMstDto = new ProjectMstDto();
		projectMstDto.setId(projectMst.getId());
		projectMstDto.setProjectName(projectMst.getProjectName());
		projectMstDto.setAddress(projectMst.getAddress());
		projectMstDto.setNoOfFloor(projectMst.getNoOfFloor());
		projectMstDto.setNoOfFlat(projectMst.getNoOfFlat());
		projectMstDto.setNoOfCarPark(projectMst.getNoOfCarPark());
		projectMstDto.setNoOfFlatAvlbl(projectMst.getNoOfFlatAvlbl());
		projectMstDto.setNoOfCarParkAvlbl(projectMst.getNoOfCarParkAvlbl());
		projectMstDto.setMilestoneCompltd(projectMst.getMilestoneCompltd());
		projectMstDto.setChqInFavour(projectMst.getChqInFavour());
		projectMstDto.setCreatedBy(projectMst.getCreatedBy());
		projectMstDto.setModifiedBy(projectMst.getModifiedBy());
		projectMstDto.setDeletedBy(projectMst.getDeletedBy());
		projectMstDto.setCreatedDate(projectMst.getCreatedDate());
		projectMstDto.setModifiedDate(projectMst.getModifiedDate());
		projectMstDto.setDeletedDate(projectMst.getDeletedDate());
		projectMstDto.setDeleteFlag(projectMst.getDeleteFlag());
		return projectMstDto;
	}

	@Override
	public Long insert(ProjectMstDto projectMstDto) {
		ProjectMst projectMst = new ProjectMst();
		projectMst.setId(projectMstDto.getId());
		projectMst.setProjectName(projectMstDto.getProjectName());
		projectMst.setAddress(projectMstDto.getAddress());
		projectMst.setNoOfFloor(projectMstDto.getNoOfFloor());
		projectMst.setNoOfFlat(projectMstDto.getNoOfFlat());
		projectMst.setNoOfCarPark(projectMstDto.getNoOfCarPark());
		projectMst.setNoOfFlatAvlbl(projectMstDto.getNoOfFlatAvlbl());
		projectMst.setNoOfCarParkAvlbl(projectMstDto.getNoOfCarParkAvlbl());
		projectMst.setMilestoneCompltd(projectMstDto.getMilestoneCompltd());
		projectMst.setChqInFavour(projectMstDto.getChqInFavour());
		projectMst.setCreatedBy(projectMstDto.getCreatedBy());
		projectMst.setModifiedBy(projectMstDto.getModifiedBy());
		projectMst.setDeletedBy(projectMstDto.getDeletedBy());
		projectMst.setCreatedDate(projectMstDto.getCreatedDate());
		projectMst.setModifiedDate(projectMstDto.getModifiedDate());
		projectMst.setDeletedDate(projectMstDto.getDeletedDate());
		projectMst.setDeleteFlag(projectMstDto.getDeleteFlag());
		return projectMstDao.insert(projectMst);
	}

	@Override
	public Integer update(ProjectMstDto projectMstDto) {
		ProjectMst projectMst = new ProjectMst();
		projectMst.setId(projectMstDto.getId());
		projectMst.setProjectName(projectMstDto.getProjectName());
		projectMst.setAddress(projectMstDto.getAddress());
		projectMst.setNoOfFloor(projectMstDto.getNoOfFloor());
		projectMst.setNoOfFlat(projectMstDto.getNoOfFlat());
		projectMst.setNoOfCarPark(projectMstDto.getNoOfCarPark());
		projectMst.setNoOfFlatAvlbl(projectMstDto.getNoOfFlatAvlbl());
		projectMst.setNoOfCarParkAvlbl(projectMstDto.getNoOfCarParkAvlbl());
		projectMst.setMilestoneCompltd(projectMstDto.getMilestoneCompltd());
		projectMst.setChqInFavour(projectMstDto.getChqInFavour());
		projectMst.setCreatedBy(projectMstDto.getCreatedBy());
		projectMst.setModifiedBy(projectMstDto.getModifiedBy());
		projectMst.setDeletedBy(projectMstDto.getDeletedBy());
		projectMst.setCreatedDate(projectMstDto.getCreatedDate());
		projectMst.setModifiedDate(projectMstDto.getModifiedDate());
		projectMst.setDeletedDate(projectMstDto.getDeletedDate());
		projectMst.setDeleteFlag(projectMstDto.getDeleteFlag());
		return projectMstDao.update(projectMst);
	}

	@Override
	public Integer delete(ProjectMstDto projectMstDto) {
		ProjectMst projectMst = new ProjectMst();
		projectMst.setId(projectMstDto.getId());
		projectMst.setProjectName(projectMstDto.getProjectName());
		projectMst.setAddress(projectMstDto.getAddress());
		projectMst.setNoOfFloor(projectMstDto.getNoOfFloor());
		projectMst.setNoOfFlat(projectMstDto.getNoOfFlat());
		projectMst.setNoOfCarPark(projectMstDto.getNoOfCarPark());
		projectMst.setNoOfFlatAvlbl(projectMstDto.getNoOfFlatAvlbl());
		projectMst.setNoOfCarParkAvlbl(projectMstDto.getNoOfCarParkAvlbl());
		projectMst.setMilestoneCompltd(projectMstDto.getMilestoneCompltd());
		projectMst.setChqInFavour(projectMstDto.getChqInFavour());
		projectMst.setCreatedBy(projectMstDto.getCreatedBy());
		projectMst.setModifiedBy(projectMstDto.getModifiedBy());
		projectMst.setDeletedBy(projectMstDto.getDeletedBy());
		projectMst.setCreatedDate(projectMstDto.getCreatedDate());
		projectMst.setModifiedDate(projectMstDto.getModifiedDate());
		projectMst.setDeletedDate(projectMstDto.getDeletedDate());
		projectMst.setDeleteFlag(projectMstDto.getDeleteFlag());
		return projectMstDao.delete(projectMst);	
	}

	@Override
	public Boolean saveOrUpdate(ProjectMstDto projectMstDto) {
		
		ProjectMst projectMst = new ProjectMst();
		projectMst.setId(projectMstDto.getId());
		projectMst.setProjectName(projectMstDto.getProjectName());
		projectMst.setAddress(projectMstDto.getAddress());
		projectMst.setNoOfFloor(projectMstDto.getNoOfFloor());
		projectMst.setNoOfFlat(projectMstDto.getNoOfFlat());
		projectMst.setNoOfCarPark(projectMstDto.getNoOfCarPark());
		projectMst.setNoOfFlatAvlbl(projectMstDto.getNoOfFlatAvlbl());
		projectMst.setNoOfCarParkAvlbl(projectMstDto.getNoOfCarParkAvlbl());
		projectMst.setMilestoneCompltd(projectMstDto.getMilestoneCompltd());
		projectMst.setChqInFavour(projectMstDto.getChqInFavour());
		projectMst.setCreatedBy(projectMstDto.getCreatedBy());
		projectMst.setModifiedBy(projectMstDto.getModifiedBy());
		projectMst.setDeletedBy(projectMstDto.getDeletedBy());
		projectMst.setCreatedDate(projectMstDto.getCreatedDate());
		projectMst.setModifiedDate(projectMstDto.getModifiedDate());
		projectMst.setDeletedDate(projectMstDto.getDeletedDate());
		projectMst.setDeleteFlag(projectMstDto.getDeleteFlag());
		
		return projectMstDao.saveOrUpdate(projectMst);
	}

}
