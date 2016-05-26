package kapatel.dhruv.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import kapatel.dhruv.model.Admin;
import kapatel.dhruv.utils.DbConnection;
import kapatel.dhruv.utils.SecurityUtility;

/**
 * Admin database operations
 * 
 * @author dhruv
 *
 */

public class AdminDao {

	Connection connect;
	public AdminDao()
	{
		connect = DbConnection.getConnection();
	}
	
		
	/**
	 * This method verifies admin credentials.
	 * 
	 * @param username
	 * @param password
	 * @return int 0 if no entry found or -1 if wrong username and password combination or id if correct credentials  
	 */
	
	public int authenticateAdmin(String username,String password)
	{
		int id = checkAdmin(username);
		
		Admin admin = new Admin();
		boolean passwordValidation = false;;
		
		if(id!=0)
		{
			String q="SELECT id,password,email,salt,status FROM admin WHERE id=? LIMIT 1";
			PreparedStatement preparedStatement;
			try 
			{
				preparedStatement = connect.prepareStatement(q);
				preparedStatement.setInt(1,id);
				ResultSet adminResultset = preparedStatement.executeQuery();
				adminResultset.first();
				
				admin.setId(adminResultset.getInt("id"));
				admin.setStatus(adminResultset.getString("status"));
				admin.setEmail(adminResultset.getString("email"));
				admin.setPassword(adminResultset.getString("password"));
				admin.setSalt(adminResultset.getString("salt"));
				
			} 
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			
			passwordValidation = SecurityUtility.validatePassword(password, admin.getSalt(),admin.getPassword());
			
			if(passwordValidation)
			{
				return id;
			}
			else
			{
				// wrong username password combination
				return -1;
			}
			
		}
		else
		{
			// not registered
			return 0;
		}
	}
	
	/**
	 * 
	 * @param username
	 * @return int 0 if no entry found or id if entry found in database 
	 */
	
	
	public int checkAdmin(String username)
	{
		
		PreparedStatement preparedStatement;
		ResultSet adminResultset;
		
		String q="SELECT id FROM admin WHERE email=? LIMIT 0,1";
		
		int id=0;
		
		try
		{
			preparedStatement = connect.prepareStatement(q);
			preparedStatement.setString(1,username);
			adminResultset = preparedStatement.executeQuery();
			 
			 if(adminResultset.isBeforeFirst())
			 {
				 adminResultset.first();
				 id = adminResultset.getInt("id");
			 }
			 	 
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return id;
		
	}
	
}
