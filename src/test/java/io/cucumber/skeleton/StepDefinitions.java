package io.cucumber.skeleton;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.matcher.RestAssuredMatchers.*;
import org.apache.commons.lang3.StringUtils;
import java.util.List;
import java.util.Map;

public class StepDefinitions {

    private Response response;
    private ValidatableResponse json;
    private RequestSpecification request;

    private String ENDPOINT_WEATHERBIT = "https://api.weatherbit.io/v2.0/current";
    private String APIKEY = "ea657a6ff545408b939b74b679269f5f";

    public void setCommons() {
        request = given()
                .contentType("application/json")
                .log().all();
    }

    @Given("I have correct latitude {float} and longitude {float}")
    public void i_have_correct_latitude_and_longitude(Float lat, Float lon) {
        setCommons();
        request
                .queryParam("key", APIKEY)
                .queryParam("lat", lat.toString())
                .queryParam("lon", lon.toString());
    }

    @Given("I have correct latitude {float} and longitude {float} but no api key")
    public void I_have_correct_coordinated_but_no_apikey(Float lat, Float lon){
        setCommons();
        request
                .queryParam("lat", lat.toString())
                .queryParam("lon", lon.toString());
    }

    @Given("I have missed latitude and longitude")
    public void I_have_missed_latitude_and_longitude(){
        setCommons();
        request
                .queryParam("key", APIKEY);
    }

    @When("I request Weatherbit weather api for current weather")
    public void i_request_current_weather(){
        response = request.when().get(ENDPOINT_WEATHERBIT);
        System.out.println("response: " + response.prettyPrint());
    }

    @Then("the status code is {int}")
    public void verify_status_code(int statusCode){
        json = response.then().statusCode(statusCode);
    }

    @And("response includes the count$")
    public void response_includes_integer(Map<String,Integer> responseFields){
        for (Map.Entry<String, Integer> field : responseFields.entrySet()) {
            json.body(field.getKey(), equalTo(field.getValue()));
        }
    }

    @And("response includes the timezone$")
    public void response_includes_string(Map<String,String> responseFields){
        for (Map.Entry<String, String> field : responseFields.entrySet()) {
            json.body(field.getKey(), equalTo(field.getValue()));
        }
    }

    @And("response includes the coordinates$")
    public void response_includes_many(Map<String,Float> responseFields){
        for (Map.Entry<String, Float> field : responseFields.entrySet()) {
            json.body(field.getKey(), equalTo(field.getValue()));
        }
    }

    @And("response includes other mandatory parameters$")
    public void response_includes_other_parameters(List<String> responseFields){
        for (String field : responseFields) {
            json.body(field, notNullValue());
        }
    }
}