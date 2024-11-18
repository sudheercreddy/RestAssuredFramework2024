package com.qa.api.mocking.tests;

import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.mocking.ApiMocks;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class MocCreateUserApiTest extends BaseTest{
	
	
	

	@Test
	public void dummyCreateUSerTest() {
		ApiMocks.createDummyUser();
		
		String dummyJson="{\"name\": \"tom\"}";
		Response  response =restClient.post(BASE_URL_LOCALHOST, "/api/users", dummyJson, null, null, AuthType.NO_AUTH, ContentType.JSON);
	
		response.
			then().assertThat().and()
					.statusLine(equalTo("HTTP/1.1 201 user is created"))
								.body("id", equalTo(1));
		
		
	}
	
	
	@Test
	public void dummyCreateUSerwithBodyFileTest() {
		ApiMocks.createDummyUserwithBodyFile();
		
		String dummyJson="{\"name\": \"api\"}";
		Response  response =restClient.post(BASE_URL_LOCALHOST, "/api/users", dummyJson, null, null, AuthType.NO_AUTH, ContentType.JSON);
	
		response.
			then().assertThat().and()
					.statusLine(equalTo("HTTP/1.1 201 user is created"))
								.body("id", equalTo(101));
		
		
	}

}
