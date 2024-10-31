package com.qa.api.contacts;


import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.manager.ConfigManager;
import com.qa.api.pojo.ContactsCredentials;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ContactsTest extends BaseTest {
	String tokenID;

	@BeforeMethod
	public void setup() {
		/*
		 * ContactsCredentials conCren =
		 * ContactsCredentials.builder().email("sud9eer@gmail.com").password(
		 * "Welcome@2024") .build();
		 */
		
		
		  ContactsCredentials
		  conCren1=new ContactsCredentials("sud9eer@gmail.com","Welcome@2024");
		 

		Response response = restClient.post(BASE_URL_CONTACTS,"/users/login", conCren1, null, null, AuthType.NO_AUTH, ContentType.JSON);
		tokenID = response.jsonPath().get("token");
		ConfigManager.set("contacts_bearerToken", tokenID);

	}

	@Test
	public void contactsTest() {

		Response res = restClient.get(BASE_URL_CONTACTS,"/contacts?firstName=sudheer", null, null, AuthType.CONTACTS_BEARER_TOKEN,
				ContentType.JSON);
		//Assert.assertEquals(res.statusCode(), 200);
		
		

	}
}
