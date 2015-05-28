package com.gtfs.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gtfs.bean.BranchMst;
import com.gtfs.bean.LicBranchHubPicMapping;
import com.gtfs.bean.LicHubMst;
import com.gtfs.bean.PicBranchMst;
import com.gtfs.bean.ProcessMst;
import com.gtfs.dto.BranchHubPicMapDto;
import com.gtfs.service.interfaces.BranchMstService;
import com.gtfs.service.interfaces.LicBranchHubPicMappingService;
import com.gtfs.service.interfaces.LicHubMstService;
import com.gtfs.service.interfaces.PicBranchMstService;
import com.gtfs.service.interfaces.ProcessMstService;

@Component
@Scope("session")
public class BranchHubPicMapAction implements Serializable{
	private Logger log = Logger.getLogger(BranchHubPicMapAction.class);
	
	@Autowired
	private LoginAction loginAction;
	@Autowired
	private BranchMstService branchMstService;
	@Autowired
	private LicHubMstService licHubMstService;
	@Autowired
	private PicBranchMstService picBranchMstService;
	@Autowired
	private LicBranchHubPicMappingService licBranchHubPicMappingService;
	@Autowired
	private ProcessMstService processMstService;
	
	private Boolean renderedFirstSearch;
	private Boolean renderedSecondSearch;
	private Boolean renderedDateTable;
	private String sourceType;
	private Long sourceId;
	private Long destinationId;
	private String destinationType;
	private ProcessMst processMst;
	
	private List<BranchMst> branchMsts = new ArrayList<BranchMst>();
	private List<LicHubMst> licHubMsts = new ArrayList<LicHubMst>();
	private List<PicBranchMst> picBranchMsts = new ArrayList<PicBranchMst>();
	private List<ProcessMst> processMsts = new ArrayList<ProcessMst>();
	
	private List<SelectItem> sourceList = new ArrayList<SelectItem>(); 
	private List<SelectItem> destinationList = new ArrayList<SelectItem>();
	
	private List<BranchHubPicMapDto> branchHubPicMapDtos = new ArrayList<BranchHubPicMapDto>();
	
	@PostConstruct
	public void init(){
		branchMsts = branchMstService.findAll();
		licHubMsts = licHubMstService.findActiveHubMst();
		picBranchMsts = picBranchMstService.findActivePicBranchMst();
		processMsts = processMstService.findAll();
		
	}
	
	public void refresh(){
		renderedFirstSearch = true;
		renderedSecondSearch = false;
		renderedDateTable = false;
	}
	
	public String onLoad(){
		refresh();
		return "/admin/branchHubPicMapping.xhtml";
	}
	
	
	public void onSearchFirst(){
		try{
			sourceList.clear();
			destinationList.clear();
			
			if(sourceType.equals("BRANCH")){
				for(BranchMst obj:branchMsts){
					sourceList.add(new SelectItem(obj.getBranchId(), obj.getBranchName()));
				}
			}else if(sourceType.equals("HUB")){
				for(LicHubMst obj:licHubMsts){
					sourceList.add(new SelectItem(obj.getId(), obj.getHubName()));
				}
			}else if(sourceType.equals("PIC")){
				for(PicBranchMst obj:picBranchMsts){
					sourceList.add(new SelectItem(obj.getPicBranchId(), obj.getPicBranchName()));
				}
			}
			
			if(destinationType.equals("BRANCH")){
				for(BranchMst obj:branchMsts){
					destinationList.add(new SelectItem(obj.getBranchId(), obj.getBranchName()));
				}
			}else if(destinationType.equals("HUB")){
				for(LicHubMst obj:licHubMsts){
					destinationList.add(new SelectItem(obj.getId(), obj.getHubName()));
				}
			}else if(destinationType.equals("PIC")){
				for(PicBranchMst obj:picBranchMsts){
					destinationList.add(new SelectItem(obj.getPicBranchId(), obj.getPicBranchName()));
				}
			}
			
			renderedFirstSearch = false;
			renderedSecondSearch = true;
		}catch(Exception e){
			log.info("BranchHubPicMapAction onSearchFirst Error : ", e);
		}
	}
	
	public void onSearchSecond(){
		try{
			if(sourceId == null || destinationId == null){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Error: ", "Please Select Both Options"));
			}else{
				branchHubPicMapDtos.clear();
				for(LicBranchHubPicMapping obj:licBranchHubPicMappingService.findSourceByIdTypeAndProcss(sourceId, sourceType, processMst)){
					
					BranchHubPicMapDto branchHubPicMapDto = new BranchHubPicMapDto();
					branchHubPicMapDto.setId(obj.getId());
					branchHubPicMapDto.setProcessName(obj.getProcessMst().getProcessName());
					branchHubPicMapDto.setProcessAbbr(obj.getProcessMst().getProcessAbbr());
					branchHubPicMapDto.setSourceId(obj.getSourceId());
					branchHubPicMapDto.setSourceType(obj.getSourceType());
					branchHubPicMapDto.setDestionId(obj.getDestinationId());
					branchHubPicMapDto.setDestinationType(obj.getDestinationType());
					branchHubPicMapDto.setProcessMst(obj.getProcessMst());
					branchHubPicMapDtos.add(branchHubPicMapDto);
				}
				
				renderedSecondSearch = false;
				renderedDateTable = true;
			}
		}catch(Exception e){
			log.info("BranchHubPicMapAction onSearchSecond Error : ", e);
		}
	}
	
	public void addList(){
		BranchHubPicMapDto branchHubPicMapDto = new BranchHubPicMapDto();
		branchHubPicMapDtos.add(branchHubPicMapDto);
		branchHubPicMapDto.setSourceType(sourceType);
		branchHubPicMapDto.setDestinationType(destinationType);
		branchHubPicMapDto.setProcessAbbr(processMst.getProcessAbbr());
		branchHubPicMapDto.setProcessName(processMst.getProcessName());
		branchHubPicMapDto.setProcessMst(processMst);
	}
	
	
	public void save(){
		try{
			Date now = new Date();
			List<LicBranchHubPicMapping> list = new ArrayList<LicBranchHubPicMapping>();
			
			for(BranchHubPicMapDto obj:branchHubPicMapDtos){
				if(obj.getId() == null){
					LicBranchHubPicMapping licBranchHubPicMapping = new LicBranchHubPicMapping();
					
					licBranchHubPicMapping.setSourceId(obj.getSourceId());
					licBranchHubPicMapping.setSourceType(obj.getSourceType());
					licBranchHubPicMapping.setDestinationId(obj.getDestionId());
					licBranchHubPicMapping.setDestinationType(obj.getDestinationType());
					licBranchHubPicMapping.setProcessMst(obj.getProcessMst());
					licBranchHubPicMapping.setCreatedBy(loginAction.getUserList().get(0).getUserid());
					licBranchHubPicMapping.setModifiedBy(loginAction.getUserList().get(0).getUserid());
					licBranchHubPicMapping.setCreatedDate(now);
					licBranchHubPicMapping.setModifiedDate(now);
					licBranchHubPicMapping.setDeleteFlag("N");
					
					list.add(licBranchHubPicMapping);
				}
			}
			
			Boolean status = licBranchHubPicMappingService.saveForMapping(list);
			
			if(status){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
	                    "Successful : ", "Branch Hub Pic Mapping Entry successful"));
				refresh();
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
	                    "Error : ", "Branch Hub Pic Mapping Entry unsuccessful"));
			}
		}catch(Exception e){
			log.info("BranchHubPicMapAction save Error : ", e);
		}
	}


	/* GETTER SETTER */
	public Boolean getRenderedFirstSearch() {
		return renderedFirstSearch;
	}


	public void setRenderedFirstSearch(Boolean renderedFirstSearch) {
		this.renderedFirstSearch = renderedFirstSearch;
	}


	public Boolean getRenderedSecondSearch() {
		return renderedSecondSearch;
	}


	public void setRenderedSecondSearch(Boolean renderedSecondSearch) {
		this.renderedSecondSearch = renderedSecondSearch;
	}


	public Boolean getRenderedDateTable() {
		return renderedDateTable;
	}


	public void setRenderedDateTable(Boolean renderedDateTable) {
		this.renderedDateTable = renderedDateTable;
	}
	
	public String getSourceType() {
		return sourceType;
	}
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}
	public String getDestinationType() {
		return destinationType;
	}
	public void setDestinationType(String destinationType) {
		this.destinationType = destinationType;
	}
	public List<BranchMst> getBranchMsts() {
		return branchMsts;
	}

	public void setBranchMsts(List<BranchMst> branchMsts) {
		this.branchMsts = branchMsts;
	}

	public List<LicHubMst> getLicHubMsts() {
		return licHubMsts;
	}

	public void setLicHubMsts(List<LicHubMst> licHubMsts) {
		this.licHubMsts = licHubMsts;
	}

	public List<PicBranchMst> getPicBranchMsts() {
		return picBranchMsts;
	}

	public void setPicBranchMsts(List<PicBranchMst> picBranchMsts) {
		this.picBranchMsts = picBranchMsts;
	}

	public Long getSourceId() {
		return sourceId;
	}

	public void setSourceId(Long sourceId) {
		this.sourceId = sourceId;
	}

	public Long getDestinationId() {
		return destinationId;
	}

	public void setDestinationId(Long destinationId) {
		this.destinationId = destinationId;
	}

	public List<SelectItem> getSourceList() {
		return sourceList;
	}

	public void setSourceList(List<SelectItem> sourceList) {
		this.sourceList = sourceList;
	}

	public List<SelectItem> getDestinationList() {
		return destinationList;
	}

	public void setDestinationList(List<SelectItem> destinationList) {
		this.destinationList = destinationList;
	}

	public List<BranchHubPicMapDto> getBranchHubPicMapDtos() {
		return branchHubPicMapDtos;
	}

	public void setBranchHubPicMapDtos(List<BranchHubPicMapDto> branchHubPicMapDtos) {
		this.branchHubPicMapDtos = branchHubPicMapDtos;
	}

	public List<ProcessMst> getProcessMsts() {
		return processMsts;
	}

	public void setProcessMsts(List<ProcessMst> processMsts) {
		this.processMsts = processMsts;
	}

	public ProcessMst getProcessMst() {
		return processMst;
	}

	public void setProcessMst(ProcessMst processMst) {
		this.processMst = processMst;
	}
	
	
}
