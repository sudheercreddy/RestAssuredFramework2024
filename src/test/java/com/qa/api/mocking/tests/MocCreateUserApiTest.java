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
		
		
		Response  response =restClient.post(BASE_URL_LOCALHOST, BASE_URL_LOCALHOST_ENDPOINT, null, null, null, AuthType.NO_AUTH, ContentType.JSON);
	
		response.
			then().
					statusCode(201)
								.body("id", equalTo(1));
		
		
	}

}
