package com.gtfs.dto;

import java.io.Serializable;

public class PrintRcptMstDto implements Serializable{
	private Long insCompyId;
	private Long branchId;
	private Long tieupCompyId;
	private String prefix;
	private long receiptFrom;
	private Long receiptTo;
	public Long getInsCompyId() {
		return insCompyId;
	}
	public void setInsCompyId(Long insCompyId) {
		this.insCompyId = insCompyId;
	}
	public Long getBranchId() {
		return branchId;
	}
	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}
	public Long getTieupCompyId() {
		return tieupCompyId;
	}
	public void setTieupCompyId(Long tieupCompyId) {
		this.tieupCompyId = tieupCompyId;
	}
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public long getReceiptFrom() {
		return receiptFrom;
	}
	public void setReceiptFrom(long receiptFrom) {
		this.receiptFrom = receiptFrom;
	}
	public Long getReceiptTo() {
		return receiptTo;
	}
	public void setReceiptTo(Long receiptTo) {
		this.receiptTo = receiptTo;
	}
	
	
}
