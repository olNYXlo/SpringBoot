package dbPOJO;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="BankAccount")
public class BankAccount {
	
	@Column(name="AccountHolderNRIC")
	private String AccHolderNRIC;
	@Column(name="AccountHolderName")
	private String AccHolderName;
	@Column(name="AccountName")
	private String AccName;
	@Column(name="UserID")
	private String UserID;
	@Id
	@Column(name="BankAccNo")
	private String BankAccNo;
	@Column(name="BankBalance")
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

	public BankAccount() {

	}

	public BankAccount(String accHolderNRIC, String accHolderName, String accName, String userID, String bankAccNo,
			double bankBalance) {
		AccHolderNRIC = accHolderNRIC;
		AccHolderName = accHolderName;
		AccName = accName;
		UserID = userID;
		BankAccNo = bankAccNo;
		BankBalance = bankBalance;
	}

	@Override
	public String toString() {
		return "BankAccount [AccHolderNRIC=" + AccHolderNRIC + ", AccHolderName=" + AccHolderName + ", AccName="
				+ AccName + ", UserID=" + UserID + ", BankAccNo=" + BankAccNo + ", BankBalance=" + BankBalance + "]";
	}
	
	
	
	
	

	
}
