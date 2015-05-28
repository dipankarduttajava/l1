package com.gtfs.service.interfaces;

import java.io.Serializable;
import java.util.List;

import com.gtfs.bean.SchemeMst;


public interface SchemeMstService extends Serializable{
	List<SchemeMst> findAcitveSchemeMstList();
}
