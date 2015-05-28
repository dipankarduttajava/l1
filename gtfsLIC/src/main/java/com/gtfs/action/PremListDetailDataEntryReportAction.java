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

import com.gtfs.bean.LicOblApplicationMst;
import com.gtfs.bean.LicPaymentDtls;
import com.gtfs.bean.LicPremPaymentDtls;
import com.gtfs.bean.LicPremiumListDtls;
import com.gtfs.service.interfaces.LicPaymentDtlsService;
import com.gtfs.service.interfaces.LicPremPaymentDtlsService;
import com.gtfs.service.interfaces.LicPremiumListService;

@Component
@Scope("session")
public class PremListDetailDataEntryReportAction implements Serializable{
	private Logger log = Logger.getLogger(PremListDetailDataEntryReportAction.class);
	
	@Autowired
	private LicPremiumListService licPremiumListService;
	@Autowired
	private LicPaymentDtlsService licPaymentDtlsService;
	@Autowired
	private LoginAction loginAction;
	@Autowired
	private LicPremPaymentDtlsService licPremPaymentDtlsService;
	
	private Double totalAmount;
	private Boolean renderedPremiumList;
	private LicPremiumListDtls licPremiumListDtls;
	
	private List<LicPremiumListDtls> premList = new ArrayList<LicPremiumListDtls>();	
	private List<LicOblApplicationMst> licOblApplicationMstList = new ArrayList<LicOblApplicationMst>();
	private List<LicPremPaymentDtls> licPremPaymentDtlses = new ArrayList<LicPremPaymentDtls>();
	
	public void refresh(){
		try{
			premList.clear();
			premList = licPremiumListService.findPremiumNoforPremListDetailEntryreport(loginAction.findHubForProcess("OBL"));
			if(licOblApplicationMstList !=null){
				licOblApplicationMstList.clear();
			}
			if(licPremPaymentDtlses!=null){
				licPremPaymentDtlses.clear();
			}
			
			renderedPremiumList = false;
			totalAmount = null;
		}catch(Exception e){
			log.info("PremListDetailDataEntryReportAction refresh Error : ", e);
		}
	}
	
	public List<LicPremiumListDtls> findPremList(String query){
		ArrayList<LicPremiumListDtls> list = new ArrayList<LicPremiumListDtls>();
		for(LicPremiumListDtls licPremiumListDtls : premList){
			if(licPremiumListDtls.getId().toString().startsWith(query)){
				list.add(licPremiumListDtls);
			}
		}
		return list;
	}
	
	public String onLoad(){
		refresh();
		return "/licHubActivity/premListDetailDataEntryReport.xhtml";
	}
	
	public void search(){
		try{
			totalAmount = 0.0;
			licOblApplicationMstList = licPremiumListService.findLicOblApplicationMstsByPremListNo(licPremiumListDtls.getId(), loginAction.getUserList().get(0).getBranchMst());
			
			if(licOblApplicationMstList == null || licOblApplicationMstList.size() == 0 || licOblApplicationMstList.contains(null) ){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Error : ", "No Record(s) Found"));
				return;
			}else{
				licPremPaymentDtlses = licPremPaymentDtlsService.findLicPremPaymentDtls(licPremiumListDtls.getId(), loginAction.getUserList().get(0).getBranchMst());
				
				for(LicOblApplicationMst obj : licOblApplicationMstList){
					List<LicPaymentDtls> licPaymentDtlses = licPaymentDtlsService.findLicPaymentDtlsByPayId(obj.getLicBusinessTxn().getLicPaymentMst());
					for(LicPaymentDtls ob : licPaymentDtlses){				
						if((ob.getPayeeName() == null || ob.getPayeeName().equals("SARADA INSURANCE CONSULTANCY LTD"))){
							totalAmount = totalAmount + ob.getAmount();
						}
					}
					obj.getLicBusinessTxn().getLicPaymentMst().setLicPaymentDtlses(licPaymentDtlses);
				}
				
				renderedPremiumList = true;
			}
			
		}catch(Exception e){
			log.info("PremListDetailDataEntryReportAction search Error : ", e);
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
			Cell premDtlsHeaderCell0 = row.createCell(0);
			premDtlsHeaderCell0.setCellStyle(headerStyle);
			premDtlsHeaderCell0.setCellValue("Premium List Detail Data Entry Report");
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));
			
			row = sheet.createRow(rowIndex++);
			Cell premDtlsSubHeaderCell0 = row.createCell(0);
			premDtlsSubHeaderCell0.setCellStyle(subHeaderStyle);
			premDtlsSubHeaderCell0.setCellValue("Application No");
			
			Cell premDtlsSubHeaderCell1 = row.createCell(1);
			premDtlsSubHeaderCell1.setCellStyle(subHeaderStyle);
			premDtlsSubHeaderCell1.setCellValue("Business Date");
			
			Cell premDtlsSubHeaderCell2 = row.createCell(2);
			premDtlsSubHeaderCell2.setCellStyle(subHeaderStyle);
			premDtlsSubHeaderCell2.setCellValue("Insured Name");
			
			Cell premDtlsSubHeaderCell3 = row.createCell(3);
			premDtlsSubHeaderCell3.setCellStyle(subHeaderStyle);
			premDtlsSubHeaderCell3.setCellValue("Proposer Name");
			
			Cell premDtlsSubHeaderCell4 = row.createCell(4);
			premDtlsSubHeaderCell4.setCellStyle(subHeaderStyle);
			premDtlsSubHeaderCell4.setCellValue("LIC Branch Name");
			
			Cell premDtlsSubHeaderCell5 = row.createCell(5);
			premDtlsSubHeaderCell5.setCellStyle(subHeaderStyle);
			premDtlsSubHeaderCell5.setCellValue("Basic Premium");
			
			Cell premDtlsSubHeaderCell6 = row.createCell(6);
			premDtlsSubHeaderCell6.setCellStyle(subHeaderStyle);
			premDtlsSubHeaderCell6.setCellValue("Sum Assured");
			
			Cell premDtlsSubHeaderCell7 = row.createCell(7);
			premDtlsSubHeaderCell7.setCellStyle(subHeaderStyle);
			premDtlsSubHeaderCell7.setCellValue("Total");
			
			Cell premDtlsSubHeaderCell8 = row.createCell(8);
			premDtlsSubHeaderCell8.setCellStyle(subHeaderStyle);
			premDtlsSubHeaderCell8.setCellValue("Cash Amount");
			
			Cell premDtlsSubHeaderCell9 = row.createCell(9);
			premDtlsSubHeaderCell9.setCellStyle(subHeaderStyle);
			premDtlsSubHeaderCell9.setCellValue("DD/Cheque Amount");
			
			Cell premDtlsSubHeaderCell10 = row.createCell(10);
			premDtlsSubHeaderCell10.setCellStyle(subHeaderStyle);
			premDtlsSubHeaderCell10.setCellValue("DD/Cheque No");
			
			Cell premDtlsSubHeaderCell11 = row.createCell(11);
			premDtlsSubHeaderCell11.setCellStyle(subHeaderStyle);
			premDtlsSubHeaderCell11.setCellValue("Bank Name");
			
			Cell premDtlsSubHeaderCell12 = row.createCell(12);
			premDtlsSubHeaderCell12.setCellStyle(subHeaderStyle);
			premDtlsSubHeaderCell12.setCellValue("Payee Name");
			
			
			
			/* End header */

			while (licOblApplicationMstIterator.hasNext()) {
				LicOblApplicationMst obj = licOblApplicationMstIterator.next();
				row = sheet.createRow(rowIndex++);
				row.createCell(0).setCellValue(obj.getOblApplNo());
				row.createCell(1).setCellValue(formatDateToString(obj.getBusinessDate()));
				row.createCell(2).setCellValue(obj.getLicInsuredDtls().getName());
				row.createCell(3).setCellValue(obj.getLicProposerDtls().getName());
				row.createCell(4).setCellValue(obj.getPicBranchMstId().getPicBranchName());
				row.createCell(5).setCellValue(obj.getLicProductValueMst().getBasicPrem());
				row.createCell(6).setCellValue(obj.getLicProductValueMst().getSumAssured());
				row.createCell(7).setCellValue(obj.getLicBusinessTxn().getLicPaymentMst().getTotalReceived());
				
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
				row.createCell(8).setCellValue(cashAmount);
				row.createCell(9).setCellValue(chqDdAmount);
				row.createCell(10).setCellValue(chqDDNo);
				row.createCell(11).setCellValue(bankName);
				row.createCell(12).setCellValue(payeeName);
				
			}
			
			row = sheet.createRow(rowIndex++);
			Cell footerCell7 = row.createCell(7);
			footerCell7.setCellStyle(subHeaderStyle);
			footerCell7.setCellValue(totalAmount);
			/* Premium Details End */
			
			/* Chq/DD Details Start */
			Iterator<LicPremPaymentDtls> licPremPaymentDtlsIterator = licPremPaymentDtlses.iterator();
			rowIndex++;
			rowIndex++;
			rowIndex++;
			
			/* Start Header */
			sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0, 4));
			row = sheet.createRow(rowIndex++);
			Cell chqDDHeaderCell0 = row.createCell(0);
			chqDDHeaderCell0.setCellStyle(headerStyle);
			chqDDHeaderCell0.setCellValue("DD/CHQ/RTGS/NEFT Details Report");
			
			row = sheet.createRow(rowIndex++);
			Cell chqDDSubHeaderCell0 = row.createCell(0);
			chqDDSubHeaderCell0.setCellStyle(subHeaderStyle);
			chqDDSubHeaderCell0.setCellValue("DD/CHQ/RTGS/NEFT No");
			
			Cell chqDDSubHeaderCell1 = row.createCell(1);
			chqDDSubHeaderCell1.setCellStyle(subHeaderStyle);
			chqDDSubHeaderCell1.setCellValue("DD/CHQ/RTGS/NEFT Date");
			
			Cell chqDDSubHeaderCell2 = row.createCell(2);
			chqDDSubHeaderCell2.setCellStyle(subHeaderStyle);
			chqDDSubHeaderCell2.setCellValue("DD/CHQ/RTGS/NEFT Bank");
			
			Cell chqDDSubHeaderCell3 = row.createCell(3);
			chqDDSubHeaderCell3.setCellStyle(subHeaderStyle);
			chqDDSubHeaderCell3.setCellValue("DD/CHQ/RTGS/NEFT Amount");
			// End Header

			while (licPremPaymentDtlsIterator.hasNext()) {
				LicPremPaymentDtls subObj = licPremPaymentDtlsIterator.next();
				row = sheet.createRow(rowIndex++);
				row.createCell(0).setCellValue(subObj.getSentPayNo());
				row.createCell(1).setCellValue(formatDateToString(subObj.getSentPayDate()));
				row.createCell(2).setCellValue(subObj.getBankName());
				row.createCell(3).setCellValue(subObj.getSentPayAmount());
			}
			/* Chq/DD Details End */

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
	public List<LicPremiumListDtls> getPremList() {
		return premList;
	}

	public void setPremList(List<LicPremiumListDtls> premList) {
		this.premList = premList;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Boolean getRenderedPremiumList() {
		return renderedPremiumList;
	}

	public void setRenderedPremiumList(Boolean renderedPremiumList) {
		this.renderedPremiumList = renderedPremiumList;
	}

	public List<LicOblApplicationMst> getLicOblApplicationMstList() {
		return licOblApplicationMstList;
	}

	public void setLicOblApplicationMstList(
			List<LicOblApplicationMst> licOblApplicationMstList) {
		this.licOblApplicationMstList = licOblApplicationMstList;
	}

	public LicPremiumListDtls getLicPremiumListDtls() {
		return licPremiumListDtls;
	}

	public void setLicPremiumListDtls(LicPremiumListDtls licPremiumListDtls) {
		this.licPremiumListDtls = licPremiumListDtls;
	}

	public List<LicPremPaymentDtls> getLicPremPaymentDtlses() {
		return licPremPaymentDtlses;
	}

	public void setLicPremPaymentDtlses(
			List<LicPremPaymentDtls> licPremPaymentDtlses) {
		this.licPremPaymentDtlses = licPremPaymentDtlses;
	}

	

}
