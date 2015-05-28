package com.gtfs.converter;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gtfs.bean.TieupCompyMst;
import com.gtfs.dao.interfaces.TieupCompyMstDao;
import com.gtfs.service.interfaces.TieupCompyMstService;
@Component
@Scope("application")
public class TieupCompyMstConverter implements Converter, Serializable{
	@Autowired
	private TieupCompyMstDao tieupCompyMstDao;
 
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
    	try{
			if(value!=null){
				return tieupCompyMstDao.findById(Long.parseLong(value));
			}
		}catch(Exception e){
			return null;
		}
		
		return null;
        		
    }
 
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
    	if(object != null) {
            return String.valueOf(((TieupCompyMst) object).getTieCompyId());
        }
        else {
            return null;
        }
    }
}
