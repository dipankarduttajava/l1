package com.gtfs.service.interfaces;

import java.util.List;
import java.util.Map;

import org.primefaces.component.datatable.DataTable;

public interface Reporting {
	DataTable createTable(List<Map<String,List<Object>>> colVals,String header);
}
