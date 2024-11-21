package com.qa.api.goresttests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.User;
import com.qa.api.utils.StringUtils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class DeleteUserTest extends BaseTest {

	@Test
	public void DeleteUserTestCase() {

		User user = User.builder().name("Myra").email(StringUtils.getRandomEmailID()).gender("Female").status("active")
				.build();
		Response res = restClient.post(BASE_URL_GOREST,"/public/v2/users", user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(res.getStatusCode(), 201);

		// fetch the iD
		String userId = res.jsonPath().getString("id");

		// Get the usID
		Response resGet = restClient.get(BASE_URL_GOREST,"/public/v2/users/" + userId, null, null, AuthType.BEARER_TOKEN,
				ContentType.JSON);
		Assert.assertEquals(resGet.getStatusCode(), 200);
		Assert.assertEquals(resGet.jsonPath().getString("id"), userId);

		
		//delete the user 
		
		Response  resDelete= restClient.delete(BASE_URL_GOREST,"/public/v2/users/" + userId, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(resDelete.getStatusCode(), 204);
		
		//GET
		
		
		Response resGetAfterDelete = restClient.get(BASE_URL_GOREST,"/public/v2/users/" + userId, null, null, AuthType.BEARER_TOKEN,
				ContentType.JSON);
		
		System.out.println("resGetAfterDelete====>"+resGetAfterDelete.statusCode());
		Assert.assertEquals(resGetAfterDelete.getStatusCode(), 404);
		Assert.assertEquals(resGetAfterDelete.jsonPath().getString("message"), "Resource not found");
	}
}