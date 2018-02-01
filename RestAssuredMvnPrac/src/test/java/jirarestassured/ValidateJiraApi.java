package jirarestassured;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import resources.Resources;

public class ValidateJiraApi {
	
	private static Logger log=LogManager.getLogger(ValidateJiraApi.class.getName());
	Properties prop=new Properties();
	String issueID;
	
	@BeforeTest
	public void getData() throws IOException {
		
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\data.properties");
		prop.load(fis);
		log.info("Property file is loaded");
	}
	//creating and returning a session is handled in resources.java
	@Test
	public void createIssue() {
		
		log.info("Start: Create Issue");
		RestAssured.baseURI=prop.getProperty("JIRAHOST");
		log.info("Host information: "+prop.getProperty("JIRAHOST"));
		
		Response res=
		given().
				header("Content-Type","application/json").
				header("Cookie","JSESSIONID="+Resources.getSessionId()).
				body("{\r\n" + 
						"\"fields\": {\r\n" + 
						"   \"project\":\r\n" + 
						"   { \r\n" + 
						"      \"key\": \"TES\"\r\n" + 
						"   },\r\n" + 
						"   \"summary\": \"REST assured EXAMPLE 1\",\r\n" + 
						"   \"description\": \"Creating my first issue via REST assured\",\r\n" + 
						"   \"issuetype\": {\r\n" + 
						"      \"name\": \"Bug\"\r\n" + 
						"   }\r\n" + 
						"  }\r\n" + 
						"}").
		when().
				post("/rest/api/2/issue/").
		then().
				assertThat().statusCode(201).
				extract().
				response();
		
		String strRes=res.asString();
		//System.out.println(strRes);
		log.info("Response string is: "+strRes);
		
		JsonPath jp=new JsonPath(strRes);
		issueID=jp.get("id");
		//System.out.println(issueID);
		log.info("Issue ID is: "+issueID);
		log.info("End Create Issue");
		
	}
	
	//adding a comment
	@Test(dependsOnMethods= {"createIssue"})
	public void addComment() {
		
		RestAssured.baseURI=prop.getProperty("JIRAHOST");
		
		Response res=
		given().
		header("Content-Type","application/json").
		header("Cookie","JSESSIONID="+Resources.getSessionId()).
		body("{\r\n" + 
				"    \"body\": \"I added this comment thru rest assured\",\r\n" + 
				"    \"visibility\": {\r\n" + 
				"        \"type\": \"role\",\r\n" + 
				"        \"value\": \"Administrators\"\r\n" + 
				"    }\r\n" + 
				"}").
		when().
				post("/rest/api/2/issue/"+issueID+"/comment").
		then().
				assertThat().statusCode(201).
				extract().response();
		
		String strRes=res.asString();
		
		JsonPath jp=new JsonPath(strRes);
		String self=jp.get("self");
		System.out.println("Self is: "+self);
	}

}
