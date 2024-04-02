package Practice;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;


public class ParsingXMLResponse {

	
	//@Test(priority=1)
	public void testXMLResponse() {
		
		
		 given()
		
		 .when()
		 	.get("http://restapi.adequateshop.com/api/Traveler?page=1")
		
		 .then()
		 	.statusCode(200)
		 	.header("Content-type","application/xml; charset=utf-8")
		    .body("TravelerinformationResponse.page",equalTo("1"))
		    .body("TravelerinformationResponse.travelers.Travelerinformation[0].name", equalTo("Developer"));
		    
		    
		
	}
	
	//@Test
	public void testXMLResponseUsingResponseObj() {
		
		
		Response res= given()
		
					  .when()
							.get("http://restapi.adequateshop.com/api/Traveler?page=1");
		
		 
		 Assert.assertEquals(res.statusCode(), 200);
		 Assert.assertEquals(res.header("Content-Type"),"application/xml; charset=utf-8");
		 String pageNo=res.xmlPath().get("TravelerinformationResponse.page").toString();
		 Assert.assertEquals(pageNo, "1");
		 String name=res.xmlPath().get("TravelerinformationResponse.travelers.Travelerinformation[0].name").toString();
		 Assert.assertEquals(name, "Developer");
		    
		
	}
	
	@Test
	

	public void parsingXMLResponseUsingXMLPath() {
		
		
		Response res= given()
		
					  .when()
							.get("http://restapi.adequateshop.com/api/Traveler?page=1");
		
		XmlPath obj= new XmlPath(res.asString());
		
		//Storing all traveler information in a list
		List<String> traveler	=obj.getList("TravelerinformationResponse.travelers.Travelerinformation");
		//Finding total number of travelers
		System.out.println("No. of travelers: "+traveler.size());
		
		//Store all traveler names in a list
		List<String> traveler_names	=obj.getList("TravelerinformationResponse.travelers.Travelerinformation.name");
		
		
		Boolean status=false;
		//Searching for a particular name in a List
		for(String traveler_Name: traveler_names) {
			
			if(traveler_Name.equals("vano")) {
				
				status=true;
				break;
			}
			
			
		}
		Assert.assertEquals(status, true);
		
		
		
		 
		 
		
	}
	
}
