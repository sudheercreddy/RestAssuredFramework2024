package com.qa.api.gorestP.tests;

import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ListUsers extends BaseTest{
	
	
	@Test
	public void getListUsers() {
		
		Response res= restClient.get(BASE_URL_GOREST, BASE_URL_LISTUSERS_ENDPOINT, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		System.out.println(res.getStatusCode());
		
		
		
	}

}
