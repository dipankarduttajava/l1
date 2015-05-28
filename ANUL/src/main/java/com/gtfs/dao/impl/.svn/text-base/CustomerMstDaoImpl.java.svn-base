package com.gtfs.dao.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gtfs.dao.CustomerMstDao;
import com.gtfs.dto.CustomerMstDto;
import com.gtfs.pojo.CustomerMst;
import com.gtfs.pojo.FlatInvoiceCombo;
import com.gtfs.pojo.FlatPaySchd;
import com.gtfs.pojo.ProjectMst;
import com.gtfs.pojo.ProjectMilestone;

@Repository
public class CustomerMstDaoImpl implements CustomerMstDao, Serializable {
	private Logger log = Logger.getLogger(CustomerMstDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<CustomerMstDto> findAll() {
		Session session = null;
        List<CustomerMstDto> list = null;
        try {
            session = sessionFactory.openSession();            
            SQLQuery query = (SQLQuery) session.createSQLQuery(
            		"SELECT "
            		+ "CM.ID as id, "
            		+ "CM.NAME1 as name1, "
            		+ "CM.NAME2 as name2, "
            		+ "CM.NAME3 as name3, "
            		+ "CM.CUST_TYPE as custType, "
            		+ "CM.CONTANC_PERSON as contancPerson, "
            		+ "CM.COMM_ADDRESS as commAddress, "
            		+ "CM.PERM_ADDRESS as permAddress, "
            		+ "CM.EMAIL as email, "
            		+ "CM.MOBILE1 as mobile1, "
            		+ "CM.MOBILE2 as mobile2, "
            		+ "CM.MOBILE3 as mobile3, "
            		+ "CM.PAN as pan, "
            		+ "CM.CREATED_BY as createdBy, "
            		+ "CM.MODIFIED_BY as modifiedBy, "
            		+ "CM.DELETED_BY as deletedBy, "
            		+ "CM.CREATED_DATE as createdDate, "
            		+ "CM.MODIFIED_DATE as modifiedDate, "
            		+ "CM.DELETED_DATE as deletedDate, "
            		+ "CM.DELETE_FLAG as deleteFlag "
            		
            		+ "FROM "
            		+ "CUSTOMER_MST CM "
            		
            		+ "WHERE "
            		+ "CM.DELETE_FLAG = 'N'");
            
            query.addScalar("id", LongType.INSTANCE);
            query.addScalar("name1", StringType.INSTANCE);
            query.addScalar("name2", StringType.INSTANCE);
            query.addScalar("name3", StringType.INSTANCE);
            query.addScalar("custType", IntegerType.INSTANCE);
            query.addScalar("contancPerson", StringType.INSTANCE);
            query.addScalar("commAddress", StringType.INSTANCE);
            query.addScalar("permAddress", StringType.INSTANCE);
            query.addScalar("email", StringType.INSTANCE);
            query.addScalar("mobile1", StringType.INSTANCE);
            query.addScalar("mobile2", StringType.INSTANCE);
            query.addScalar("mobile3", StringType.INSTANCE);
            query.addScalar("pan", StringType.INSTANCE);
            query.addScalar("createdBy", LongType.INSTANCE);
            query.addScalar("modifiedBy", LongType.INSTANCE);
            query.addScalar("deletedBy", LongType.INSTANCE);
            query.addScalar("createdDate", DateType.INSTANCE);
            query.addScalar("modifiedDate", DateType.INSTANCE);
            query.addScalar("deletedDate", DateType.INSTANCE);
            query.addScalar("deleteFlag", StringType.INSTANCE);
            query.setResultTransformer(Transformers.aliasToBean(CustomerMstDto.class));
            
            list = query.list();
            
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
	}

	@Override
	public CustomerMst findById(Long id) {
		CustomerMst customerMst = null;
	 	Session session = null;
	    try {
	        session = sessionFactory.openSession();
	        customerMst = (CustomerMst) session.get(CustomerMst.class, id);
	    } catch (Exception e) {
	    	e.printStackTrace();
	    } finally {
	        session.close();
	    }
	    return customerMst;
	}

	@Override
	public List<CustomerMstDto> findCustomerByNameMobilePan(String name, String mobile, String pan) {
		Session session = null;
        List<CustomerMstDto> list = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(CustomerMst.class);
            
            ProjectionList projectionList = Projections.projectionList();
            projectionList.add(Projections.property("id"),"id");
            projectionList.add(Projections.property("name1"), "name1");
            projectionList.add(Projections.property("name2"), "name2");
            projectionList.add(Projections.property("name3"), "name3");
            projectionList.add(Projections.property("custType"), "custType");
            projectionList.add(Projections.property("contancPerson"), "contancPerson");
            projectionList.add(Projections.property("commAddress"), "commAddress");
            projectionList.add(Projections.property("permAddress"), "permAddress");
            projectionList.add(Projections.property("email"), "email");
            projectionList.add(Projections.property("mobile1"), "mobile1");
            projectionList.add(Projections.property("mobile2"), "mobile2");
            projectionList.add(Projections.property("mobile3"), "mobile3");
            projectionList.add(Projections.property("pan"), "pan");
            projectionList.add(Projections.property("createdBy"), "createdBy");
            projectionList.add(Projections.property("modifiedBy"), "modifiedBy");
            projectionList.add(Projections.property("deletedBy"), "deletedBy");
            projectionList.add(Projections.property("createdDate"), "createdDate");
            projectionList.add(Projections.property("modifiedDate"), "modifiedDate");
            projectionList.add(Projections.property("deletedDate"), "deletedDate");
            projectionList.add(Projections.property("deleteFlag"), "deleteFlag");
            
            criteria.setProjection(projectionList);
            
            if(!(name ==null || name.equals(""))){
            	criteria.add(Restrictions.or(Restrictions.like("name1", name, MatchMode.ANYWHERE), Restrictions.like("name2", name, MatchMode.ANYWHERE), Restrictions.like("name3", name, MatchMode.ANYWHERE)));
            }
            
            if(!(mobile == null || mobile.equals(""))){
            	criteria.add(Restrictions.or(Restrictions.eq("mobile1", mobile), Restrictions.eq("mobile2", mobile), Restrictions.eq("mobile3", mobile)));
            }
			
            if(!(pan == null || pan.equals(""))){
            	criteria.add(Restrictions.eq("pan", pan));
            }
            
			criteria.add(Restrictions.eq("deleteFlag", "N"));
            criteria.setResultTransformer(Transformers.aliasToBean(CustomerMstDto.class));            
            list = criteria.list();
            
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
	}

	@Override
	public String saveCustomerBooking(Long customerId, Long flatId, Long projectId) {
		String status = null;
		Session session = null;
		Transaction tx = null;
		try{
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			 
			/* Update FLAT_MST */
			Query query1 = session.createQuery("UPDATE FlatMst fm SET "
					+ "fm.bookingStatus = :bookingStatus, "
					+ "fm.bookingDate = :bookingDate, "
					+ "fm.customerMst.id = :customerId "

					+ "WHERE " + "fm.id = :flatId");
			 
			query1.setParameter("bookingStatus", "Y");
			query1.setParameter("bookingDate", new Date());
			query1.setParameter("customerId", customerId);
			query1.setParameter("flatId", flatId);
			query1.executeUpdate();
			
			/* SELECT FROM PROJECT_MILESTONE */
			Query projMil = session.createQuery("FROM ProjectMilestone WHERE milestoneSrlNo = :milestoneSrlNo AND projectMst.id = :projectId AND deleteFlag = :deleteFlag");
			projMil.setParameter("milestoneSrlNo", 1l);
			projMil.setParameter("projectId", projectId);
			projMil.setParameter("deleteFlag", "N");
			List<ProjectMilestone> projMilList = projMil.list();
			 
			 
			/* UPDATE FLAT_PAY_SCHDLE */
			Query query2 = session.createQuery("UPDATE FlatPaySchd fps SET "
					+ "fps.dueDate = :dueDate, "
					+ "fps.customerMst.id = :customerId "

					+ "WHERE " + "fps.flatMst.id = :flatId "
					+ "AND fps.projectMilestone.id = :projectMilestoneId");
			
			query2.setParameter("flatId", flatId);
			query2.setParameter("dueDate", new Date());
			query2.setParameter("projectMilestoneId", projMilList.get(0).getId());
			query2.setParameter("customerId", customerId);
			query2.executeUpdate();
			
			
			
			/* UPDATE Customer_id FLAT_PAY_SCHDLE */
			Query query4 = session.createQuery("UPDATE FlatPaySchd SET "
					+ "customerMst.id = :customerId "

					+ "WHERE flatMst.id = :flatId ");
			
			query4.setParameter("flatId", flatId);
			query4.setParameter("customerId", customerId);
			query4.executeUpdate();
			

			
			/* Select ID From FLAT_PAY_SCHDLE */
			Query query3 = session.createQuery("FROM FlatPaySchd WHERE projectMilestone.id = :projectMilestoneId AND flatMst.id = :flatId");
			query3.setParameter("projectMilestoneId", projMilList.get(0).getId());
			query3.setParameter("flatId", flatId);
			List<FlatPaySchd> list = query3.list();
			
			
			/* Insert Into FLAT_INVOICE_COMBO*/
			FlatInvoiceCombo flatInvoiceCombo = new FlatInvoiceCombo();
			flatInvoiceCombo.setFlatPaySchd(list.get(0));
			flatInvoiceCombo.setInvoiceNo("Inv" + new Date().getTime() + list.get(0).getId());
			flatInvoiceCombo.setInvoiceDate(new Date());
			flatInvoiceCombo.setCreatedDate(new Date());
			flatInvoiceCombo.setDeleteFlag("N");
			session.save(flatInvoiceCombo);
			
			
			
			
			tx.commit();
			status = "true";
		}catch(Exception e){
			status = "false";
			log.info("Error ", e);
			if(tx != null){
				tx.rollback();
			}
		}finally {
			try{
				session.close();
			}catch(Exception e){
				status = "false";
				log.info("Error ", e);
				if(tx != null){
					tx.rollback();
				}
			}
        }		
		return status;
	}

	@Override
	public List<CustomerMstDto> findCustomerForAggrementByNameMobilePan(String name, String mobile, String pan) {
		Session session = null;
        List<CustomerMstDto> list = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(CustomerMst.class,"cm");
            criteria.createAlias("cm.flatMsts", "fm");
            
            ProjectionList projectionList = Projections.projectionList();
            projectionList.add(Projections.property("cm.id"),"id");
            projectionList.add(Projections.property("cm.name1"), "name1");
            projectionList.add(Projections.property("cm.name2"), "name2");
            projectionList.add(Projections.property("cm.name3"), "name3");
            projectionList.add(Projections.property("cm.custType"), "custType");
            projectionList.add(Projections.property("cm.contancPerson"), "contancPerson");
            projectionList.add(Projections.property("cm.commAddress"), "commAddress");
            projectionList.add(Projections.property("cm.permAddress"), "permAddress");
            projectionList.add(Projections.property("cm.email"), "email");
            projectionList.add(Projections.property("cm.mobile1"), "mobile1");
            projectionList.add(Projections.property("cm.mobile2"), "mobile2");
            projectionList.add(Projections.property("cm.mobile3"), "mobile3");
            projectionList.add(Projections.property("cm.pan"), "pan");
            projectionList.add(Projections.property("cm.createdBy"), "createdBy");
            projectionList.add(Projections.property("cm.modifiedBy"), "modifiedBy");
            projectionList.add(Projections.property("cm.deletedBy"), "deletedBy");
            projectionList.add(Projections.property("cm.createdDate"), "createdDate");
            projectionList.add(Projections.property("cm.modifiedDate"), "modifiedDate");
            projectionList.add(Projections.property("cm.deletedDate"), "deletedDate");
            projectionList.add(Projections.property("cm.deleteFlag"), "deleteFlag");
            
            
            criteria.setProjection(Projections.distinct(projectionList));
            
            if(!(name ==null || name.equals(""))){
            	criteria.add(Restrictions.or(Restrictions.like("name1", name, MatchMode.ANYWHERE), Restrictions.like("name2", name, MatchMode.ANYWHERE), Restrictions.like("name3", name, MatchMode.ANYWHERE)));
            }
            
            if(!(mobile == null || mobile.equals(""))){
            	criteria.add(Restrictions.or(Restrictions.eq("mobile1", mobile), Restrictions.eq("mobile2", mobile), Restrictions.eq("mobile3", mobile)));
            }
			
            if(!(pan == null || pan.equals(""))){
            	criteria.add(Restrictions.eq("pan", pan));
            }
            
			criteria.add(Restrictions.eq("deleteFlag", "N"));
			criteria.add(Restrictions.eq("fm.bookingStatus", "Y"));
			criteria.add(Restrictions.isNull("fm.agrmntFSaleDate"));
			
            criteria.setResultTransformer(Transformers.aliasToBean(CustomerMstDto.class));            
            list = criteria.list();
            
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
	}

	@Override
	public String saveCustomerAggrement(Long customerId, Long flatId, Long projectId) {
		String status = null;
		Session session = null;
		Transaction tx = null;
		try{
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			 
			/* Update FLAT_MST */
			Query query1 = session.createQuery("UPDATE FlatMst fm SET "
					+ "fm.agrmntFSaleDate = :agrmntFSaleDate, "
					+ "fm.customerMst.id = :customerId "

					+ "WHERE " + "fm.id = :flatId");
			 
			query1.setParameter("agrmntFSaleDate", new Date());
			query1.setParameter("flatId", flatId);
			query1.setParameter("customerId", customerId);
			query1.executeUpdate();
			 
			
			/* SELECT FROM PROJECT_MILESTONE */
			Query projMil = session.createQuery("FROM ProjectMilestone WHERE milestoneSrlNo = :milestoneSrlNo AND projectMst.id = :projectId AND deleteFlag = :deleteFlag");
			projMil.setParameter("milestoneSrlNo", 2l);
			projMil.setParameter("projectId", projectId);
			projMil.setParameter("deleteFlag", "N");
			List<ProjectMilestone> projMilList = projMil.list();
			
			
			/* UPDATE FLAT_PAY_SCHDLE */
			Query query2 = session.createQuery("UPDATE FlatPaySchd fps SET "
					+ "fps.dueDate = :dueDate, "
					+ "fps.customerMst.id = :customerId "

					+ "WHERE " + "fps.flatMst.id = :flatId "
					+ "AND fps.projectMilestone.id = :projectMilestoneId");
			
			query2.setParameter("flatId", flatId);
			query2.setParameter("dueDate", new Date());
			query2.setParameter("projectMilestoneId", projMilList.get(0).getId());
			query2.setParameter("customerId", customerId);
			query2.executeUpdate();
			
			
			/* Select ID From FLAT_PAY_SCHDLE */
			Query query3 = session.createQuery("FROM FlatPaySchd WHERE projectMilestone.id = :projectMilestoneId AND flatMst.id = :flatId");
			query3.setParameter("projectMilestoneId", projMilList.get(0).getId());
			query3.setParameter("flatId", flatId);
			List<FlatPaySchd> list = query3.list();	
			
			
			/* Insert Into FLAT_INVOICE_COMBO*/
			FlatInvoiceCombo flatInvoiceCombo = new FlatInvoiceCombo();
			flatInvoiceCombo.setFlatPaySchd(list.get(0));
			flatInvoiceCombo.setInvoiceNo("Inv" + new Date().getTime() + list.get(0).getId());
			flatInvoiceCombo.setInvoiceDate(new Date());
			flatInvoiceCombo.setCreatedDate(new Date());
			flatInvoiceCombo.setDeleteFlag("N");
			session.save(flatInvoiceCombo);	
			
			
			
			tx.commit();
			status = "true";
		}catch(Exception e){
			status = "false";
			log.info("Error ", e);
			if(tx != null){
				tx.rollback();
			}
		}finally {
			try{
				session.close();
			}catch(Exception e){
				status = "false";
				log.info("Error ", e);
				if(tx != null){
					tx.rollback();
				}
			}
        }		
		return status;
	}

	@Override
	public String saveOrUpdate(CustomerMst customerMst) {
		String status = null;
		Session session = null;
		Transaction tx = null;
		try{
			 session = sessionFactory.openSession();
			 tx = session.beginTransaction();
			 session.saveOrUpdate(customerMst);
			 tx.commit();
			 status = "true";
		}catch(Exception e){
			status = "false";
			if(tx != null){
				tx.rollback();
			}
			log.info("Error ", e);
		}finally {
			try{
				session.close();
			}catch(Exception e){
				status = "false";
				e.printStackTrace();
				if(tx != null){
					tx.rollback();
				}
				log.info("Error ", e);
			}
        }
		return status;
	}

	@Override
	public String delete(Long customerId, Long deletedBy) {
		String status = null;
		Session session = null;
		Transaction tx = null;
		try{
			 session = sessionFactory.openSession();
			 tx = session.beginTransaction();
			 Query query = session.createQuery("UPDATE CustomerMst SET "
			 		+ "deletedBy = :deletedBy, "
			 		+ "deletedDate = :deletedDate, "
			 		+ "deleteFlag = :deleteFlag "
			 		
			 		+ "WHERE "
			 		+ "id = :id");
			 
			 query.setParameter("id", customerId);
			 query.setParameter("deletedBy", deletedBy);
			 query.setParameter("deletedDate", new Date());
			 query.setParameter("deleteFlag", "Y");
			 query.executeUpdate();
			 tx.commit();
			 status = "true";
		}catch(Exception e){
			status = "false";
			if(tx != null){
				tx.rollback();
			}
			log.info("Error ", e);
		}finally {
			try{
				session.close();
			}catch(Exception e){
				status = "false";
				if(tx != null){
					tx.rollback();
				}
				log.info("Error ", e);
			}
        }
		return status;
	}

	@Override
	public String updateMilesoneAfterBooking(Long customerId, Long flatId,Long projectId) {
		String status = null;
		Session session = null;
		Transaction tx = null;
		try{
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			ProjectMst projectMst = (ProjectMst) session.get(ProjectMst.class, projectId);
			
			log.info("info "+projectMst.getMilestoneCompltd());
			
			if(projectMst.getMilestoneCompltd()!=null){
				for(int i=3;i<=projectMst.getMilestoneCompltd();i++){
					
					Query projMilestoneQuery = session.createQuery("FROM ProjectMilestone WHERE milestoneSrlNo = :milestoneSrlNo AND projectMst.id = :projectId AND deleteFlag = :deleteFlag");
					projMilestoneQuery.setParameter("milestoneSrlNo", Long.parseLong(""+i));
					projMilestoneQuery.setParameter("projectId", projectId);
					projMilestoneQuery.setParameter("deleteFlag", "N");
					List<ProjectMilestone> projMilestoneList = projMilestoneQuery.list();
					
					
					/* Update FLAT_PAY_SCHD */
					 Query q1 = session.createQuery("UPDATE FlatPaySchd SET "
					 		+ "dueDate = :dueDate "
					 		
					 		+ "WHERE "
					 		+ "projectMilestone.id = :milestoneId "
					 		+ "and deleteFlag = :deleteFlag "
					 		+ "and customerMst.id = :customerId");
					 
					 q1.setParameter("dueDate", new Date());
					 q1.setParameter("milestoneId", projMilestoneList.get(0).getId());
					 q1.setParameter("deleteFlag", "N");
					 q1.setParameter("customerId", customerId);
					 q1.executeUpdate();
					 
					 
					 
					 /* Insert Into FLAT_INVOICE_COMBO */
						Query q3 = session.createQuery("FROM FlatPaySchd WHERE projectMilestone.id = :projectMilestoneId "
								+ "and deleteFlag = :deleteFlag and customerMst.id = :customerId");
						
						q3.setParameter("projectMilestoneId", projMilestoneList.get(0).getId());
						q3.setParameter("deleteFlag", "N");
						q3.setParameter("customerId", customerId);
						
						List<FlatPaySchd> FlatPaySchdList = q3.list();
						
						/* Insert Into FLAT_INVOICE_COMBO*/
						for(FlatPaySchd flatPaySchd : FlatPaySchdList){
							FlatInvoiceCombo flatInvoiceCombo = new FlatInvoiceCombo();
							flatInvoiceCombo.setFlatPaySchd(flatPaySchd);
							flatInvoiceCombo.setInvoiceNo("Inv" + new Date().getTime() + flatPaySchd.getId());
							flatInvoiceCombo.setInvoiceDate(new Date());
							flatInvoiceCombo.setCreatedDate(new Date());
							flatInvoiceCombo.setDeleteFlag("N");
							session.save(flatInvoiceCombo);	
						}
				}
			}
			
			tx.commit();
			status = "true";
		}catch(Exception e){
			status = "false";
			log.info("Error ", e);
			if(tx != null){
				tx.rollback();
			}
		}finally {
			try{
				session.close();
			}catch(Exception e){
				status = "false";
				log.info("Error ", e);
				if(tx != null){
					tx.rollback();
				}
			}
        }		
		return status;
	}
}
