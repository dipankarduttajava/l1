package com.gtfs.converter;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gtfs.bean.LicProofMst;
import com.gtfs.dao.interfaces.LicProofMstDao;

@Component
@Scope("application")
public class LicProofMstConverter implements Serializable, Converter{
	@Autowired
	private LicProofMstDao licProofMstDao;

	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		try{
			if(value!=null){
				
				return licProofMstDao.findById(Long.parseLong(value));
			}
		}catch(Exception e){
			return null;
		}
		
		return null;
		
	}

	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if(value !=null){
			if(value instanceof LicProofMst){
				return ((LicProofMst)value).getId().toString();
			}else{
				return null;
			}
			
		}
		return null;
	}

}
