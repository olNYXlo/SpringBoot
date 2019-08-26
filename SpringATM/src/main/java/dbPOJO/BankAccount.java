package dbPOJO;

public class BankAccount {
	
	private String AccHolderNRIC;
	private String AccHolderName;
	private String AccName;
	private String UserID;
	private String BankAccNo;
	private double BankBalance;
	
	public String getAccHolderNRIC() {
		return AccHolderNRIC;
	}

	public void setAccHolderNRIC(String accHolderNRIC) {
		AccHolderNRIC = accHolderNRIC;
	}

	public String getAccHolderName() {
		return AccHolderName;
	}

	public void setAccHolderName(String accHolderName) {
		AccHolderName = accHolderName;
	}

	
	public String getBankAcc() {
		return BankAccNo;
	}

	public String getAccName() {
		return AccName;
	}
	public void setAccName(String accName) {
		AccName = accName;
	}
	public String getUserID() {
		return UserID;
	}
	public void setUserID(String userID) {
		UserID = userID;
	}
	public void setBankAcc(String bankAcc) {
		BankAccNo = bankAcc;
	}
	public double getBankBalance() {
		return BankBalance;
	}
	public void setBankBalance(double bankBalance) {
		BankBalance = bankBalance;
	}
	

	
}
