package org.bank.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.bank.dto.TransactionDetails;
import org.bank.util.JdbcConnections;

public class TransactionDAO {
 private static final String insert="insert into transaction_details(transaction_Amount,transaction_type,"
 		+ "transaction_Date,transaction_Time,Account_number,Balance_Amount,R_Account_Number) "
 		+ "values(?,?,?,?,?,?,?)";
 private static final String select_transactions =
		    "SELECT transaction_type,transaction_Amount,Balance_Amount,transaction_Date FROM transaction_details WHERE Account_number=?";
	public boolean insertTransactionDetails(TransactionDetails transactiondetails)
	{
	try {
		Connection connection=JdbcConnections.forMysqlConnection();
		PreparedStatement prepareStatement=connection.prepareStatement(insert);
		prepareStatement.setDouble(2,transactiondetails.getTransactionamount());
		prepareStatement.setString(1,transactiondetails.getTransactionType());
		prepareStatement.setDate(3,Date.valueOf(transactiondetails.getTransactiondate()));
		prepareStatement.setTime(4,Time.valueOf(transactiondetails.getTransactiontime()));
		prepareStatement.setInt(5,transactiondetails.getAccountNumber());
		prepareStatement.setDouble(6,transactiondetails.getBalanceAmount());
		prepareStatement.setInt(7,transactiondetails.getRaccountnumber());
		int result=prepareStatement.executeUpdate();
		if(result!=0)
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
	public static List<String> getTransactionsByAccount(int accountNumber) {
		List<String> transactions = new ArrayList<>();
			try {
				Connection connection = JdbcConnections.forMysqlConnection();
				PreparedStatement prepareStatement = connection.prepareStatement(select_transactions);
			    prepareStatement.setInt(1, accountNumber);
			    ResultSet resultset = prepareStatement.executeQuery();

			    while (resultset.next()) {
	 String record = resultset.getString("transaction_date") + " | " + resultset.getString("transaction_type").toUpperCase() + " | " +
			         resultset.getDouble("transaction_Amount") + " | Balance: " +resultset.getDouble("Balance_Amount");
			       transactions.add(record);
			    }
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
			return transactions;
	}
}
