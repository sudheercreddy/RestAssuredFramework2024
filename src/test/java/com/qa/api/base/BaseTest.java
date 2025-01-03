package com.qa.api.base;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.qa.api.client.RestClient;
import com.qa.api.mocking.WireMockSetup;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;

public class BaseTest {

	protected static final String BASE_URL_REQ_RES = "https://reqres.in";

	protected static final String BASE_URL_PRODUCT = "https://fakestoreapi.com";

	protected static final String BASE_URL_GOREST = "https://gorest.co.in";

	protected static final String BASE_URL_CONTACTS = "https://thinking-tester-contact-list.herokuapp.com";

	protected static final String BASE_URL_CIRCUIT = "https://ergast.com";

	protected static final String BASE_URL_BASIC_AUTH = "https://the-internet.herokuapp.com";

	protected static final String BASE_URL_AMADEUS = "https://test.api.amadeus.com";
	
	protected static final String BASE_URL_LOCALHOST = "http://localhost:8089";
	
	
	//************************************ENDPOINTS**********************************
	
	protected static final String BASE_URL_LOCALHOST_ENDPOINT = "/api/users";
	protected static final String BASE_URL_LISTUSERS_ENDPOINT = "/public/v2/users";

	protected RestClient restClient;

	@BeforeSuite
	public void setupReports() {
		RestAssured.filters(new AllureRestAssured());
	}

	@BeforeTest
	public void setup() {

	restClient = new RestClient();
	WireMockSetup.createWireMockServer();
	}

	@AfterTest
	public void stopServer() {
	WireMockSetup.stopWireMockServer();

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
