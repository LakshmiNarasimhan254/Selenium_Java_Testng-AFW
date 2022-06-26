package com.mln.testngListeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNg_Listeners implements ITestListener{

	public void onTestStart(ITestResult result) {
		System.out.println("****Test started**** " + result.getName());
		
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("****Test success****  " + result.getName());
	}

	public void onTestFailure(ITestResult result) {
		System.out.println("****Test failure****  " + result.getName());
		
	}

	public void onTestSkipped(ITestResult result) {
		System.out.println("****Test Skipped****  " + result.getName());
		
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		System.out.println("****Test completed**** " + context.getPassedTests());
		
		
	}

}
