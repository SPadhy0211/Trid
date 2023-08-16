package practice;

import org.testng.annotations.Test;

import com.trid.GenericUtility.BaseClass;

public class Product extends BaseClass {
	 @Test(groups = "Smoke")
	    public void createProduct()
	    {
	    	System.out.println("product created");
	    }
    
	 @Test(groups = "Regression")
	 public void editProduct()
	 {
		 System.out.println("edited successfully");
	 }
	 
	 @Test(groups = {"Smoke","Regression"})
	 public void deleteProduct()
	 {
		 System.out.println("product deleted");
	 }
}
