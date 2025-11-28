package org.bank.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.bank.dto.CustomerDetails;
import org.bank.util.JdbcConnections;
public class CustomerDAO {
private static final String insert_customer_details="insert into customer_details( customer_name,"
		+ "customer_emailid, customer_mobileNo, customer_panNo, customer_aadharNo, "
		+ "customer_gender, customer_dateOfBirth,customer_address, customer_status,  "
		+ "customer_Amount) "
		+ "values(?,?,?,?,?,?,?,?,?,?)"; 
private static final String select_all_customers="select * from customer_details";
private static final String select_by_using_status="select * from customer_details where customer_status=?";
private static final String update_customer_details="update customer_details set customer_Account_number=?,customer_PIN=?,customer_status=?,customer_IFSCcode=? where customer_emailid=?";
private static final String customer_details="select * from customer_details where customer_emailid=? or customer_mobileNo=? ";
private static final String customer_pin="select * from customer_details  where (customer_emailid=? or customer_mobileNo=?)and customer_PIN=?";
private static final String customer_details_by_using_account_number="select * from customer_details  where customer_Account_number=?";
private static final String customer_amount="update customer_details set customer_Amount=? where customer_Account_number=?";
private static final String customer_by_account_and_pin =
"select * from customer_details where customer_Account_number=? and customer_PIN=?";
private static final String update_pin =
"UPDATE customer_details SET customer_PIN=? WHERE customer_emailid=? customer_Account_number=? AND customer_PIN=?";
public boolean insertCustomerDetails(CustomerDetails customerDetails)
{
	try {
	Connection connection=JdbcConnections.forMysqlConnection();
	PreparedStatement prepareStatement=connection.prepareStatement(insert_customer_details);
	prepareStatement.setString(1,customerDetails.getCustomername());
	prepareStatement.setString(2,customerDetails.getEmailid());
	prepareStatement.setLong(3,customerDetails.getMobileNo());
	prepareStatement.setString(4,customerDetails.getPanNo());
	prepareStatement.setLong(5,customerDetails.getCustomerAadharNo());
	prepareStatement.setString(6,customerDetails.getCustomerGender());
	prepareStatement.setDate(7,customerDetails.getCdataOfBirth());
	prepareStatement.setString(8,customerDetails.getcAddress());
	prepareStatement.setString(9,"pending");
	prepareStatement.setDouble(10,customerDetails.getcAmount());
	int result=prepareStatement.executeUpdate();
	if(result!=0)
	{
		return true;
	}
	else
	{
		return false;
	}
	} catch (ClassNotFoundException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;//because it will given exception in method because that declared boolean applicable to try block so that in catch we will return boolean value
	}
}
public static  List<CustomerDetails> getAllCustomerDetails()
{
	try {
		
		Connection connection=JdbcConnections.forMysqlConnection();
		PreparedStatement prepareStatement=connection.prepareStatement(select_all_customers);//we use prepare statement even without place holders because we need to do pre compliation
		ResultSet result=prepareStatement.executeQuery();
		List<CustomerDetails> listofCustomers=new ArrayList<CustomerDetails>();/*we use collections to store the objects because after storing one object in 
		customer details reference for second one also we same reference so that object gets vanished without vanishing the objects we need to store those objects so that we use collections*/
		if(result.isBeforeFirst())
		{
			while(result.next())
			{
				CustomerDetails customerdetails=new CustomerDetails();//we create inside while loop because for every customer we need to create object so that we declared in while
				customerdetails.setCustomerAadharNo(result.getLong("customer_aadharNo"));
				customerdetails.setPanNo(result.getString("customer_panNo"));
				customerdetails.setMobileNo(result.getLong("customer_mobileNo"));
				customerdetails.setEmailid(result.getString("customer_emailid"));
				listofCustomers.add(customerdetails);
			}
			return listofCustomers;//we written outside the while because after adding we should return the object
		}
		else
			return null;
	} 
	catch (ClassNotFoundException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}
}
public static  List<CustomerDetails> selectCustomerDetailsByUsingStatus()
{
	try {
		Connection connection=JdbcConnections.forMysqlConnection();
		PreparedStatement prepareStatement=connection.prepareStatement(select_by_using_status);
		prepareStatement.setString(1,"pending");
		ResultSet result=prepareStatement.executeQuery();
		List<CustomerDetails> listofCustomers=new ArrayList<CustomerDetails>();
		if(result.isBeforeFirst())
		{
			while(result.next())
			{
				CustomerDetails customerdetails=new CustomerDetails();//we create inside while loop because for every customer we need to create object so that we declared in while
				customerdetails.setCustomerAadharNo(result.getLong("customer_aadharNo"));
				customerdetails.setPanNo(result.getString("customer_panNo"));
				customerdetails.setMobileNo(result.getLong("customer_mobileNo"));
				customerdetails.setEmailid(result.getString("customer_emailid"));
				customerdetails.setCustomername(result.getString("customer_name"));
				customerdetails.setCustomerGender(result.getString("customer_gender"));
				customerdetails.setCdataOfBirth(result.getDate("customer_dateOfBirth"));
				customerdetails.setcAddress(result.getString("customer_address"));
				customerdetails.setcStatus(result.getString("customer_status"));
				customerdetails.setcAmount(result.getDouble("customer_Amount"));
				listofCustomers.add(customerdetails);
			}
			return listofCustomers;
		}
		else
			return null;
	} catch (ClassNotFoundException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}
}
public static  boolean updateAccountNumberandPinandIfscCodeandStatus(CustomerDetails customerDetails)
{
	/* update customer_details set customer_Account_number=?,customer_PIN=?,customer_status=?,customer_IFSCcode=? where customer_emailid*/
	try {
		Connection connection=JdbcConnections.forMysqlConnection();
		PreparedStatement preparedStatement=connection.prepareStatement(update_customer_details);
		preparedStatement.setLong(1, customerDetails.getcAccountNumber());
		preparedStatement.setInt(2, customerDetails.getcPinNo());
		preparedStatement.setString(3,customerDetails.getcStatus());
		preparedStatement.setString(4, customerDetails.getcIFSCCode());
		preparedStatement.setString(5,customerDetails.getEmailid());
		int result=preparedStatement.executeUpdate();
		if(result!=0)
		{
			return true;
		}
		else
		{
			return false;
		}
		
	} catch (ClassNotFoundException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	}
}
public static   String selectByUsingEmailidOrMobileNumber(String emailid)
{
	Connection connection;
	try {
		connection = JdbcConnections.forMysqlConnection();
		PreparedStatement preparedStatement=connection.prepareStatement(customer_details);
		preparedStatement.setString(1,emailid);
		preparedStatement.setString(2, emailid);
		ResultSet resultset=preparedStatement.executeQuery();
		if(resultset.next())
		{
			String status=resultset.getString("customer_status");
		   return status;
		}
		else
			return null;
	} catch (ClassNotFoundException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}	
}
public static  boolean selectCustomerDetailsByusingpin(String emailid,int pin)
{
	Connection connection;
	try {
		connection = JdbcConnections.forMysqlConnection();
		PreparedStatement preparedStatement=connection.prepareStatement(customer_pin);
		preparedStatement.setString(1,emailid);
		preparedStatement.setString(2, emailid);
		preparedStatement.setInt(3,pin);
		ResultSet resultset=preparedStatement.executeQuery();
		if(resultset.next())
		{
			return true;
		}
		else
			return false;
	} catch (ClassNotFoundException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	}	
}
public static  CustomerDetails selectCustomerDetailsByUsingAccountNumber(int accountNumber,int pin)
{
	try {
		Connection connection=JdbcConnections.forMysqlConnection();
		PreparedStatement prepareStatement=connection.prepareStatement(customer_details_by_using_account_number);
		prepareStatement.setInt(1, accountNumber);
		ResultSet resultset=prepareStatement.executeQuery();
		if(resultset.next())
		{
			CustomerDetails customerDetails=new CustomerDetails();
			customerDetails.setcAmount(resultset.getDouble("customer_Amount"));
			return customerDetails;
		}
		else
			return null;
	} catch (ClassNotFoundException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}
}
public static boolean updateBalanceAmountByUsingAccountNumber(double balance,int accountNumber)
{
		try {
			Connection connection = JdbcConnections.forMysqlConnection();
			PreparedStatement prepareStatement=connection.prepareStatement(customer_amount);
			prepareStatement.setDouble(1,balance);
			prepareStatement.setInt(2, accountNumber);
			int result=prepareStatement.executeUpdate();
			if(result!=0)
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
public static CustomerDetails selectCustomerDetailsByAccountAndPin(int accountNumber, int pin) {
		try {
			 Connection connection = JdbcConnections.forMysqlConnection();
			PreparedStatement prepareStatement = connection.prepareStatement(customer_by_account_and_pin);
	        prepareStatement.setInt(1, accountNumber);
	        prepareStatement.setInt(2, pin);
	        ResultSet resultset = prepareStatement.executeQuery();

	        if(resultset.next()) {
	            CustomerDetails customerDetails = new CustomerDetails();
	            customerDetails.setcAmount(resultset.getDouble("customer_Amount"));
	            customerDetails.setcAccountNumber(resultset.getLong("customer_Account_number"));
	            customerDetails.setcPinNo(resultset.getInt("customer_PIN"));
	            return customerDetails;
	        } else {
	            return null;
	        }
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
        
    }
public static boolean updatePin(String email,int accountNumber, int oldPin, int newPin) {
    try {
        Connection connection = JdbcConnections.forMysqlConnection();
        PreparedStatement prepareStatement = connection.prepareStatement(update_pin);
        prepareStatement.setInt(1, newPin);
        prepareStatement.setString(2,email);
        prepareStatement.setInt(3, accountNumber);
        prepareStatement.setInt(4, oldPin);

        int result = prepareStatement.executeUpdate();
       if( result > 0)
    	   return true;// if updated
       else
    	   return false;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}




}


