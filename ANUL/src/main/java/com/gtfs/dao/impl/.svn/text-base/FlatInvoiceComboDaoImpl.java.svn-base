package com.gtfs.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gtfs.dao.FlatInvoiceComboDao;
import com.gtfs.dto.FlatInvoiceComboDto;
import com.gtfs.pojo.FlatInvoiceCombo;

@Repository
public class FlatInvoiceComboDaoImpl implements Serializable, FlatInvoiceComboDao {
	private Logger log = Logger.getLogger(FlatMstDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<FlatInvoiceComboDto> findByInvoiceNo(String invoiceNo) {
		Session session = null;
        List<FlatInvoiceComboDto> list = null;
        try {
        	session = sessionFactory.openSession();
        	Criteria criteria = session.createCriteria(FlatInvoiceCombo	.class, "fic");
        	criteria.createAlias("fic.flatPaySchd", "fps");
            criteria.createAlias("fps.projectMilestone", "pmil");
            criteria.createAlias("fps.flatMst", "fm");
            criteria.createAlias("fm.projectMst", "pm");
            
            ProjectionList projectionList = Projections.projectionList();
            projectionList.add(Projections.property("fic.id"),"id");
            projectionList.add(Projections.property("fic.invoiceNo"),"invoiceNo");
            projectionList.add(Projections.property("fic.invoiceDate"),"invoiceDate");
            projectionList.add(Projections.property("fic.invoiceAmt"),"invoiceAmt");
            
            projectionList.add(Projections.property("fps.id"), "flatPaySchdId");
            
            projectionList.add(Projections.property("pmil.id"), "projectMilestoneId");
            projectionList.add(Projections.property("pmil.milestoneDesc"), "projectMilestoneDesc");
            
            projectionList.add(Projections.property("fm.id"),"flatMstId");
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
            projectionList.add(Projections.property("fm.agrmntFSaleDate"), "agrmntFSaleDate");
            projectionList.add(Projections.property("fm.payScheduleFileId"), "payScheduleFileId");

            projectionList.add(Projections.property("pm.id"), "projectMstId");
            projectionList.add(Projections.property("pm.projectName"), "projectName");
            
            criteria.setProjection(projectionList);
            
            
            if(invoiceNo != null){
            	criteria.add(Restrictions.like("fic.invoiceNo", invoiceNo, MatchMode.ANYWHERE));
            }

            criteria.add(Restrictions.eq("fic.deleteFlag", "N"));
            criteria.add(Restrictions.or(Restrictions.isNull("fic.fullAmtPaidFlag"), Restrictions.eq("fic.fullAmtPaidFlag", "N")));
            criteria.setResultTransformer(Transformers.aliasToBean(FlatInvoiceComboDto.class));            
            list = criteria.list();
            
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
	}

	@Override
	public FlatInvoiceCombo findById(Long flatInvoiceComboId) {
		FlatInvoiceCombo flatInvoiceCombo = null;
	 	Session session = null;
	    try {
	        session = sessionFactory.openSession();
	        flatInvoiceCombo = (FlatInvoiceCombo) session.get(FlatInvoiceCombo.class, flatInvoiceComboId);	            
	    } catch (Exception e) {
	    	e.printStackTrace();
	    } finally {
	        session.close();
	    }
	    return flatInvoiceCombo;
	}

}
