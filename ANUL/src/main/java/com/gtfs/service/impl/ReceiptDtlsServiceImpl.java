package com.gtfs.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtfs.dao.FlatInvoiceComboDao;
import com.gtfs.dao.FlatPaySchdDao;
import com.gtfs.dao.ReceiptDtlsDao;
import com.gtfs.dao.impl.FlatMstDaoImpl;
import com.gtfs.dto.ReceiptDtlsDto;
import com.gtfs.pojo.FlatInvoiceCombo;
import com.gtfs.pojo.FlatPaySchd;
import com.gtfs.pojo.ReceiptDtls;
import com.gtfs.pojo.ReceiptMst;
import com.gtfs.service.ReceiptDtlsService;

@Service
public class ReceiptDtlsServiceImpl implements ReceiptDtlsService, Serializable{
	private Logger log = Logger.getLogger(ReceiptDtlsServiceImpl.class);
	
	@Autowired
	private FlatInvoiceComboDao flatInvoiceComboDao;
	@Autowired
	private FlatPaySchdDao flatPaySchdDao;
	@Autowired
	private ReceiptDtlsDao receiptDtlsDao;

	@Override
	public String saveReceiptDtls(List<ReceiptDtlsDto> receiptDtlsDtos) {
		try{			
			Date now = new Date();
			FlatInvoiceCombo flatInvoiceCombo = flatInvoiceComboDao.findById(receiptDtlsDtos.get(0).getFlatInvoiceComboId());
			FlatPaySchd flatPaySchd = flatPaySchdDao.findById(receiptDtlsDtos.get(0).getFlatPaySchdId());
			
			ReceiptMst receiptMst = new ReceiptMst();
			receiptMst.setPaymantRcvd("Y");
			receiptMst.setDeleteFlag("N");
			receiptMst.setCreatedBy(receiptDtlsDtos.get(0).getCreatedBy());
			receiptMst.setCreatedDate(now);
			receiptMst.setModifiedBy(receiptDtlsDtos.get(0).getCreatedBy());
			receiptMst.setModifiedDate(now);
			receiptMst.setFlatInvoiceCombo(flatInvoiceCombo);
			receiptMst.setFlatPaySchd(flatPaySchd);
			
			String receiptMode = "";
			
			List<ReceiptDtls> receiptDtlses = new ArrayList<ReceiptDtls>();			
			for(ReceiptDtlsDto obj: receiptDtlsDtos){
				ReceiptDtls receiptDtls = new ReceiptDtls();
				receiptDtls.setRcptMode(obj.getRcptMode());
				receiptDtls.setChdNo(obj.getChdNo());
				receiptDtls.setFavourOf(obj.getFavourOf());
				receiptDtls.setChddBank(obj.getChddBank());
				receiptDtls.setChddBankBranch(obj.getChddBankBranch());
				receiptDtls.setChddDate(obj.getChddDate());
				receiptDtls.setChddAmount(obj.getChddAmount());
				receiptDtls.setDeleteFlag("N");
				receiptDtls.setCreatedBy(receiptDtlsDtos.get(0).getCreatedBy());
				receiptDtls.setModifiedBy(receiptDtlsDtos.get(0).getCreatedBy());
				receiptDtls.setCreatedDate(now);
				receiptDtls.setModifiedDate(now);				
				
				if(receiptMode.equals("")){
					receiptMst.setRcptMode(receiptDtls.getRcptMode());
					receiptMode = receiptDtls.getRcptMode();
				}else{
					if(!receiptDtls.getRcptMode().equals(receiptMode)){
						receiptMst.setRcptMode("B");
					}
				}
				receiptDtlses.add(receiptDtls);
			}
			return receiptDtlsDao.saveReceiptDtls(receiptMst, receiptDtlses);
		}catch(Exception e){
			log.info("Error : ", e);
			return null;
		}
	}
}
