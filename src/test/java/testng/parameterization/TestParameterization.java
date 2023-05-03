package testng.parameterization;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestParameterization {
	
	
	@Test(dataProvider="getData")
	public void doLogin(String username, String password) {
		
		System.out.println(username+"---"+password);
		
	}

	
	@DataProvider
	public Object[][] getData() {
		
		
		Object[][] data = new Object[3][2];
		
		data[0][0] = "Shivaji Bajpai";
		data[0][1] = "Atmecs";
		
		data[1][0] = "Sofware Engineer";
		data[1][1] = "Hyderabad";
		
		data[2][0] = "Lanco Hills";
		data[2][1] = "Hyderabad Telangana";
		
		return data;
		
	}
	
	
	
}
