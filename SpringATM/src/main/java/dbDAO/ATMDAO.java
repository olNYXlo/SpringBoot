package dbDAO;

import java.sql.SQLException;

import dbPOJO.BankAccount;
import dbPOJO.OnlineLoginAccount;

public interface ATMDAO {
	
	public void register(OnlineLoginAccount OLA, BankAccount BA) throws SQLException;
	
	public boolean login(OnlineLoginAccount OLA) throws SQLException;
	
	public void ForgotPassword(OnlineLoginAccount OLA) throws SQLException;
	
	public void withdraw(BankAccount BA, double val) throws SQLException;
	
	public void deposit (BankAccount BA, double val) throws SQLException;
	
	public double checkbalance(BankAccount BA) throws SQLException;
	
	public BankAccount getBANo(OnlineLoginAccount OLA) throws SQLException;
	
}
