package dbDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbPOJO.BankAccount;
import dbPOJO.OnlineLoginAccount;

public class ATMDAOImpl implements ATMDAO {

	Connection con;
	PreparedStatement ps,ps2;

	void getConnection() {
		try {
			con = ATMDBConnection.myConnection();
			System.out.println("Connected");

			// when using db.properties file to connect
			// do not need to have catch block as already
			// exception already handled in the myConnection method
		} finally {
		}

	}// end of getConnection
	
	
	public static boolean checkBankAccExists(String BankAccNo) throws ClassNotFoundException, SQLException {
		// Checks if Bank Account exists in records

		boolean check = false;

		Connection con = ATMDBConnection.myConnection();

		PreparedStatement ps = con.prepareStatement("SELECT * from BankAccount Where BankAccNo = ?"); // Create statement using SQL
																							// connection

		ps.setString(1, BankAccNo);
		ResultSet rs = ps.executeQuery();

		if (rs.next() == false) { // if result set does not have any value rs.next() == false
			System.out.println("No existing Bank Account with that number. Please Try Again");
			System.out.println("======================================================================================");
		} else {
			System.out.println("Existing Bank Account with that number found");
			System.out.println("======================================================================================");
			check = true;
		}
		con.close();
		return check;

	}
	
	public static boolean checkSK(String SK, String UID) throws SQLException, ClassNotFoundException{
		//Checks if input NRIC matches that in database
		// Done to link bank account to online account
		
		Connection con = ATMDBConnection.myConnection();

		boolean result = false;
		PreparedStatement ps = con.prepareStatement("SELECT SecurityKey FROM Account Where UserID = ?"); // Create statement using SQL connection

		ps.setString(1, UID);

		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) { // have to include this line when processing result set else will not be able to access rs.getString or anything not sure why
			String compareNRIC = rs.getString(1);
			result = compareNRIC.equals(SK); // if true, means Security Key matches that in DB
			}
		con.close(); // closes connection
		return result;
		
	}


	public static boolean checkNRIC(String NRIC, String BANo) throws SQLException, ClassNotFoundException{
		//Checks if input NRIC matches that in database
		// Done to link bank account to online account
		// takes in NRIC and bank account number
		
		Connection con = ATMDBConnection.myConnection();

		boolean result = false;
		PreparedStatement ps = con.prepareStatement("SELECT AccountHolderNRIC FROM BankAccount Where BankAccNo = ?"); // Create statement using SQL connection

		ps.setString(1, BANo);

		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) { // have to include this line when processing result set else will not be able to access rs.getString or anything not sure why
			String compareNRIC = rs.getString(1);
			result = compareNRIC.equals(NRIC); // if true, means NRIC matches that in DB
			}
		con.close(); // closes connection
		return result;
		
	}
	
	
	public static boolean checkWithdraw(double amt, BankAccount BA) throws SQLException, ClassNotFoundException{
		//Checks if valid withdrawl amount is supplied
		
		Connection con = ATMDBConnection.myConnection();

		boolean result = false;
		PreparedStatement ps = con.prepareStatement("SELECT BankBalance FROM BankAccount Where UserID = ?"); // Create statement using SQL connection

		ps.setString(1, BA.getUserID());

		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) { // have to include this line when processing result set else will not be able to access rs.getString or anything not sure why
			if (rs.getDouble("BankBalance") >= amt && amt >= 0) {
				result = true;
			}// end of if case
			else if (amt < 0) {
				System.out.println("Amount Can't Be Negative");
				System.out.println("======================================================================================");
				result = false;
			} // end of else if case
			else if (rs.getDouble("BankBalance") < amt && amt > 0) {
				result = false;
				System.out.println("Insufficient Balance");
				System.out.println("======================================================================================");
			} //  end of else if case
			
			} // end of while loop
		con.close(); // closes connection
		return result;
		
	}
	


	public static boolean checkBankAccLinked(String BankAccNo) throws ClassNotFoundException, SQLException {
		
		// Checks ATM records if the bank account has already been linked to an online account

		boolean check = false;

		Connection con = ATMDBConnection.myConnection();

		PreparedStatement ps = con.prepareStatement("SELECT UserID from BankAccount Where BankAccNo = ?"); // Create statement using SQL
																							// connection

		ps.setString(1, BankAccNo);
		ResultSet rs = ps.executeQuery();

		if (rs.next()) { // if resultset returns null, will run this code
			System.out.println("No Online Account Linked to this Bank Account Yet");
			System.out.println("======================================================================================");
		} else { // if resultset returns some value will run this code
			System.out.println("Bank Account is already linked to another Online account");
			System.out.println("======================================================================================");
			check = true;
		}
		con.close();
		return check;
		// do not close connection here else will not run properly

	}

	public static boolean checkIDExists(String UID) throws ClassNotFoundException, SQLException {
		
		// checks if UserID exists in login DB

		boolean check = false;

		Connection con = ATMDBConnection.myConnection();

		PreparedStatement ps = con.prepareStatement("SELECT * from Account Where UserID = ?"); // Create statement using SQL
																							// connection

		ps.setString(1, UID);
		ResultSet rs = ps.executeQuery();

		if (rs.next() == false) { // if resultset returns a null value
			System.out.println("UserID Does Not Exist In Records");
			System.out.println("======================================================================================");
		} else {
			System.out.println("UserID Already Exists. Please try logging in to your account");
			System.out.println("======================================================================================");
			check = true;
		}
		con.close();
		return check;
		// do not close connection here else will not run properly

	}

	@Override
	public void register(OnlineLoginAccount OLA, BankAccount BA) throws SQLException {
		getConnection(); // starts up DB connection

		ps = con.prepareStatement("INSERT INTO Account VALUES(?,?,?)"); // Create statement using SQL connection
		
		
		ps.setString(1, OLA.getUserID());
		ps.setString(2, OLA.getPassword());
		ps.setString(3, OLA.getSecurityKey()); // inserts new row into account table
		
		ps.executeUpdate();
		
		ps2 = con.prepareStatement("UPDATE BankAccount SET UserID = ? Where AccountHolderNRIC =? AND BankAccNo = ?");
		ps2.setString(1, OLA.getUserID());
		ps2.setString(2, BA.getAccHolderNRIC());
		ps2.setString(3, BA.getBankAcc()); // updates existing record of bank accounts linked to online banking accounts
		
		ps2.executeUpdate();

	
		con.close(); // closes connection

	}

	@Override
	public boolean login(OnlineLoginAccount OLA) throws SQLException {
		getConnection(); // starts up DB connection

		boolean result = false;
		ps = con.prepareStatement("SELECT pw FROM Account Where UserID = ?"); // Create statement using SQL connection

		ps.setString(1, OLA.getUserID());

		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) { // have to include this line when processing result set else will not be able to access rs.getString or anything not sure why
			String comparePW = rs.getString("pw");
			result = comparePW.equals(OLA.getPassword());
			}
		con.close(); // closes connection
		return result;
		
		

	}
	
	public BankAccount getBANo(OnlineLoginAccount OLA) throws SQLException{
		getConnection(); // starts up DB connection

		BankAccount BA = new BankAccount();
		ps = con.prepareStatement("SELECT * FROM BankAccount Where UserID = ?"); // Create statement using SQL connection

		ps.setString(1, OLA.getUserID());

		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) { // have to include this line when processing result set else will not be able to access rs.getString or anything not sure why
			BA.setBankAcc(rs.getString("BankAccNo"));	
			BA.setUserID(OLA.getUserID());
			BA.setBankBalance(rs.getDouble("BankBalance"));
			}
		con.close(); // closes connection
		return BA;
		
		
		
	}

	@Override
	public void ForgotPassword(OnlineLoginAccount OLA) throws SQLException {
		getConnection(); // starts up DB connection

		ps = con.prepareStatement("SELECT pw FROM Account Where UserID = ?"); // Create statement using SQL connection

		ps.setString(1, OLA.getUserID());

		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) { // have to include this line when processing result set else will not be able to access rs.getString or anything not sure why
			System.out.println("Printing Password");
			System.out.println("Your Password is : " + rs.getString("pw"));
			System.out.println("======================================================================================");
			}
		
		con.close(); // closes connection

	}

	@Override
	public void withdraw(BankAccount BA, double val) throws SQLException {
		getConnection(); // starts up DB connection

		ps = con.prepareStatement("UPDATE BankAccount Set BankBalance = ? Where UserID = ?"); // Create statement using SQL connection
		
		
		ps.setDouble(1, BA.getBankBalance()-val);
		ps.setString(2, BA.getUserID());
		
		ps.executeUpdate();

	
		con.close(); // closes connection

	}

	@Override
	public void deposit(BankAccount BA,double val) throws SQLException {
		getConnection(); // starts up DB connection

		ps = con.prepareStatement("UPDATE BankAccount Set BankBalance = ? Where UserID = ?"); // Create statement using SQL connection
		
		
		ps.setDouble(1, BA.getBankBalance()+val);
		ps.setString(2, BA.getUserID());
		
		ps.executeUpdate();

	
		con.close(); // closes connection

	}

	@Override
	public void checkbalance(BankAccount BA) throws SQLException {
		getConnection(); // starts up DB connection

		ps = con.prepareStatement("SELECT * FROM BankAccount Where UserID = ?"); // Create statement using SQL connection

		ps.setString(1, BA.getUserID());

		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) { // have to include this line when processing result set else will not be able to access rs.getString or anything not sure why
			System.out.println("Printing Bank Balance");
			System.out.println("Bank Balance for : " + rs.getString("BankAccNo") + " is SGD " + rs.getDouble("BankBalance"));
			System.out.println("======================================================================================");
			}
		
		con.close(); // closes connection

	}



}
