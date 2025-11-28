package org.bank.service;

import org.bank.dao.TransactionDAO;
import org.bank.dto.TransactionDetails;

public class TransactionService {
TransactionDAO transactiondao=new TransactionDAO();
public void addtransactionDetails(TransactionDetails transactiondetails)
{
	if(transactiondao.insertTransactionDetails(transactiondetails))
		System.out.println("inserted successfully..............");
	else
		System.out.println("server error");
}
}
