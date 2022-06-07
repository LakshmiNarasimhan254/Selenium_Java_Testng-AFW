package testNG;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

public class DataProviderTest {
  @Test(dataProvider = "data-provider1")
  public void f1(Integer n, String s) {
	  System.out.println(n+s);
  }
  @Test(dataProvider = "data-provider3")
  public void f2(String s1,String s2) {
	  System.out.println(s1 +":"+ s2);
  } 
  
  
  //This is inheritated DataProvider.i:e The Data Provider are in separate class
  @Test(dataProvider = "data-provider", dataProviderClass = DP.class)
  public void f3(String s1) {
	  System.out.println(s1);
  }
  
  @Test(dataProvider = "data-provider4")
  public void f4(String s1,String s2,String s3,Integer i1, String s4,Double d1) {
	  System.out.println(s1+":"+s2);
	  System.out.println(s3+":"+i1);
	  System.out.println(s4+":"+d1);
  }
  
  
  
  //Data Provider with 1 Integer and 1 String - 2 Sets of Data
  @DataProvider (name = "data-provider1")
  public Object[][] dpMethod1(){
	 return new Object[][] {{1,"A"}, {2,"B"},{3,"C"}};
  }
  
  //Data Provider with 1 Integer and 1 String - 1 Set of Data
  @DataProvider (name = "data-provider2")
  public Object[][] dpMethod2(){
	 return new Object[][] {{3,"C"}};
  }
  
  //Data Provider with 2 Strings - More than 2  Set of Data
  @DataProvider (name = "data-provider3")
  public Object[][] dpMethod3(){
		 return new Object[][] {{"Name","Srinath"}, {"Name","Archana"},{"Name","Srinath"}, {"Age","25"}};
  }
  
  @DataProvider (name = "data-provider4")
  public Object[][] dpMethod4(){
		 return new Object[][] {{"Name","Srinath","Age",45,"Height",7.5}};
  }
}