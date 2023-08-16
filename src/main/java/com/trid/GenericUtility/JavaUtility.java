package com.trid.GenericUtility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class JavaUtility {
	/**
	 * This method is used to get random number
	 * @author Sasmita
	 * @return
	 */
	public int getRandomNo()
	{
		Random rn = new Random();
		int ran = rn.nextInt(1000);
		return ran;
	}
	/**
	 * this method is used to get system date
	 * @author Sasmita
	 * @return
	 */
	public String getSystemData()
	{
		Date d = new Date();
		String date = d.toString();
		return date;
	}
	/**
	 * This method is used to get system date in format
	 * @author Sasmita
	 * @return
	 */
	public String getSystemDateInFormat()
	{
		SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy_hh-mm-ss");
		Date date = new Date();
		String systemDateFormat = dateformat.format(date);
		return systemDateFormat;
		
	}

}
