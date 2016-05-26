package kapatel.dhruv.utils;

import java.sql.Connection;
import kapatel.dhruv.DBproperties;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Singleton pattern for database connection
 * 
 * @author dhruv
 *
 */

public class DbConnection {

	private static Connection con;
	
	private DbConnection() 	{}

	public static Connection getConnection()
	{
		if(con!=null)
		{
			return con;
		}
		else
		{
			return getConnection(DBproperties.URL,DBproperties.USERNAME,DBproperties.PASSWORD);
		}
	}
	
	private static Connection getConnection(String url,String username,String password)
	{
		try
		{
			Class.forName(DBproperties.DRIVER_CLASS);
			con = DriverManager.getConnection(url,username,password);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return con;
	}
	
	// check connection
	public static void main(String a[]) throws SQLException
	{
		DbConnection db = new DbConnection();
		
		System.out.println(db.getConnection());
		
	}
	
}
