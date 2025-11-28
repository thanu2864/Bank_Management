package org.bank.service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

import org.bank.dao.CustomerDAO;
import org.bank.dao.TransactionDAO;
import org.bank.dto.CustomerDetails;
import org.bank.dto.TransactionDetails;
import org.bank.exception.CustomerInvalidDataException;
 public class CustomerService {
	 Scanner sc=new Scanner(System.in);//declared as global to access in another method 
	CustomerDAO customerDao=new CustomerDAO();
	TransactionService transactionService=new TransactionService();
public void customerRegistration()
{
	List<CustomerDetails>allCustomerdetails= CustomerDAO.getAllCustomerDetails();
	CustomerDetails customerdetails=new CustomerDetails();
	System.out.println("enter the customer name");
	String cname=sc.next();
	 boolean onlyLetters = true;
	    for (int i = 0; i < cname.length(); i++) 
	    {
	    	char ch = cname.charAt(i);
	        if (!(ch == ' ' || (ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z')))
	        {
	        	onlyLetters = false;
	            break;
	        }
	    }
	    if (onlyLetters && cname.length() >= 2)
	    {
	    	customerdetails.setCustomername(cname);
	    } 
	    else 
	    {
	    	throw new CustomerInvalidDataException("Invalid Name");
	    }
	System.out.println("enter customer emailid");
	while(true)
	{
		try
		{
	String cemail=sc.next();
	long emailcount=allCustomerdetails.stream().filter((customer)->customer.getEmailid().equals(cemail)).count();
	if(!(cemail.endsWith("@gmail.com")))
	{
		throw new CustomerInvalidDataException("invalid email id");
	}
	if(emailcount>0)
	{
		throw new CustomerInvalidDataException("Already email exists");
	}
	else
       customerdetails.setEmailid(cemail);	
	break;
	}
	catch(CustomerInvalidDataException e)
	{
		System.out.println(e.getExceptionmsg());
		System.out.println("re enter  valid emailid ");
	}
	}
	
	System.out.println("enter customer mobile number");
	while(true)
	{
		try
		{
	long cmobileNo=sc.nextLong();
	long cmobilecount=allCustomerdetails.stream().filter((customer)->customer.getMobileNo()==cmobileNo).count();
	if(!(cmobileNo>=6000000000l&& cmobileNo<=9999999999l))
	{
				throw new CustomerInvalidDataException("invalid mobile number");

	}
	if(cmobilecount>0)
	{
		throw new CustomerInvalidDataException("Already mobileNo exists");
	}
	else
		customerdetails.setMobileNo(cmobileNo);
	break;
		}
		catch(CustomerInvalidDataException e)
		{
			System.out.println(e.getExceptionmsg());
			System.out.println("re-enter valid mobile number");
		}
	}
	System.out.println("enter customer aadhar number");
	while(true)
	{
	try {
	long caadharNo=sc.nextLong();
	long aadharcount=allCustomerdetails.stream().filter((customer)->customer.getCustomerAadharNo()==caadharNo).count();
	if(!(caadharNo>=100000000000l&& caadharNo<=999999999999l))
	{
		throw new CustomerInvalidDataException("invalid aadharno");
	}
	if(aadharcount>0)
		throw new CustomerInvalidDataException("Already aadharNo exists");
	else
		customerdetails.setCustomerAadharNo(caadharNo);
	   break;
	}
	catch(CustomerInvalidDataException e)
	{
		System.out.println(e.getExceptionmsg());
		System.out.println("re-enter aadharNo");
	}
	}
	System.out.println("enter customer pan number");
	while(true)
	{
		try {
	String cpanNo=sc.next();
	long pancount=allCustomerdetails.stream().filter((customer)->customer.getPanNo().equals(cpanNo)).count();
	if ((!cpanNo.matches("[A-Z]{5}[0-9]{4}[A-Z]{1}")))
	{
		throw new CustomerInvalidDataException("Invalid PAN");
    } 
	else
    {
    	if(pancount>0)
    	{
    		throw new CustomerInvalidDataException("ALready PAN Number Existed");
    	}
    	else
    	{
    		customerdetails.setPanNo(cpanNo);
    		break;
    	}
    }
}
catch(CustomerInvalidDataException e)
{
	System.out.println(e.getExceptionmsg());
	System.out.println("RE-enter valid PAN Number");
}
}
	System.out.println("enter customer gender");
	String cGender=sc.next();
	if(cGender.equalsIgnoreCase("male")||cGender.equalsIgnoreCase("female")||cGender.equalsIgnoreCase("others"))
	{
		customerdetails.setCustomerGender(cGender);
	}
	else
		throw new CustomerInvalidDataException("invalid gender");
	System.out.println("enter customer Date Of Birth");
	String  cdob=sc.next();
	System.out.println("enter customer Address");
	String cAddress=sc.next();
	System.out.println("enter customer Amount");
	double camount=sc.nextDouble();
	if(camount>0)
	{
		customerdetails.setcAmount(camount);
	}
	else
	{
		throw new CustomerInvalidDataException("insufficient amount");
	}
	customerdetails.setCustomername(cname);
	customerdetails.setCdataOfBirth(Date.valueOf(cdob));
	customerdetails.setcAddress(cAddress);
	if(customerDao.insertCustomerDetails(customerdetails))
	{
		System.out.println("Registration Successfull");
	}
	else
	{
		System.out.println("Server error 500");
	}	
}
public List<CustomerDetails> getAllPendingDetails()
{
	return CustomerDAO.selectCustomerDetailsByUsingStatus();
}
public void updateCustomerDetails(CustomerDetails customerDetails)
{
	if(CustomerDAO.updateAccountNumberandPinandIfscCodeandStatus(customerDetails))
	{
		System.out.println("updated successfully");
	}
	else
		System.out.println("server issues");
}
public void customerLogin()
{
	System.out.println("enter customer emailid or mobileNumber");
	String email=sc.next();
	String status=CustomerDAO.selectByUsingEmailidOrMobileNumber(email);
	try//when email id is not present it will give null when null compared to active or pending it will exception handled by try and catch (null pointer exception)
	{
	if(status.equals("Active"))
	{
		System.out.println("enter customer pin number");
		int pin=sc.nextInt();
		if(CustomerDAO.selectCustomerDetailsByusingpin(email,pin))
		{
			System.out.println("Login successfull......");
		    customerOperations(pin,email);
		}
		else
			System.out.println("invalid pin number");
	}
	else
	{
		System.out.println("Account still under process");
	}
	}
	catch(NullPointerException e)
	{
		System.out.println("invalid credentials");
	}
}
public  void customerOperations(int pin, String email)
{
	boolean continuing=true;
	while(continuing)
	{
	System.out.println("enter \n1.for debit \n2.for credit \n3.for check balance \n4.for check statement \n5.for mobile transaction \n6.for change pin \n7.for closing account");
	switch (sc.nextInt()) {
	case 1:
		System.out.println("debit");
		debitOperations(pin);
		break;
	case 2:
		System.out.println("credit");
		creditOperations(pin);
		break;
	case 3:
		System.out.println("check balance");
		break;
	case 4:
		System.out.println("check statement");
		checkStatement(pin);
		break;
	case 5:
		System.out.println("mobile transaction");
		break;
	case 6:
		System.out.println("change pin");
		changePin(email);
		break;
	case 7:
		System.out.println("closing account");
		break;
	default:
		System.out.println("invalid request");
		break;
	}
	System.out.println("do you want to continue \n1.enter 1 for yes \n2.enter 2 for no");
	int count=sc.nextInt();
	if(count==2)
	{
		continuing=false;
		System.out.println("thank you......");
	}
	}	
}
public void debitOperations(int pin)
{
	System.out.println("enter customer account number");
	int accountNumber=sc.nextInt();
	CustomerDetails customerDetails=CustomerDAO.selectCustomerDetailsByAccountAndPin(accountNumber, pin);
	if(customerDetails!=null)
	{
		System.out.println("enter the amount");
		double uamount=sc.nextDouble();
		double databaseAmount=customerDetails.getcAmount();
		if(uamount<=databaseAmount)
		{
			double balance=databaseAmount-uamount;
			System.out.println("balance is:"+balance);
			//update balance amount
		boolean update=	CustomerDAO.updateBalanceAmountByUsingAccountNumber(balance, accountNumber);
		if(update)
		{
		System.out.println("debited successfully");
		TransactionDetails transactiondetails=new TransactionDetails();
		transactiondetails.setTransactionType("Debit");
		transactiondetails.setTransactionamount(uamount);
		transactiondetails.setTransactiondate(LocalDate.now());
		transactiondetails.setTransactiontime(LocalTime.now());
		transactiondetails.setAccountNumber(accountNumber);
		transactiondetails.setBalanceAmount(balance);
		transactiondetails.setRaccountnumber(accountNumber);
		transactionService.addtransactionDetails(transactiondetails);
		}
		else
			System.err.println("underProcess......");
		}
		else
		System.err.println("insufficient balance");
	}
	else
		System.out.println("invalid accountNumber");
}
public void creditOperations(int pin)
{
	System.out.println("enter customer account number");
	int accountNumber=sc.nextInt();
	CustomerDetails customerDetails=CustomerDAO.selectCustomerDetailsByAccountAndPin(accountNumber, pin);
	if(customerDetails!=null)
	{
		System.out.println("enter the amount");
		double uamount=sc.nextDouble();
		double databaseAmount=customerDetails.getcAmount();
		if(uamount<=databaseAmount)
		{
			double balance=databaseAmount+uamount;
			System.out.println("balance is:"+balance);
			//update balance amount
		boolean update=	CustomerDAO.updateBalanceAmountByUsingAccountNumber(balance, accountNumber);
		if(update)
		{
			System.out.println("credited successfully");
		 TransactionDetails transactiondetails=new TransactionDetails();
		transactiondetails.setTransactionType("CREDIT");
		transactiondetails.setTransactionamount(uamount);
		transactiondetails.setTransactiondate(LocalDate.now());
		transactiondetails.setTransactiontime(LocalTime.now());
		transactiondetails.setAccountNumber(accountNumber);
		transactiondetails.setBalanceAmount(balance);
		transactiondetails.setRaccountnumber(accountNumber);
		transactionService.addtransactionDetails(transactiondetails);
		}
		else
			System.err.println("underProcess......");
		}
		else
		System.err.println("invalid amount.........");
	}
	else
		System.out.println("invalid accountNumber");
}
	public void checkStatement(int pin) {
	    System.out.println("enter customer account number");
	    int accountNumber = sc.nextInt();

	    // validate account + pin
	    CustomerDetails customerDetails = CustomerDAO.selectCustomerDetailsByAccountAndPin(accountNumber, pin);

	    if (customerDetails != null) {
	        List<String> statement = TransactionDAO.getTransactionsByAccount(accountNumber);

	        System.out.println("-----Statement for Account: " + accountNumber +"-----");
	        for (String row : statement) {
	            System.out.println(row);
	        }
	        System.out.println("--------------------------------------");
	    } else {
	        System.out.println("Invalid account number or PIN");
	    }
	}
	public void changePin(String email) {
	    System.out.println("enter customer account number");
	    int accountNumber = sc.nextInt();

	    System.out.println("enter old PIN");
	    int oldPin = sc.nextInt();

	    System.out.println("enter new PIN");
	    int newPin = sc.nextInt();

	    System.out.println("re-enter new PIN for confirmation");
	    int confirmPin = sc.nextInt();

	    if (newPin != confirmPin) {
	        System.out.println("New PIN and confirmation PIN do not match!");
	        return;
	    }
	    

	    if (CustomerDAO.updatePin(email,accountNumber, oldPin, newPin)) {
	        System.out.println("PIN changed successfully!");
	    } else {
	        System.out.println("Invalid account number or old PIN. Try again.");
	    }
	    }
	

	
}


