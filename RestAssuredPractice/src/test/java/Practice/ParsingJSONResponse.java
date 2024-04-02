package Practice;


import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class ParsingJSONResponse {
	
	//Approach 1
	//@Test
	
	public void testJSONResponse1() {
		
		given()
			.contentType(ContentType.JSON)
		
		.when()
			.get("https://reqres.in/api/users?page=2")
			
		.then()
			.statusCode(200)
			.header("Content-Type","application/json; charset=utf-8")
			.body("data[1].last_name", equalTo("Ferguson"));
			
	}

	// Approach 2
	
	@Test(priority=1)
	
	public void testJSONResponse2() {
		
		Response res=given()
			.contentType(ContentType.JSON)
		
		.when()
			.get("https://reqres.in/api/users?page=2");
		
		String lastName=res.jsonPath().get("data[1].last_name");
		System.out.println(lastName);
		Assert.assertEquals(lastName,"Ferguson");
		
			
	}
	
	@Test(priority=2)
	
	public void getLastNameFromJSONResponse() {
		
		Response res=given()
			
						.contentType(ContentType.JSON)
						.accept("application/json")
					.when()
						.get("https://reqres.in/api/users?page=2");
		
			JSONObject obj= new JSONObject(res.asString());
		
			boolean status= false;
			for(int i=0;  i< obj.getJSONArray("data").length(); i++)
			{
				
				 String lastName= obj.getJSONArray("data").getJSONObject(i).get("last_name").toString();
				 if(lastName.equals("Ferguson")) {
					 status = true;
				 	 break;
				 }
			}
	
		
		 Assert.assertEquals(status, true);
			
	}
	
	
}
