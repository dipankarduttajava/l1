package com.gtfs.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtfs.bean.BranchMst;
import com.gtfs.bean.LicBrnhHubPicPodDtls;
import com.gtfs.bean.LicHubMst;
import com.gtfs.bean.LicOblApplicationMst;
import com.gtfs.bean.LicPolicyDtls;
import com.gtfs.bean.LicRequirementDtls;
import com.gtfs.dao.interfaces.LicBrnhHubPicPodDtlsDao;
import com.gtfs.dto.LicOblApplicationMstDto;
import com.gtfs.service.interfaces.LicBrnhHubPicPodDtlsService;

@Service
public class LicBrnhHubPicPodDtlsServiceImpl implements LicBrnhHubPicPodDtlsService,Serializable{
	@Autowired
	private LicBrnhHubPicPodDtlsDao licBrnhHubPicPodDtlsDao;
	
	public Long saveForBranchHubPodDtls(LicBrnhHubPicPodDtls licBrnhHubPicPodDtls){
		return licBrnhHubPicPodDtlsDao.saveForBranchHubPodDtls(licBrnhHubPicPodDtls);
	}
	
	public List<Object> findDistinctPodDtlsForConsldMarking(List<LicHubMst> licHubMsts) throws Exception{
		return licBrnhHubPicPodDtlsDao.findDistinctPodDtlsForConsldMarking(licHubMsts);
	}
	
	public List<LicOblApplicationMst> findApplicationByPodId(Long id){
		return licBrnhHubPicPodDtlsDao.findApplicationByPodId(id);
	}
	
	public Long saveForHubPicPodDtls(LicBrnhHubPicPodDtls licBrnhHubPicPodDtls){
		return licBrnhHubPicPodDtlsDao.saveForHubPicPodDtls(licBrnhHubPicPodDtls);
	}

	public Boolean saveForBranchHubPodDtlsForReq(LicBrnhHubPicPodDtls licBrnhHubPicPodDtls) {
		return licBrnhHubPicPodDtlsDao.saveForBranchHubPodDtlsForReq(licBrnhHubPicPodDtls);
	}
	
	public List<Object> findDistinctPodDtlsConsldMarkingForReq(List<LicHubMst> licHubMsts) throws Exception{
		return licBrnhHubPicPodDtlsDao.findDistinctPodDtlsConsldMarkingForReq(licHubMsts);
	}

	@Override
	public List<LicRequirementDtls> findApplicationByPodIdForReq(Long id) {
		return licBrnhHubPicPodDtlsDao.findApplicationByPodIdForReq(id);
	}

	@Override
	public Long saveForBranchHubPodDtlsForRenewal(LicBrnhHubPicPodDtls licBrnhHubPicPodDtls) {
		return licBrnhHubPicPodDtlsDao.saveForBranchHubPodDtlsForRenewal(licBrnhHubPicPodDtls);
	}

	@Override
	public List<Object> findDistinctPodDtlsForConsldMarkingForRnl(List<LicHubMst> licHubMsts) throws Exception {
		return licBrnhHubPicPodDtlsDao.findDistinctPodDtlsForConsldMarkingForRnl(licHubMsts);
	}

	@Override
	public List<LicPolicyDtls> findPolicyDtlsByPodId(Long PodId) {
		return licBrnhHubPicPodDtlsDao.findPolicyDtlsByPodId(PodId);
	}

	@Override
	public Long updatePicPodDtlsForRnl(LicBrnhHubPicPodDtls licBrnhHubPicPodDtls) {
		return licBrnhHubPicPodDtlsDao.updatePicPodDtlsForRnl(licBrnhHubPicPodDtls);
	}

	@Override
	public Long updatePicPodDtlsForRenewalPosView(LicBrnhHubPicPodDtls licBrnhHubPicPodDtls) {
		return licBrnhHubPicPodDtlsDao.updatePicPodDtlsForRenewalPosView(licBrnhHubPicPodDtls);
	}

	@Override
	public List<Object> findDistinctPodDtlsForConsldMarkingForPos(List<LicHubMst> licHubMsts) throws Exception {
		return licBrnhHubPicPodDtlsDao.findDistinctPodDtlsForConsldMarkingForPos(licHubMsts);
	}

	@Override
	public List<LicRequirementDtls> findRequiremrntDtlsByPodIdForPos(Long PodId) {
		return licBrnhHubPicPodDtlsDao.findRequiremrntDtlsByPodIdForPos(PodId);
	}

	@Override
	public List<LicOblApplicationMstDto> findBranchHubPodReport(Date businessFromDate, Date businessToDate, BranchMst branchMst) {
		return licBrnhHubPicPodDtlsDao.findBranchHubPodReport(businessFromDate, businessToDate, branchMst);
	}

	@Override
	public List<LicOblApplicationMst> findPicPodReport(Date businessFromDate, Date businessToDate, BranchMst branchMst) {
		return licBrnhHubPicPodDtlsDao.findPicPodReport(businessFromDate, businessToDate, branchMst);
	}
}
