package com.mln.testngListeners;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.mln.config.Properties_File;
import com.mln.pages.Login_MyStore;
import com.mln.pages.MyStore;
import com.mln.util.Excel_Methods;
import com.mln.util.Wrapper_Methods;

public class TestNg_ListenerTC3 {
	WebDriver driver = null;
	static String strTestName = "TC-1";
	String ProjectPath = System.getProperty("user.dir");
	Wrapper_Methods wm = new Wrapper_Methods(driver, strTestName);
	Excel_Methods em = new Excel_Methods(strTestName);
	String strBrowser = em.getCellValueString(1, 0);
	String stxtboxvalue = em.getCellValueString(1, 2);	

	@BeforeTest(groups ={"functest","checkintest"})
	public void testSetup() {
		String strUrl = Properties_File.getProperties("URL");
		driver = wm.setBrowser(strBrowser);
		wm.LaunchApp(driver,strUrl);
	}
	


	@Test(priority = 3,groups = {"functest","checkintest"})
	public void TC1(){
		MyStore mystore = new MyStore(driver, strTestName);		
		mystore.ClickSignin(wm);		
		Login_MyStore loginmystore = new Login_MyStore(driver, strTestName);		
		loginmystore.EnterTxtCreateEmail(wm, stxtboxvalue);
		loginmystore.ClickCreateAccount(wm);
	}
	
	@Test(priority = 2 ,groups = {"functest"})
	public void TC2(){
		MyStore mystore = new MyStore(driver, strTestName);		
		mystore.ClickSignin(wm);		
		Login_MyStore loginmystore = new Login_MyStore(driver, strTestName);		
		loginmystore.EnterTxtCreateEmail(wm, stxtboxvalue);
		loginmystore.ClickCreateAccount(wm);
		
		
	}
	
	@Test(priority = 1,groups = {"checkintest"})
	public void TC3(){
		MyStore mystore = new MyStore(driver, strTestName);		
		mystore.ClickSignin(wm);		
		Login_MyStore loginmystore = new Login_MyStore(driver, strTestName);		
		loginmystore.EnterTxtCreateEmail(wm, stxtboxvalue);
		loginmystore.ClickCreateAccount(wm);
		
	}
	@AfterTest(groups ={"functest","checkintest"})
	public void tearDown(){		
		wm.CloseApp(driver);;
	}



}


