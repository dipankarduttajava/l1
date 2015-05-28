package com.gtfs.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gtfs.bean.BranchMst;
import com.gtfs.bean.ParentCompyMst;
import com.gtfs.bean.PrintRcptMst;
import com.gtfs.bean.TieupCompyMst;
import com.gtfs.service.interfaces.BranchMstService;
import com.gtfs.service.interfaces.ParentCompyMstService;
import com.gtfs.service.interfaces.PrintRcptMstService;
import com.gtfs.service.interfaces.TieupCompyMstService;
@Component
@Scope("session")
public class PrintRcptMstAction implements Serializable{
	private Logger log = Logger.getLogger(PrintRcptMstAction.class);
		
	@Autowired
	private ParentCompyMstService parentCompyMstService;
	@Autowired
	private BranchMstService branchMstService;
	@Autowired
	private TieupCompyMstService tieupCompyMstService;
	@Autowired
	private LoginAction loginAction;
	@Autowired
	private PrintRcptMstService printRcptMstService;
	
	private PrintRcptMst printRcptMst;
	private List<ParentCompyMst> parentCompyMsts = new ArrayList<ParentCompyMst>();
	private List<BranchMst> branchMsts = new ArrayList<BranchMst>();
	private List<TieupCompyMst> tieupCompyMsts = new ArrayList<TieupCompyMst>(); 
	
	@PostConstruct
	public void init(){
		parentCompyMsts = parentCompyMstService.findAll();
		branchMsts = branchMstService.findAll();
		tieupCompyMsts = tieupCompyMstService.findAllActiveTieupCompyList();
	}
	
	
	public void refresh(){
		Date now = new  Date();
		printRcptMst = new PrintRcptMst();
		printRcptMst.setCreatedBy(loginAction.getUserList().get(0).getUserid());
		printRcptMst.setModifiedBy(loginAction.getUserList().get(0).getUserid());
		printRcptMst.setCreatedDate(now);
		printRcptMst.setModifiedDate(now);
		printRcptMst.setDeleteFlag("N");
	}
	
	public String onLoad(){
		refresh();
		return "/admin/printRcptMst.xhtml";
	}

	public void prefixChange(){
		try{
			List<Long> list = printRcptMstService.findMaximunReceiptNoByPrefix(printRcptMst.getPrefix());
			
			if(list == null || list.size()== 0 || list.contains(null)){
				printRcptMst.setReceiptFrom(1l);
			}else{
				printRcptMst.setReceiptFrom(list.get(0)+1);
			}
		}catch(Exception e){
			log.info("PrintRcptMstAction prefixChange Error : ", e);
		}		
	}
	
	public void save(){
		try{
			Boolean status = printRcptMstService.saveForPrintReceipt(printRcptMst);
			
			if(status){
				 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
		                    "Success: ", "Save successful"));
				 refresh();
			}else{
				 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
		                    "Error: ", "Save Unsuccessful"));
			}
		}catch(Exception e){
			log.info("PrintRcptMstAction save Error : ", e);
		}
	}

	/* GETTER SETTER */
	public PrintRcptMst getPrintRcptMst() {
		return printRcptMst;
	}

	public void setPrintRcptMst(PrintRcptMst printRcptMst) {
		this.printRcptMst = printRcptMst;
	}

	public List<ParentCompyMst> getParentCompyMsts() {
		return parentCompyMsts;
	}

	public void setParentCompyMsts(List<ParentCompyMst> parentCompyMsts) {
		this.parentCompyMsts = parentCompyMsts;
	}

	public List<BranchMst> getBranchMsts() {
		return branchMsts;
	}

	public void setBranchMsts(List<BranchMst> branchMsts) {
		this.branchMsts = branchMsts;
	}

	public List<TieupCompyMst> getTieupCompyMsts() {
		return tieupCompyMsts;
	}

	public void setTieupCompyMsts(List<TieupCompyMst> tieupCompyMsts) {
		this.tieupCompyMsts = tieupCompyMsts;
	}
	
}
