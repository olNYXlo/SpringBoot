package dbController;

import java.sql.SQLException;
import java.util.Scanner;

import dbPOJO.BankAccount;
import dbService.CheckBalanceServiceImpl;
import dbService.DepositServiceImpl;
import dbService.WithdrawServiceImpl;

public class TransactionMenu {

	private static boolean loopcheck;

	// launches the TransactionMenu after logging in

	public static void setLoopcheck(boolean loopcheck) {
		TransactionMenu.loopcheck = loopcheck;
	}

	public static void main(BankAccount BA) throws SQLException, ClassNotFoundException {

		// Once Logged in, transaction menu will be run with BankAccount linked
		// to the Logged In account & any transactions will be based on it

		// similar implementation to BaseMenu
		// same concept of implementing logical loops to create "recursive" functions

		loopcheck = true;

		while (loopcheck) {

			// displays the available transaction options after logging in
			System.out.println("===================================DBS BANK===========================================");
			System.out.println("Transaction Home Page:");
			System.out.println("1. Check Available Balance");
			System.out.println("2. Deposit Amount");
			System.out.println("3. Withdraw Amount");
			System.out.println("======================================================================================");
			System.out.println("Enter Your Choice :");
			Scanner sc = new Scanner(System.in);
			int Choice = sc.nextInt();
			System.out.println("======================================================================================");
			switch (Choice) {

			case 1:// checking available balance

				CheckBalanceServiceImpl CBSI = new CheckBalanceServiceImpl();
				CBSI.InvokeCheckBalance(BA);

				break;
			// end of case 1

			case 2:// deposit service

				DepositServiceImpl DSI = new DepositServiceImpl();

				DSI.InvokeDeposit(BA);

				break;// end of case 2

			case 3:// withdraw service

				WithdrawServiceImpl WSI = new WithdrawServiceImpl();

				WSI.InvokeWithdraw(BA);

				break;
			// end of case 3

			default:
				System.out.println("Invalid Choice");
				System.out.println("======================================================================================");
				// loops back to transaction menu
				break;
			// end of default case
			// default case replaces "exception handling"? in the case of invalid command
			// will route users back to the base menu

			}// end of switch case

		} // end of loopcheck
	}// end of main method
}// end of TransactionMenu Class
