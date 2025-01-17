package org.tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.pages.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.utils.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.Matchers.hasKey;
import static org.utils.ApiActions.getResponseJsonValue;

@Epic("Regression tests")
@Feature("Simple books tests")
public class SimpleBookTests {

    JsonFileManager jsonFileManager;
    private GetBookAPI getBookAPI;
    private RegisterClientAPI registerClientAPI;
    private OrdersManagement ordersManagement;
    String currentTime = new SimpleDateFormat("ddMMyyyyHHmmssSSS").format(new Date());
    String accessToken;
    String orderID;

    @Test(description = "verify getting book info")
    public void verifyGetingBookInfo(){

        Response response = getBookAPI.getBook(jsonFileManager.getTestData("BookId"),200);

        Assert.assertEquals(getResponseJsonValue(response,"name"),jsonFileManager.getTestData("BookName"));
    }

    @Test(description = "verify registering new client successfully")
    public void registerClientSuccessfully(){

        String email= jsonFileManager.getTestData("email")+currentTime+jsonFileManager.getTestData("domain") ;
        Response response = registerClientAPI.registerClient(jsonFileManager.getTestData("order.clientName"),email,201);

        response.then().assertThat().body(".",hasKey("accessToken"));
        accessToken = getResponseJsonValue(response,"accessToken");
    }

    @Test(dependsOnMethods = "registerClientSuccessfully", description = "verify placing an order successfully")
    public void placeAnOrder(){
        Response response = ordersManagement.addOrder(jsonFileManager.getTestData("order.clientName")
                                                    ,jsonFileManager.getTestData("order.bookID"),accessToken,201);

        Assert.assertEquals(getResponseJsonValue(response,"created"),jsonFileManager.getTestData("order.expectedCreatedValue"));
        orderID = getResponseJsonValue(response,"orderId");
    }

    @Test(dependsOnMethods = "placeAnOrder", description = "verify updating existing order successfully")
    public void updateAnOrder(){
       ordersManagement.updateOrder(jsonFileManager.getTestData("order.updatedClientName"),orderID,accessToken,204);
    }

    @Test(dependsOnMethods = "updateAnOrder", description = "verify deleting specific order")
    public void deleteOrder(){
        ordersManagement.deleteOrder(orderID,accessToken,204);
    }

    @BeforeMethod
    public void setUp(){
        jsonFileManager = new JsonFileManager("src\\test\\java\\org\\testData\\SimpleBookTestData.json");
        ApiActions apiObject = new ApiActions();
        getBookAPI = new GetBookAPI(apiObject);
        registerClientAPI = new RegisterClientAPI(apiObject);
        ordersManagement = new OrdersManagement(apiObject);
    }

}
