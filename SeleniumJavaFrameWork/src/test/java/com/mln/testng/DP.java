package com.mln.testng;


import org.testng.annotations.DataProvider;

import com.mln.util.Excel_Methods;


public class DP {
	/* This is a separate class , where data provider methods can be created 
	 * 
	 */
	
	 @DataProvider (name = "data-provider")
	  public static Object[][] dpMethod(){
		 Excel_Methods em = new Excel_Methods("TC-1");		 
		 return  em.getexceldata();
	  }
}
