package com.semantic;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

public class ReadJSON {

	public Set readJsonValues(String jsonString) {
		
		Set<String> keylist = null;
		
		JsonFactory jsonFactory = new JsonFactory();
		try{
		JsonParser parser = jsonFactory.createParser(jsonString); // (new File("C:/Users/sumit.rathore/Desktop/test.json"));

		// Map where to store your field-value pairs per object
		Map<String, String> fieldsMap = new HashMap<String, String>();

		JsonToken token;
		while ((token = parser.nextToken()) != JsonToken.END_ARRAY) {
		    switch (token) {

		        // Starts a new object, clear the map
		        case START_OBJECT:
		            fieldsMap.clear();
		            break;

		        // For each field-value pair, store it in the map 'fieldsMap'
		        case FIELD_NAME:
		            String field = parser.getCurrentName();
		            token = parser.nextToken();
		            String value = parser.getValueAsString();
		            fieldsMap.put(field, value);
		            break;

		        // Do something with the field-value pairs
		        case END_OBJECT:
		            System.out.println("Json map = "+fieldsMap);
		            System.out.println("Json map Size = "+ fieldsMap.size());
		            
		             keylist = fieldsMap.keySet();
		            
		           // System.out.println("Keys = "+keylist);
		            break;
		        }
		    }
		    parser.close();
		}catch(Exception e){
			
		}
		return keylist;
	}

}
