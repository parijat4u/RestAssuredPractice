package Practice;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import org.testng.annotations.Test;

public class HttpRequest {
	
int id;
	
	@Test(priority=1)
	public void getUsers() {
		
		given()
		
		.when()
		
			.get("https://reqres.in/api/users?page=2")
		
		.then()
			.statusCode(200)
			.log()
			.all();
	}
	
	@Test(priority=2)
	public void createUser() {
		
		HashMap data=new HashMap();
		data.put("name", "Payel");
		data.put("job", "Test Lead");
		
		id= given()
		
				.contentType("application/json")
				
				.body(data)
		
		    .when()
			
				.post("https://reqres.in/api/users")
				.jsonPath().getInt("id");
		
	
	}
	
	@Test(priority=3, dependsOnMethods= {"createUser"})
	public void updateUser() {
		HashMap data=new HashMap();
		data.put("name", "Payel");
		data.put("job", "Test Automation Engineer");
		
		 given()
		
			.contentType("application/json")
			.body(data)
		
		.when()
			
		    .put("https://reqres.in/api/users/"+id)
		    
		.then()
		     .statusCode(200)
		     .log().all();
		    
		
	}
	
	@Test(priority=4)
	public void deleteUser() {
		
		given()
		
		.when()
			.delete("https://reqres.in/api/users/"+id)
		
		.then()
			.statusCode(204)
			.log().all();
	}

}
