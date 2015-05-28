package com.gtfs.dao;

import java.util.List;

import com.gtfs.dto.FlatInvoiceComboDto;
import com.gtfs.pojo.FlatInvoiceCombo;

public interface FlatInvoiceComboDao {
	List<FlatInvoiceComboDto> findByInvoiceNo(String invoiceNo);
	FlatInvoiceCombo findById(Long flatInvoiceComboId);
}
