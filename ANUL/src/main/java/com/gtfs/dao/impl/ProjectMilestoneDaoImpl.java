package com.gtfs.dao.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gtfs.dao.ProjectMilestoneDao;
import com.gtfs.dto.ProjectMilestoneDto;
import com.gtfs.pojo.FlatInvoiceCombo;
import com.gtfs.pojo.FlatPaySchd;
import com.gtfs.pojo.ProjectMilestone;
import com.gtfs.pojo.ProjectMst;

@Repository
public class ProjectMilestoneDaoImpl implements ProjectMilestoneDao,Serializable {
	private Logger log = Logger.getLogger(ProjectMilestoneDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<ProjectMilestoneDto> findAll() {
		Session session = null;
        List<ProjectMilestoneDto> list = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(ProjectMilestone.class);
            
            ProjectionList projectionList = Projections.projectionList();
            projectionList.add(Projections.property("id"),"id");
            projectionList.add(Projections.property("projectMst.id"), "projectMst");
            projectionList.add(Projections.property("milestoneSrlNo"), "milestoneSrlNo");
            projectionList.add(Projections.property("milestoneDesc"), "milestoneDesc");
            projectionList.add(Projections.property("milestoneScope"), "milestoneScope");
            projectionList.add(Projections.property("milestoneStatus"), "milestoneStatus");
            projectionList.add(Projections.property("paymentPerc"), "paymentPerc");
            projectionList.add(Projections.property("createdBy"), "createdBy");
            projectionList.add(Projections.property("modifiedBy"), "modifiedBy");
            projectionList.add(Projections.property("deletedBy"), "deletedBy");
            projectionList.add(Projections.property("createdDate"), "createdDate");
            projectionList.add(Projections.property("modifiedDate"), "modifiedDate");
            projectionList.add(Projections.property("deletedDate"), "deletedDate");            
            projectionList.add(Projections.property("deleteFlag"), "deleteFlag");
            
            criteria.setProjection(projectionList);
            
            criteria.add(Restrictions.eq("deleteFlag", "N"));
            criteria.setResultTransformer(Transformers.aliasToBean(ProjectMilestoneDto.class));            
            list = criteria.list();
            
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
	}

	@Override
	public ProjectMilestone findById(Long id) {
		Session session = null;
		ProjectMilestone projectMilestone = null;
        try {
            session = sessionFactory.openSession();
            
            projectMilestone = (ProjectMilestone) session.get(ProjectMilestone.class, id);
            
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
            session.close();
        }
        return projectMilestone;

	}

	@Override
	public List<ProjectMilestoneDto> findByProjectId(Long projectId) {
		Session session = null;
        List<ProjectMilestoneDto> list = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(ProjectMilestone.class);
            
            ProjectionList projectionList = Projections.projectionList();
            projectionList.add(Projections.property("id"),"id");
            projectionList.add(Projections.property("projectMst.id"), "projectMst");
            projectionList.add(Projections.property("milestoneSrlNo"), "milestoneSrlNo");
            projectionList.add(Projections.property("milestoneDesc"), "milestoneDesc");
            projectionList.add(Projections.property("milestoneScope"), "milestoneScope");
            projectionList.add(Projections.property("milestoneStatus"), "milestoneStatus");
            projectionList.add(Projections.property("paymentPerc"), "paymentPerc");
            projectionList.add(Projections.property("createdBy"), "createdBy");
            projectionList.add(Projections.property("modifiedBy"), "modifiedBy");
            projectionList.add(Projections.property("deletedBy"), "deletedBy");
            projectionList.add(Projections.property("createdDate"), "createdDate");
            projectionList.add(Projections.property("modifiedDate"), "modifiedDate");
            projectionList.add(Projections.property("deletedDate"), "deletedDate");            
            projectionList.add(Projections.property("deleteFlag"), "deleteFlag");
            
            criteria.setProjection(projectionList);
            
            criteria.add(Restrictions.eq("deleteFlag", "N"));
            criteria.add(Restrictions.eq("projectMst.id", projectId));
            criteria.setResultTransformer(Transformers.aliasToBean(ProjectMilestoneDto.class));            
            list = criteria.list();
            
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
	}

	@Override
	public List<ProjectMilestoneDto> findFlatSpecificByProjectId(Long projectId) {
		Session session = null;
        List<ProjectMilestoneDto> list = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(ProjectMilestone.class);
            
            ProjectionList projectionList = Projections.projectionList();
            projectionList.add(Projections.property("id"),"id");
            projectionList.add(Projections.property("projectMst.id"), "projectMst");
            projectionList.add(Projections.property("milestoneSrlNo"), "milestoneSrlNo");
            projectionList.add(Projections.property("milestoneDesc"), "milestoneDesc");
            projectionList.add(Projections.property("milestoneScope"), "milestoneScope");
            projectionList.add(Projections.property("milestoneStatus"), "milestoneStatus");
            projectionList.add(Projections.property("paymentPerc"), "paymentPerc");
            projectionList.add(Projections.property("createdBy"), "createdBy");
            projectionList.add(Projections.property("modifiedBy"), "modifiedBy");
            projectionList.add(Projections.property("deletedBy"), "deletedBy");
            projectionList.add(Projections.property("createdDate"), "createdDate");
            projectionList.add(Projections.property("modifiedDate"), "modifiedDate");
            projectionList.add(Projections.property("deletedDate"), "deletedDate");            
            projectionList.add(Projections.property("deleteFlag"), "deleteFlag");
            
            criteria.setProjection(projectionList);
            
            criteria.add(Restrictions.eq("deleteFlag", "N"));
            criteria.add(Restrictions.eq("projectMst.id", projectId));
            criteria.add(Restrictions.eq("milestoneScope", "F"));
            criteria.setResultTransformer(Transformers.aliasToBean(ProjectMilestoneDto.class));            
            list = criteria.list();
            
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
	}

	@Override
	public List<ProjectMilestoneDto> findNextProjectSpcMilstoneByProjectId(Long projectId) {
		Session session = null;
        List<ProjectMilestoneDto> list = null;
        try {
        	 session = sessionFactory.openSession();
        	 
        	 ProjectMst projectMst = (ProjectMst) session.load(ProjectMst.class, projectId);
        	 
        	 System.out.println("xxxxxxxxxxxxxx "+projectMst.getMilestoneCompltd());
        	
        	 Criteria criteria = session.createCriteria(ProjectMilestone.class);
             
             ProjectionList projectionList = Projections.projectionList();
             projectionList.add(Projections.property("id"),"id");
             projectionList.add(Projections.property("projectMst.id"), "projectMst");
             projectionList.add(Projections.property("milestoneSrlNo"), "milestoneSrlNo");
             projectionList.add(Projections.property("milestoneDesc"), "milestoneDesc");
             projectionList.add(Projections.property("milestoneScope"), "milestoneScope");
             projectionList.add(Projections.property("milestoneStatus"), "milestoneStatus");
             projectionList.add(Projections.property("paymentPerc"), "paymentPerc");
             projectionList.add(Projections.property("createdBy"), "createdBy");
             projectionList.add(Projections.property("modifiedBy"), "modifiedBy");
             projectionList.add(Projections.property("deletedBy"), "deletedBy");
             projectionList.add(Projections.property("createdDate"), "createdDate");
             projectionList.add(Projections.property("modifiedDate"), "modifiedDate");
             projectionList.add(Projections.property("deletedDate"), "deletedDate");            
             projectionList.add(Projections.property("deleteFlag"), "deleteFlag");
             
             criteria.setProjection(projectionList);
             
             criteria.add(Restrictions.eq("deleteFlag", "N"));
             criteria.add(Restrictions.eq("projectMst.id", projectId));
             
        	if(projectMst.getMilestoneCompltd() == null || projectMst.getMilestoneCompltd()<3){
        		criteria.add(Restrictions.eq("milestoneSrlNo", 3l));
        	}else{
        		criteria.add(Restrictions.eq("milestoneSrlNo", ((long)(projectMst.getMilestoneCompltd()+1))));
        	}
        	
        	
        	criteria.setResultTransformer(Transformers.aliasToBean(ProjectMilestoneDto.class));            
            list = criteria.list();
       	
        	
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
	}

	@Override
	public String updateProjectMilestone(Long projectId, Long milestoneId, Date dateGiven) {
		Session session = null;
		String status = null;
		Transaction tx = null;
		try{
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			Query projMil = session.createQuery("FROM ProjectMilestone WHERE milestoneSrlNo = :milestoneSrlNo AND projectMst.id = :projectId AND deleteFlag = :deleteFlag");
			projMil.setParameter("milestoneSrlNo", milestoneId);
			projMil.setParameter("projectId", projectId);
			projMil.setParameter("deleteFlag", "N");
			List<ProjectMilestone> projMilList = projMil.list();
			
			/* Update FLAT_PAY_SCHD */
			 Query query1 = session.createQuery("UPDATE FlatPaySchd SET "
			 		+ "dueDate = :dueDate "
			 		
			 		+ "WHERE "
			 		+ "projectMilestone.id = :milestoneId "
			 		+ "and deleteFlag = :deleteFlag "
			 		+ "and customerMst is not null "
			 		+ "and dueDate is null");
			 
			 query1.setParameter("dueDate", dateGiven);
			 query1.setParameter("milestoneId", projMilList.get(0).getId());
			 query1.setParameter("deleteFlag", "N");
			 query1.executeUpdate();
			 
			 
			 /* Update PROJECT_MST */
			 Query query2 = session.createQuery("UPDATE ProjectMst SET "
			 		+ "milestoneCompltd = :milestoneCompltd "
			 		
			 		+ "WHERE "
			 		+ "id = :projectMstId "
			 		+ "and deleteFlag = :deleteFlag");
			 
			 query2.setParameter("milestoneCompltd", milestoneId.intValue());
			 query2.setParameter("projectMstId", projectId);
			 query2.setParameter("deleteFlag", "N");
			 query2.executeUpdate();
			 
			 
			 /* Insert Into FLAT_INVOICE_COMBO */
			Query query3 = session.createQuery("FROM FlatPaySchd WHERE projectMilestone.id = :projectMilestoneId "
					+ "and deleteFlag = :deleteFlag and customerMst is not null and dueDate is null");
			
			query3.setParameter("projectMilestoneId", projMilList.get(0).getId());
			query3.setParameter("deleteFlag", "N");
			
			List<FlatPaySchd> list = query3.list();
			
			/* Insert Into FLAT_INVOICE_COMBO*/
			int count=1;
			for(FlatPaySchd flatPaySchd : list){
				FlatInvoiceCombo flatInvoiceCombo = new FlatInvoiceCombo();
				flatInvoiceCombo.setFlatPaySchd(flatPaySchd);
				flatInvoiceCombo.setInvoiceNo("Inv" + new Date().getTime() + flatPaySchd.getId());
				flatInvoiceCombo.setInvoiceDate(new Date());
				flatInvoiceCombo.setCreatedDate(new Date());
				flatInvoiceCombo.setDeleteFlag("N");
				session.save(flatInvoiceCombo);	
				
				if( count % 50 == 0 ) { 
			        session.flush();
			        session.clear();
			    }
				count++;
			}
			
			tx.commit();
			status = "true";
		}catch(Exception e){
			status = "false";
			log.info("Error : ", e);
		}
		return status;
	}

}
