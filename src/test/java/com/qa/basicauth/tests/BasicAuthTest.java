package com.qa.basicauth.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class BasicAuthTest extends BaseTest{
	
	
	@Test
	public void basicAuthTest() {
		
		Response res = restClient.get(BASE_URL_BASIC_AUTH, "basic_auth", null, null, AuthType.BASIC_AUTH, ContentType.ANY);
		int statusCode = res.statusCode();
		System.out.println(statusCode);
		
	//	System.out.println(res.asString().concat("Congratulations! You"));
		
		Assert.assertEquals(res.asString().contains("Congratulations! You must have the proper credentials."), true);
	}
	

}
