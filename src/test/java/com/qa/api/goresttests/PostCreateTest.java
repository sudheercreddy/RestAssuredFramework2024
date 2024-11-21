package com.qa.api.goresttests;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.User;
import com.qa.api.utils.StringUtils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PostCreateTest extends BaseTest {

	@DataProvider
	public Object[][] getuserData() {

		return new Object[][] {

				{ "xsudheer", "male", "active" },
				{ "xmyra", "female", "active" },
				{ "xbindu", "female", "active" }

		};

	}

	@Test(dataProvider = "getuserData",invocationCount = 5)
	public void createTestApiwithLombakTest(String firstName, String gender, String status) {

		// User usera = new User(null,"aaSudheer", StringUtils.getRandomEmailID(),
		// "male", "active");
		User usera = new User(null, firstName, StringUtils.getRandomEmailID(), gender, status);

		Response response = restClient.post(BASE_URL_GOREST, "/public/v2/users", usera, null, null,
				AuthType.BEARER_TOKEN, ContentType.JSON);

		Assert.assertEquals(response.getStatusCode(), 201);
		// fetch userid:

		String userId = response.jsonPath().getString("id");
		System.out.println("userid====>" + userId);

		// GET
		Response resGet = restClient.get(BASE_URL_GOREST, "/public/v2/users/" + userId, null, null,
				AuthType.BEARER_TOKEN, ContentType.JSON);
		System.out.println("Status Code===>" + resGet.statusCode());
		Assert.assertEquals(resGet.statusCode(), 200, "******Status code is not 201");
		Assert.assertEquals(resGet.jsonPath().getString("id"), userId);
		Assert.assertEquals(resGet.jsonPath().getString("name"), usera.getName());
		Assert.assertEquals(resGet.jsonPath().getString("email"), usera.getEmail());

	}

	@Test(dataProvider = "getuserData")
	public void createTestAPIWithbuilderPat(String firstName, String gender, String status) {

		User user = User.builder().name(firstName).email(StringUtils.getRandomEmailID()).gender(gender).status(status)
				.build();
		Response res = restClient.post(BASE_URL_GOREST, "/public/v2/users", user, null, null, AuthType.BEARER_TOKEN,
				ContentType.JSON);
		Assert.assertEquals(res.getStatusCode(), 201);

		String userId = res.jsonPath().getString("id");

		Response resGet = restClient.get(BASE_URL_GOREST, "/public/v2/users/" + userId, null, null,
				AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(resGet.jsonPath().getString("id"), userId);

	}

	/*
	 * @Test public void createTestAPIWithJsonFile() {
	 * 
	 * File jsonFile = new File("/src/test/resources/jsons/user.json"); Response res
	 * = restClient.post("/public/v2/users", jsonFile, null, null,
	 * AuthType.BEARER_TOKEN, ContentType.JSON);
	 * Assert.assertEquals(res.getStatusCode(), 201);
	 * 
	 * }
	 */

}
