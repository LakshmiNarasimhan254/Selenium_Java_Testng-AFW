package com.mln.testng;

import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class FirstTest {
	
	WebDriver driver;
	@Test
	public void f() {
		 String baseUrl = "https://www.toolsqa.com/";
		 System.out.println("Launching Google Chrome browser"); 
		 WebDriverManager.chromedriver().setup(); 
	     driver = new ChromeDriver();
	     driver.get(baseUrl);
	     String testTitle = "Just a moment...";
	     String originalTitle = driver.getTitle();
	     Assert.assertEquals(originalTitle, testTitle);
	 


	}
	@BeforeMethod
	public void beforeMethod() {
		System.out.println("Starting Test On Chrome Browser");
	}

	@AfterMethod
	public void afterMethod() {
		driver.close();
		System.out.println("Finished Test On Chrome Browser");
	}

}
