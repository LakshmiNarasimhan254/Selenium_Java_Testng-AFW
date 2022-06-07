package testNG;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class DP {
	/* This is a separate class , where data provider methods can be created 
	 * 
	 */
	
	 @DataProvider (name = "data-provider")
	  public static Object[][] dpMethod(){
		 return new Object[][] {{"Am a Inteherited DP"},{"Am also a Inteherited DP"}};
	  }
}
