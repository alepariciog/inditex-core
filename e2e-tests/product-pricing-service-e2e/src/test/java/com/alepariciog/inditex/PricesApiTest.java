package com.alepariciog.inditex;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

/** Tests for the prices API. */
public class PricesApiTest {

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
    }

    @DisplayName("Verify that retrieves price of ")
    @ParameterizedTest(name = "{0}.")
    @MethodSource("priceApiTestCases")
    void pricesApiShouldReturnTheProperPrice(
            RequestParameters parameters,
            ExpectedResponse response) {

        given()
                .queryParam("datetime", parameters.datetime())
                .pathParam("productId", parameters.productId())
                .pathParam("brandId", parameters.brandId())
            .when()
                .get("/api/prices/product/{productId}/brand/{brandId}")
            .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("priceList", equalTo((int) response.price_list()))
                .body("price", equalTo((float) response.price()))
                .body("brandId", equalTo((int) response.brandId()))
                .body("productId", equalTo((int) response.productId()))
                .body("curr", equalTo(response.curr()))
                .body("startDate", equalTo(response.startDate()))
                .body("endDate", equalTo(response.endDate()));
    }

    private static Stream<Arguments> priceApiTestCases() {
        return Stream.of(
                Arguments.arguments(
                        new RequestParameters(
                                35455L,
                                1L,
                                "2020-06-14T10:00:00"),
                        new ExpectedResponse(
                                35455L,
                                1L,
                                1L,
                                "2020-06-14T00:00:00",
                                "2020-12-31T23:59:59",
                                35.50,
                                "EUR")),
                Arguments.arguments(
                        new RequestParameters(
                                35455L,
                                1L,
                                "2020-06-14T16:00:00"),
                        new ExpectedResponse(
                                35455L,
                                1L,
                                2L,
                                "2020-06-14T15:00:00",
                                "2020-06-14T18:30:00",
                                25.45,
                                "EUR")),
                Arguments.arguments(
                        new RequestParameters(
                                35455L,
                                1L,
                                "2020-06-14T21:00:00"),
                        new ExpectedResponse(
                                35455L,
                                1L,
                                1L,
                                "2020-06-14T00:00:00",
                                "2020-12-31T23:59:59",
                                35.50,
                                "EUR")),
                Arguments.arguments(
                        new RequestParameters(
                                35455L,
                                1L,
                                "2020-06-15T10:00:00"),
                        new ExpectedResponse(
                                35455L,
                                1L,
                                3L,
                                "2020-06-15T00:00:00",
                                "2020-06-15T11:00:00",
                                30.50,
                                "EUR")),
                Arguments.arguments(
                        new RequestParameters(
                                35455L,
                                1L,
                                "2020-06-16T21:00:00"),
                        new ExpectedResponse(
                                35455L,
                                1L,
                                4L,
                                "2020-06-15T16:00:00",
                                "2020-12-31T23:59:59",
                                38.95,
                                "EUR")));
    }

    private record RequestParameters(
            long productId,
            long brandId,
            String datetime) {

        @Override
        public String toString() {
            return String.format("product %s of brand %s at %s", productId, brandId, datetime);
        }

    }

    private record ExpectedResponse(
            long productId,
            long brandId,
            long price_list,
            String startDate,
            String endDate,
            double price,
            String curr) {

    }
}