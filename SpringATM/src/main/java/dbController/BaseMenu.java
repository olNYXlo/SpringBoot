package dbController;

import java.sql.SQLException;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dbService.ForgotPwServiceImpl;
import dbService.LoginService2Impl;
import dbService.RegisterServiceImpl;


@RestController
@RequestMapping("/ATM")
public class BaseMenu {
	
	@Autowired
	RegisterServiceImpl RSI;
	@Autowired
	LoginService2Impl LSI;
	@Autowired
	ForgotPwServiceImpl FPWSI;
	

	// launches the base menu when the application starts

	public void launch() throws ClassNotFoundException, SQLException {


		// this loopcheck is to ensure the Base Menu will always be displayed after each
		// choice
		// as the value is kept at true so the while loop will always run
		// use of loopcheck makes it a "recursive" method that keeps calling itself

		boolean loopcheck = true; // this value will always be true, hence the user will never be able to exit
									// from this program or end it
		while (loopcheck) {
			// Prints available options for user to pick from
			System.out.println("===================================DBS BANK===========================================");
			System.out.println("User Home Page:");
			System.out.println("1. Register");
			System.out.println("2. Login");
			System.out.println("3. Forget Password");
			System.out.println("4. Logout (exit)");
			System.out.println("======================================================================================");
			System.out.println("Enter Your Choice :");
			Scanner sc = new Scanner(System.in);
			int Choice = sc.nextInt();
			System.out.println("======================================================================================");
			switch (Choice) {// Depending on user input, will start the relevant service

			case 1:// Register Service
				RSI = new RegisterServiceImpl();
				RSI.InvokeRegister();
				 // launch Register Service
				break; // end of case 1
				

			case 2:// Login Service
								LSI = new LoginService2Impl();
				LSI.InvokeLogin();
				
				break; // end of case 2;


			case 3:// Forgot Password Service
				FPWSI = new ForgotPwServiceImpl();
				FPWSI.InvokeForgotPw();
				

				
				break;
				//end of case 3 (forgot password service)

			case 4:// Logout Service

				// loops back to base menu
				// pointless service and could be removed as you are not even logged in yet

				break;
				//end of case 4 (logout service)
			default: // Invalid Choice
				System.out.println("Invalid Choice");
				System.out.println("======================================================================================");
				// loops back to base menu to prompt user for a valid choice
				break;
				//end of default case
				// default case replaces "exception handling"? in the case of invalid command will route users back to the base menu
			}// end of switch case

		}// end of loopcheck
	}// end of launch method
}// end of BaseMenu class
