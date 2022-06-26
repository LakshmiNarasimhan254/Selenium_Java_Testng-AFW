package com.mln.pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.mln.util.Wrapper_Methods;

public class Login_MyStore {
	
	WebDriver driver;
	String strTestName;
	By txtbxCreateEmail = By.xpath(" //input[@id='email_create']");
	By btnCreateAccount = By.xpath("//a[contains(text(),'Sign in')]");

	public Login_MyStore(WebDriver driver , String strTestName){
		this.driver =driver;
		this.strTestName = strTestName;
	}
	
	public void EnterTxtCreateEmail(Wrapper_Methods wmobj,String stxtboxvalue)
	{		
		wmobj.setValue(driver.findElement(txtbxCreateEmail), stxtboxvalue);
	}
	public void ClickCreateAccount(Wrapper_Methods wmobj)
	{
		wmobj.clickLnkBtn(driver.findElement(btnCreateAccount));
	}


}
