package apiClass;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.io.FileUtils;
import org.testng.Assert;
import org.testng.annotations.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resources.AllureResultsCleaner;
import java.io.IOException;

/**
 * @author Subrat
 */

public class GetProducts
{
    public int StatusCode;
    public RequestSpecification httpRequest;
    public Response response;
    @Before
    public void cleanAllureResults() throws IOException {
        AllureResultsCleaner.cleanAllureResults();
    }
    @Given("User enter endpoint url for GET products api")
    public void user_enter_endpoint_url_for_get_products_api() {
        RestAssured.baseURI = "https://fakestoreapi.com/";
    }
    @When("User make request for GET products api")
    public void user_make_request_for_get_products_api() {
        httpRequest = RestAssured.given();
        response = httpRequest.get("products");
    }
    @Then("User receive the response code as {int} Ok")
    public void user_receive_the_response_code_as_ok(Integer int1) {
        StatusCode = response.getStatusCode();
        Assert.assertEquals(StatusCode, 200);
    }
}
