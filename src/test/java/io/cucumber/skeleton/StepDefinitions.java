package io.cucumber.skeleton;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
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
    private String INVALID_APIKEY = "ea657a6ff545408b939b74b679269f5";

    @Given("I have set response type to json")
    public void i_have_set_response_type_to_json() {
        request = given()
                .contentType("application/json").log().all();
    }

    @Given("I have APIKEY")
    public void i_have_apikey() {
        request.queryParam("key", APIKEY);
    }

    @Given("I have INVALID_APIKEY")
    public void i_have_invalid_apikey() {
        request.queryParam("key", INVALID_APIKEY);
    }

    @And("I have latitude {word}")
    public void i_have_latitude(String lat) {
        request.queryParam("lat", lat);
    }

    @And("I have longitude {word}")
    public void i_have_longitude(String lon) {
        request.queryParam("lon", lon);
    }

    @And("I have country code {word}")
    public void i_have_country(String country) {
        request.queryParam("country", country);
    }

    @And("I have post code {word}")
    public void i_have_postcode(String postcode) {
        request.queryParam("postal_code", postcode);
    }

    @When("I request Weatherbit weather api for current weather")
    public void i_request_current_weather(){
        response = request.when().get(ENDPOINT_WEATHERBIT);
    }

    @Then("the status code is {int}")
    public void verify_status_code(int statusCode){
        json = response.then().statusCode(statusCode).log().all();
    }

    @And("response includes correct headers$")
    public void response_includes_correct_headers(Map<String,String> requiredHeaders){
        String checkedHeaderValue = "";
        for (Map.Entry<String, String> field : requiredHeaders.entrySet()) {
            assertThat(response.getHeader(field.getKey()), equalTo(field.getValue()));
        }
    }

    @And("response includes the following integer values$")
    public void response_includes_integer(Map<String,Integer> responseFields){
        for (Map.Entry<String, Integer> field : responseFields.entrySet()) {
            json.body(field.getKey(), equalTo(field.getValue()));
        }
    }

    @And("response includes the following string values$")
    public void response_includes_string(Map<String,String> responseFields){
        for (Map.Entry<String, String> field : responseFields.entrySet()) {
            json.body(field.getKey(), equalTo(field.getValue()));
        }
    }

    @And("response includes the following float values$")
    public void response_includes_float(Map<String,Float> responseFields){
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

    @And("response includes error {string}")
    public void response_includes_error(String msg){
        json.body("error", containsString(msg));
    }

}