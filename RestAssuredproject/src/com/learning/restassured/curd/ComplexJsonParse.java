package com.learning.restassured.curd;

import com.learning.restassured.files.PayLoad;

import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {
	
	public static void main(String[] args) {
		
		JsonPath jsPath=new JsonPath(PayLoad.coursePrice());
		
		int count=jsPath.getInt("courses.size()");
		System.out.println("Count: " + count);
		
		int totalAmount=jsPath.getInt("dashboard.purchaseAmount");
		System.out.println("Total purchase amount: "+totalAmount);
		
		String titleFirstCourse=jsPath.getString("courses[0].title");
		System.out.println("Title first course: "+titleFirstCourse);
		

		String titleThirdCourse=jsPath.getString("courses[2].title");
		System.out.println("Title third course: "+titleThirdCourse);
		
		for(int i=0;i<count;i++) {
			String title=jsPath.get("courses["+i+"].title");
			int price=jsPath.get("courses["+i+"].price");
			System.out.println("Title: "+title);
			System.out.println("Price: "+price);
		}
		
		System.out.println("Print no of copy sold by RPA");
		for(int i=0; i<count;i++) {
			String title=jsPath.get("courses["+i+"].title");
			if(title.equalsIgnoreCase("RPA")) {
				int copies=jsPath.get("courses["+i+"].copies");
				System.out.println("Total copies: "+copies);
			}
		}
	} 

}
