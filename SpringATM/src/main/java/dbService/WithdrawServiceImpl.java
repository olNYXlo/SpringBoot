package dbService;

import java.sql.SQLException;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dbController.TransactionMenu;
import dbDAO.ATMDAO;
import dbDAO.ATMDAOImpl;
import dbPOJO.BankAccount;


@Service
public class WithdrawServiceImpl implements WithdrawService {
	@Autowired
	ATMDAO refDAO; // creates ATMDAO reference
	
	@Override
	public void InvokeWithdraw(BankAccount BA) throws ClassNotFoundException, SQLException {

		ATMDAO refDAO; // creates ATMDAO reference
		refDAO = new ATMDAOImpl(); // creates ATMDAOImpl object

		boolean loopcheckWith = true;

		// logical loop that continuously prompts user until the withdraw service is
		// completed successfully

		// checks validity of withdraw. If amount is positive or negative and if the
		// account has enough balance

		Scanner sc = new Scanner(System.in);

		while (loopcheckWith) {
			
			boolean loopcheckAmt = true;
			while (loopcheckAmt) {
				
				System.out.println("Enter Amount : ");
				if (sc.hasNextDouble()) {
					
					double amt = sc.nextDouble();
					System.out.println("======================================================================================");

					if (ATMDAOImpl.checkWithdraw(amt, BA) == true) {
						// checks if the withdrawal amount is valid (lesser
						// than bank balance and is a positive number)

						refDAO.withdraw(BA, amt);

						System.out.println("Transaction Successful!!");
						System.out.println("======================================================================================");
						loopcheckAmt = false; // breaks out of the loop requesting valid amount

						boolean CheckContinue = true;
						// loop to make the checking of if the user wants to continue with another
						// transaction until a valid response is given
						while (CheckContinue) {
							System.out.println("Wish to Continue? (y\\n) ");
							System.out.println("======================================================================================");
							char ch;
							ch = sc.next().charAt(0);
							switch (ch) {
							case 'y':
								// loops back to transaction menu
								CheckContinue = false;
								break;
							case 'n':
								System.out.println("Thanks for Banking With us!!!");
								System.out.println("======================================================================================");
								TransactionMenu.setLoopcheck(false);
								CheckContinue = false;
								// loops back to base menu.
								// changes the global loopcheck for the transaction menu to be false, breaking
								// out of the while loop
								// and reverting back to the base menu
								// logout
								break;
							default:
								System.out.println("invalid command");
								System.out.println("======================================================================================");
								// loop back for input for continuation of transactions
								break;
							}// end of switch case for continuation of another transaction
						} // end of while loop that checks if user wants to continue with another
							// transaction or not
						loopcheckWith = false;
					} // end of if case
					else if (ATMDAOImpl.checkWithdraw(amt, BA) == false) {
						// loops back to withdraw service

					} // end of else if case
					
				} // end of if case for checking valid user input
				else {
					sc.next();
					System.out.println("Please enter a number!");
					System.out.println("======================================================================================");
					
				}// end of else case for checking valid user input				
				
			} // end of loopcheckAmt

			
		} // end of while loop for withdraw service
	}// end of withdraw service method

}// end of withdrawserviceimpl class
