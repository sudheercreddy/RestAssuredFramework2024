package com.qa.api.mocking.tests;

import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.mocking.ApiMocks;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class MockPatchUserApiTest extends BaseTest{
	
	
	@Test
	public void dummyPatchUSerTest() {
		ApiMocks.patchDummyUser();
		
		String dummypatchJson="{\"name\": \"tom\"}";
		Response  response =restClient.patch(BASE_URL_LOCALHOST, "/api/users", dummypatchJson, null, null, AuthType.NO_AUTH, ContentType.JSON);
	
		response.
			then().assertThat().and()
					.statusLine(equalTo("HTTP/1.1 200 user is created"))
								.body("id", equalTo(1));
		
		
	}
	
	

}
