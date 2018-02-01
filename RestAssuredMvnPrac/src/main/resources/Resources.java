package resources;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Resources {
	
	public static String placePostData() {
		String res="/maps/api/place/add/json";
		return res;
	}
	
	public static String placeGetData() {
		
		String res="/maps/api/place/nearbysearch/json";
		return res;
	}
	
	public static String placeDeleteData() {
		
		String res="/maps/api/place/delete/json";
		return res;
	}
	
	public static String getSessionId() {
		
		RestAssured.baseURI="http://localhost:8080";
		
		Response res=
		given().
				header("Content-Type","application/json").
				body("{ \"username\": \"ksubash83\", \"password\": \"Dallas1234\" }").
		when().
				post("/rest/auth/1/session").
		then().
				assertThat().statusCode(200).
				extract().
				response();
		
		String strRes=res.asString();
		//System.out.println(strRes);
		
		JsonPath jp=new JsonPath(strRes);
		String sessionId=jp.get("session.value");
		System.out.println("Session ID from reusable: "+sessionId);
		
		return sessionId;
		
	}

}
