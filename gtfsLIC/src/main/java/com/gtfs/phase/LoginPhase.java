package com.gtfs.phase;

import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.gtfs.action.AccessListAction;
import com.gtfs.action.LoginAction;

public class LoginPhase implements PhaseListener{
	private Logger log = Logger.getLogger(LoginPhase.class);

	public void afterPhase(PhaseEvent event) {
		 FacesContext fc = event.getFacesContext();
		 HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		 LoginAction loginAction =(LoginAction) session.getAttribute("loginAction");
		 
		 
		if(loginAction==null){
			//log.info("View "+((HttpServletRequest)fc.getCurrentInstance().getExternalContext().getRequest()).getRequestURL());
			/*NavigationHandler nh = fc.getApplication().getNavigationHandler();
            nh.handleNavigation(fc, null, "login.xhtml");*/
			
			 ConfigurableNavigationHandler handler = (ConfigurableNavigationHandler) FacesContext.getCurrentInstance().getApplication().getNavigationHandler();

             handler.performNavigation("/login.xhtml");
		}else{
			if(!fc.getViewRoot().getViewId().equals("/login.xhtml")){
				if(loginAction.getLoginFlag()==null || loginAction.getLoginFlag()==false){
					ConfigurableNavigationHandler handler = (ConfigurableNavigationHandler) FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
					handler.performNavigation("/login.xhtml");
		            
		            
				}
			}
		}
		
	}

	public void beforePhase(PhaseEvent event) {
		
		
	}

	public PhaseId getPhaseId() {
		// TODO Auto-generated method stub
		return PhaseId.ANY_PHASE;
	}

}
