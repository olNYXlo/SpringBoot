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
public class DepositServiceImpl implements DepositService {
	
	@Autowired
	ATMDAOImpl refDAO; // creates ATMDAO reference

	@Override
	public void InvokeDeposit(BankAccount BA) throws ClassNotFoundException, SQLException {

		ATMDAO refDAO; // creates ATMDAO reference
		refDAO = new ATMDAOImpl(); // creates ATMDAOImpl object

		boolean loopcheckDep = true;

		Scanner sc = new Scanner(System.in);
		while (loopcheckDep) {

			// logical loop that repeats itself until the deposit transaction is performed
			// successfully
			// re-prompting users to key in a valid amount if they provided an invalid one
			// (negative value)
			
			
			boolean loopcheckAmt = true;
			while (loopcheckAmt) {
				System.out.println("Enter Amount : ");
				if (sc.hasNextDouble()) {
					double amt = sc.nextDouble();
					System.out.println("======================================================================================");

					if (amt >= 0) {
						// checks validity of deposit. If amount is positive or negative

						refDAO.deposit(BA, amt);
						System.out.println(amt + " dollar deposited successfully!!\n");
						System.out.println("======================================================================================");
						loopcheckAmt = false; // breaks out of loop checking for valid double input from user

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
								CheckContinue = false;
								// breaks out of checking for valid input of continue
								// loops back to transaction menu
								break;
							case 'n':
								System.out.println("Thanks for Banking With us!!!");
								System.out.println("======================================================================================");
								CheckContinue = false;
								TransactionMenu.setLoopcheck(false);
								break;
							// loops back to base menu.
							// changes the global loopcheck for the transaction menu to be false, breaking
							// out of the while loop
							// and reverting back to the base menu
							// logout
							default:
								System.out.println("invalid command");
								System.out.println("======================================================================================");
								// loop back for input
								break;

							}// end of switch case for continuation of another transaction
						} // end of while loop that checks if user wants to continue with another

						loopcheckDep = false;
					} // end of if case
					else if (amt < 0) {
						System.out.println("Amount can't be negative!!");
						System.out.println("======================================================================================");
						// automatically loops back to the start of while loop that prompts user to key
						// in amount
					} // end of else if case
					
					
					
				}// end of if case for checking valid double input from user
				else {
					sc.next();
					System.out.println("Please enter a number!");
					System.out.println("======================================================================================");
				} // end of else case for checking valid double input from user
				
				
			}// end of loopcheckAmt
			
			

		} // end of while loop for deposit service
	}// end of deposit method

}// end of depositserviceimpl class
