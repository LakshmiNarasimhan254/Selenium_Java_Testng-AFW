package com.mln.testng;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.mln.config.Properties_File;
import com.mln.pages.Login_MyStore;
import com.mln.pages.MyStore;
import com.mln.util.Excel_Methods;
import com.mln.util.Wrapper_Methods;

public class BrowserTest_TestNG {
	WebDriver driver = null;
	static String strTestName = "TC-1";
	String ProjectPath = System.getProperty("user.dir");
	Wrapper_Methods wm = new Wrapper_Methods(driver, strTestName);
	Excel_Methods em = new Excel_Methods(strTestName);
	String strBrowser = em.getCellValueString(1, 0);
	String stxtboxvalue = em.getCellValueString(1, 2);	

	@BeforeTest
	public void testSetup() {
		String strUrl = Properties_File.getProperties("URL");	

		driver = wm.setBrowser(strBrowser);
		wm.LaunchApp(driver,strUrl);
	}
	@Test
	public void TC1(){
		MyStore mystore = new MyStore(driver, strTestName);		
		mystore.ClickSignin(wm);		
		Login_MyStore loginmystore = new Login_MyStore(driver, strTestName);
		loginmystore.EnterTxtCreateEmail(wm, stxtboxvalue);
		loginmystore.ClickCreateAccount(wm);
		
	
	}
	@AfterTest
	public void tearDown(){		
		wm.CloseApp(driver);;
	}



}
