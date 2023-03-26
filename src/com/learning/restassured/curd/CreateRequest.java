package com.learning.restassured.curd;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import com.learning.restassured.files.PayLoad;
import com.learning.restassured.files.ReusableMethod;

public class CreateRequest {
	
	public static void main(String[] args) {
		
		//addPlace
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response=given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json").body(PayLoad.addBody())
		.when().post("maps/api/place/add/json").
		then().log().all().assertThat().statusCode(200)
		.body("scope", equalTo("APP")).header("Server","Apache/2.4.41 (Ubuntu)")
		.extract().response().asString();
		
		System.out.println("Response Body: " + response);
		
		JsonPath path = ReusableMethod.rawToJson(response);
		String placeId=path.getString("place_id");
		System.out.println("Place ID: " + placeId);
		
		//updatePlace
		
		String newAddress="70 Summer walk, USA";
		
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+placeId+"\",\r\n"
				+ "\"address\":\""+newAddress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}")
		.when().put("maps/api/place/update/json").
		then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		//get
		
		String getPlaceResponse=given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId)
		.when().get("maps/api/place/get/json")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		System.out.println("Get Place Response: " + getPlaceResponse);
		
		JsonPath jsPath = ReusableMethod.rawToJson(getPlaceResponse);
		String actualAddress = jsPath.getString("address");
		System.out.println("Actual Address: " + actualAddress);
		Assert.assertEquals(actualAddress, newAddress);
	}
	
	

}
