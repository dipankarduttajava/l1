package com.gtfs.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtfs.dao.FlatInvoiceComboDao;
import com.gtfs.dto.FlatInvoiceComboDto;
import com.gtfs.service.FlatInvoiceComboService;

@Service
public class FlatInvoiceComboServiceImpl implements FlatInvoiceComboService, Serializable {

	@Autowired
	private FlatInvoiceComboDao flatInvoiceComboDao;
	
	@Override
	public List<FlatInvoiceComboDto> findByInvoiceNo(String invoiceNo) {
		return flatInvoiceComboDao.findByInvoiceNo(invoiceNo);
	}

}
