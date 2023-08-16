package com.trid.GenericUtility;

import java.sql.*;

import com.mysql.cj.jdbc.Driver;

public class DatabaseUtility {
	Connection con;
	public void connectToDB() throws SQLException
	{
		Driver driver = new Driver();
		DriverManager.registerDriver(driver);
		con =DriverManager.getConnection(IPathConstants.dbURL, IPathConstants.dbUserName, IPathConstants.dbPassword);
	}
	/**
	 * This method is used to execute and fetch data
	 * @author Sasmita 
	 * @param query
	 * @param colNum
	 * @param expData
	 * @return
	 * @throws SQLException
	 */
	
	public String executeQueryAndGetData(String query, int colNum,  String expData) throws SQLException
	{
		ResultSet result = con.createStatement().executeQuery(query);
		boolean flag = false;
		while(result.next())
		{
			String data = result.getString(colNum);
			if(data.equalsIgnoreCase(expData))
			{
				flag = true;
				break;
			}
		}
		if(flag==true)
		{
			System.out.println("data is varified");
			return expData;
		}
		else
		{
			System.out.println("data is not present");
			return"";
		}
	}
	/**
	 * This method is used to close database connection
	 * @throws SQLException
	 */
	public void closeDB() throws SQLException
	{
		con.close();
	}

}
