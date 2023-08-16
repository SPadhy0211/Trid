package practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class NonSelectQueryTest {
    public static void main(String[] args) throws SQLException {
		Connection con = null;
		int result = 0;
		
		try
		{
			//register the database
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			
			//get connection for database
		    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sdet50", "root", "root");
		    
		    //create statement
		    Statement state = con.createStatement();
		    String query = "insert into Rmg values( 'H0132','Himaja','Manual','2023-03-15',9080765432);";
		    		
		    //update query
		    result = state.executeUpdate(query);
		}
		catch (Exception e) {

		}    		
		finally {
			if(result>0) {
				System.out.println("Data updated successfully");
			}
			else {
				System.err.println("Data not inserted");
			}
			con.close();	
		}
	}
}
