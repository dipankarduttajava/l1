package com.gtfs.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.gtfs.bean.RoleMst;
import com.gtfs.dao.interfaces.RoleMstDao;

@Repository
public class RoleMstDaoImpl implements RoleMstDao,Serializable {
	@Autowired
	private SessionFactory sessionFactory;
	

	public List<RoleMst> findAllActiveRole() {
		Session session = null;
		List<RoleMst> list = null;
		try {
			session = sessionFactory.openSession();
			Query query = session.createQuery("FROM RoleMst "
							+ "WHERE activeFlag = :activeFlag "
							+ "ORDER BY roleId asc");
			query.setParameter("activeFlag", "Y");
			list = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}

	public List<RoleMst> findRoleByRoleName(String roleName) {
		Session session = null;
		List<RoleMst> list = null;
		try {
			session = sessionFactory.openSession();
			Query query = session.createQuery("FROM RoleMst"
					+ " WHERE activeFlag = :activeFlag " 
					+ " AND upper(roleName) like upper(:roleName)"
					+ " ORDER BY roleId asc" );
			query.setParameter("activeFlag", "Y");
			query.setParameter("roleName", "%"+roleName+"%");
			list = query.list();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}

	public RoleMst findRoleById(Long roleId) {
		RoleMst roleMst = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			roleMst = (RoleMst) session.get(RoleMst.class, roleId);

		} catch (Exception e) {
		} finally {
			session.close();
		}
		return roleMst;
	}

	public Boolean deleteRoleMst(Long roleId, Long userId) {
		Boolean status = false;
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Query query = session.createQuery("UPDATE RoleMst SET activeFlag = :activeFlag, userId = :userId WHERE roleId = :roleId");
			query.setParameter("activeFlag", "N");
			query.setParameter("userId", userId);
			query.setParameter("roleId", roleId);
			int rows = query.executeUpdate();
			if (rows > 0) {
				tx.commit();
				status = true;
			} else {
				tx.rollback();
				status = false;
			}
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			status = false;
		} finally {
			session.close();
		}
		return status;
	}

	public Boolean saveRoleMst(List<RoleMst> roleMstList) {
		Boolean status = false;
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			 tx = session.beginTransaction();
			
			for (RoleMst obj : roleMstList) {
				session.saveOrUpdate(obj);
			}

			tx.commit();
			status = true;
		} catch (Exception e) {
			e.printStackTrace();
			status = false;
		} finally {
			session.close();
		}
		return status;
	}
}
