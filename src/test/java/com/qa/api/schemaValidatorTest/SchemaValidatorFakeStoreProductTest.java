package com.qa.api.schemaValidatorTest;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.utils.SchemaValidator;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class SchemaValidatorFakeStoreProductTest extends BaseTest{

	
	@Test
	public void fakeStoreApi() {
		
	Response res = 	restClient.get(BASE_URL_PRODUCT, "/products", null, null,  AuthType.NO_AUTH, ContentType.JSON);
	
	
	Assert.assertEquals(SchemaValidator.validateSchema(res, "schema/product_fakeStore.json"), true);
		
	}
}
