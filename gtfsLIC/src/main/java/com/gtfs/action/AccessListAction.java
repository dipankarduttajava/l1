package com.gtfs.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gtfs.bean.AccessList;
import com.gtfs.bean.RoleAccessRlns;
import com.gtfs.bean.RoleMst;
import com.gtfs.dto.AccessListDto;
import com.gtfs.dto.RoleAccessRlnsDto;
import com.gtfs.service.interfaces.AccessListService;
import com.gtfs.service.interfaces.RoleAccessRlnsService;
import com.gtfs.service.interfaces.RoleMstService;


@Component
@Scope("session")
public class AccessListAction implements Serializable{
	private Logger log = Logger.getLogger(AccessListAction.class);
	
	@Autowired
	private AccessListService accessListService;
	@Autowired
	private LoginAction loginAction;
	@Autowired
	private RoleAccessRlnsService roleAccessRlnsService;
	@Autowired
	private RoleMstService roleMstService;
	
	private String accessName;
	private Long accessId;
	private List<RoleMst> roleMstList = new ArrayList<RoleMst>();	
	private List<RoleAccessRlnsDto> roleAccessRlnsDtos = new ArrayList<RoleAccessRlnsDto>();
	private List<AccessListDto> listOfAccess = new ArrayList<AccessListDto>();
	
	
	@PostConstruct
	public void loadRoleMst(){
		roleMstList = roleMstService.findAllActiveRole();
	}
	
	public void refresh(){
		accessName = null;
		accessId = null;
		
		if(listOfAccess != null){
			listOfAccess.clear();
		}
	}
	
	public String onLoad(){
		refresh();
		return "/admin/accessList.xhtml";
	}
	
	public void changeStatusListener(RoleAccessRlnsDto roleAccessRlnsDto){
		try{
			if(roleAccessRlnsDto.getRoleGivenStatus() == true){
				RoleAccessRlns roleAccessRlns = new RoleAccessRlns();
				roleAccessRlns.setAccessList(accessListService.findById(roleAccessRlnsDto.getAccessId()));
				roleAccessRlns.setRoleMst(roleMstService.findRoleById(roleAccessRlnsDto.getRoleId()));
				roleAccessRlns.setActiveFlag("Y");
				roleAccessRlns.setUserId(loginAction.getUserList().get(0).getUserid());
				roleAccessRlns.setDateTime(new Date());
				roleAccessRlnsService.insertIntoRoleAccessRlns(roleAccessRlns);
			}else if(roleAccessRlnsDto.getRoleGivenStatus()== false){
				roleAccessRlnsService.deleteRoleAccessRlnsByRoleIdAndAccessId(roleAccessRlnsDto.getRoleId(), roleAccessRlnsDto.getAccessId());
			}			
		}catch(Exception e){
			log.info("AccessListAction changeStatusListener Error ", e);
		}
	}

	public void editRoleForAccessList(AccessListDto accessListDto){
		try{
			roleAccessRlnsDtos.clear();
			List<RoleAccessRlns> list = roleAccessRlnsService.findActiveRoleAccessRlnsByAccessId(accessListDto.getAccessId());
			List<Long> roleIdList = new ArrayList<Long>();
			
			for(RoleAccessRlns roleAccessRlns:list){
				roleIdList.add(roleAccessRlns.getRoleMst().getRoleId());
			}

			for(RoleMst obj:roleMstList){
				RoleAccessRlnsDto roleAccessRlnsDto = new RoleAccessRlnsDto();
				roleAccessRlnsDto.setAccessId(accessListDto.getAccessId());
				roleAccessRlnsDto.setRoleId(obj.getRoleId());
				roleAccessRlnsDto.setRoleName(obj.getRoleName());
				roleAccessRlnsDto.setRoleGivenStatus(roleIdList.contains(obj.getRoleId()));
				roleAccessRlnsDtos.add(roleAccessRlnsDto);
			}
			 RequestContext.getCurrentInstance().openDialog("roleAccessDialog");
		}catch(Exception e){
			log.info("AccessListAction editRoleForAccessList Error ", e);
		}
	}
	
	public void saveList(){
		try{
			if(listOfAccess.size()>0){
				Boolean status = false;
				List<AccessList> list=new ArrayList<AccessList>();
				Date now = new Date();
				
				for(AccessListDto accessListDto : listOfAccess){
					if(accessListDto.getDisabledFlag() == false){
						AccessList accessList = new AccessList();
						accessList.setAccessId(accessListDto.getAccessId());
						accessList.setAccessName(accessListDto.getAccessName());
						
						accessList.setModifiedBy(loginAction.getUserList().get(0).getUserid());
						
						if(accessListDto.getCreatedBy() == null || accessListDto.getCreatedBy().equals("")){
							accessList.setCreatedBy(loginAction.getUserList().get(0).getUserid());
							accessList.setCreatedDate(now);
						}else{
							accessList.setCreatedBy(accessListDto.getCreatedById());
							accessList.setCreatedDate(accessListDto.getCreatedDate());
						}					
						accessList.setModifiedDate(now);
						accessList.setDeleteFlag("N");
						list.add(accessList);
					}
				}
				
				if(list.size()>0){
					status = accessListService.saveAccessList(list);
				}
				
				if(status){
					onSearch();
					 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
			                    "Save Successful: ", "Access List Save Successfully"));
					 refresh();
				}else{
					 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
			                    "Error: ", "Access List Not Save Successfully"));
				}
				
			}else{
				 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
		                    "Error: ", "Please Click On Add Button To Save"));
			}
		}catch(Exception e){
			log.info("AccessList Save Error : ", e);
		}
	}
	
	
	public void editAccessList(AccessListDto accessListDto){
		try{
			accessListDto.setDisabledFlag(false);
		}catch(Error e){
			log.info("AccessList editAccessList Error : ", e);
		}
	}
	
	public void deleteAccessList(AccessListDto accessListDto){
		try{
			Boolean status = accessListService.deleteAccessList(accessListDto.getAccessId(), loginAction.getUserList().get(0).getUserid());
			
			if(status){
				listOfAccess.remove(accessListDto);
				 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
		                    "Delete Successful: ", "Access List Deleted Successfully"));
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Error: ", "Access List Deletion Unsuccessful"));
				return;
			}
		}catch(Exception e){
			log.info("AccessList deleteAccessList Error : ", e);
		}
	}
	
	public void addList(){
		try{
			AccessListDto accessListDto=new AccessListDto();
			accessListDto.setDisabledFlag(false);
			listOfAccess.add(accessListDto);
		}catch(Exception e){
			log.info("AccessList addList Error : ", e);
		}
	}
	
	public void onSearch(){
		try{
			List<Object> list = null;
			
			if(accessName !=null ){
				list = accessListService.findAllActiveAccessListByAccessName(accessName);	
			}else{
				list = accessListService.findAllActiveAccessList();	
			}
			
			if(list.size() == 0 || list == null || list.contains(null)){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Error: ", "No Record(s) Found"));
				return;
			}
			
			listOfAccess.clear();
			for(Object obj : list){
				Object[] objArray = (Object[]) obj;
				AccessListDto accessListDto = new AccessListDto();
				accessListDto.setAccessId((Long) objArray[0]);
				accessListDto.setAccessName((String) objArray[1]);
				accessListDto.setCreatedBy((String) objArray[2]);
				accessListDto.setModifiedBy((String) objArray[3]);
				accessListDto.setCreatedDate((Date) objArray[4]);
				accessListDto.setModifiedDate((Date) objArray[5]);
				accessListDto.setCreatedById((Long) objArray[6]);
				accessListDto.setModifiedById((Long) objArray[7]);
				accessListDto.setDisabledFlag(true);
				listOfAccess.add(accessListDto);
			}
		}catch(Exception e){
			log.info("AccessListAction onSearch Error : ", e);
		}
	}

	
	/* GETTER SETTER */
	public List<AccessListDto> getListOfAccess() {
		return listOfAccess;
	}

	public void setListOfAccess(List<AccessListDto> listOfAccess) {
		this.listOfAccess = listOfAccess;
	}

	public String getAccessName() {
		return accessName;
	}

	public void setAccessName(String accessName) {
		this.accessName = accessName;
	}

	public List<RoleAccessRlnsDto> getRoleAccessRlnsDtos() {
		return roleAccessRlnsDtos;
	}

	public void setRoleAccessRlnsDtos(List<RoleAccessRlnsDto> roleAccessRlnsDtos) {
		this.roleAccessRlnsDtos = roleAccessRlnsDtos;
	}

	public List<RoleMst> getRoleMstList() {
		return roleMstList;
	}

	public void setRoleMstList(List<RoleMst> roleMstList) {
		this.roleMstList = roleMstList;
	}

	public Long getAccessId() {
		return accessId;
	}

	public void setAccessId(Long accessId) {
		this.accessId = accessId;
	}
	
}
