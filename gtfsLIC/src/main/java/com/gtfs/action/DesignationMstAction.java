package com.gtfs.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gtfs.bean.DesignationMst;
import com.gtfs.service.interfaces.DesignationMstService;

@Component
@Scope("session")
public class DesignationMstAction implements Serializable{
	private Logger log = Logger.getLogger(DesignationMstAction.class);
	
	@Autowired
	private DesignationMstService designationMstService;
	
	private UploadedFile file;
	private List<DesignationMst> designationMstList = new ArrayList<DesignationMst>();
	
	
	public String onLoad(){
		return "/admin/designationMst.xhtml";
	}
	
	public void onSearch(){
		try{
			designationMstList = designationMstService.findAllActiveFromDesignationMst();
			
			if(designationMstList == null || designationMstList.size() == 0 || designationMstList.contains(null)){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Error : ", "No Record(s) Found"));
				return;
			}
		}catch(Exception e){
			log.info("DesignationMstAction onSearch Error : ", e);
		}
	}
	
	public void uploadDesignationList() {
        if(file != null) {
            FacesMessage message = new FacesMessage("File : ", file.getFileName() + " is Successfully Uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
	
	
	/* GETTER SETTER */
    public UploadedFile getFile() {
        return file;
    }
 
    public void setFile(UploadedFile file) {
        this.file = file;
    }

	public List<DesignationMst> getDesignationMstList() {
		return designationMstList;
	}

	public void setDesignationMstList(List<DesignationMst> designationMstList) {
		this.designationMstList = designationMstList;
	}
    
}
