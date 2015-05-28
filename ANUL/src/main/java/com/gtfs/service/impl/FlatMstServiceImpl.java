package com.gtfs.service.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtfs.dao.FlatMstDao;
import com.gtfs.dao.ProjectMstDao;
import com.gtfs.dto.FlatMstDto;
import com.gtfs.pojo.FlatMst;
import com.gtfs.service.FlatMstService;

@Service
public class FlatMstServiceImpl implements FlatMstService, Serializable {
	private Logger log = Logger.getLogger(FlatMstServiceImpl.class);
	
	@Autowired
	private FlatMstDao flatMstDao;
	@Autowired
	private ProjectMstDao projectMstDao;
	
	@Override
	public List<FlatMstDto> findAll() {
		return flatMstDao.findAll();
	}

	@Override
	public FlatMstDto findById(Long id) {
		try{
			FlatMst flatMst = flatMstDao.findById(id);
			FlatMstDto flatMstDto = new FlatMstDto();
			flatMstDto.setId(flatMst.getId());
			flatMstDto.setProjectMstId(flatMst.getProjectMst().getId());
			//flatMstDto.setProjectName(flatMst.getProjectMst().getProjectName());
			flatMstDto.setFlatNo(flatMst.getFlatNo());
			flatMstDto.setFlatDesc(flatMst.getFlatDesc());
			flatMstDto.setFlatFacing(flatMst.getFlatFacing());
			flatMstDto.setFloorName(flatMst.getFloorName());
			flatMstDto.setBuiltUpArea(flatMst.getBuiltUpArea());
			flatMstDto.setSprBuiltUpArea(flatMst.getSprBuiltUpArea());
			flatMstDto.setCarpetArea(flatMst.getCarpetArea());
			flatMstDto.setAreaFlag(flatMst.getAreaFlag());
			flatMstDto.setPricePerSqft(flatMst.getPricePerSqft());
			flatMstDto.setFlatPrice(flatMst.getFlatPrice());
			flatMstDto.setOtherChrgs(flatMst.getOtherChrgs());
			flatMstDto.setNoOfCarPark(flatMst.getNoOfCarPark());
			flatMstDto.setCarParkValue(flatMst.getCarParkValue());
			flatMstDto.setTotalPrice(flatMst.getTotalPrice());
			flatMstDto.setBookingStatus(flatMst.getBookingStatus());
			flatMstDto.setBookingDate(flatMst.getBookingDate());
			flatMstDto.setCustomerId(flatMst.getCustomerMst().getId());
			flatMstDto.setAgentId(flatMst.getAgentId());
			flatMstDto.setAgrmntFSaleDate(flatMst.getAgrmntFSaleDate());
			flatMstDto.setPayScheduleFileId(flatMst.getPayScheduleFileId());
			flatMstDto.setPaySchdType(flatMst.getPaySchdType());
			flatMstDto.setCreatedBy(flatMst.getCreatedBy());
			flatMstDto.setModifiedBy(flatMst.getModifiedBy());
			flatMstDto.setDeletedBy(flatMst.getDeletedBy());
			flatMstDto.setCreatedDate(flatMst.getCreatedDate());
			flatMstDto.setModifiedDate(flatMst.getModifiedDate());
			flatMstDto.setDeletedDate(flatMst.getDeletedDate());
			flatMstDto.setDeleteFlag(flatMst.getDeleteFlag());
			return flatMstDto;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<FlatMstDto> findByProjectIdFlatNo(Long projectId, String flatNo) {
		return flatMstDao.findByProjectIdFlatNo(projectId, flatNo);
	}

	@Override
	public List<FlatMstDto> findAvailableByProjectIdFlatNo(Long projectId,String flatNo) {
		return flatMstDao.findAvailableByProjectIdFlatNo(projectId, flatNo);
	}

	@Override
	public List<FlatMstDto> findForAggrementByCustomerId(Long customerId,String flatNo) {
		return flatMstDao.findForAggrementByCustomerId(customerId,flatNo);
	}

	@Override
	public String saveOrUpdate(FlatMstDto flatMstDto) {
		try{
			FlatMst flatMst = new FlatMst();
			flatMst.setId(flatMstDto.getId());
			flatMst.setProjectMst(projectMstDao.findById(flatMstDto.getProjectMstId()));
			flatMst.setFlatNo(flatMstDto.getFlatNo());
			flatMst.setFlatDesc(flatMstDto.getFlatDesc());
			flatMst.setFlatFacing(flatMstDto.getFlatFacing());
			flatMst.setFloorName(flatMstDto.getFloorName());
			flatMst.setAreaFlag(flatMstDto.getAreaFlag());
			flatMst.setBuiltUpArea(flatMstDto.getBuiltUpArea());
			flatMst.setSprBuiltUpArea(flatMstDto.getSprBuiltUpArea());
			flatMst.setCarpetArea(flatMstDto.getCarpetArea());
			flatMst.setPricePerSqft(flatMstDto.getPricePerSqft());
			flatMst.setFlatPrice(flatMstDto.getFlatPrice());
			flatMst.setOtherChrgs(flatMstDto.getOtherChrgs());
			flatMst.setNoOfCarPark(flatMstDto.getNoOfCarPark());
			flatMst.setCarParkValue(flatMstDto.getCarParkValue());
			flatMst.setTotalPrice(flatMstDto.getTotalPrice());
			flatMst.setBookingStatus(flatMstDto.getBookingStatus());
			flatMst.setBookingDate(flatMstDto.getBookingDate());
			flatMst.setAgrmntFSaleDate(flatMstDto.getAgrmntFSaleDate());
			flatMst.setFinalFlatPrice(flatMstDto.getFinalFlatPrice());
			flatMst.setFinalCarParkPrice(flatMstDto.getFinalCarParkPrice());
			flatMst.setPaySchdType(flatMstDto.getPaySchdType());
			flatMst.setCreatedBy(flatMstDto.getCreatedBy());
			flatMst.setModifiedBy(flatMstDto.getModifiedBy());
			flatMst.setCreatedDate(flatMstDto.getCreatedDate());
			flatMst.setModifiedDate(flatMstDto.getModifiedDate());
			flatMst.setDeleteFlag(flatMstDto.getDeleteFlag());
			return flatMstDao.saveOrUpdate(flatMst);
		}catch(Exception e){
			log.info("Error ", e);
			return null;
		}
	}

	@Override
	public String delete(Long flatId, Long userId) {
		return flatMstDao.delete(flatId,userId);
	}

	@Override
	public String saveOrUpdateForFlatEntry(FlatMstDto flatMstDto) {
		try{
			FlatMst flatMst = new FlatMst();
			flatMst.setId(flatMstDto.getId());
			flatMst.setProjectMst(projectMstDao.findById(flatMstDto.getProjectMstId()));
			flatMst.setFlatNo(flatMstDto.getFlatNo());
			flatMst.setFlatDesc(flatMstDto.getFlatDesc());
			flatMst.setFlatFacing(flatMstDto.getFlatFacing());
			flatMst.setFloorName(flatMstDto.getFloorName());
			flatMst.setAreaFlag(flatMstDto.getAreaFlag());
			flatMst.setBuiltUpArea(flatMstDto.getBuiltUpArea());
			flatMst.setSprBuiltUpArea(flatMstDto.getSprBuiltUpArea());
			flatMst.setCarpetArea(flatMstDto.getCarpetArea());
			flatMst.setPricePerSqft(flatMstDto.getPricePerSqft());
			flatMst.setFlatPrice(flatMstDto.getFlatPrice());
			flatMst.setOtherChrgs(flatMstDto.getOtherChrgs());
			flatMst.setNoOfCarPark(flatMstDto.getNoOfCarPark());
			flatMst.setCarParkValue(flatMstDto.getCarParkValue());
			flatMst.setTotalPrice(flatMstDto.getTotalPrice());
			flatMst.setBookingStatus(flatMstDto.getBookingStatus());
			flatMst.setBookingDate(flatMstDto.getBookingDate());
			flatMst.setAgrmntFSaleDate(flatMstDto.getAgrmntFSaleDate());
			flatMst.setFinalFlatPrice(flatMstDto.getFinalFlatPrice());
			flatMst.setFinalCarParkPrice(flatMstDto.getFinalCarParkPrice());
			flatMst.setPaySchdType(flatMstDto.getPaySchdType());
			flatMst.setCreatedBy(flatMstDto.getCreatedBy());
			flatMst.setModifiedBy(flatMstDto.getModifiedBy());
			flatMst.setCreatedDate(flatMstDto.getCreatedDate());
			flatMst.setModifiedDate(flatMstDto.getModifiedDate());
			flatMst.setDeleteFlag(flatMstDto.getDeleteFlag());
			return flatMstDao.saveOrUpdateForFlatEntry(flatMst);
		}catch(Exception e){
			log.info("Error ", e);
			return null;
		}
	}

	@Override
	public String saveOrUpdateForFlatNegotiation(FlatMstDto flatMstDto) {
		try{
			FlatMst flatMst = new FlatMst();
			flatMst.setId(flatMstDto.getId());
			flatMst.setProjectMst(projectMstDao.findById(flatMstDto.getProjectMstId()));
			flatMst.setFlatNo(flatMstDto.getFlatNo());
			flatMst.setFlatDesc(flatMstDto.getFlatDesc());
			flatMst.setFlatFacing(flatMstDto.getFlatFacing());
			flatMst.setFloorName(flatMstDto.getFloorName());
			flatMst.setAreaFlag(flatMstDto.getAreaFlag());
			flatMst.setBuiltUpArea(flatMstDto.getBuiltUpArea());
			flatMst.setSprBuiltUpArea(flatMstDto.getSprBuiltUpArea());
			flatMst.setCarpetArea(flatMstDto.getCarpetArea());
			flatMst.setPricePerSqft(flatMstDto.getPricePerSqft());
			flatMst.setFlatPrice(flatMstDto.getFlatPrice());
			flatMst.setOtherChrgs(flatMstDto.getOtherChrgs());
			flatMst.setNoOfCarPark(flatMstDto.getNoOfCarPark());
			flatMst.setCarParkValue(flatMstDto.getCarParkValue());
			flatMst.setTotalPrice(flatMstDto.getTotalPrice());
			flatMst.setBookingStatus(flatMstDto.getBookingStatus());
			flatMst.setBookingDate(flatMstDto.getBookingDate());
			flatMst.setAgrmntFSaleDate(flatMstDto.getAgrmntFSaleDate());
			flatMst.setFinalFlatPrice(flatMstDto.getFinalFlatPrice());
			flatMst.setFinalCarParkPrice(flatMstDto.getFinalCarParkPrice());
			flatMst.setPaySchdType(flatMstDto.getPaySchdType());
			flatMst.setCreatedBy(flatMstDto.getCreatedBy());
			flatMst.setModifiedBy(flatMstDto.getModifiedBy());
			flatMst.setCreatedDate(flatMstDto.getCreatedDate());
			flatMst.setModifiedDate(flatMstDto.getModifiedDate());
			flatMst.setDeleteFlag(flatMstDto.getDeleteFlag());
			return flatMstDao.saveOrUpdateForFlatNegotiation(flatMst);
		}catch(Exception e){
			log.info("Error ", e);
			return null;
		}
	}
}
