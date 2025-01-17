package org.pages;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.utils.ApiActions;
import org.utils.PropertiesManager;

import java.util.HashMap;

public class RegisterClientAPI {
    public static final String BASE_URL = PropertiesManager.getProperty("simpleBookURL");

    ApiActions apiActions;

    public RegisterClientAPI(ApiActions apiActions) {
        this.apiActions = apiActions;
    }

    @Step("Register a client with Name: {clientName} and Email: {clientEmail}")
    public Response registerClient(String clientName,String clientEmail, int expectedStatusCode) {
        String requestUrl = BASE_URL +"api-clients/";
        HashMap<String,String> body = new HashMap<>();
        body.put("clientName", clientName);
        body.put("clientEmail", clientEmail);
        return apiActions.performRequest("POST", requestUrl, expectedStatusCode, ContentType.JSON, null, null, null, body);
    }
}
