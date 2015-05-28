package com.gtfs.dto;

import java.io.Serializable;
import java.util.List;

public class ReceiptDtlsWrapper implements Serializable {
	private List<ReceiptDtlsDto> receiptDtlsDtos;

	public List<ReceiptDtlsDto> getReceiptDtlsDtos() {
		return receiptDtlsDtos;
	}

	public void setReceiptDtlsDtos(List<ReceiptDtlsDto> receiptDtlsDtos) {
		this.receiptDtlsDtos = receiptDtlsDtos;
	}

	
	
	
}
