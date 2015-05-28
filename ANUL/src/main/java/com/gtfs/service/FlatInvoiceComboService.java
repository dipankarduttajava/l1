package com.gtfs.service;

import java.util.List;

import com.gtfs.dto.FlatInvoiceComboDto;


public interface FlatInvoiceComboService {
	List<FlatInvoiceComboDto> findByInvoiceNo(String invoiceNo);
}
