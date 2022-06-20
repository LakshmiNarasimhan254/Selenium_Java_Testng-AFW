package com.mln.testng;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;

public class SingletonTest {
	
	WebDriver driver;
	
  @Test
  public void f() {
	 driver=SingleDriver.getInstance().driverinit();
	  driver.close();
  }
  @BeforeMethod
  public void beforeMethod() {
	  driver =SingleDriver.getInstance().driverinit();
	  
  }

  @AfterMethod
  public void afterMethod() {
  }

}
