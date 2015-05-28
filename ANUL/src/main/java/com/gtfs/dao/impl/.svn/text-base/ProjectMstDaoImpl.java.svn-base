package com.gtfs.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gtfs.dao.ProjectMstDao;
import com.gtfs.dto.ProjectMstDto;
import com.gtfs.pojo.ProjectMst;

@Repository
public class ProjectMstDaoImpl implements ProjectMstDao, Serializable {

	@Autowired
	private SessionFactory sessionFactory;
		
	@Override
	public List<ProjectMstDto> findAll() {
		Session session = null;
		List<ProjectMstDto> list = null;
		try{
			session = sessionFactory.openSession();
			Query query = session.createQuery(
					"SELECT "
					+ "PM.id as id, "
					+ "PM.projectName as projectName, "
					+ "PM.address as address, "
					+ "PM.noOfFloor as noOfFloor, "
					+ "PM.noOfFlat as noOfFlat,"
					+ "PM.noOfCarPark as noOfCarPark, "
					+ "PM.noOfFlatAvlbl as noOfFlatAvlbl, "
					+ "PM.noOfCarParkAvlbl as noOfCarParkAvlbl, "
					+ "PM.milestoneCompltd as milestoneCompltd, "
					+ "PM.chqInFavour as chqInFavour, "
					+ "PM.createdBy as createdBy, "
					+ "PM.modifiedBy as modifiedBy, "
					+ "PM.deletedBy as deletedBy, "
					+ "PM.createdDate as createdDate, "
					+ "PM.modifiedDate as modifiedDate, "
					+ "PM.deletedDate as deletedDate, "
					+ "PM.deleteFlag as deleteFlag "
					
					+ "FROM "
					+ "ProjectMst as PM "
					
					+ "WHERE "
					+ "PM.deleteFlag = 'N'");
			
            query.setResultTransformer(Transformers.aliasToBean(ProjectMstDto.class));
            
            list = query.list();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				session.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public ProjectMst findById(Long id) {
		ProjectMst projectMst = null;
	 	Session session = null;
	    try {
	        session = sessionFactory.openSession();
	        projectMst = (ProjectMst) session.get(ProjectMst.class, id);	            
	    } catch (Exception e) {
	    	e.printStackTrace();
	    } finally {
	        session.close();
	    }
	    return projectMst;
	}

	@Override
	public Long insert(ProjectMst projectMst) {
		Long status = 0l;
		Session session = null;
		Transaction tx = null;
		try{
			 session = sessionFactory.openSession();
			 tx = session.beginTransaction();
			 status = (Long) session.save(projectMst);
			 tx.commit();
		}catch(Exception e){
			status = 0l;
			e.printStackTrace();
			if(tx != null){
				tx.rollback();
			}
		}finally {
			try{
				session.close();
			}catch(Exception e){
				e.printStackTrace();
				if(tx != null){
					tx.rollback();
				}
			}
        }
		return status;
	}

	@Override
	public Integer update(ProjectMst projectMst) {
		Integer status = 0;
		Session session = null;
		Transaction tx = null;
		try{
			 session = sessionFactory.openSession();
			 tx = session.beginTransaction();
			 Query query = session.createQuery("UPDATE ProjectMst SET "
			 		+ "projectName  = :projectName, "
			 		+ "address = :address, "
			 		+ "noOfFloor = :noOfFloor, "
			 		+ "noOfFlat  = :noOfFlat, "
			 		+ "noOfCarPark = :noOfCarPark, "
			 		+ "noOfFlatAvlbl = :noOfFlatAvlbl, "
			 		+ "noOfCarParkAvlbl = :noOfCarParkAvlbl, "
			 		+ "milestoneCompltd = :milestoneCompltd, "
			 		+ "chqInFavour = :chqInFavour, "
			 		+ "createdBy = :createdBy, "
			 		+ "modifiedBy = :modifiedBy, "
			 		+ "deletedBy = :deletedBy, "
			 		+ "createdDate = :createdDate, "
			 		+ "modifiedDate = :modifiedDate, "
			 		+ "deletedDate = :deletedDate, "
			 		+ "deleteFlag = :deleteFlag "
			 		
			 		+ "WHERE "
			 		+ "id = :id");
			 
			 query.setParameter("id", projectMst.getId());
			 query.setParameter("projectName", projectMst.getProjectName());
			 query.setParameter("address", projectMst.getAddress());
			 query.setParameter("noOfFloor", projectMst.getNoOfFloor());
			 query.setParameter("noOfFlat", projectMst.getNoOfFlat());
			 query.setParameter("noOfCarPark", projectMst.getNoOfCarPark());
			 query.setParameter("noOfFlatAvlbl", projectMst.getNoOfFlatAvlbl());
			 query.setParameter("noOfCarParkAvlbl", projectMst.getNoOfCarParkAvlbl());
			 query.setParameter("milestoneCompltd", projectMst.getMilestoneCompltd());
			 query.setParameter("chqInFavour", projectMst.getChqInFavour());
			 query.setParameter("createdBy", projectMst.getCreatedBy());
			 query.setParameter("modifiedBy", projectMst.getModifiedBy());
			 query.setParameter("deletedBy", projectMst.getDeletedBy());
			 query.setParameter("createdDate", projectMst.getCreatedDate());
			 query.setParameter("modifiedDate", projectMst.getModifiedDate());
			 query.setParameter("deletedDate", projectMst.getDeletedDate());
			 query.setParameter("deleteFlag", projectMst.getDeleteFlag());
			 status = query.executeUpdate();
			 tx.commit();
		}catch(Exception e){
			status = 0;
			e.printStackTrace();
			if(tx != null){
				tx.rollback();
			}
		}finally {
			try{
				session.close();
			}catch(Exception e){
				e.printStackTrace();
				if(tx != null){
					tx.rollback();
				}
			}
        }
		return status;
	}

	@Override
	public Integer delete(ProjectMst projectMst) {
		Integer status = 0;
		Session session = null;
		Transaction tx = null;
		try{
			 session = sessionFactory.openSession();
			 tx = session.beginTransaction();
			 Query query = session.createQuery("UPDATE ProjectMst SET "
			 		+ "deletedBy = :deletedBy, "
			 		+ "deletedDate = :deletedDate, "
			 		+ "deleteFlag = :deleteFlag "
			 		
			 		+ "WHERE "
			 		+ "id = :id");
			 
			 query.setParameter("id", projectMst.getId());
			 query.setParameter("deletedBy", projectMst.getDeletedBy());
			 query.setParameter("deletedDate", projectMst.getDeletedDate());
			 query.setParameter("deleteFlag", "Y");
			 status = query.executeUpdate();
			 tx.commit();
		}catch(Exception e){
			status = 0;
			e.printStackTrace();
			if(tx != null){
				tx.rollback();
			}
		}finally {
			try{
				session.close();
			}catch(Exception e){
				e.printStackTrace();
				if(tx != null){
					tx.rollback();
				}
			}
        }
		return status;
	}

	@Override
	public Boolean saveOrUpdate(ProjectMst projectMst) {
		Boolean status = false;
		Session session = null;
		Transaction tx = null;
		try{
			 session = sessionFactory.openSession();
			 tx = session.beginTransaction();
			 session.saveOrUpdate(projectMst);
			 tx.commit();
			 status = true;
		}catch(Exception e){
			status = false;
			if(tx != null){
				tx.rollback();
			}
		}finally {
			try{
				session.close();
			}catch(Exception e){
				e.printStackTrace();
				if(tx != null){
					tx.rollback();
				}
			}
        }
		return status;
	}
}
