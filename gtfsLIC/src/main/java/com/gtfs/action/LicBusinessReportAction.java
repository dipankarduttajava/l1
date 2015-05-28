package com.gtfs.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.component.datatable.DataTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gtfs.bean.LicOblApplicationMst;
import com.gtfs.service.interfaces.LicOblApplicationMstService;
import com.gtfs.service.interfaces.Reporting;

@Component
@Scope("session")
public class LicBusinessReportAction implements Serializable{
	private Logger log = Logger.getLogger(LicBusinessReportAction.class);
	
	@Autowired
	private LicOblApplicationMstService licOblApplicationMstService;
	@Autowired
	private Reporting reporting;
	
	public void refresh(){
		
	}
	
	public String onLoad(){
		refresh();
		return "/report/licBusinessReport.xhtml";
	}
	
	
	public void onSearch(){
		try{
			List<LicOblApplicationMst> list = licOblApplicationMstService.findAll();
			
			Map<String , List<Object>> map = new HashMap<String , List<Object>>();
			List<Object> colIdVals = new ArrayList<Object>();
			
			for(LicOblApplicationMst obj:list){
				colIdVals.add(obj.getId());
			}
			
			map.put("ID", colIdVals);
			List<Map<String,List<Object>>> colVals = new ArrayList<Map<String,List<Object>>>();
			colVals.add(map);
					
			DataTable table = reporting.createTable(colVals,"Lic Business Report");
			UIComponent panel =  FacesContext.getCurrentInstance().getViewRoot().findComponent(":listForm:panelList");
			panel.getChildren().add(table);
		}catch(Exception e){
			log.info("LicBusinessReportAction Search Error : ", e);
		}
	}
}
