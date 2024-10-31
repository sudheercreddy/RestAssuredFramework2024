package com.qa.api.reqres;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Singleusernotfound extends BaseTest{
	
	
	@Test(enabled=true)
	public void SingleusernotfoundTest() {
		Response  res= restClient.get(BASE_URL_REQ_RES,"/api/users/23", null, null, AuthType.NO_AUTH, ContentType.JSON);
		Assert.assertEquals(res.getStatusCode(), 404);
	}

}
