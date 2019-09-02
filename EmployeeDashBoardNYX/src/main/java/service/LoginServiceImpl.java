package service;

import java.sql.SQLException;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

import dao.AccountDAO;
import pojo.LoginAccount;


public class LoginServiceImpl implements LoginService {

	@Autowired
	AccountDAO refDAO;

	@Override
	public void invokeLogin(LoginAccount LA) throws ClassNotFoundException, SQLException {

		refDAO = new AccountDAO(); // creates AccountDAO object

		boolean loopcheckLogin = true;

		Scanner sc = new Scanner(System.in);

		while (loopcheckLogin) {
			// logical loop to check if the login was successful or not
			// if successful, loopchecklogin = false
			// it could be unsuccessful due to wrong password being entered
			// in which case the menu loops back to attempt the loginService again.

			// in the case of login failure due to wrong password entered, user will be
			// directed back to start of login service
			// where he has to enter a user id and password again
			boolean loopcheckLoginUID = true;
			while (loopcheckLoginUID) {
				if (AccountDAO.checkIDExists(LA.getEmail()) == true) {
					loopcheckLoginUID = false;
					// breaks out of the loop prompting valid user ID

				} // end of if case
					// else case is already handled by ATMDAOImpl

			} // end of loopcheckLoginUID loop. Checking for valid user account that exists

			boolean loopcheckPw = true;
			while (loopcheckPw) {

				if (refDAO.login(LA) == true) {

					System.out.println("Login successful");
					System.out.println("======================================================================================");

					// checks if the login userID and password matches the database
					// creates a reference pointing to the account in the accountList.

					// launches transaction menu based on the account that you logged in with
					loopcheckPw = false; // to close loop for prompting user input correct password
					loopcheckLogin = false;
					// to close the loop for trying to login
					TransactionMenu.main(refDAO.getBANo(OLA));

				} else if (refDAO.login(LA) == false) {
					System.out.println("Login failed");
					System.out.println("======================================================================================");
					// reverts back to prompt user to try to login again
				}

			} // end of while loop
		}		
		
		

	}
}
