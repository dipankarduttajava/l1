package com.gtfs.json;

import java.io.Serializable;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gtfs.dto.ReceiptDtlsWrapper;
import com.gtfs.service.ReceiptDtlsService;

@Controller
@RequestMapping("/receiptDtls")
public class ReceiptDtlsJson implements Serializable {
	
	@Autowired
	private ReceiptDtlsService receiptDtlsService;
	
	@RequestMapping(value="/saveReceiptDtls",method=RequestMethod.POST)
	public @ResponseBody String saveReceiptDtls(@RequestBody ReceiptDtlsWrapper receiptDtlsWrapper, Model model, Principal principal ){
		
		receiptDtlsWrapper.getReceiptDtlsDtos().get(0).setCreatedBy(Long.parseLong(principal.getName()));
		receiptDtlsWrapper.getReceiptDtlsDtos().get(0).setModifiedBy(Long.parseLong(principal.getName()));
		
		return receiptDtlsService.saveReceiptDtls(receiptDtlsWrapper.getReceiptDtlsDtos());
	}
}