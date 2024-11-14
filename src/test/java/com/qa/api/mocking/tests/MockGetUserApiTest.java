package com.qa.api.mocking.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.mocking.ApiMocks;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.equalTo;

public class MockGetUserApiTest extends BaseTest{
	
	
	
	
	
	@Test
	public void dummyGetUSerTest() {
		ApiMocks.getDummyUser();
		Response  response = restClient.get(BASE_URL_LOCALHOST, BASE_URL_LOCALHOST_ENDPOINT, null, null, AuthType.NO_AUTH, ContentType.JSON);
	//	Assert.assertEquals(response.statusCode(), 200);
		
		response.
			then().
					statusCode(200)
								.body("name", equalTo("tom"));
		
		
	}

}
