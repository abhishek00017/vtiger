package com.daphnis.restassured.learning;

import com.learning.restassured.files.PayLoad;

import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {
		String s="abhhi";
		String sb=new String("abhhi");
		String sd="abhi";

		String sc="yadav";
		sc.concat(s);
		System.out.println(s==sd);
		System.out.println(s==sb);
		System.out.println(s==sc);
		/*
		 * JsonPath jsPath=new JsonPath(PayLoad.coursePrice());
		 * 
		 * int courseSize=jsPath.getInt("courses.size()");
		 * System.out.println("Course size: "+courseSize);
		 * 
		 * String website=jsPath.getString("dashboard.website");
		 * System.out.println("Website: "+website);
		 * 
		 * String title=jsPath.getString("courses[1].title");
		 * System.out.println("Title: "+title);
		 */
	}

}
