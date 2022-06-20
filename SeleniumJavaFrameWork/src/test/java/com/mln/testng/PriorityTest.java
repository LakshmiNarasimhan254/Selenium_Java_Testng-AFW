package com.mln.testng;

import org.testng.annotations.Test;

public class PriorityTest {
  @Test
  public void f() {
	  System.out.println("am from Function f");
  }
  @Test(priority = 1)
  public void E() {
	  System.out.println("am from Function E");
  }
  @Test
  public void e() {
	  System.out.println("am from Function e");
  }
  @Test
  public void d() {
	  System.out.println("am from Function d");
  }
  @Test(priority = -1)
  public void c() {
	  System.out.println("am from Function c");
  }
  @Test
  public void b() {
	  System.out.println("am from Function b");
  }
  @Test(priority = 1)
  public void a() {
	  System.out.println("am from Function a");
  }
  
}
//Priority can have - Integers ,Zero and + Integers
//Test without Priority are Zero Priority by default
//Test with same priority executes in the alphabetical order of the method name.
//Uppercase letter take Priority to lower case letters in Alphabetical order
	