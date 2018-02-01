package basicspackage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import resources.Resources;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class AllElementsOfResponse {
	
	Properties prop=new Properties();
	
	@BeforeTest
	public void getData() throws IOException {
		
		//C:\Users\ksuba\Documents\Work\RestAssuredMvnPrac\src\main\resources\data.properties
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\data.properties");
		prop.load(fis);
	}
	
	@Test
	public void getAllElements() {
		
		RestAssured.baseURI=prop.getProperty("HOST");
		
		Response res=
		given().
				param("location","32.819031, -97.056512").
				param("radius",500).
				param("keyword","restaurants").
				param("key",prop.getProperty("KEY")).log().all().
		when().
				get(Resources.placeGetData()).
		then().
				assertThat().statusCode(200).
				header("Server","scaffolding on HTTPServer2").log().body().
				extract().response();
		
		String strRes=res.asString();
	//	System.out.println("Response string is: "+strRes);
		
		JsonPath jp=new JsonPath(strRes);
		
		int count=jp.get("results.size()");
		System.out.println(count);
		
		String name;
		
		for(int i=0;i<count;i++) {
			
			name=jp.get("results["+i+"].name");
			System.out.println(name);
		}
	}
}
