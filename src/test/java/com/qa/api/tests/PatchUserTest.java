package com.qa.api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.User;
import com.qa.api.utils.StringUtils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PatchUserTest extends BaseTest{
	
	
	@Test
	public void updateTestAPIWithbuilderPatch() {

		User user = User.builder().name("Myra").email(StringUtils.getRandomEmailID()).gender("female").status("active")
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

		// partial update the userdetails using setters;

		user.setName("ApiAutomation");
		user.setEmail(StringUtils.getRandomEmailID());
		
		Response resPut = restClient.patch(BASE_URL_GOREST,"/public/v2/users/"+userId, user, null, null, AuthType.BEARER_TOKEN,
				ContentType.JSON);
		Assert.assertEquals(resGet.getStatusCode(), 200);
		
		Assert.assertEquals(resPut.jsonPath().getString("gender"),user.getGender());
		Assert.assertEquals(resPut.jsonPath().getString("email"),user.getEmail());
	}

}
