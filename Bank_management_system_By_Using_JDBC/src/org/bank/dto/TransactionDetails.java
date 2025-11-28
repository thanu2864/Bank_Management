package org.bank.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class TransactionDetails {
private int transactionid;
private String transactionType;
private double transactionamount;
private LocalDate transactiondate;
private LocalTime transactiontime;
private int accountNumber;
private double balanceAmount;
private int raccountnumber;
public TransactionDetails()
{
	
}
public TransactionDetails(int transactionid, String transactionType, double transactionamount,
		LocalDate transactiondate, LocalTime transactiontime, int accountNumber, double balanceAmount,
		int raccountnumber) {
	super();
	this.transactionid = transactionid;
	this.transactionType = transactionType;
	this.transactionamount = transactionamount;
	this.transactiondate = transactiondate;
	this.transactiontime = transactiontime;
	this.accountNumber = accountNumber;
	this.balanceAmount = balanceAmount;
	this.raccountnumber = raccountnumber;
}
public int getTransactionid() {
	return transactionid;
}
public void setTransactionid(int transactionid) {
	this.transactionid = transactionid;
}
public String getTransactionType() {
	return transactionType;
}
public void setTransactionType(String transactionType) {
	this.transactionType = transactionType;
}
public double getTransactionamount() {
	return transactionamount;
}
public void setTransactionamount(double transactionamount) {
	this.transactionamount = transactionamount;
}
public LocalDate getTransactiondate() {
	return transactiondate;
}
public void setTransactiondate(LocalDate transactiondate) {
	this.transactiondate = transactiondate;
}
public LocalTime getTransactiontime() {
	return transactiontime;
}
public void setTransactiontime(LocalTime transactiontime) {
	this.transactiontime = transactiontime;
}
public int getAccountNumber() {
	return accountNumber;
}
public void setAccountNumber(int accountNumber) {
	this.accountNumber = accountNumber;
}
public double getBalanceAmount() {
	return balanceAmount;
}
public void setBalanceAmount(double balanceAmount) {
	this.balanceAmount = balanceAmount;
}
public int getRaccountnumber() {
	return raccountnumber;
}
public void setRaccountnumber(int raccountnumber) {
	this.raccountnumber = raccountnumber;
}
@Override
public String toString() {
	return "TransactionDetails [transactionid=" + transactionid + ", transactionType=" + transactionType
			+ ", transactionamount=" + transactionamount + ", transactiondate=" + transactiondate + ", transactiontime="
			+ transactiontime + ", accountNumber=" + accountNumber + ", balanceAmount=" + balanceAmount
			+ ", raccountnumber=" + raccountnumber + "]";
}

}
