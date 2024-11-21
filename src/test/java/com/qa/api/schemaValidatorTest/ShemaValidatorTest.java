package com.qa.api.schemaValidatorTest;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.User;
import com.qa.api.utils.SchemaValidator;
import com.qa.api.utils.StringUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class ShemaValidatorTest extends BaseTest {

	@Test(enabled=false)
	public void SchemeValidatorTest() {

		
		  RestAssured.given().header("Authorization",
		  "Bearer 4bca2c8e24d669e65c44c1c6788ecdef1fe48b1acfb5e0864847e0cc8510c04c")
		  .when() .get("https://gorest.co.in/public/v2/users/7498783") .then()
		  .assertThat() .statusCode(200)
		  .body(JsonSchemaValidator.matchesJsonSchemaInClasspath(
		  "schema/userSchema.json"));
		 

		/*
		 * User usera = new User(null, "aaSudheer", StringUtils.getRandomEmailID(),
		 * "male", "active");
		 * 
		 * Response response = restClient.post(BASE_URL_GOREST, "/public/v2/users",
		 * usera, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		 * 
		 * Assert.assertEquals(response.getStatusCode(), 201); // fetch userid:
		 * 
		 * String userId = response.jsonPath().getString("id");
		 * System.out.println("userid====>" + userId); Response responseGet =
		 * restClient.get(BASE_URL_GOREST, "in/public/v2/users/" + userId, null, null,
		 * AuthType.BEARER_TOKEN, ContentType.JSON);
		 */

		//Assert.assertEquals(SchemaValidator.validateSchema(responseGet, "schema/userSchema.json"), true);

	}

}
