package org.bank.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.bank.util.JdbcConnections;
public class AdminDAO {
	private static final String admin_details="select * from admin_details where Admin_emailid=? and Admin_password=?";	
public boolean selectAdminDetailsByUsingEmailidAndPassword(String emailid,String password)
{
	try {
	Connection connection=JdbcConnections.forMysqlConnection();
	PreparedStatement prepareStatement=connection.prepareStatement(admin_details);
	prepareStatement.setString(1,emailid);
	prepareStatement.setString(2,password);
	ResultSet resultset=prepareStatement.executeQuery();
	if(resultset.next())
		return true;
	else
		return false;
	} 
	catch (ClassNotFoundException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	} 
	}
}

