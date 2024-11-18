package com.qa.api.mocking;

import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;


public class ApiMocks {
	
	
	//********************* Create Mock GET CALLS*******************

	// create stub , create mock or behaviors of a user
	
	// url : - http://localhost:8089/api/users
	public static void getDummyUser() {
		stubFor(get(urlEqualTo("/api/users"))
				.willReturn(aResponse()
				.withStatus(200)
				.withHeader("Content-Type" ,"application/json")
				.withBody("{\r\n"
							+ "    "+ "\"name\" : \"tom\"\r\n" 
							+ "}")
				)

		);
	}
	
	
	//********************* Create Mock POST CALLS*******************
	
	public static void createDummyUser() {
		stubFor(get(urlEqualTo("/api/users"))
				.withHeader("Content-Type" ,equalTo("application/json"))
				.willReturn(aResponse()
				.withStatus(201)
				.withHeader("Content-Type" ,"application/json")
				.withStatusMessage("a user is created")
				.withBody("{\"id\": 1,\"name\": \"tom\"}")
				));
	}
	
	

}
