package twitterrestassured;

import org.testng.annotations.Test;
import org.apache.logging.log4j.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

public class TwitterAPIPrac {
	
	//These 4 parameters are required to authenticate by OAUTH.
	//given().auth().oauth method is used to authenticate using OAUTH. It accepts these 4 arguments.
	
	String consumerKey="ABZPn0GGW6JMXqajpP9FEFBFW";
	String consumerSecret="Adu7GIOSjzy5Kob25ManVdaBgoQdNLGADZm9E0dcw1GHJeiibm";
	String accessToken="854903630-thh0oMkh62MYZSE1NanT2iS9gZFlYxx6N5bLxdcN";
	String tokenSecret="KNUiQ2aSgPCTFNlHKaQhDF5P3LdskxBMDhuVFFh7UtuFO";
	
	private static Logger log=LogManager.getLogger(TwitterAPIPrac.class.getName());
	
	@Test
	public void getLatestTweet() {	
	
	RestAssured.baseURI="https://api.twitter.com/1.1/statuses";
	
	Response res=
	given().auth().oauth(consumerKey, consumerSecret, accessToken, tokenSecret).
			queryParam("count","2").
	when().
			get("/home_timeline.json").
	then().
			extract().response();
	
	String strRes=res.asString();
	//System.out.println(strRes);
	
	JsonPath jp=new JsonPath(strRes);
	
	int count=jp.get("text.size");
	//System.out.println(count);
	log.info("Number of tweets: "+count);
	
	List<String> names=new ArrayList<String>();	
	
	for(int i=0;i<count;i++) {		
		names.add((String) jp.get("text["+i+"]"));
		System.out.println("No. "+(i+1)+" tweet is: "+names.get(i));
	}
	
}
}
