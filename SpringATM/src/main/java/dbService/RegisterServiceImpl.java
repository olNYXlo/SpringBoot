package dbService;

import java.sql.SQLException;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dbDAO.ATMDAO;
import dbDAO.ATMDAOImpl;
import dbPOJO.OnlineLoginAccount;
import dbPOJO.BankAccount;


@Service
public class RegisterServiceImpl implements RegisterService {
	@Autowired
	ATMDAO refDAO; // creates ATMDAO reference
	
	
	@Override
	public void InvokeRegister() throws ClassNotFoundException, SQLException {

		ATMDAO refDAO; // creates ATMDAO reference
		refDAO = new ATMDAOImpl(); // creates ATMDAOImpl object

		boolean loopcheckreg = true;

		while (loopcheckreg) {

			// logical loop to check if Register service can be started
			// if user ID already exists in register, will prompt user again
			// only upon full completion of the registration process will the user
			// be able to exit back to the base menu

			Scanner sc = new Scanner(System.in);

			System.out.println("Enter email address: ");
			String UserID = sc.nextLine();
			System.out.println("======================================================================================");
			if (ATMDAOImpl.checkIDExists(UserID) == false
					&& (UserID.contains("@gmail.com") || UserID.contains("@hotmail.com"))) {
				// ensures a proper email address is used

				// if CheckIDExists is false, means user ID does not exist in the records,
				// so can create a new account object and put it into the AccountList HashMap

				OnlineLoginAccount NewAcc = new OnlineLoginAccount();
				// creates a new account object
				// use setters to input attributes to object & pass onto method

				NewAcc.setUserID(UserID); // sets UserID

				System.out.println("Enter password : ");
				String password = sc.nextLine();
				System.out.println("======================================================================================");
				boolean loopcheckregpw = true;

				// logical loop to check if the given password is the same as the re-typed
				// password
				// will continuously show this menu until the passwords match up

				while (loopcheckregpw) {
					System.out.println("Re-Type Password :");
					String repassword = sc.nextLine();
					System.out.println("======================================================================================");
					if (password.equals(repassword)) {
						NewAcc.setPassword(repassword);
						System.out.println("What is your favourite color?");
						String SecurityKey = sc.nextLine();
						System.out.println("======================================================================================");
						NewAcc.setSecurityKey(SecurityKey);
						System.out.println(SecurityKey + " is your security key, in case you forget your password.");
						System.out.println("======================================================================================");
						loopcheckregpw = false; // breaks out of the loop for checking password

						boolean loopcheckregBankAcc = true;
						// Logical loop to check if Bank account exists in bank records & is not
						// linked to an online account yet

						while (loopcheckregBankAcc) {
							System.out.println("Enter Your 10 Digit Bank Account");
							String BankAccNo = sc.nextLine();
							System.out.println("======================================================================================");
							if (BankAccNo.length() == 10 && ATMDAOImpl.checkBankAccExists(BankAccNo)) {
								// checks for valid Bank Account Number that exists in records
								if (ATMDAOImpl.checkBankAccLinked(BankAccNo) == false) {
									// checks if Bank Account is already linked

									boolean loopcheckNRIC = true;

									// logical loop to prompt NRIC attached to the Bank account in the records

									while (loopcheckNRIC) {
										System.out.println("Enter Your NRIC");
										String NRIC = sc.nextLine();
										System.out.println("======================================================================================");
										if (NRIC.length() == 9 && ATMDAOImpl.checkNRIC(NRIC, BankAccNo)) { // checks for
																											// valid
																											// NRIC

											BankAccount BA = new BankAccount();
											BA.setUserID(UserID);
											BA.setAccHolderNRIC(NRIC);
											BA.setBankAccNo(BankAccNo);
											refDAO.register(NewAcc, BA);
											System.out.println("Bank Account is successfully linked to your account");
											System.out.println("======================================================================================");
											System.out.println("Registration Successful!!");
											System.out.println("======================================================================================");
											loopcheckNRIC = false; // breaks out of the logical loop for NRIC checking
											loopcheckreg = false; // breaks out of the logical loop for Register service
										} // end of if check for NRIC
										else {
											System.out.println("Invalid NRIC given. Please Try Again");
											System.out.println("======================================================================================");
										} // loops back to check NRIC
									} // end of loop for checking NRIC
									loopcheckregBankAcc = false;
									// breaks out of the logical loop to check if
									// bank account exists and is linked to
									// another account

								} // end of if case for BankAcc is not linked

							} // end of if case for BankAcc exists

						} // end of loop for registering bank account

					} else if (!password.equals(repassword)) {// prints error message if re-typed password does
																// not match initially typed password
						System.out.println("Password doesn't match!!");
						System.out.println("======================================================================================");
					} // end of else if case
						// loops back to prompt re typing of password

				} // end of loop check for registration password

			} // end of if condition
			else {
				System.out.println("Please enter a valid email address");
				System.out.println("======================================================================================");
			} // end of else case for valid email address

		} // end of while loop

	}// end of register method

}// end of RegisterServiceImpl Class
