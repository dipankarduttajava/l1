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

import com.gtfs.bean.LicCmsMst;
import com.gtfs.bean.LicPisMst;
import com.gtfs.dto.LicPisDto;
import com.gtfs.service.interfaces.LicPisMstService;

@Component
@Scope("session")
public class LicOblCmsViewReport implements Serializable{
	private Logger log = Logger.getLogger(LicOblCmsViewReport.class);
	
	@Autowired
	private LicPisMstService licPisMstService;
	
	private Long pisId;
	private Date busineeFormDate;
	private Date businessToDate;
	private Boolean renderedSearch;
	private Boolean renderedPisList;
	private Boolean renderReportList;
	private Double totalAmount;
	private Double totalCash;
	private Double totalDDChqTieUpCo;
	private Double totalDDChqInsCo;
	
	private List<LicPisMst> licPisMsts = new ArrayList<LicPisMst>();
	private List<LicPisDto> licPisDtos = new ArrayList<LicPisDto>();
	private List<LicCmsMst> licCmsMsts = new ArrayList<LicCmsMst>();
	
	
	public void refresh(){
		if(licPisMsts!=null){
			licPisMsts.clear();
		}
		if(licPisDtos!=null){
			licPisDtos.clear();
		}
		
		if(licCmsMsts!=null){
			licCmsMsts.clear();
		}
		
		pisId=null;
		busineeFormDate=null;
		businessToDate=null;
		renderedSearch = true;
		renderedPisList = false;
		renderReportList = false;
	}
	
	public String onLoad(){
		refresh();
		return "/licBranchActivity/licOblCmsViewReport.xhtml";
	}
	
	public void search(){
		licPisMsts = licPisMstService.findPisListForPisReport(pisId,busineeFormDate,businessToDate);
		
		if(licPisMsts == null || licPisMsts.size() == 0 || licPisMsts.contains(null)){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error : ", "No Record(s) Found"));
			return;
		}
		renderedPisList = true;
	}
	
	public void selectRow(LicPisMst licPisMst){
		
		totalCash = 0.0;
		totalDDChqInsCo = 0.0;
		totalDDChqTieUpCo = 0.0;
		totalAmount = 0.0;
		
		if(licPisDtos!=null){
			licPisDtos.clear();
		}
		if(licCmsMsts!=null){
			licCmsMsts.clear();
		}
		
		List<Object> objectList = licPisMstService.findApplicationByPis(licPisMst.getId());
		
		for(Object objList : objectList){
			Object[] objs = (Object[]) objList;
			LicPisDto licPisDto = new LicPisDto();
			licPisDto.setApplicationNo((String) objs[0]);
			licPisDto.setBusinessDate((Date) objs[1]);
			licPisDto.setCashAmt((Double) objs[2]);
			licPisDto.setDraftChqInsAmt((Double) objs[3]);
			licPisDto.setDraftChqTieAmt((Double) objs[4]);
			licPisDto.setTotalAmt((Double) objs[2] + (Double) objs[3] + (Double) objs[4]);
			licPisDto.setApplicationMstId((Long) objs[5]);
			licPisDtos.add(licPisDto);
			
			totalCash = totalCash + licPisDto.getCashAmt();
			totalDDChqInsCo = totalDDChqInsCo + licPisDto.getDraftChqInsAmt();
			totalDDChqTieUpCo = totalDDChqTieUpCo + licPisDto.getDraftChqTieAmt();
			totalAmount = totalAmount + licPisDto.getTotalAmt();
		}
		
		licCmsMsts = licPisMstService.findCmsByPisId(licPisMst.getId());
		
		renderedSearch = false;
		renderedPisList = false;
		renderReportList = true;
	}
	
	public void backToPisSearch(){
		renderedSearch = true;
		renderedPisList = true;
		renderReportList = false;
	}
	
	public void exportToExcel() throws IOException {
		try {
			Workbook wb = new HSSFWorkbook();
			Sheet sheet = wb.createSheet("CMS Report");
			
			Row row = null;
			
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
			
			
			/* PIS Detail List Report */
			Iterator<LicPisDto> licPisDtosIterator = licPisDtos.iterator();
			int rowIndex = 0;
			
			/* Start Header */
			row = sheet.createRow(rowIndex++);
			Cell pisHeaderCell0 = row.createCell(0);
			pisHeaderCell0.setCellStyle(headerStyle);
			pisHeaderCell0.setCellValue("PIS Detail List Report");
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));
			
			row = sheet.createRow(rowIndex++);
			Cell pisSubHeaderCell0 = row.createCell(0);
			pisSubHeaderCell0.setCellStyle(subHeaderStyle);
			pisSubHeaderCell0.setCellValue("Application No");
			
			Cell pisSubHeaderCell1 = row.createCell(1);
			pisSubHeaderCell1.setCellStyle(subHeaderStyle);
			pisSubHeaderCell1.setCellValue("Business Date");
			
			Cell pisSubHeaderCell2 = row.createCell(2);
			pisSubHeaderCell2.setCellStyle(subHeaderStyle);
			pisSubHeaderCell2.setCellValue("DD/Cheque Parent Company");
			
			Cell pisSubHeaderCell3 = row.createCell(3);
			pisSubHeaderCell3.setCellStyle(subHeaderStyle);
			pisSubHeaderCell3.setCellValue("DD/Cheque TieUp Company");
			
			Cell pisSubHeaderCell4 = row.createCell(4);
			pisSubHeaderCell4.setCellStyle(subHeaderStyle);
			pisSubHeaderCell4.setCellValue("Cash");
			
			Cell pisSubHeaderCell5 = row.createCell(5);
			pisSubHeaderCell5.setCellStyle(subHeaderStyle);
			pisSubHeaderCell5.setCellValue("Total Amount");
			/* End header */

			while (licPisDtosIterator.hasNext()) {
				LicPisDto obj = licPisDtosIterator.next();
				row = sheet.createRow(rowIndex++);
				row.createCell(0).setCellValue(obj.getApplicationNo());
				row.createCell(1).setCellValue(formatDateToString(obj.getBusinessDate()));
				row.createCell(2).setCellValue(obj.getDraftChqInsAmt());
				row.createCell(3).setCellValue(obj.getDraftChqTieAmt());
				row.createCell(4).setCellValue(obj.getCashAmt());
				row.createCell(5).setCellValue(obj.getTotalAmt());
			}
			
			row = sheet.createRow(rowIndex++);
			Cell footerCell2 = row.createCell(2);
			footerCell2.setCellStyle(subHeaderStyle);
			footerCell2.setCellValue(totalDDChqInsCo);
			
			Cell footerCell3 = row.createCell(3);
			footerCell3.setCellStyle(subHeaderStyle);
			footerCell3.setCellValue(totalDDChqTieUpCo);
			
			Cell footerCell4 = row.createCell(4);
			footerCell4.setCellStyle(subHeaderStyle);
			footerCell4.setCellValue(totalCash);
			
			Cell footerCell5 = row.createCell(5);
			footerCell5.setCellStyle(subHeaderStyle);
			footerCell5.setCellValue(totalAmount);
			
			/* CMS Detail List Report */
			Iterator<LicCmsMst> licCmsMstsIterator = licCmsMsts.iterator();
			rowIndex++;
			rowIndex++;
			rowIndex++;
			
			/* Start Header */
			sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0, 3));
			row = sheet.createRow(rowIndex++);
			Cell cmsHeaderCell0 = row.createCell(0);
			cmsHeaderCell0.setCellStyle(headerStyle);
			cmsHeaderCell0.setCellValue("CMS Detail List Report");
			
			row = sheet.createRow(rowIndex++);
			Cell cmsSubHeaderCell0 = row.createCell(0);
			cmsSubHeaderCell0.setCellStyle(subHeaderStyle);
			cmsSubHeaderCell0.setCellValue("CMS Slip Number");
			
			Cell cmsSubHeaderCell1 = row.createCell(1);
			cmsSubHeaderCell1.setCellStyle(subHeaderStyle);
			cmsSubHeaderCell1.setCellValue("CMS Slip Mode");
			
			Cell cmsSubHeaderCell2 = row.createCell(2);
			cmsSubHeaderCell2.setCellStyle(subHeaderStyle);
			cmsSubHeaderCell2.setCellValue("Amount");
			/* End header */

			
			while (licCmsMstsIterator.hasNext()) {
				LicCmsMst subObj = licCmsMstsIterator.next();
				row = sheet.createRow(rowIndex++);
				row.createCell(0).setCellValue(subObj.getCmsNo());
				row.createCell(1).setCellValue(subObj.getPayMode());
				row.createCell(2).setCellValue(subObj.getAmount());
			}

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
	public Long getPisId() {
		return pisId;
	}

	public void setPisId(Long pisId) {
		this.pisId = pisId;
	}

	public Date getBusineeFormDate() {
		return busineeFormDate;
	}

	public void setBusineeFormDate(Date busineeFormDate) {
		this.busineeFormDate = busineeFormDate;
	}

	public Date getBusinessToDate() {
		return businessToDate;
	}

	public void setBusinessToDate(Date businessToDate) {
		this.businessToDate = businessToDate;
	}

	public Boolean getRenderedSearch() {
		return renderedSearch;
	}

	public void setRenderedSearch(Boolean renderedSearch) {
		this.renderedSearch = renderedSearch;
	}

	public Boolean getRenderedPisList() {
		return renderedPisList;
	}

	public void setRenderedPisList(Boolean renderedPisList) {
		this.renderedPisList = renderedPisList;
	}

	public Boolean getRenderReportList() {
		return renderReportList;
	}

	public void setRenderReportList(Boolean renderReportList) {
		this.renderReportList = renderReportList;
	}

	public List<LicPisMst> getLicPisMsts() {
		return licPisMsts;
	}

	public void setLicPisMsts(List<LicPisMst> licPisMsts) {
		this.licPisMsts = licPisMsts;
	}

	public List<LicPisDto> getLicPisDtos() {
		return licPisDtos;
	}

	public void setLicPisDtos(List<LicPisDto> licPisDtos) {
		this.licPisDtos = licPisDtos;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Double getTotalCash() {
		return totalCash;
	}

	public void setTotalCash(Double totalCash) {
		this.totalCash = totalCash;
	}

	public Double getTotalDDChqTieUpCo() {
		return totalDDChqTieUpCo;
	}

	public void setTotalDDChqTieUpCo(Double totalDDChqTieUpCo) {
		this.totalDDChqTieUpCo = totalDDChqTieUpCo;
	}

	public Double getTotalDDChqInsCo() {
		return totalDDChqInsCo;
	}

	public void setTotalDDChqInsCo(Double totalDDChqInsCo) {
		this.totalDDChqInsCo = totalDDChqInsCo;
	}

	public List<LicCmsMst> getLicCmsMsts() {
		return licCmsMsts;
	}

	public void setLicCmsMsts(List<LicCmsMst> licCmsMsts) {
		this.licCmsMsts = licCmsMsts;
	}

	

}
