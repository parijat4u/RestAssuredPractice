package Practice;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class UploadFile {
	
	@Test
	public void uploadFile() {
		
		File myfile = new File("//Users//payel//eclipse-workspace//RestAssuredPractice//src/test//resources//Test1.txt");
		
		
		
		Response res= given()
		
			.multiPart("file",myfile)
			.contentType("multipart/form-data")
		
		.when()
			.post("http://postman-echo.com/post");
		
			String filename=res.jsonPath().get("files").toString();
			System.out.println(filename.contains("Test1.txt"));
		
			
	}

}
