package com.gtfs.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gtfs.bean.RoleMst;
import com.gtfs.dto.RoleMstDto;
import com.gtfs.service.interfaces.RoleMstService;

@Component
@Scope("session")
public class RoleMstAction implements Serializable {
	private Logger log = Logger.getLogger(RoleMstAction.class);
	
	@Autowired
	private LoginAction loginAction;

	@Autowired
	private RoleMstService roleMstService;

	private RoleMst roleMst;
	private RoleMstDto roleMstDto;

	private List<RoleMstDto> roleMstList = new ArrayList<RoleMstDto>();
	private String roleName;
	private Boolean renderedAction;
	private Boolean renderedRoleList;

	public void refresh(){
		roleName = null;
		roleMstList.clear();
		renderedAction = false;
		renderedRoleList = false;
	}
	
	public String onLoad() {
		refresh();
		return "/admin/roleMst.xhtml";
	}

	public void onSearch() {
		try{
			List<RoleMst> list = null;
			if(roleName != null ){
				list = roleMstService.findRoleByRoleName(roleName);
				renderedRoleList = true;
			}else{
				list = roleMstService.findAllActiveRole();
				renderedRoleList = false;
			}

			roleMstList.clear();
			for(RoleMst obj : list){
				RoleMstDto roleMstDto = new RoleMstDto();
				roleMstDto.setRoleId(obj.getRoleId());
				roleMstDto.setRoleName(obj.getRoleName());
				roleMstDto.setDisabledFlag(true);
				roleMstList.add(roleMstDto);
			}
			renderedAction = true;
		}catch(Exception e){
			log.info("RoleMstAction Search Error : ", e);
		}
	}
	
	/*public void onSearchAll(){
		List<RoleMst> list = roleMstService.findAllActiveRole();
		roleMstList.clear();
		for(RoleMst obj : list){
			RoleMstDto roleMstDto = new RoleMstDto();
			roleMstDto.setRoleId(obj.getRoleId());
			roleMstDto.setRoleName(obj.getRoleName());
			roleMstDto.setDisabledFlag(true);
			roleMstList.add(roleMstDto);
		}
		renderedAction = true;
	}*/


	public void editRoleMst(RoleMstDto roleMstDto) {
		roleMstDto.setDisabledFlag(false);
	}

	public void deleteRoleMst(RoleMstDto roleMstDto) {
		try{
			Long userId = loginAction.getUserList().get(0).getUserid();
			Boolean status = roleMstService.deleteRoleMst(roleMstDto.getRoleId(),userId);
			
			if (status) {
				roleMstList.remove(roleMstDto);
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Role Deleted Successfully ", null));
			} else {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Error Occured, Please try Again ", null));
				
			}
		}catch(Exception e){
			log.info("RoleMstAction deleteRoleMst Error : ", e);
		}
	}

	public void addNewRoleList() {
		RoleMstDto roleMstDto = new RoleMstDto();
		roleMstDto.setDisabledFlag(false);
		roleMstList.add(roleMstDto);
	}

	public void saveNewRoleList() {
		try{
			if (roleMstList.size() > 0) {
				Boolean status = false;
				List<RoleMst> list = new ArrayList<RoleMst>();
				Date now = new Date();
				
				for(RoleMstDto roleMstDto : roleMstList){
					if(roleMstDto.getDisabledFlag() ==false){
						RoleMst roleMst = new RoleMst();
						roleMst.setRoleId(roleMstDto.getRoleId());
						roleMst.setRoleName(roleMstDto.getRoleName());
						roleMst.setUserId(loginAction.getUserList().get(0).getUserid());
						roleMst.setDateTime(now);
						roleMst.setActiveFlag("Y");
						list.add(roleMst);
					}
				}

				if (list.size() > 0) {
					status = roleMstService.saveRoleMst(list);
				}
				if (status) {
					onSearch();
					FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,
									"Save Successful: ", "Role Save Successfully"));
				} else {
					FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,
									"Error: ", "Role Not Save Successfully"));
				}
			} else {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Error Occured: ", "Please Try Again"));
			}
		}catch(Exception e){
			log.info("RoleMstAction saveNewRoleList Error : ", e);
		}
	}

	
	/* GETTER SETTER */
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}


	public RoleMst getRoleMst() {
		return roleMst;
	}

	public void setRoleMst(RoleMst roleMst) {
		this.roleMst = roleMst;
	}

	public Boolean getRenderedAction() {
		return renderedAction;
	}

	public void setRenderedAction(Boolean renderedAction) {
		this.renderedAction = renderedAction;
	}

	public List<RoleMstDto> getRoleMstList() {
		return roleMstList;
	}

	public void setRoleMstList(List<RoleMstDto> roleMstList) {
		this.roleMstList = roleMstList;
	}

	public RoleMstDto getRoleMstDto() {
		return roleMstDto;
	}

	public void setRoleMstDto(RoleMstDto roleMstDto) {
		this.roleMstDto = roleMstDto;
	}

	public Boolean getRenderedRoleList() {
		return renderedRoleList;
	}

	public void setRenderedRoleList(Boolean renderedRoleList) {
		this.renderedRoleList = renderedRoleList;
	}
	
}
