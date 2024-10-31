package com.qa.api.utils;

import java.util.List;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;

import io.restassured.response.Response;

public class JsonPathValidatorJayWay {

	private static String getJsonResponseAsString(Response response) {

		return response.getBody().asString();

	}

	public static <T> T read(Response response, String jsonPath) {

		String jsonResponse = getJsonResponseAsString(response);
		ReadContext readCont = JsonPath.parse(jsonResponse);

		return readCont.read(jsonPath);

	}

	public static <T> List<T> readList(Response response, String jsonPath) {

		String jsonResponse = getJsonResponseAsString(response);
		ReadContext readCont = JsonPath.parse(jsonResponse);

		return readCont.read(jsonPath);

	}

	public static <T> List<T> readListOfMaps(Response response, String jsonPath) {

		String jsonResponse = getJsonResponseAsString(response);
		ReadContext readCont = JsonPath.parse(jsonResponse);

		return readCont.read(jsonPath);

	}

}
