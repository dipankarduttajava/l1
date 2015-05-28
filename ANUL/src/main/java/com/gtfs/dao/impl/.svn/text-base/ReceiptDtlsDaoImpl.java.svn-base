package com.gtfs.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gtfs.dao.ReceiptDtlsDao;
import com.gtfs.pojo.ReceiptDtls;
import com.gtfs.pojo.ReceiptMst;

@Repository
public class ReceiptDtlsDaoImpl implements ReceiptDtlsDao, Serializable {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public String saveReceiptDtls(ReceiptMst receiptMst, List<ReceiptDtls> receiptDtlses){
		Session session = null;
		Transaction tx = null;
		String status = "false";
        try {            
        	session = sessionFactory.openSession();     
        	tx = session.beginTransaction();
        	session.save(receiptMst);
        	
        	Double amount = 0.0;
        	
        	for(ReceiptDtls obj : receiptDtlses){
        		amount += obj.getChddAmount();
        		obj.setReceiptMst(receiptMst);
        		session.save(obj);
        	}
        	
        	if(receiptMst.getFlatInvoiceCombo().getInvoiceAmt() <= amount){
        		Query query = session.createQuery("update FlatInvoiceCombo set fullAmtPaidFlag = :fullAmtPaidFlag where id = :id");
        		query.setParameter("fullAmtPaidFlag", "Y");
        		query.setParameter("id", receiptMst.getFlatInvoiceCombo().getId());
        		query.executeUpdate();
        	}
        	
            
            tx.commit();
            status = "true";
        } catch (Exception e) {
        	if(tx !=null){
        		tx.rollback();
        		status = "false";
        	}
        	e.printStackTrace();
        } finally {
            session.close();
        }
        return status;
	}

}
