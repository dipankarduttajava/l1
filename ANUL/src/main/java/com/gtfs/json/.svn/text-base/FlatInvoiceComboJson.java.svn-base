package com.gtfs.json;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gtfs.dto.FlatInvoiceComboDto;
import com.gtfs.service.FlatInvoiceComboService;

@Controller
@RequestMapping("/flatInvoiceCombo")
public class FlatInvoiceComboJson implements Serializable {
	private Logger log = Logger.getLogger(FlatInvoiceComboJson.class);
	
	@Autowired
	private FlatInvoiceComboService flatInvoiceComboService;

	@RequestMapping(value="/findByInvoiceNo",method=RequestMethod.GET)
	public @ResponseBody List<FlatInvoiceComboDto> findByInvoiceNo(@RequestParam("invoiceNo") String invoiceNo){
		return flatInvoiceComboService.findByInvoiceNo(invoiceNo);
	}
}