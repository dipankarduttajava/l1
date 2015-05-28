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

import com.gtfs.dao.FlatMstDao;
import com.gtfs.dto.FlatMstDto;
import com.gtfs.pojo.FlatMst;
import com.gtfs.pojo.FlatPaySchd;
import com.gtfs.pojo.ProjectMilestone;

@Repository
public class FlatMstDaoImpl implements Serializable, FlatMstDao {
	private Logger log = Logger.getLogger(FlatMstDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<FlatMstDto> findAll() {
		Session session = null;
        List<FlatMstDto> list = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(FlatMst.class, "fm");
            criteria.createAlias("fm.projectMst", "pm");
            
            ProjectionList projectionList = Projections.projectionList();
            projectionList.add(Projections.property("fm.id"),"id");
            projectionList.add(Projections.property("pm.id"), "projectMstId");
            projectionList.add(Projections.property("pm.projectName"), "projectName");
            projectionList.add(Projections.property("fm.flatNo"), "flatNo");
            projectionList.add(Projections.property("fm.flatDesc"), "flatDesc");
            projectionList.add(Projections.property("fm.flatFacing"), "flatFacing");
            projectionList.add(Projections.property("fm.floorName"), "floorName");
            projectionList.add(Projections.property("fm.builtUpArea"), "builtUpArea");
            projectionList.add(Projections.property("fm.sprBuiltUpArea"), "sprBuiltUpArea");
            projectionList.add(Projections.property("fm.carpetArea"), "carpetArea");
            projectionList.add(Projections.property("fm.areaFlag"), "areaFlag");
            projectionList.add(Projections.property("fm.pricePerSqft"), "pricePerSqft");
            projectionList.add(Projections.property("fm.flatPrice"), "flatPrice");            
            projectionList.add(Projections.property("fm.otherChrgs"), "otherChrgs");
            projectionList.add(Projections.property("fm.noOfCarPark"), "noOfCarPark");
            projectionList.add(Projections.property("fm.carParkValue"), "carParkValue");
            projectionList.add(Projections.property("fm.totalPrice"), "totalPrice");
            projectionList.add(Projections.property("fm.bookingStatus"), "bookingStatus");
            projectionList.add(Projections.property("fm.bookingDate"), "bookingDate");
            projectionList.add(Projections.property("fm.customerMst.id"), "customerId");
            projectionList.add(Projections.property("fm.agentId"), "agentId");
            projectionList.add(Projections.property("fm.agrmntFSaleDate"), "agrmntFSaleDate");
            projectionList.add(Projections.property("fm.payScheduleFileId"), "payScheduleFileId");
            projectionList.add(Projections.property("fm.finalFlatPrice"), "finalFlatPrice");
            projectionList.add(Projections.property("fm.createdBy"), "createdBy");
            projectionList.add(Projections.property("fm.modifiedBy"), "modifiedBy");
            projectionList.add(Projections.property("fm.deletedBy"), "deletedBy");
            projectionList.add(Projections.property("fm.createdDate"), "createdDate");
            projectionList.add(Projections.property("fm.modifiedDate"), "modifiedDate");
            projectionList.add(Projections.property("fm.deletedDate"), "deletedDate");
            projectionList.add(Projections.property("fm.deleteFlag"), "deleteFlag");
            
            criteria.setProjection(projectionList);
            
            criteria.add(Restrictions.eq("deleteFlag", "N"));
            criteria.setResultTransformer(Transformers.aliasToBean(FlatMstDto.class));            
            list = criteria.list();
            
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
	}

	@Override
	public FlatMst findById(Long id) {
		FlatMst flatMst = null;
		Session session = null;
		try{
			session = sessionFactory.openSession();
			flatMst = (FlatMst) session.get(FlatMst.class, id);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return flatMst;
	}

	@Override
	public List<FlatMstDto> findByProjectIdFlatNo(Long projectId, String flatNo) {
		Session session = null;
        List<FlatMstDto> list = null;
        try {
        	session = sessionFactory.openSession();
        	Criteria criteria = session.createCriteria(FlatMst.class, "fm");
            criteria.createAlias("fm.projectMst", "pm");
            
            ProjectionList projectionList = Projections.projectionList();
            projectionList.add(Projections.property("fm.id"),"id");
            projectionList.add(Projections.property("pm.id"), "projectMstId");
            projectionList.add(Projections.property("pm.projectName"), "projectName");
            projectionList.add(Projections.property("fm.flatNo"), "flatNo");
            projectionList.add(Projections.property("fm.flatDesc"), "flatDesc");
            projectionList.add(Projections.property("fm.flatFacing"), "flatFacing");
            projectionList.add(Projections.property("fm.floorName"), "floorName");
            projectionList.add(Projections.property("fm.builtUpArea"), "builtUpArea");
            projectionList.add(Projections.property("fm.sprBuiltUpArea"), "sprBuiltUpArea");
            projectionList.add(Projections.property("fm.carpetArea"), "carpetArea");
            projectionList.add(Projections.property("fm.areaFlag"), "areaFlag");
            projectionList.add(Projections.property("fm.pricePerSqft"), "pricePerSqft");
            projectionList.add(Projections.property("fm.flatPrice"), "flatPrice");            
            projectionList.add(Projections.property("fm.otherChrgs"), "otherChrgs");
            projectionList.add(Projections.property("fm.noOfCarPark"), "noOfCarPark");
            projectionList.add(Projections.property("fm.carParkValue"), "carParkValue");
            projectionList.add(Projections.property("fm.totalPrice"), "totalPrice");
            projectionList.add(Projections.property("fm.bookingStatus"), "bookingStatus");
            projectionList.add(Projections.property("fm.bookingDate"), "bookingDate");
            projectionList.add(Projections.property("fm.customerMst.id"), "customerId");
            projectionList.add(Projections.property("fm.agentId"), "agentId");
            projectionList.add(Projections.property("fm.agrmntFSaleDate"), "agrmntFSaleDate");
            projectionList.add(Projections.property("fm.payScheduleFileId"), "payScheduleFileId");
            projectionList.add(Projections.property("fm.createdBy"), "createdBy");
            projectionList.add(Projections.property("fm.modifiedBy"), "modifiedBy");
            projectionList.add(Projections.property("fm.deletedBy"), "deletedBy");
            projectionList.add(Projections.property("fm.createdDate"), "createdDate");
            projectionList.add(Projections.property("fm.modifiedDate"), "modifiedDate");
            projectionList.add(Projections.property("fm.deletedDate"), "deletedDate");
            projectionList.add(Projections.property("fm.deleteFlag"), "deleteFlag");
            
            criteria.setProjection(projectionList);
            
            
            if(projectId != null){
            	criteria.add(Restrictions.eq("pm.id", projectId));
            }
            
            if(!(flatNo == null || flatNo.equals(""))){
            	criteria.add(Restrictions.eq("fm.flatNo", flatNo));
            }
            
            criteria.add(Restrictions.eq("fm.deleteFlag", "N"));
            criteria.setResultTransformer(Transformers.aliasToBean(FlatMstDto.class));            
            list = criteria.list();
            
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
	}

	@Override
	public List<FlatMstDto> findAvailableByProjectIdFlatNo(Long projectId,String flatNo) {
		Session session = null;
        List<FlatMstDto> list = null;
        try {
        	session = sessionFactory.openSession();
        	Criteria criteria = session.createCriteria(FlatMst.class, "fm");
            criteria.createAlias("fm.projectMst", "pm");
            criteria.createAlias("fm.flatPaySchds", "fps");
            
            ProjectionList projectionList = Projections.projectionList();
            projectionList.add(Projections.property("fm.id"),"id");
            projectionList.add(Projections.property("pm.id"), "projectMstId");
            projectionList.add(Projections.property("pm.projectName"), "projectName");
            projectionList.add(Projections.property("fm.flatNo"), "flatNo");
            projectionList.add(Projections.property("fm.flatDesc"), "flatDesc");
            projectionList.add(Projections.property("fm.flatFacing"), "flatFacing");
            projectionList.add(Projections.property("fm.floorName"), "floorName");
            projectionList.add(Projections.property("fm.builtUpArea"), "builtUpArea");
            projectionList.add(Projections.property("fm.sprBuiltUpArea"), "sprBuiltUpArea");
            projectionList.add(Projections.property("fm.carpetArea"), "carpetArea");
            projectionList.add(Projections.property("fm.areaFlag"), "areaFlag");
            projectionList.add(Projections.property("fm.pricePerSqft"), "pricePerSqft");
            projectionList.add(Projections.property("fm.flatPrice"), "flatPrice");            
            projectionList.add(Projections.property("fm.otherChrgs"), "otherChrgs");
            projectionList.add(Projections.property("fm.noOfCarPark"), "noOfCarPark");
            projectionList.add(Projections.property("fm.carParkValue"), "carParkValue");
            projectionList.add(Projections.property("fm.totalPrice"), "totalPrice");
            projectionList.add(Projections.property("fm.bookingStatus"), "bookingStatus");
            projectionList.add(Projections.property("fm.bookingDate"), "bookingDate");
            projectionList.add(Projections.property("fm.customerMst.id"), "customerId");
            projectionList.add(Projections.property("fm.agentId"), "agentId");
            projectionList.add(Projections.property("fm.agrmntFSaleDate"), "agrmntFSaleDate");
            projectionList.add(Projections.property("fm.payScheduleFileId"), "payScheduleFileId");
            projectionList.add(Projections.property("fm.finalFlatPrice"), "finalFlatPrice");
            projectionList.add(Projections.property("fm.createdBy"), "createdBy");
            projectionList.add(Projections.property("fm.modifiedBy"), "modifiedBy");
            projectionList.add(Projections.property("fm.deletedBy"), "deletedBy");
            projectionList.add(Projections.property("fm.createdDate"), "createdDate");
            projectionList.add(Projections.property("fm.modifiedDate"), "modifiedDate");
            projectionList.add(Projections.property("fm.deletedDate"), "deletedDate");
            projectionList.add(Projections.property("fm.deleteFlag"), "deleteFlag");
            
            criteria.setProjection(Projections.distinct(projectionList));
            
            
            if(projectId != null){
            	criteria.add(Restrictions.eq("pm.id", projectId));
            }
            
            if(!(flatNo == null || flatNo.equals(""))){
            	criteria.add(Restrictions.eq("fm.flatNo", flatNo));
            }
            
            criteria.add(Restrictions.eq("fm.deleteFlag", "N"));
            criteria.add(Restrictions.isNull("fm.bookingStatus"));
            criteria.add(Restrictions.isNotNull("fm.finalFlatPrice"));
            criteria.add(Restrictions.isNotNull("fps.payScheType"));
            criteria.setResultTransformer(Transformers.aliasToBean(FlatMstDto.class));            
            list = criteria.list();
            
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
	}

	@Override
	public List<FlatMstDto> findForAggrementByCustomerId(Long customerId,String flatNo) {
		Session session = null;
        List<FlatMstDto> list = null;
        try {
        	session = sessionFactory.openSession();
        	Criteria criteria = session.createCriteria(FlatMst.class, "fm");
            criteria.createAlias("fm.projectMst", "pm");
            
            ProjectionList projectionList = Projections.projectionList();
            projectionList.add(Projections.property("fm.id"),"id");
            projectionList.add(Projections.property("pm.id"), "projectMstId");
            projectionList.add(Projections.property("pm.projectName"), "projectName");
            projectionList.add(Projections.property("fm.flatNo"), "flatNo");
            projectionList.add(Projections.property("fm.flatDesc"), "flatDesc");
            projectionList.add(Projections.property("fm.flatFacing"), "flatFacing");
            projectionList.add(Projections.property("fm.floorName"), "floorName");
            projectionList.add(Projections.property("fm.builtUpArea"), "builtUpArea");
            projectionList.add(Projections.property("fm.sprBuiltUpArea"), "sprBuiltUpArea");
            projectionList.add(Projections.property("fm.carpetArea"), "carpetArea");
            projectionList.add(Projections.property("fm.areaFlag"), "areaFlag");
            projectionList.add(Projections.property("fm.pricePerSqft"), "pricePerSqft");
            projectionList.add(Projections.property("fm.flatPrice"), "flatPrice");
            projectionList.add(Projections.property("fm.finalFlatPrice"), "finalFlatPrice");
            projectionList.add(Projections.property("fm.otherChrgs"), "otherChrgs");
            projectionList.add(Projections.property("fm.noOfCarPark"), "noOfCarPark");
            projectionList.add(Projections.property("fm.carParkValue"), "carParkValue");
            projectionList.add(Projections.property("fm.totalPrice"), "totalPrice");
            projectionList.add(Projections.property("fm.bookingStatus"), "bookingStatus");
            projectionList.add(Projections.property("fm.bookingDate"), "bookingDate");
            projectionList.add(Projections.property("fm.customerMst.id"), "customerId");
            projectionList.add(Projections.property("fm.agentId"), "agentId");
            projectionList.add(Projections.property("fm.agrmntFSaleDate"), "agrmntFSaleDate");
            projectionList.add(Projections.property("fm.payScheduleFileId"), "payScheduleFileId");
            projectionList.add(Projections.property("fm.createdBy"), "createdBy");
            projectionList.add(Projections.property("fm.modifiedBy"), "modifiedBy");
            projectionList.add(Projections.property("fm.deletedBy"), "deletedBy");
            projectionList.add(Projections.property("fm.createdDate"), "createdDate");
            projectionList.add(Projections.property("fm.modifiedDate"), "modifiedDate");
            projectionList.add(Projections.property("fm.deletedDate"), "deletedDate");
            projectionList.add(Projections.property("fm.deleteFlag"), "deleteFlag");
            
            criteria.setProjection(projectionList);
            
            criteria.add(Restrictions.eq("fm.customerMst.id", customerId));
            
            if(!(flatNo == null || flatNo.equals(""))){
            	criteria.add(Restrictions.eq("fm.flatNo", flatNo));
            }
            
            criteria.add(Restrictions.eq("fm.deleteFlag", "N"));
            criteria.add(Restrictions.eq("fm.bookingStatus","Y"));
            criteria.add(Restrictions.isNull("fm.agrmntFSaleDate"));
            
            criteria.setResultTransformer(Transformers.aliasToBean(FlatMstDto.class));            
            list = criteria.list();
            
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
	}

	@Override
	public String saveOrUpdate(FlatMst flatMst) {
		String status = null;
		Session session = null;
		Transaction tx = null;
		try{
			 session = sessionFactory.openSession();
			 tx = session.beginTransaction();
			 session.saveOrUpdate(flatMst);
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
	public String delete(Long flatId, Long userId) {
		String status = null;
		Session session = null;
		Transaction tx = null;
		try{
			 session = sessionFactory.openSession();
			 tx = session.beginTransaction();
			 Query query = session.createQuery("UPDATE FlatMst SET deleteFlag = :deleteFlag , deletedDate = :deletedDate , deletedBy = :userId "
			 		+ "WHERE id = :flatId");
			 
			 query.setParameter("deleteFlag", "Y");
			 query.setParameter("deletedDate", new Date());
			 query.setParameter("userId", userId);
			 query.setParameter("flatId", flatId);			 
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
	public String saveOrUpdateForFlatEntry(FlatMst flatMst) {
		String status = null;
		Session session = null;
		Transaction tx = null;
		try{
			 session = sessionFactory.openSession();
			 tx = session.beginTransaction();
			 
			 Criteria criteria = session.createCriteria(ProjectMilestone.class);
			 criteria.add(Restrictions.eq("deleteFlag", "N"));
			 criteria.add(Restrictions.eq("projectMst",flatMst.getProjectMst()));			 
			 List<ProjectMilestone> projectMilestoneList = criteria.list();			 
			 session.saveOrUpdate(flatMst);
			 
			 session.createQuery("update FlatPaySchd set deleteFlag = 'Y' where flatMst = :flatMst ")
			 	.setParameter("flatMst", flatMst).executeUpdate();
			 
			 
			 for(ProjectMilestone obj:projectMilestoneList){
				 FlatPaySchd flatPaySchd = new FlatPaySchd();
				 flatPaySchd.setFlatMst(flatMst);
				 flatPaySchd.setFlatPaySchdType("S");
				 flatPaySchd.setProjectMilestone(obj);
				 flatPaySchd.setEventTimeFlag("E");
				 flatPaySchd.setPaymentStatus("N");
				 flatPaySchd.setDeleteFlag("N");
				 session.save(flatPaySchd);
			 }
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
	public String saveOrUpdateForFlatNegotiation(FlatMst flatMst) {
		String status = null;
		Session session = null;
		Transaction tx = null;
		try{
			 session = sessionFactory.openSession();
			 tx = session.beginTransaction();
			 
			 session.update(flatMst);
			 
			 Criteria criteria = session.createCriteria(ProjectMilestone.class);
			 criteria.add(Restrictions.eq("projectMst", flatMst.getProjectMst()));
			 criteria.add(Restrictions.eq("deleteFlag", "N"));
			 
			 List<ProjectMilestone> list = criteria.list();
			 
			 for(ProjectMilestone obj : list){
				 Query query = session.createQuery("UPDATE FlatPaySchd SET paymentRcvbl = :paymentRcvbl , payScheType = :payScheType "
				 		+ "WHERE flatMst = :flatMst and projectMilestone = :projectMilestone and deleteFlag = :deleteFlag");
				 
				 query.setParameter("paymentRcvbl", ((flatMst.getFinalFlatPrice() * obj.getPaymentPerc())/100));
				 query.setParameter("payScheType", flatMst.getPaySchdType());
				 query.setParameter("flatMst", flatMst);
				 query.setParameter("projectMilestone", obj);
				 query.setParameter("deleteFlag", "N");
				 query.executeUpdate();
			 }
			 
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
}