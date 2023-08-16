package practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class SelectQueryTest {
        public static void main(String[] args) throws SQLException {
        	Connection con = null;
        	ResultSet result = null;
		try
		{
        	//register the database
        	Driver driver = new Driver();
        	DriverManager.registerDriver(driver);
			
        	//get connection for database
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sdet50","root","root");
        	
        	//create statement
        	Statement state= con.createStatement();
        	String query = "select * from Rmg;";
        	
        	//execute query
        	result = state.executeQuery(query);
        	
        	while(result.next())
        	{
        		System.out.println(result.getString(1)+" "+result.getString(2)+" "+result.getString(3)+" "+result.getString(4));
        	}
		}
        	catch(Exception e)
		{
        		
		}
		finally
		{
        	//close db connection
        	con.close();
		}
}
}