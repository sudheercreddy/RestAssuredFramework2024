package com.qa.products.api.tests;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.utils.JsonPathValidatorJayWay;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ProductApiTestJsonPathValidator extends BaseTest {

	@Test
	public <T> void getProductTest() {
		Response res = restClient.get(BASE_URL_PRODUCT,"/products", null, null, AuthType.NO_AUTH, ContentType.JSON);

		Assert.assertEquals(res.getStatusCode(), 200);

		List<Number> priceList = JsonPathValidatorJayWay.readList(res, "$[?(@.price<50)].price");
		System.out.println(priceList);

		List<Number> ids = JsonPathValidatorJayWay.readList(res, "$[?(@.price<50)].id");
		System.out.println(ids);

		List<Double> rates = JsonPathValidatorJayWay.readList(res, "$[?(@.price<50)].rating.rate");
		System.out.println(rates);

		List<Integer> count = JsonPathValidatorJayWay.readList(res, "$[?(@.price<50)].rating.count");
		System.out.println(count);

		/*
		 * for (Number p : priceList) { System.out.println("The price is" + p); }
		 */

		// List of Map

		List<Map<String, T>> lmJ = JsonPathValidatorJayWay.readListOfMaps(res,
				"$[?(@.category=='jewelery')].['title','price']");
		System.out.println(lmJ);

		for (Map<String, T> map : lmJ) {
			System.out.println("===>map.get(\"title\")" + map.get("title"));

		}

		// single product

		Double maxprice = JsonPathValidatorJayWay.read(res, "max($[*].price)");
		System.out.println(maxprice);
		Double minprice = JsonPathValidatorJayWay.read(res, "min($[*].price)");
		System.out.println(minprice);
		Double avgprice = JsonPathValidatorJayWay.read(res, "avg($[*].price)");
		System.out.println(avgprice);
		Double sumprice = JsonPathValidatorJayWay.read(res, "sum($[*].price)");
		System.out.println(sumprice);
		Double stdDevprice = JsonPathValidatorJayWay.read(res, "stddev($[*].price)");
		System.out.println(stdDevprice);
	}

}
