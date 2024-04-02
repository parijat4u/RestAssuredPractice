package Practice;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

public class DifferentWaysToCreatePostRequest {
	
	//1. Using HashMap passing Request body or payload

	//@Test(priority=1)
	public void usingHashMap() {
		
		

		HashMap data = new HashMap();
		data.put("firstName","Iraav");
		data.put("lastName","Malkhade");
		data.put("subjectId", "1");
	
	
		
		given()
		  .contentType("application/json")
		  .body(data)
		
		.when()
			.post("http://localhost:3000/users")
		
		.then()
			.statusCode(201)
			.body("firstName", equalTo("Iraav"))
			.body("lastName", equalTo("Malkhade"))
			.body("subjectId", equalTo("1"))
			.log().all();
		

	}
	
	//2. Using Org.JSON passing Request body or payload

		//@Test(priority=1)
		public void usingJSONObject() {
			
			

			JSONObject data=new JSONObject();
			data.put("firstName","Iraav");
			data.put("lastName","Malkhade");
			data.put("subjectId", "1");
		
		
			
			given()
			  .contentType("application/json")
			  .body(data.toString())
			
			.when()
				.post("http://localhost:3000/users")
			
			.then()
				.statusCode(201)
				.body("firstName", equalTo("Iraav"))
				.body("lastName", equalTo("Malkhade"))
				.body("subjectId", equalTo("1"))
				.log().all();
			

		}
	

	
	//3. Using POJO class passing Request body or payload

	//@Test(priority=1)
	public void usingPOJOUser() {
		
		pojoUser data=new pojoUser();
		data.setFirstName("Iraav");
		data.setLastName("Malkhade");
		data.setSubjectId("2");

		given()
		  .contentType("application/json")
		  .body(data)
		
		.when()
			.post("http://localhost:3000/users")
		
		.then()
			.statusCode(201)
			.body("firstName", equalTo("Iraav"))
			.body("lastName", equalTo("Malkhade"))
			.body("subjectId", equalTo("2"))
			.log().all();
		

	}
	
	//4. Using External JSON file passing Request body or payload

		@Test(priority=1)
		public void usingExternalJSONFile() throws FileNotFoundException {
			
			File file=new File(".//body.json");
			
			FileReader fr=new FileReader(file);
			
			JSONTokener jt= new JSONTokener(fr);
			
			JSONObject data= new JSONObject(jt);
			
			

			given()
			  .contentType("application/json")
			  .body(data.toString())
			
			.when()
				.post("http://localhost:3000/users")
			
			.then()
				.statusCode(201)
				.body("firstName", equalTo("Iraav"))
				.body("lastName", equalTo("Malkhade"))
				.body("subjectId", equalTo("2"))
				.log().all();
			

		}
	
	//Deleting the user
	@Test(priority=2)
	public void deleteUser() {
		
		given()
		
		
		.when()
			.delete("http://localhost:3000/users/4")
		
		
		.then()
			.statusCode(200);
	}

}
