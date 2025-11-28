package org.bank.dto;
import java.sql.Date;
public class CustomerDetails {
	/*customer_id, customer_name, customer_emailid, customer_mobileNo, customer_panNo, 
	customer_aadharNo, customer_gender, customer_dateOfBirth, customer_address, 
	customer_Account_number,customer_PIN, customer_status, customer_IFSCcode, customer_Amount*/
	private int customerid;
	private String customername;
	private String emailid;
	private long mobileNo;
	private String panNo;
	private long customerAadharNo;
	private String customerGender;
	private Date cdataOfBirth;
	private String cAddress;
	private long cAccountNumber;
	private int cPinNo;
	private String cStatus;
	private String cIFSCCode;
	private double cAmount;
	public  CustomerDetails()
	{	
	}
	public CustomerDetails(int customerid, String customername, String emailid, long mobileNo, String panNo,
			long customerAadharNo, String customerGender, Date cdataOfBirth, String cAddress, long cAccountNumber,
			int cPinNo, String cStatus, String cIFSCCode, double cAmount) {
		super();
		this.customerid = customerid;
		this.customername = customername;
		this.emailid = emailid;
		this.mobileNo = mobileNo;
		this.panNo = panNo;
		this.customerAadharNo = customerAadharNo;
		this.customerGender = customerGender;
		this.cdataOfBirth = cdataOfBirth;
		this.cAddress = cAddress;
		this.cAccountNumber = cAccountNumber;
		this.cPinNo = cPinNo;
		this.cStatus = cStatus;
		this.cIFSCCode = cIFSCCode;
		this.cAmount = cAmount;
	}
	public int getCustomerid() {
		return customerid;
	}
	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}
	public String getCustomername() {
		return customername;
	}
	public void setCustomername(String customername) {
		this.customername = customername;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public long getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getPanNo() {
		return panNo;
	}
	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}
	public long getCustomerAadharNo() {
		return customerAadharNo;
	}
	public void setCustomerAadharNo(long customerAadharNo) {
		this.customerAadharNo = customerAadharNo;
	}
	public String getCustomerGender() {
		return customerGender;
	}
	public void setCustomerGender(String customerGender) {
		this.customerGender = customerGender;
	}
	public Date getCdataOfBirth() {
		return cdataOfBirth;
	}
	public void setCdataOfBirth(Date cdataOfBirth) {
		this.cdataOfBirth = cdataOfBirth;
	}
	public String getcAddress() {
		return cAddress;
	}
	public void setcAddress(String cAddress) {
		this.cAddress = cAddress;
	}
	public  long getcAccountNumber() {
		return cAccountNumber;
	}
	public void setcAccountNumber(long cAccountNumber) {
		this.cAccountNumber = cAccountNumber;
	}
	public int getcPinNo() {
		return cPinNo;
	}
	public void setcPinNo(int cPinNo) {
		this.cPinNo = cPinNo;
	}
	public String getcStatus() {
		return cStatus;
	}
	public void setcStatus(String cStatus) {
		this.cStatus = cStatus;
	}
	public String getcIFSCCode() {
		return cIFSCCode;
	}
	public void setcIFSCCode(String cIFSCCode) {
		this.cIFSCCode = cIFSCCode;
	}
	public double getcAmount() {
		return cAmount;
	}
	public void setcAmount(double cAmount) {
		this.cAmount = cAmount;
	}
	@Override
	public String toString() {
		return "CustomerDetails [customerid=" + customerid + ", customername=" + customername + ", emailid=" + emailid
				+ ", mobileNo=" + mobileNo + ", panNo=" + panNo + ", customerAadharNo=" + customerAadharNo
				+ ", customerGender=" + customerGender + ", cdataOfBirth=" + cdataOfBirth + ", cAddress=" + cAddress
				+ ", cAccountNumber=" + cAccountNumber + ", cPinNo=" + cPinNo + ", cStatus=" + cStatus + ", cIFSCCode="
				+ cIFSCCode + ", cAmount=" + cAmount + "]";
	}	
}
