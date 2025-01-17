package org.pages;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.utils.ApiActions;
import org.utils.PropertiesManager;

import java.util.HashMap;

public class OrdersManagement {
    public static final String BASE_URL = PropertiesManager.getProperty("simpleBookURL");

    ApiActions apiActions;

    public OrdersManagement(ApiActions apiActions) {
        this.apiActions = apiActions;
    }

    @Step("Place an order with client name: {clientName} and book ID: {bookID}")
    public Response addOrder(String clientName, String bookID,String bearerToken, int expectedStatusCode) {
        String requestUrl = BASE_URL +"orders";
        HashMap<String,String> body = new HashMap<>();
        body.put("bookId", bookID);
        body.put("customerName", clientName);

        HashMap<String,String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + bearerToken);
        return apiActions.performRequest("POST", requestUrl, expectedStatusCode, ContentType.JSON, headers, null, null, body);
    }

    @Step("Update an order with client name: {clientName} and Order ID: {orderID}")
    public Response updateOrder(String clientName, String orderID, String bearerToken, int expectedStatusCode) {
        String requestUrl = BASE_URL +"orders/"+orderID;
        HashMap<String,String> body = new HashMap<>();
        body.put("customerName", clientName);

        HashMap<String,String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + bearerToken);
        return apiActions.performRequest("PATCH", requestUrl, expectedStatusCode, ContentType.JSON, headers, null, null, body);
    }

    @Step("Delete an order Order ID: {orderID}")
    public Response deleteOrder(String orderID, String bearerToken, int expectedStatusCode) {
        String requestUrl = BASE_URL +"orders/"+orderID;

        HashMap<String,String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + bearerToken);
        return apiActions.performRequest("DELETE", requestUrl, expectedStatusCode, ContentType.JSON, headers, null, null, null);
    }

}
