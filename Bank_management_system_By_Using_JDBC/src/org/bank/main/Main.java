package org.bank.main;
import java.util.Scanner;
import org.bank.service.AdminService;
import org.bank.service.CustomerService;
public class Main {
public static void main(String[] args) {
	CustomerService customerService=new CustomerService();
	AdminService adminService=new AdminService();
	System.out.println("**--**Welcome To M16 Bank**--**");
	System.out.println("enter \n1.for Customer Login \n2.for Customer Registration \n3.for Admin login");
	Scanner sc=new Scanner(System.in);
	switch (sc.nextInt()) {
	case 1:
		System.out.println("customer login");
		customerService.customerLogin();
		break;
	case 2:
		System.out.println("Customer registration");
		customerService.customerRegistration();
		break;
	case 3:
		System.out.println("Admin login");
		adminService.adminLogin();
		break;
	default:
		System.out.println("Invaild request");
		break;
	}
	sc.close();
}
}
