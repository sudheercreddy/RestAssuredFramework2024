package com.qa.api.utils;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class SchemaValidator {

	public static boolean validateSchema(Response response, String schemeFileName) {

		try {
			response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(schemeFileName));
			System.out.println("Scheme Validation is working as expected");
			return true;
		} catch (Exception e) {
			System.out.println("JsonSchemaValidator FAILED" + e.getMessage());
			return false;
		}

	}

}
