package com.qa.api.mocking;

import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.patch;
import static com.github.tomakehurst.wiremock.client.WireMock.delete;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;

public class ApiMocks {

	// ********************* Create Mock GET CALLS*******************

	// create stub , create mock or behaviors of a user

	// url : - http://localhost:8089/api/users
	public static void getDummyUser() {
		stubFor(get(urlEqualTo("/api/users"))
				.willReturn(aResponse().withStatus(200).withHeader("Content-Type", "application/json")
						.withBody("{\r\n" + "    " + "\"name\" : \"tom\"\r\n" + "}"))

		);
	}

	public static void getDummyUserWithJsonFile() {
		stubFor(get(urlEqualTo("/api/users")).willReturn(
				aResponse().withStatus(200).withHeader("Content-Type", "application/json").withBodyFile("user.json"))

		);
	}

	public static void getDummyUserWithQueryParams() {
		stubFor(get(urlPathEqualTo("/api/users"))
				.withQueryParam("name", equalTo("sudheer"))
				.willReturn(aResponse()
						.withStatus(200)
						.withHeader("Content-Type", "application/json")
						.withBodyFile("user.json"))

		);
	}

	// ********************* Create Mock POST CALLS*******************

	public static void createDummyUser() {
		stubFor(post(urlEqualTo("/api/users")).withHeader("Content-Type", equalTo("application/json"))
				.willReturn(aResponse().withStatus(201).withHeader("Content-Type", "application/json")
						.withStatusMessage("user is created").withBody("{\"id\": 1,\"name\": \"tom\"}")));
	}



	public static void createDummyUserwithBodyFile() {
		stubFor(post(urlEqualTo("/api/users")).withHeader("Content-Type", equalTo("application/json"))
				.willReturn(aResponse().withStatus(201).withHeader("Content-Type", "application/json")
						.withStatusMessage("user is created").withBodyFile("user.json")));
	}

	

	public static void patchDummyUser() {
		stubFor(patch(urlEqualTo("/api/users")).withHeader("Content-Type", equalTo("application/json"))
				.willReturn(aResponse().withStatus(201).withHeader("Content-Type", "application/json")
						.withStatusMessage("user is created").withBody("{\"id\": 1,\"name\": \"sudheer\"}")));
	}
	
	// ********************* DELETE Mock POST CALLS*******************
	
	public static void deleteDummyUser() {
		stubFor(delete(urlEqualTo("/api/users/1"))
				.willReturn(aResponse().withStatus(204)
						.withStatusMessage("USER DELETED")
						.withHeader("server", "NALabs")
						
						
						));
	}

}
