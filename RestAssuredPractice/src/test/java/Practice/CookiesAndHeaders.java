package Practice;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class CookiesAndHeaders {
	
	@Test(priority=1)
	
	public void testCookies() {
		
			Response res=given()
			
						.when()
							.get("https://www.google.com/");
			
		String	cookie_val= res.getCookie("AEC");
		System.out.println("Cookie value is: "+cookie_val);
							
		
		
	}
@Test(priority=2)
	
	public void getCookies() {
		
			Response res=given()
			
						.when()
							.get("https://www.google.com/");
			
		Map<String, String>	data=res.getCookies();
		
		
		System.out.println("Cookies keys are: "+data.keySet());
		
		for(String k:data.keySet()) {
			
			System.out.println(k+": "+res.getCookie(k));
		}
							
		
		
	}
	
}
