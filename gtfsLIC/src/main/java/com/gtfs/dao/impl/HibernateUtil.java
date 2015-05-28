package com.gtfs.dao.impl;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil implements Serializable{
	private static final SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;
	private static Logger log = Logger.getLogger(HibernateUtil.class);
    
    static {
        try {        	
        	 Configuration configuration = new Configuration().configure();
        	 serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        	 sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        	
        } catch (Throwable ex) {
        	log.info("HibernateUtil Error", ex);
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
