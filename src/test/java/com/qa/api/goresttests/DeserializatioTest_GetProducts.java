package com.qa.api.goresttests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.Products;
import com.qa.api.utils.ObjectMapperUtil;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class DeserializatioTest_GetProducts extends BaseTest {

	@Test(enabled=false)
	public void getProducts() throws Exception  {

		Response res = restClient.get(BASE_URL_GOREST,"/products", null, null, AuthType.NO_AUTH, ContentType.JSON);

		Assert.assertEquals(res.getStatusCode(), 200);
		Products[] product = ObjectMapperUtil.deSerialization(res, Products[].class);
		System.out.println(product);

		for (Products i : product) {
			System.out.println("ID is " + i.getId());
			System.out.println("Title is " + i.getTitle());
			System.out.println("Price is " + i.getPrice());
			System.out.println("Rate is: " + i.getRating().getRate());
			System.out.println("Rating of count is: " + i.getRating().getCount());
		System.out.println("*******");
		}

	}

}
