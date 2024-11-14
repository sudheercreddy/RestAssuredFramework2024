package com.qa.api.mocking;

import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;


public class ApiMocks {

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

}
