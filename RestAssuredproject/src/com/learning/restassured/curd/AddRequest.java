package com.learning.restassured.curd;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;
 
import com.learning.restassured.files.ReusableMethod;

public class AddRequest {

	public static void main(String[] args) {
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response=given().log().all().queryParam("key", "qaclick123")
				.header("Content-Type","application/json")
		.body(PayLoadRequest.addRequestBody()).when().post("maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP"))
		.header("Server","Apache/2.4.41 (Ubuntu)").extract().response().asString();
		
		System.out.println("Response Body: " + response);
		
		JsonPath jsPath = ReusableMethod.rawToJson(response);
		String placeId = jsPath.getString("place_id");
		System.out.println("Place ID: " + placeId);
		
		//update
		
		String newAddress="70 Summer walk, USA";
		
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+placeId+"\",\r\n"
				+ "\"address\":\""+newAddress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}")
		.when().put("maps/api/place/update/json")
		.then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		//get
		
		String getPlaceResponse = given().log().all().queryParam("key", "qaclick123")
				.queryParam("place_id", placeId)
		.when().get("maps/api/place/get/json")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		System.out.println("Get Place Response: " + getPlaceResponse);
		
		JsonPath js = ReusableMethod.rawToJson(getPlaceResponse);
		String actualAddress = js.getString("address");
		System.out.println("Actual Address: " + actualAddress);
		Assert.assertEquals(actualAddress, newAddress);

	}

}
 class PayLoadRequest {
	
	 static String addRequestBody() {
		return "{\r\n"
				+ "  \"location\": {\r\n"
				+ "    \"lat\": -38.383494,\r\n"
				+ "    \"lng\": 33.427362\r\n"
				+ "  },\r\n"
				+ "  \"a  ccuracy\": 50,\r\n"
				+ "  \"name\": \"Frontline house\",\r\n"
				+ "  \"phone_number\": \"(+91) 983 893 3937\",\r\n"
				+ "  \"address\": \"29, side layout, cohen 09\",\r\n"
				+ "  \"types\": [\r\n"
				+ "    \"shoe park\",\r\n"
				+ "    \"shop\"\r\n"
				+ "  ],\r\n"
				+ "  \"website\": \"http://google.com\",\r\n"
				+ "  \"language\": \"French-IN\"\r\n"
				+ "}";
	}

}
