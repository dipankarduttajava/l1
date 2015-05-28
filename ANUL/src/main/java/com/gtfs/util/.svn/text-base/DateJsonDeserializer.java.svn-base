package com.gtfs.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;



public class DateJsonDeserializer extends JsonDeserializer<Date>{

	@Override
	public Date deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
			throws IOException, JsonProcessingException {
		 SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	        String date = jsonparser.getText();
	        try {
	            return format.parse(date);
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	}

}
