package org.pages;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.utils.ApiActions;
import org.utils.PropertiesManager;

public class GetBookAPI {

    public static final String BASE_URL = PropertiesManager.getProperty("simpleBookURL");

    ApiActions apiActions;

    public GetBookAPI(ApiActions apiActions) {
        this.apiActions = apiActions;
    }

    @Step("Get book information with ID: {bookID}")
    public Response getBook(String bookID, int expectedStatusCode) {
        String requestUrl = BASE_URL +"books/"+ bookID;
        return apiActions.performRequest("GET", requestUrl, expectedStatusCode, null, null, null, null, null);

    }


}
