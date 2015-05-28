package com.gtfs.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.gtfs.bean.UserMst;
import com.gtfs.dao.interfaces.UserMstDao;

@Repository
public class UserMstDaoImpl implements UserMstDao,Serializable{
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<UserMst> login(String loginId, String password) {
        Session session = null;
        List<UserMst> list = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(UserMst.class);
            criteria.createAlias("branchMst", "bm");
            criteria.add(Restrictions.eq("login", loginId));
            criteria.add(Restrictions.eq("passwd", password));
            criteria.add(Restrictions.eq("activeFlag", "Y"));
            list = criteria.list();

        } catch (Exception re) {
            re.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }
	
	public List<UserMst> findActiveUserByUserId(Long userId) {
        Session session = null;
        List<UserMst> list = null;
        try {
            session = sessionFactory.openSession();
            Criteria crit = session.createCriteria(UserMst.class);
            crit.add(Restrictions.eq("userid", userId));
            crit.add(Restrictions.eq("activeFlag", "Y"));
            list = crit.list();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }	
	
	public UserMst findById(Serializable id) {
        Session session = null;
        Transaction tx = null;
        UserMst userMst = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            userMst = (UserMst) session.get(UserMst.class, id);
            tx.commit();
        } catch (RuntimeException re) {
        	if (tx!=null) tx.rollback();
        } finally {
            session.close();
        }
        return userMst;
    }
	
	 public Long save(UserMst userMst) {
		 	Long id=0l;
	        Session session = null;
	        Transaction tx = null;
	        try {
	            session = sessionFactory.openSession();
	            tx = session.beginTransaction();
	            id = (Long) session.save(userMst);
	            tx.commit();
	        } catch (RuntimeException re) {
	            if (tx != null) {
	                tx.rollback();
	            }
	        } finally {
	            session.close();
	        }
	        return id;
	    }
	  
	 public Boolean update(UserMst userMst) {
		 	Boolean status = false;
	        Session session = null;
	        Transaction tx = null;
	        try {
	            session = sessionFactory.openSession();
	            tx = session.beginTransaction();
	            session.update(userMst);
	            tx.commit();
	            status=true;
	        } catch (RuntimeException re) {
	            if (tx != null) {
	                tx.rollback();
	            }
	            status=false;
	        } finally {
	            session.close();
	        }
	        return status;
	    }
	
	 public List<UserMst> findActiveUserInfoByUserId(Long userId) {
	        Session session = null;
	        List<UserMst> list = null;
	        try {
	            session = sessionFactory.openSession();            
	            Query query = session.createQuery("FROM UserMst as um "
	            		+ " inner join fetch um.designationMst as dgm "
	            		+ " left join fetch um.hoMst as hm "
	            		+ " left join fetch um.regionMst as rm "
	            		+ " inner join fetch um.divisionMst as dm "
	            		+ " inner join fetch um.branchMst as bm "
	            		+ " inner join fetch um.tieupCompyMst as tcm "
	                    + "WHERE um.activeFlag = 'Y' "
	                    + "and dgm.activeFlag = 'Y' "
	                    + "and dm.activeFlag = 'Y' "
	                    + "and bm.activeFlag = 'Y' "
	                    + "and tcm.activeFlag = 'Y' "
	                    + "and um.userid = :userId");
	            query.setParameter("userId", userId);
	            list = query.list();
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            session.close();
	        }
	        return list;
	    }
	 
	 public List<UserMst> findActiveUserInfoByUserName(String userName) {
	        Session session = null;
	        List<UserMst> list = null;
	        try {
	            session = sessionFactory.openSession();            
	            Query query = session.createQuery("FROM UserMst as um "
	            		+ " inner join fetch um.designationMst as dgm "
	            		+ " left join fetch um.hoMst as hm "
	            		+ " left join fetch um.regionMst as rm "
	            		+ " inner join fetch um.divisionMst as dm "
	            		+ " inner join fetch um.branchMst as bm "
	            		+ " inner join fetch um.tieupCompyMst as tcm "
	                    + "WHERE um.activeFlag = 'Y' "
	                    + "and dgm.activeFlag = 'Y' "
	                    + "and dm.activeFlag = 'Y' "
	                    + "and bm.activeFlag = 'Y' "
	                    + "and tcm.activeFlag = 'Y' "
	                    + "and um.userName = :userName");
	            query.setParameter("userName", userName);
	            list = query.list();
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            session.close();
	        }
	        return list;
	    }
	 
	 public List<UserMst> findAllActiveUserInfo() {
	        Session session = null;
	        List<UserMst> list = null;
	        try {
	            session = sessionFactory.openSession();            
	            Query query = session.createQuery("FROM UserMst as um "
	            		+ " inner join fetch um.designationMst as dgm "
	            		+ " left join fetch um.hoMst as hm "
	            		+ " left join fetch um.regionMst as rm "
	            		+ " inner join fetch um.divisionMst as dm "
	            		+ " inner join fetch um.branchMst as bm "
	            		+ " inner join fetch um.tieupCompyMst as tcm "
	                    + "WHERE um.activeFlag = 'Y' "
	                    + "and dgm.activeFlag = 'Y' "
	                    + "and dm.activeFlag = 'Y' "
	                    + "and bm.activeFlag = 'Y' "
	                    + "and tcm.activeFlag = 'Y' "
	                    + "ORDER BY um.userid");
	            list = query.list();
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            session.close();
	        }
	        return list;
	    }
	 
		public Boolean deleteUserMst(Long userId, Long userLoginId) {
			Boolean status = false;
			Session session = null;
			Transaction tx = null;
			try {
				session = sessionFactory.openSession();
				tx = session.beginTransaction();
				Query query = session.createQuery("UPDATE UserMst SET activeFlag = :activeFlag, userId = :userLoginId WHERE userid = :userId");
				query.setParameter("activeFlag", "N");
				query.setParameter("userLoginId", userLoginId);
				query.setParameter("userId", userId);
				int rows = query.executeUpdate();
				if (rows > 0) {
					tx.commit();
					status = true;
				} else {
					tx.rollback();
					status = false;
				}
			} catch (Exception e) {
				e.printStackTrace();
				if (tx != null)
					tx.rollback();
				status = false;
			} finally {
				session.close();
			}
			return status;
		}
}
