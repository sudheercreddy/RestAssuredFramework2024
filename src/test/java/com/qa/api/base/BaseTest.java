package com.qa.api.base;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.qa.api.client.RestClient;
import com.qa.api.manager.ConfigManager;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;

public class BaseTest {

	protected static final String BASE_URL_REQ_RES = "https://reqres.in";

	protected static final String BASE_URL_PRODUCT = "https://fakestoreapi.com";

	protected static final String BASE_URL_GOREST = "https://gorest.co.in";

	protected static final String BASE_URL_CONTACTS = "https://thinking-tester-contact-list.herokuapp.com";

	protected static final String BASE_URL_CIRCUIT = "https://ergast.com";
	
	protected static final String BASE_URL_BASIC_AUTH = "https://the-internet.herokuapp.com";
	
	protected static final String BASE_URL_AMADEUS="https://test.api.amadeus.com";

	protected RestClient restClient;

	@BeforeTest
	public void setup() {
		RestAssured.filters(new AllureRestAssured() );
		restClient = new RestClient();
	}

	/*
	 * @Parameters({ "baseUrl" })
	 * 
	 * @BeforeTest public void setup(@Optional String baseUrl) {
	 * 
	 * if (baseUrl != null) { ConfigManager.set("baseUrl", baseUrl); } restClient =
	 * new RestClient(); }
	 */

}