package com.qa.products.api.tests;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ProductsTests extends BaseTest {

	@Test(enabled=true)
	public void ProductsTestAPi() {
		Response res = restClient.get(BASE_URL_PRODUCT,"/products", null, null, AuthType.NO_AUTH, ContentType.JSON);
		
		List<String> fname= res.jsonPath().getList("title");
		
		for (String name : fname) {
			System.out.println(name);
			
		}
		
		Assert.assertEquals(res.statusCode(), 200);

	}
	
	
	
	@Test
	public void ProductsTestLimitResultsAPi() {
		
		HashMap<String,String> queryParms = new HashMap<String, String>();
		queryParms.put("limit", "5");
		
		Response res = restClient.get(BASE_URL_PRODUCT,"/products", queryParms, null, AuthType.NO_AUTH, ContentType.JSON);
		
		
		Assert.assertEquals(res.statusCode(), 200);

	}


}
