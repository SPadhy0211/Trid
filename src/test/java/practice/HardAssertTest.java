package practice;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.Test;

public class HardAssertTest {
      @Test
      public void hardAssert()
      {
    	  System.out.println("test-1");
    	  System.out.println("test-2");
    	  assertEquals("a", "a", "***test failed***");
    	  System.out.println("test-3");
    	  System.out.println("test-4");
    	  Assert.assertNotEquals("a", "a", "---test failed---");
      }
      
      @Test
      public void assertion()
      {
    	  System.out.println("test-5");
    	  System.out.println("test-6");
    	  int a = 100;
    	  Assert.assertNotNull(a);
    	  System.out.println("test-7");
    	  Assert.assertNull(a); 
    	  System.out.println("test-8");
    	  
      }
}
