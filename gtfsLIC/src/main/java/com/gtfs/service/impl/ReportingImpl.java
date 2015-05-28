package com.gtfs.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.component.html.HtmlOutputText;

import org.primefaces.component.api.UIColumn;
import org.primefaces.component.column.Column;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.row.Row;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.gtfs.service.interfaces.Reporting;
@Service

public class ReportingImpl implements Reporting,Serializable{
	
	public DataTable createTable(List<Map<String,List<Object>>> colVals,String header){
		DataTable table = new DataTable();
		
		//header
		
		HtmlOutputText headerOutputText = new HtmlOutputText();
		headerOutputText.setValue(header);
		
		table.setHeader(headerOutputText);
		
		List<UIColumn> cols = new ArrayList<UIColumn>();
		List<Row> rowList = new ArrayList<Row>();
		
		System.out.println("dd "+colVals);
		
		for(Map<String,List<Object>> obj:colVals){
			Set<Map.Entry<String,List<Object>>>  set = obj.entrySet();
			
			Iterator<Map.Entry<String,List<Object>>> iterator = set.iterator();
			
			while(iterator.hasNext()){
				Map.Entry<String,List<Object>> col = iterator.next();
				
				Column column = new Column();
				column.setHeaderText(col.getKey());
				Row row = new Row();
				row.getChildren().add(column);
				
			}	
		}
		
		table.setColumns(cols);
		table.getChildren().addAll(rowList);
		
		
		return table;
	}
}
