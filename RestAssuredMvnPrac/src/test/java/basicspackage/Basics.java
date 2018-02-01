package basicspackage;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import resources.PayLoad;
import resources.Resources;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Basics {
	
	public String plcId;
	Properties prop=new Properties();
	
	@BeforeTest
	public void getData() throws IOException {
		
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\data.properties");
		prop.load(fis);
	}
	@Test
	public void getPlace() {
		
		// TODO Auto-generated method stub
		
		//base url or host
		RestAssured.baseURI=prop.getProperty("HOST");
		
		given().
				param("location","32.819031, -97.056512").
				param("radius","500").
				param("key",prop.getProperty("KEY")).
		when().
				get(Resources.placeGetData()).
		then().
				assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
				body("results[0].geometry.location.lat",equalTo("32.821457")).and().
				body("results[0].name",equalTo("SUBWAY® Restaurants")).and().
				body("results[0].place_id",equalTo("ChIJxbew-eKAToYRfYLrMNKEu1E")).and().
				header("Server","scaffolding on HTTPServer2");
	}
	
	@Test
	public void postPlace() {
		
		String b=PayLoad.getPostBody();
		
		//task 1-grab the response
		RestAssured.baseURI=prop.getProperty("HOST");
		
		Response res=
		
		given().
				queryParam("key",prop.get("KEY")).
				body(b).
		when().
				post(Resources.placePostData()).
		then().
				assertThat().statusCode(200).
		extract().
				response();
		
		//the extracted response is in raw format. From raw format, it needs to be converted into string format, and then in json format
		
		String responseString=res.asString();
		System.out.println("Response string is: "+responseString);
		
		//task 2- grab place_id from the response
		
		//jsonpath is the class which consumes the json format response.
		JsonPath jp=new JsonPath(responseString);
		plcId=jp.get("place_id");
		System.out.println("Place ID is: "+plcId);
		
	}
	
	@Test(dependsOnMethods= {"postPlace"})
	public void deletePlace() {
		//task-3 - create a delete request using place_id
		String delBody="{\r\n" + 
				"  \"place_id\": \""+plcId+"\"\r\n" + 
				"}";
		
		System.out.println("Delete body is: "+delBody);
		Response resDel=
		given().
				queryParam("key",prop.getProperty("KEY")).
				body(delBody).
		when().
				post(Resources.placeDeleteData()).
		then().
				assertThat().statusCode(200).
		extract().
				response();
		
		String strResDel=resDel.asString();
		System.out.println("Delete response is: " +strResDel);
		
	}

}
