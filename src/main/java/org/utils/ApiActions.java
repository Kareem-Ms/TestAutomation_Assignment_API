package org.utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;

import static org.testng.Assert.fail;

public class ApiActions {

    private Response response;

    public Response performRequest(String RequestType, String requestUrl, int expectedStatusCode, ContentType contentType, HashMap<String, String> headers, HashMap<String, String> queryParams, HashMap<String, String> FormParams, HashMap<String, String> body) {

        System.out.println("\n"+"API Request");
        System.out.println("**********************************************" + "\n");
        RequestSpecification request = RestAssured.given().log().all();
        try {
            //forming the request with the given parameters
            if (contentType != null) {
                request.contentType(contentType);
            }
            if (headers != null) {
                request.headers(headers);
            }
            if (queryParams != null) {
                request.queryParams(queryParams);
            }
            if (FormParams != null) {
                for (HashMap.Entry<String, String> entry : FormParams.entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    request.multiPart(key, value);
                }
            }
            if (body != null) {
                request.body(body);
            }

            //check request type
            if (RequestType.equalsIgnoreCase("POST")) {
                response = request.when().post(requestUrl);
            }
            if (RequestType.equalsIgnoreCase("GET")) {
                response = request.get(requestUrl);
            }
            if (RequestType.equalsIgnoreCase("PUT")) {
                response = request.when().put(requestUrl);
            }
            if (RequestType.equalsIgnoreCase("DELETE")) {
                response = request.when().delete(requestUrl);
            }
            if (RequestType.equalsIgnoreCase("PATCH")) {
                response = request.when().patch(requestUrl);
            }

            System.out.println("\n"+"API Response");
            System.out.println("**********************************************" + "\n");
            response.then().log().all().assertThat().statusCode(expectedStatusCode);

        } catch (Exception e) {
            fail(e.getMessage());
        }

        return response;
    }

    public static String getResponseJsonValue(Response response, String jsonPath) {
        return response.jsonPath().getString(jsonPath);
    }

}