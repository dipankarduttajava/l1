package com.gtfs.action;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gtfs.bean.LicHubMst;
import com.gtfs.bean.LicOblApplicationMst;
import com.gtfs.bean.LicPaymentDtls;
import com.gtfs.bean.LicPremApplMapping;
import com.gtfs.bean.LicPremPaymentDtls;
import com.gtfs.bean.LicPremiumListDtls;
import com.gtfs.bean.PicBranchMst;
import com.gtfs.service.interfaces.LicPaymentDtlsService;
import com.gtfs.service.interfaces.LicPremiumListService;
import com.gtfs.service.interfaces.PicBranchMstService;
@Component
@Scope("session")
public class PremiumListReportAction implements Serializable{
	private Logger log = Logger.getLogger(PremiumListReportAction.class);
	

	@Autowired
	private LoginAction loginAction;
	@Autowired
	private LicPremiumListService licPremiumListService;
	@Autowired
	private LicPaymentDtlsService licPaymentDtlsService;
	
	private Date businessFromDate;
	private Date businessToDate;
	private Long premListNo;
	private Boolean renderedPanel;
	private Double totalAmount;
	
	
	private List<LicOblApplicationMst> licOblApplicationMstList = new ArrayList<LicOblApplicationMst>();
	private List<Long> premListNos = new ArrayList<Long>();
	
	public void refresh(){
		
		if(premListNos != null){
			premListNos.clear();
		}
		if(licOblApplicationMstList!=null){
			licOblApplicationMstList.clear();
		}
		businessFromDate = null;
		businessToDate = null;
		renderedPanel = false;
	}
	
	public String onLoad(){
		refresh();
		return "/licHubActivity/premiumListReport.xhtml";
	}
	
	public void businessDateChangeListener(){
		premListNos = licPremiumListService.findPremiumListByBusinessDate(loginAction.findHubForProcess("OBL"),businessFromDate, businessToDate);
	}
	
	
	
	public void onSearch(){
		try{
			totalAmount = 0.0;
			licOblApplicationMstList = licPremiumListService.findPremiumListReport(loginAction.findHubForProcess("OBL"), businessFromDate, businessToDate,premListNo);

			if(licOblApplicationMstList == null || licOblApplicationMstList.size() == 0 || licOblApplicationMstList.contains(null)){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Error : ", "No Record(s) Found"));
				renderedPanel = false;
				return;
			}
			
			for(LicOblApplicationMst obj : licOblApplicationMstList){
				List<LicPaymentDtls> licPaymentDtlses = licPaymentDtlsService.findLicPaymentDtlsByPayId(obj.getLicBusinessTxn().getLicPaymentMst());

				for(LicPaymentDtls ob : licPaymentDtlses){
					if((ob.getPayeeName() == null || ob.getPayeeName().equals("SARADA INSURANCE CONSULTANCY LTD"))){
						totalAmount = totalAmount + ob.getAmount();
					}
				}
				obj.getLicBusinessTxn().getLicPaymentMst().setLicPaymentDtlses(licPaymentDtlses);
			}
			
			
			renderedPanel = true;
		}catch(Exception e){
			log.info("PremiumListAction search Error : ", e);
		}
	}
	
	public void exportToExcel() throws IOException {
		try {
			Workbook wb = new HSSFWorkbook();
			Sheet sheet = wb.createSheet("Premium Detail Data Entry Report");

			/* Start Style */
			/* Header */
			Font headerFont = wb.createFont();
			headerFont.setFontHeightInPoints((short) 12);
			headerFont.setColor(HSSFColor.RED.index);
			headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			
			CellStyle headerStyle = wb.createCellStyle();
			headerStyle.setFont(headerFont);
			headerStyle.setAlignment(headerStyle.ALIGN_CENTER);
			
			/* Sub Header */
			Font subHeaderFont = wb.createFont();
			subHeaderFont.setFontHeightInPoints((short) 10);
			subHeaderFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			
			CellStyle subHeaderStyle = wb.createCellStyle();
			subHeaderStyle.setFont(subHeaderFont);
			subHeaderStyle.setAlignment(subHeaderStyle.ALIGN_CENTER);
			/* End Style */
						
			/* Premium Details Start */
			Row row = null;
			Iterator<LicOblApplicationMst> licOblApplicationMstIterator = licOblApplicationMstList.iterator();
			int rowIndex = 0;

			/* Start Header */
			row = sheet.createRow(rowIndex++);
			Cell premListHeaderCell0 = row.createCell(0);
			premListHeaderCell0.setCellStyle(headerStyle);
			premListHeaderCell0.setCellValue("Premium List Report");
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));
			
			row = sheet.createRow(rowIndex++);
			Cell premDtlsSubHeaderCell0 = row.createCell(0);
			premDtlsSubHeaderCell0.setCellStyle(subHeaderStyle);
			premDtlsSubHeaderCell0.setCellValue("Application No");
			
			/* Start Sub-Header */
			Cell premListSubHeaderCell1 = row.createCell(1);
			premListSubHeaderCell1.setCellStyle(subHeaderStyle);
			premListSubHeaderCell1.setCellValue("Business Date");
			
			Cell premListSubHeaderCell2 = row.createCell(2);
			premListSubHeaderCell2.setCellStyle(subHeaderStyle);
			premListSubHeaderCell2.setCellValue("Insured Name");
			
			Cell premListSubHeaderCell3 = row.createCell(3);
			premListSubHeaderCell3.setCellStyle(subHeaderStyle);
			premListSubHeaderCell3.setCellValue("Proposer Name");
			
			Cell premListSubHeaderCell4 = row.createCell(4);
			premListSubHeaderCell4.setCellStyle(subHeaderStyle);
			premListSubHeaderCell4.setCellValue("LIC Branch Name");
			
			Cell premListSubHeaderCell5 = row.createCell(5);
			premListSubHeaderCell5.setCellStyle(subHeaderStyle);
			premListSubHeaderCell5.setCellValue("Sum Assured");
			
			Cell premListSubHeaderCell6 = row.createCell(6);
			premListSubHeaderCell6.setCellStyle(subHeaderStyle);
			premListSubHeaderCell6.setCellValue("Total");
			
			Cell premListSubHeaderCell7 = row.createCell(7);
			premListSubHeaderCell7.setCellStyle(subHeaderStyle);
			premListSubHeaderCell7.setCellValue("Cash Amount");
			
			Cell premListSubHeaderCell8 = row.createCell(8);
			premListSubHeaderCell8.setCellStyle(subHeaderStyle);
			premListSubHeaderCell8.setCellValue("DD/Cheque Amount");
			
			Cell premListSubHeaderCell9 = row.createCell(9);
			premListSubHeaderCell9.setCellStyle(subHeaderStyle);
			premListSubHeaderCell9.setCellValue("DD/Cheque No");
			
			Cell premListSubHeaderCell10 = row.createCell(10);
			premListSubHeaderCell10.setCellStyle(subHeaderStyle);
			premListSubHeaderCell10.setCellValue("Bank Name");
			
			Cell premListSubHeaderCell11 = row.createCell(11);
			premListSubHeaderCell11.setCellStyle(subHeaderStyle);
			premListSubHeaderCell11.setCellValue("Payee Name");
			/* End Sub-Header */
			/* End header */

			while (licOblApplicationMstIterator.hasNext()) {
				LicOblApplicationMst obj = licOblApplicationMstIterator.next();
				row = sheet.createRow(rowIndex++);
				row.createCell(0).setCellValue(obj.getOblApplNo());
				row.createCell(1).setCellValue(formatDateToString(obj.getBusinessDate()));
				row.createCell(2).setCellValue(obj.getLicInsuredDtls().getName());
				row.createCell(3).setCellValue(obj.getLicProposerDtls().getName());
				row.createCell(4).setCellValue(obj.getPicBranchMstId().getPicBranchName());
				row.createCell(5).setCellValue(obj.getLicProductValueMst().getSumAssured());
				row.createCell(6).setCellValue(obj.getLicBusinessTxn().getLicPaymentMst().getTotalReceived());
				
				double cashAmount = 0.0;
				double chqDdAmount = 0.0;
				String chqDDNo = "";
				String bankName = "";
				String payeeName = "";
				for(LicPaymentDtls dtls : obj.getLicBusinessTxn().getLicPaymentMst().getLicPaymentDtlses()){
					if(dtls.getPayMode().equals("C")){
						cashAmount = cashAmount + dtls.getAmount();
					}else{
						chqDdAmount = chqDdAmount + dtls.getAmount();
						chqDDNo = chqDDNo + dtls.getDraftChqNo() + " , ";
						bankName = bankName + dtls.getDraftChqBank() + " , ";
						payeeName = payeeName + dtls.getPayeeName() + " , ";
					}
				}
				row.createCell(7).setCellValue(cashAmount);
				row.createCell(8).setCellValue(chqDdAmount);
				row.createCell(9).setCellValue(chqDDNo);
				row.createCell(10).setCellValue(bankName);
				row.createCell(11).setCellValue(payeeName);
				
			}
			
			row = sheet.createRow(rowIndex++);
			Cell footerCell7 = row.createCell(7);
			footerCell7.setCellStyle(subHeaderStyle);
			footerCell7.setCellValue(totalAmount);
			/* Premium Details End */

			HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

			// response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename=download.xls");
			OutputStream out = response.getOutputStream();
			wb.write(out);
			out.flush();
			out.close();
			FacesContext.getCurrentInstance().responseComplete();
			
		} catch (Exception e) {
			log.info("Error", e);
		}
	}
	
	public String formatDateToString(Date date) {
		Format formatter = new SimpleDateFormat("dd-MM-yyyy");
		return formatter.format(date);
	}

	/* GETTER SETTER */
	public Date getBusinessFromDate() {
		return businessFromDate;
	}

	public void setBusinessFromDate(Date businessFromDate) {
		this.businessFromDate = businessFromDate;
	}

	public Date getBusinessToDate() {
		return businessToDate;
	}

	public void setBusinessToDate(Date businessToDate) {
		this.businessToDate = businessToDate;
	}

	public Boolean getRenderedPanel() {
		return renderedPanel;
	}

	public void setRenderedPanel(Boolean renderedPanel) {
		this.renderedPanel = renderedPanel;
	}

	public List<LicOblApplicationMst> getLicOblApplicationMstList() {
		return licOblApplicationMstList;
	}

	public void setLicOblApplicationMstList(
			List<LicOblApplicationMst> licOblApplicationMstList) {
		this.licOblApplicationMstList = licOblApplicationMstList;
	}
	public List<Long> getPremListNos() {
		return premListNos;
	}
	public void setPremListNos(List<Long> premListNos) {
		this.premListNos = premListNos;
	}
	public Long getPremListNo() {
		return premListNo;
	}
	public void setPremListNo(Long premListNo) {
		this.premListNo = premListNo;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
}
