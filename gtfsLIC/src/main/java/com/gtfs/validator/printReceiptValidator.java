package com.gtfs.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


@FacesValidator("com.gtfs.validator.printReceiptValidator")
public class printReceiptValidator implements Validator{
	
	public void validate(FacesContext facesContext, UIComponent component, Object value) throws ValidatorException {		
		UIInput receiptFrom = (UIInput)component.findComponent("receiptFrom");
		UIInput receiptTo = (UIInput)component.findComponent("receiptTo");
        
        Long from = (Long) receiptFrom.getValue();
        Long to = (Long) receiptTo.getValue();
        
        if(to < from){
        	FacesMessage msg = new FacesMessage("Save Unsuccessful","Receipt No. To must be Greater than Receipt No. From");
                 msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                 throw new ValidatorException(msg);
        }
	}
}