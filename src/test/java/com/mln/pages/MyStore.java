package com.mln.pages;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.mln.util.Wrapper_Methods;

public class MyStore {
	WebDriver driver;
	String strTestName;
	By btnSignin = By.xpath("//a[contains(text(),'Sign in')]");

	public MyStore(WebDriver driver , String strTestName){
		this.driver =driver;
		this.strTestName = strTestName;
	}
	
	public void ClickSignin(Wrapper_Methods wmobj)
	{
		
		wmobj.clickLnkBtn(driver.findElement(btnSignin));
	}
}