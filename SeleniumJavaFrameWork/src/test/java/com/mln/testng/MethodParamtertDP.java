package com.mln.testng;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class MethodParamtertDP {
	
	/*
	 * Dataproviders also accept a method as a parameter,
	 * and then we can just check the method name 
	 * and provide the parameters according to it.
	 */
	
	@DataProvider (name = "data-provider")
	public Object[][] dpMethod (Method m){
		switch (m.getName()) {
		case "Sum": 
			return new Object[][] {{2, 3 , 5}, {5, 7, 12}};
		case "Diff": 
			return new Object[][] {{2, 3, 1}, {5, 7, 1}};
		}
		return null;
		
	}
	
	@Test (dataProvider = "data-provider")
	 public void Sum (int a, int b, int result) {
	      int sum = a + b;
	      Assert.assertEquals(result, sum);
	 }
	  
	@Test (dataProvider = "data-provider")
	public void Diff (int a, int b, int result) {
	      int diff = a - b;
	      Assert.assertEquals(result, diff);
	 }

}
