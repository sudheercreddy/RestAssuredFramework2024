package com.qa.api.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.response.Response;

public class ObjectMapperUtil {
	
	
	private static ObjectMapper objMapper = new ObjectMapper();
	
	/**
	 * This method will deserialze , with Passing the response and class with generic
	 * @param <T>
	 * @param response
	 * @param targetClass
	 * @return
	 * @throws Exception 
	 */
	public static <T>  T deSerialization(Response response, Class<T>targetClass) throws Exception {
		
		try {
			return objMapper.readValue(response.getBody().asString(), targetClass);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception("Deserialization failed ....."+ targetClass.getName());
		}
		
		
	}
	

}
