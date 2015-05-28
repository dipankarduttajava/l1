package com.gtfs.dao;

import java.util.List;

import com.gtfs.pojo.ReceiptDtls;
import com.gtfs.pojo.ReceiptMst;

public interface ReceiptDtlsDao {
	String saveReceiptDtls(ReceiptMst receiptMst, List<ReceiptDtls> receiptDtlses);
}
