package com.gtfs.converter;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gtfs.bean.BranchMst;
import com.gtfs.bean.LicPremiumListDtls;
import com.gtfs.dao.interfaces.BranchMstDao;
import com.gtfs.dao.interfaces.LicPremiumListDao;

@Component
@Scope("application")
public class PremListConverter implements Converter, Serializable {
	
	@Autowired
	private LicPremiumListDao licPremiumListDao;
 
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
    	try{
			if(value != null){
				return licPremiumListDao.findById(Long.parseLong(value));
			}
		}catch(Exception e){
			return null;
		}
		
		return null;
        		
    }
 
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
    	if(object != null) {
            return String.valueOf(((LicPremiumListDtls) object).getId());
        }
        else {
            return null;
        }
    }

}     