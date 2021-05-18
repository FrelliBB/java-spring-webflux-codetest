package com.example.demo.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromValue;

@SpringBootTest
@AutoConfigureWebTestClient
class CustomerControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void testSortCustomers_singleCustomer_responseFormatSameAsRequestFormat() throws Exception {
        // this would be a nicer looking multiline string with Java 13 :)
        String requestBody = "[\n" +
                "  {\n" +
                "    \"id\": 10000000,\n" +
                "    \"name\": \"Ulysses Leon\",\n" +
                "    \"duetime\": \"2014-06-18T06:26:56-07:00\",\n" +
                "    \"jointime\": \"2015-04-08T12:47:16-07:00\"\n" +
                "  }\n" +
                "]";

        webTestClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/customers/sort")
                        .queryParam("order", "DUE_TIME_ASC")
                        .build())
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .body(fromValue(requestBody))
                .exchange()
                .expectStatus().isOk()
                .expectBody().json(requestBody);
    }
}
