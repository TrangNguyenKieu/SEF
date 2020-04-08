
public class Account {
	private String bankName;
	private int accountNumber;
	private int BSB;
	
	public Account(String bankName, int accountNumber, int BSB) {
		this.bankName = bankName;
		this.accountNumber = accountNumber;
		this.BSB = BSB;
	}
	
	public String getBankName() {
		return bankName;
	}
	
	public int getAccountNumber() {
		return accountNumber;
	}
	
	public int getBSB() {
		return BSB;
	}
	
	public String getAccountDetails() {
		return "Bank Name: " + bankName + "\n"
				+ "Account Number: " + accountNumber + "\n"
				+ "BSB: " + BSB;
	}

}
