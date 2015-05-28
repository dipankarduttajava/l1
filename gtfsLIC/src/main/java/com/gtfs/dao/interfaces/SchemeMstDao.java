package com.gtfs.dao.interfaces;

import java.util.List;

import com.gtfs.bean.SchemeMst;

public interface SchemeMstDao {
	List<SchemeMst> findAcitveSchemeMstList();
}
