package com.shang.spray.utils;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class Utils {

	static volatile ObjectMapper objectMapper = null;
	
	public static String toJSON(Object obj){
		ObjectMapper mapper = getInstance();
		String json = "";
		try {
			json = mapper.writer().writeValueAsString(obj == null ? new java.util.HashMap() : obj);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return json;
	}


	public static ObjectMapper getInstance(){
		if (objectMapper==null){
			synchronized (ObjectMapper.class) {
				if (objectMapper==null){
					objectMapper = new ObjectMapper();
				}
			}
		}
		return objectMapper;
	}

}
