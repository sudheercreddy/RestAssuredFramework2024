package com.qa.api.mocking.tests;

import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.mocking.ApiMocks;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class MockDeleteUserAPITest extends BaseTest {
	
	
	@Test
	public void deleteDummyUSeTest() {
		
		ApiMocks.deleteDummyUser();
		Response response = restClient.delete(BASE_URL_LOCALHOST, "/api/users/1", null, null, AuthType.NO_AUTH, ContentType.ANY);
		response.then().assertThat().statusCode(204);
	}

}
