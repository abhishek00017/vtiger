package com.daphnis.restassured.learning;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import com.learning.restassured.files.PayLoad;
import com.learning.restassured.files.ReusableMethod;


public class Create {

	public static void main(String[] args) {

		// create

		RestAssured.baseURI="https://rahulshettyacademy.com";

		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response=given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
				.body(PayLoad.addBody()).when().post("maps/api/place/add/json")
				.then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP"))
				.header("Server", "Apache/2.4.41 (Ubuntu)").extract().response().asString();

		System.out.println("Response Body: " + response);

		JsonPath js = ReusableMethod.rawToJson(response);
		String placeId=js.getString("place_id");
		System.out.println("Place ID: " + placeId);

		// update
		String address="70 Summer walk, USA";

		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+placeId+"\",\r\n"
				+ "\"address\":\""+address+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}")
		.when().put("maps/api/place/update/json")
		.then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));

		//get
		String getPlaceResponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId)
				.when().get("maps/api/place/get/json")
				.then().log().all().assertThat().statusCode(200).extract().response().asString();

		System.out.println("Get Place Response: " + getPlaceResponse);

		JsonPath jspath = ReusableMethod.rawToJson(getPlaceResponse);
		String actualAddress = jspath.getString("address");
		System.out.println("Actual Address: " + actualAddress);
		Assert.assertEquals(actualAddress, address);
	}


}
