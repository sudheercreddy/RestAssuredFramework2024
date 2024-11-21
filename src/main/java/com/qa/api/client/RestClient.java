package com.qa.api.client;

import static io.restassured.RestAssured.expect;

import java.util.Base64;
import java.util.Map;

import com.qa.api.constants.AuthType;
import com.qa.api.exceptions.FrameworkExceptions;
import com.qa.api.manager.ConfigManager;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.equalTo;

public class RestClient {

	private ResponseSpecification responseSpec200 = expect().statusCode(200);
	private ResponseSpecification responseSpec200or404 = expect().statusCode(anyOf(equalTo(200), equalTo(404)));
	private ResponseSpecification responseSpec201 = expect().statusCode(201);
	private ResponseSpecification responseSpec201or200 = expect().statusCode(anyOf(anyOf(equalTo(200), equalTo(201))));
	private ResponseSpecification responseSpec204 = expect().statusCode(204);
	private ResponseSpecification responseSpec400 = expect().statusCode(400);
	private ResponseSpecification responseSpec401 = expect().statusCode(401);
	private ResponseSpecification responseSpec404 = expect().statusCode(404);
	private ResponseSpecification responseSpec422 = expect().statusCode(422);
	private ResponseSpecification responseSpec500 = expect().statusCode(500);
	private ResponseSpecification DeleteresponseSpec404 = expect().statusCode(404);

	/*
	 * Encapsulation
	 */

	// private String baseUrl = ConfigManager.get("baseUrl");

	/**
	 * This method is used to setupRequest
	 * 
	 * @param authType    -BEARER_TOKEN, OAUTH2, BASCI_AUTH, API_KEY, NO_AUTH
	 * @param contentType - JSON,XML,FORM
	 * @return it return Get API Request
	 * @throws FrameworkExceptions 
	 */

	private RequestSpecification setupRequest(String baseUrl, AuthType authType, ContentType contentType) throws FrameworkExceptions {

		RequestSpecification request = RestAssured.given().log().all().baseUri(baseUrl).contentType(contentType)
				.accept(contentType);

		switch (authType) {
		case BEARER_TOKEN:
			request.header("Authorization", ConfigManager.get("bearerToken"));
			break;

		case CONTACTS_BEARER_TOKEN:
			request.header("Authorization", ConfigManager.get("contacts_bearerToken"));
			break;

		case OAUTH2:
			request.header("Authorization", "Bearer " + generateOAuth2Token());
			break;

		case BASIC_AUTH:
			request.header("Authorization", "Basic " + generateBaseAuthToken());
			break;

		case API_KEY:
			request.header("Authorization", ConfigManager.get("apiKey"));
			break;

		case NO_AUTH:
			System.out.println("Auth is not required....");
			break;

		default:
			System.out.println("This auth is not supported...Please pass the right AuthType");
			throw new FrameworkExceptions("NO AUTH SUPPORTED");
		}

		return request;

	}

	/**
	 * This method -generateOauth2Token, gets the access_token
	 * 
	 * @return
	 * 
	 */

	private String generateOAuth2Token() {
		return RestAssured.given()
				.formParam("client_id", ConfigManager.get("clientId"))
				.formParam("client_secret", ConfigManager.get("clientSecret"))
				.formParam("grand_type", ConfigManager.get("grantType"))
				.when().post(ConfigManager.get("tokenUrl"))
				.then().extract().path("access_token");

	}
/**
 * 
 * This method is used to generate the Base64 encoded String
 * @return
 */
	private String generateBaseAuthToken() {

		String credentials = ConfigManager.get("BasicUsername") + ":" + ConfigManager.get("BasicPassword");//admin:admin
		return Base64.getEncoder().encodeToString(credentials.getBytes());

	}

	// *****************************************CRUD//
	// METHODS//*****************************************

	/**
	 * This Get method returns the Response of API
	 * 
	 * @param endPoint
	 * @param queryParams
	 * @param pathparams
	 * @param authType
	 * @param contentType
	 * @return Response
	 * @throws FrameworkExceptions 
	 */
	public Response get(String baseUrl, String endPoint, Map<String, String> queryParams,
			Map<String, String> pathparams, AuthType authType, ContentType contentType) throws FrameworkExceptions {

		RequestSpecification request = setUpAuthAndContentType(baseUrl, authType, contentType);

		applyParams(request, queryParams, pathparams);

		Response response = request.log().all().get(endPoint).then().spec(responseSpec200or404).extract().response();
		response.prettyPrint();
		return response;

	}

	/**
	 * this method is used to call the post API
	 * 
	 * @param <T>
	 * @param endPoint
	 * @param body
	 * @param queryParams
	 * @param pathparams
	 * @param authType
	 * @param contentType
	 * @return post Response
	 * @throws FrameworkExceptions 
	 */
	public <T> Response post(String baseUrl, String endPoint, T body, Map<String, String> queryParams,
			Map<String, String> pathparams, AuthType authType, ContentType contentType) throws FrameworkExceptions {

		RequestSpecification request = setUpAuthAndContentType(baseUrl, authType, contentType);

		applyParams(request, queryParams, pathparams);

		Response response = request.body(body).post(endPoint).then().spec(responseSpec201or200).extract().response();
		response.prettyPrint();
		return response;
	}

	/**
	 * This methos is used to call the post API with class object
	 * 
	 * @param endPoint
	 * @param body
	 * @param queryParams
	 * @param pathparams
	 * @param authType
	 * @param contentType
	 * @return
	 */

	/*
	 * public Response postObject(String baseUrl,String endPoint, Object body,
	 * Map<String, String> queryParams, Map<String, String> pathparams, AuthType
	 * authType, ContentType contentType) {
	 * 
	 * RequestSpecification request = setUpAuthAndContentType(baseUrl,authType,
	 * contentType);
	 * 
	 * applyParams(request, queryParams, pathparams);
	 * 
	 * Response response =
	 * request.body(body).post(endPoint).then().log().all().spec(responseSpec201).
	 * extract().response(); response.prettyPrint(); return response; }
	 * 
	 */

	/**
	 * This method is used to call the POST API with body type- File
	 * 
	 * @param endPoint
	 * @param file
	 * @param queryParams
	 * @param pathparams
	 * @param authType
	 * @param contentType
	 * @return
	 * @throws FrameworkExceptions 
	 *//*
		 * public Response post(String endPoint, File file, Map<String, String>
		 * queryParams, Map<String, String> pathparams, AuthType authType, ContentType
		 * contentType) {
		 * 
		 * RequestSpecification request = setUpAuthAndContentType(authType,
		 * contentType);
		 * 
		 * applyParams(request, queryParams, pathparams);
		 * 
		 * Response response =
		 * request.body(file).post(endPoint).then().spec(responseSpec201).extract().
		 * response(); response.prettyPrint(); return response;
		 * 
		 * }
		 */

	public <T> Response put(String baseUrl, String endPoint, T body, Map<String, String> queryParams,
			Map<String, String> pathparams, AuthType authType, ContentType contentType) throws FrameworkExceptions {

		RequestSpecification request = setUpAuthAndContentType(baseUrl, authType, contentType);

		applyParams(request, queryParams, pathparams);

		Response response = request.body(body).put(endPoint).then().spec(responseSpec200).extract().response();
		response.prettyPrint();
		return response;
	}

	public <T> Response patch(String baseUrl, String endPoint, T body, Map<String, String> queryParams,
			Map<String, String> pathparams, AuthType authType, ContentType contentType) throws FrameworkExceptions {

		RequestSpecification request = setUpAuthAndContentType(baseUrl, authType, contentType);

		applyParams(request, queryParams, pathparams);

		Response response = request.body(body).patch(endPoint).then().spec(responseSpec200).extract().response();
		response.prettyPrint();
		return response;
	}

	public Response delete(String baseUrl, String endPoint, Map<String, String> queryParams,
			Map<String, String> pathparams, AuthType authType, ContentType contentType) throws FrameworkExceptions {

		RequestSpecification request = setUpAuthAndContentType(baseUrl, authType, contentType);

		applyParams(request, queryParams, pathparams);

		Response response = request.delete(endPoint).then().spec(responseSpec204).extract().response();
		response.prettyPrint();
		return response;
	}

	/**
	 * This method will add the Params
	 * 
	 * @param request
	 * @param queryParams
	 * @param pathparams
	 */
	private void applyParams(RequestSpecification request, Map<String, String> queryParams,
			Map<String, String> pathparams) {

		if (queryParams != null) {
			request.queryParams(queryParams);
		}
		if (pathparams != null) {
			request.queryParams(pathparams);
		}

	}

	/**
	 * This method will return the RequestSpecification
	 * 
	 * @param authType
	 * @param contentType
	 * @return
	 * @throws FrameworkExceptions 
	 */
	private RequestSpecification setUpAuthAndContentType(String baseUrl, AuthType authType, ContentType contentType) throws FrameworkExceptions {
		return setupRequest(baseUrl, authType, contentType);

	}

}
