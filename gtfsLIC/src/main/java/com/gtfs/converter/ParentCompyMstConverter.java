package com.gtfs.converter;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gtfs.bean.ParentCompyMst;
import com.gtfs.dao.interfaces.ParentCompyMstDao;
import com.gtfs.service.interfaces.ParentCompyMstService;

@Component
@Scope("application")
public class ParentCompyMstConverter implements Converter, Serializable {
	@Autowired
	private ParentCompyMstDao parentCompyMstDao;
 
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
    	try{
			if(value!=null){
				return parentCompyMstDao.findById(Long.parseLong(value));
			}
		}catch(Exception e){
			return null;
		}
		
		return null;
        		
    }
 
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
    	if(object != null) {
            return String.valueOf(((ParentCompyMst) object).getPrntCompyId());
        }
        else {
            return null;
        }
    }
}
