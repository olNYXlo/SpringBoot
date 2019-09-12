package dbService;

import java.sql.SQLException;

import dbPOJO.BankAccount;

public interface WithdrawService {
	//checks validity of withdraw. If amount is positive or negative and if the account has enough balance
	public void InvokeWithdraw(BankAccount BA) throws ClassNotFoundException, SQLException;
}
