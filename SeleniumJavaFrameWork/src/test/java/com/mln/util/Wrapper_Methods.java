package com.mln.util;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.mln.reports.Excel_Reports;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Wrapper_Methods {

	public WebElement element = null; 
	public String strResult;
	String sAbsPath = 	System.getProperty("user.dir")+"/";
	public String sFinalPath;
	public String sTestName;
	public int i = 1;
	int iRun =1;
	DateFormat dfDate = new SimpleDateFormat("dd-MM-yyyy");
	Date sDate = new Date();
	String dDate = dfDate.format(sDate);
	WebDriver adriver;
	Excel_Reports xlrpt;

	public Wrapper_Methods(WebDriver driver, String sTest){

		try {
			sFinalPath = sAbsPath + "screenshots\\" + sTest + "\\" + dDate + "\\";
			File file = new File(sFinalPath);
			File[] fFile = file.listFiles();
			if (!file.exists()) {
				file.mkdir();
				iRun = 1;
			}
			else { 
				iRun = fFile.length + 1;
			}
			sFinalPath = sAbsPath + "screenshots/" + sTest + "/" + dDate + "/Run" + iRun + "/snap" ;

			sTestName = sTest;
			adriver = driver;
			xlrpt = new Excel_Reports(sTest);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	}

	public WebDriver setBrowser(String strBrowser){
		
		if (strBrowser.equalsIgnoreCase("chrome")){
			WebDriverManager.chromedriver().setup();
			adriver = new ChromeDriver();
		}else if (strBrowser.equalsIgnoreCase("firefox")){
			WebDriverManager.firefoxdriver().setup();
			adriver = new FirefoxDriver();
		}else if(strBrowser.equalsIgnoreCase("edge")){
			WebDriverManager.edgedriver().setup();
			adriver = new EdgeDriver();
		}		
		return adriver;
		}



	public void CloseApp(WebDriver driver) {
		driver.quit();
	}
	public void LaunchApp(WebDriver driver, String url){
		// TODO Auto-generated method stub
		try {
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.get(url);
			strResult ="The URL: "+url+ " is loaded succesfully ";

			xlrpt.reportEvent(strResult, "PASS",sFinalPath);
		} catch (WebDriverException e) {
			// TODO Auto-generated catch block
			strResult ="The browser has not been launched";
			xlrpt.reportEvent(strResult, "FAIL",sFinalPath);
		}
		catch(Exception e1){
			strResult ="An exception occured";
			xlrpt.reportEvent(strResult, "FAIL",sFinalPath);
		}
		finally{
			takeScreen(adriver,sFinalPath);
			//xlrpt.reportSnapshot(sFinalPath);
		}
		
	}
	public void setValue(WebElement element, String val) {
		try {
			element.clear();
			element.sendKeys(val);
			strResult = "Value " + val + "entered successfully" ;
			xlrpt.reportEvent(strResult, "PASS",sFinalPath);
		} catch (NoSuchElementException e) {
			strResult ="The element is not found or not visible";
			xlrpt.reportEvent(strResult, "FAIL",sFinalPath);
		}catch (WebDriverException e1){
			strResult ="The browser is not available.";
			xlrpt.reportEvent(strResult, "FAIL",sFinalPath);		
		} catch(Exception e2){
			strResult ="An exception occured";
			xlrpt.reportEvent(strResult, "FAIL",sFinalPath);			
		}finally {
			takeScreen(adriver,sFinalPath);
			//xlrpt.reportSnapshot(sFinalPath);

		}

	}
	public void clickLnkBtn(WebElement element){
		try{
			element.click();
			strResult = "Button clicked successfully";
			xlrpt.reportEvent(strResult, "PASS",sFinalPath);
		}catch (NoSuchElementException e) {
			strResult ="The element is not found or not visible";
			xlrpt.reportEvent(strResult, "FAIL",sFinalPath);
		} catch (WebDriverException e1){
			strResult ="The browser is not available.";
			xlrpt.reportEvent(strResult, "FAIL",sFinalPath);		
		} catch(Exception e2){
			strResult ="An exception occured" + e2.getCause();
			xlrpt.reportEvent(strResult, "FAIL",sFinalPath);			
		} finally {
			takeScreen(adriver,sFinalPath);
			//xlrpt.reportSnapshot(sFinalPath);

		}
	}
	public void verifyText(WebElement element, String sVal) throws IOException{
		try {
			String sValue = element.getText();
			System.out.println(sValue);
			if(sVal == sValue){
				strResult = "The Actual Text value " + sVal +" is displayed";
				xlrpt.reportEvent(strResult, "PASS",sFinalPath);
			}
			else
			{
				strResult = "The Actual Text value " + sVal +" is not displayed";
				xlrpt.reportEvent(strResult, "FAIL",sFinalPath);
			}	
		}catch (NoSuchElementException e) {
			strResult ="The element is not found or not visible.";
			xlrpt.reportEvent(strResult, "FAIL",sFinalPath);
		} catch (WebDriverException e1){
			strResult ="The browser is not available.";
			xlrpt.reportEvent(strResult, "FAIL",sFinalPath);			
		} catch(Exception e2){
			strResult ="An exception occured.";
			xlrpt.reportEvent(strResult, "FAIL",sFinalPath);				
		} finally {

			takeScreen(adriver,sFinalPath);
			//xlrpt.reportSnapshot(sFinalPath);

		}
	}

	public void takeScreen(WebDriver driver,String sPath1) {
		try {

			File src = ((TakesScreenshot)adriver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(src, new File( sPath1 + i +".png"));
		} catch (IOException e) {
			strResult ="The Snapshot could not be captured";
		} catch (Exception e1){
			strResult ="An exception occured";
		} finally{
			i++;
		}
	}
}












