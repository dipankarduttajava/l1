package com.gtfs.action;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gtfs.bean.LicProductCatMst;
import com.gtfs.bean.SchemeMst;
import com.gtfs.bean.TieupCompyMst;
import com.gtfs.dto.LicProductMstDto;
import com.gtfs.service.interfaces.LicProductCatMstService;
import com.gtfs.service.interfaces.SchemeMstService;
import com.gtfs.service.interfaces.TieupCompyMstService;

@Component
@Scope("session")
public class LicProductMstAction implements Serializable{
	private Logger log = Logger.getLogger(LicProductMstAction.class);
	
	@Autowired
	private SchemeMstService schemeMstService;
	@Autowired
	private LicProductCatMstService licProductCatMstService;
	@Autowired
	private TieupCompyMstService tieupCompyMstService;
	
	private LicProductMstDto licProductMstDto;

	
	@PostConstruct
	public List<SchemeMst> findAllFromSchemeMst(){
		return schemeMstService.findAcitveSchemeMstList();
	}
	@PostConstruct
	public List<LicProductCatMst> findAllfromLicProductCatMst(){
		return licProductCatMstService.findActiveLicProdCategory();
	}
	@PostConstruct
	public List<TieupCompyMst> findAllActiveTieupCompyList(){
		return tieupCompyMstService.findAllActiveTieupCompyList();
	}

	public String onLoad(){
		licProductMstDto = new LicProductMstDto();
		return "/admin/licProductMst.xhtml";
	}
	
	public void setLicProductMstDto(LicProductMstDto licProductMstDto) {
		this.licProductMstDto = licProductMstDto;
	}
	
	public LicProductMstDto getLicProductMstDto() {
		return licProductMstDto;
	}
	
}
