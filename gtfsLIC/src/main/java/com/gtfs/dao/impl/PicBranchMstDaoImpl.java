package com.gtfs.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.gtfs.bean.LicBranchHubPicMapping;
import com.gtfs.bean.PicBranchMst;
import com.gtfs.dao.interfaces.PicBranchMstDao;

@Repository
public class PicBranchMstDaoImpl implements PicBranchMstDao,Serializable{
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<Long> findPicIdsForHubsByProcessName(List<Long> licHubMsts,String processName){
		List<Long> list=null;
	 	Session session = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(LicBranchHubPicMapping.class,"lbhpm");
            criteria.createAlias("lbhpm.processMst", "pm");
            
            criteria.add(Restrictions.eq("lbhpm.sourceType","HUB"));
            criteria.add(Restrictions.eq("lbhpm.destinationType","PIC"));
            criteria.add(Restrictions.in("lbhpm.sourceId", licHubMsts));
            criteria.add(Restrictions.eq("lbhpm.deleteFlag","N"));
            criteria.add(Restrictions.eq("pm.processAbbr", processName));
            
            criteria.setProjection(Projections.projectionList().add(Projections.property("destinationId")));
            list = criteria.list();
        } catch (Exception e) {
        } finally {
            session.close();
        }
        return list;
	}
	
	
	public List<Long> findPicIdForBranchIdByProcessName(Long branchId,String processName){
		List<Long> list=null;
	 	Session session = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(LicBranchHubPicMapping.class,"lbhpm");
            criteria.createAlias("lbhpm.processMst", "pm");
            criteria.setProjection(Projections.projectionList().add(Projections.property("destinationId")));
            
            criteria.add(Restrictions.eq("lbhpm.sourceType","BRANCH"));
            criteria.add(Restrictions.eq("lbhpm.destinationType","PIC"));
            criteria.add(Restrictions.eq("lbhpm.sourceId",branchId));
            criteria.add(Restrictions.eq("lbhpm.deleteFlag","N"));
            criteria.add(Restrictions.eq("pm.processAbbr",processName));
            list=criteria.list();
        } catch (Exception e) {
        } finally {
            session.close();
        }
        return list;
	}
	
	public List<PicBranchMst> findActivePicBranchMst(){
		List<PicBranchMst> list=null;
	 	Session session = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(PicBranchMst.class);
            list=criteria.list();
        } catch (Exception e) {
        } finally {
            session.close();
        }
        return list;
	}
	
	public PicBranchMst findbyId(Long picBranchId){
		PicBranchMst picBranchMst=null;
	 	Session session = null;
        try {
            session = sessionFactory.openSession();
            picBranchMst = (PicBranchMst) session.get(PicBranchMst.class, picBranchId);
        } catch (Exception e) {
        } finally {
            session.close();
        }
        return picBranchMst;
	}
	
	public List<PicBranchMst> findPicsByPicIds(List<Long> picBranchIds){
		List<PicBranchMst> picBranchMsts = null;
	 	Session session = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria=session.createCriteria(PicBranchMst.class);
            criteria.add(Restrictions.in("picBranchId", picBranchIds));
            picBranchMsts = criteria.list();
        } catch (Exception e) {
        } finally {
            session.close();
        }
        return picBranchMsts;
	}
	
	
}
