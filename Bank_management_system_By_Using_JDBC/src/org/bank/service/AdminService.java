package org.bank.service;

import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.bank.dao.AdminDAO;
import org.bank.dto.CustomerDetails;

public class AdminService {
	Scanner sc = new Scanner(System.in);
	AdminDAO admindao = new AdminDAO();
	CustomerService customerservice = new CustomerService();
public void adminLogin() {
		System.out.println("Enter Emailid :");
		String amailid = sc.next();
		System.out.println("Enter Password :");
		String epassword = sc.next();
		if (admindao.selectAdminDetailsByUsingEmailidAndPassword(amailid, epassword)) {
			System.out.println("Login Successfull");
			System.out.println("Enter \n 1. To Get All Customer Details" + "\n 2. To Accept Pending Detaiils "
					+ "\n 3. To Get All Account Closing Requests");
			switch (sc.nextInt()) {
			case 1:
				System.out.println("All Customer Details");
				break;
			case 2:
				System.out.println("Accept Pending Details");
				acceptPendingDetails();
				break;
			case 3:
				System.out.println("Get All Account Closing Requests");
				break;
			default:
				System.out.println("Invalid Request");
				break;
			}
		} else {
			System.out.println("Invalid Emailid and Password");
		}
	}
public List<CustomerDetails> getAllPendingDetails() {
		List<CustomerDetails> customerslist = customerservice.getAllPendingDetails();
		for (CustomerDetails customer : customerslist) {
			System.out.println("Customer Name: " + customer.getCustomername());
			System.out.println("Customer Emailid: " + customer.getEmailid());
			String mobno = String.valueOf(customer.getMobileNo());
			String first = mobno.substring(0, 3);
			String last = mobno.substring(7);
			System.out.println("Customer MobileNumber: " + first + "****" + last);
			System.out.println("Customer gender: " + customer.getCustomerGender());
			System.out.println("Customer Address: " + customer.getcAddress());
			System.out.println("Customer Amount: " + customer.getcAmount());
			System.out.println("-------------------------------------------");
		}
		// assigning account number and pin number by using the email id (if emailid is
		// present in the list we have to set)
		System.out.println("Enter Emailid of the Customer :");
		String cemailid = sc.next();
		return customerslist.stream().filter((customer) -> customer.getEmailid().equals(cemailid))
				.collect((Collectors.toList()));
		// in the list only one value will be present because email id is unique so it
		// only give one object at 0 index only
		// so the returntype always returns one object details

	}
	// ACcepting Pending Details and assigning the Account Number,Pin number and
	// IFSC code
	public void acceptPendingDetails() {
		// first we have to get the object from getAllPendingDetailsMethod to set the
		// values,It returns only one object bec
		// it contains only one object at 0 index
		CustomerDetails customerDetails = getAllPendingDetails().get(0);
		// we have to generate the random values to the account number
		Random random = new Random();
		int accountnumber = random.nextInt(10000000); // till 9999999 it will generate(generating 7 digit account
														// number)
		if (accountnumber < 1000000) {
			accountnumber += 1000000; // if it is 15 1000000+15=1000015;
		}
		int pin = random.nextInt(10000); // till 9999 it will generate(generating 5 digit pin number)
		if (pin < 1000) {
			pin += 1000;
			 // if it is 999 then 999+1000=1999
		}
     	customerDetails.setcAccountNumber(accountnumber);
		customerDetails.setcPinNo(pin);
		customerDetails.setcIFSCCode("M16BANK007");
		customerDetails.setcStatus("Active");
		customerservice.updateCustomerDetails(customerDetails);
		System.out.println(customerDetails);
	}
}