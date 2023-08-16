package com.trid.GenericUtility;

import java.io.FileInputStream;
import java.util.Properties;

public class FileUtility {
     public String readDataFromPropertyFile(String key) throws Exception
     {
    	 FileInputStream fis = new FileInputStream(IPathConstants.FilePath);
    	 Properties pObj = new Properties();
    	 pObj.load(fis);
    	 String value = pObj.getProperty(key);
    	 return value;
     }
}
