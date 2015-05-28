package com.gtfs.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.gtfs.bean.PrintRcptMst;
import com.gtfs.dao.interfaces.PrintRcptMstDao;
@Repository
public class PrintRcptMstDaoImpl implements PrintRcptMstDao,Serializable{
	@Autowired
	private SessionFactory sessionFactory;
	
	public Boolean saveForPrintReceipt(PrintRcptMst printRcptMst){
		Boolean status=false;
	 	Session session = null;
	 	Transaction tx=null;
        try {
            session = sessionFactory.openSession();
            tx=session.beginTransaction();
            session.save(printRcptMst);
            tx.commit();
            status=true;
        } catch (Exception e) {
        	status=false;
        } finally {
            session.close();
        }
        return status;
	}
	
	public List<Long> findMaximunReceiptNoByPrefix(String prefix){
		List<Long> list=null;
		Session session = null;
		try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(PrintRcptMst.class);
            ProjectionList proj = Projections.projectionList();
            proj.add(Projections.max("receiptTo"));
            criteria.setProjection(proj);
            criteria.add(Restrictions.eq("prefix", prefix));
            criteria.add(Restrictions.eq("deleteFlag", "N"));
            
            list= criteria.list();

        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
	}
	
	
	public List<String> findPrintRcptPrefixByBranchParentTieupCoId(Long branchId,Long  tieupCompyId,Long parentCompyId){
		List<String> list=null;
		Session session = null;
		try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(PrintRcptMst.class);
            criteria.add(Restrictions.eq("branchMst.branchId", branchId));
            criteria.add(Restrictions.eq("tieupCompyMst.tieCompyId", tieupCompyId));
            criteria.add(Restrictions.eq("parentCompyMst.prntCompyId", parentCompyId));
            criteria.add(Restrictions.eq("deleteFlag", "N"));
            
            criteria.setProjection(Projections.distinct(Projections.property("prefix")));
            list= criteria.list();
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
	}
	
	public List<PrintRcptMst> findPrintRcptMstByPrefixBranchIdTieupCoIdParentCoId(String prefix,Long branchId,Long tieupCoId,Long parentCoId){
		List<PrintRcptMst> list=null;
		Session session = null;
		try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(PrintRcptMst.class);
            criteria.add(Restrictions.eq("branchMst.branchId",branchId));
            criteria.add(Restrictions.eq("tieupCompyMst.tieCompyId",tieupCoId));
            criteria.add(Restrictions.eq("parentCompyMst.prntCompyId", parentCoId));
            criteria.add(Restrictions.eq("prefix", prefix));
            criteria.add(Restrictions.eq("deleteFlag", "N"));
            list= criteria.list();
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
	}

}
