package dbService;

import java.sql.SQLException;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

import dbDAO.ATMDAO;
import dbDAO.ATMDAOImpl;
import dbPOJO.OnlineLoginAccount;


public class ForgotPwServiceImpl implements ForgotPwService {
	
	@Autowired
	ATMDAO refDAO; // creates ATMDAO reference

	@Override
	public void InvokeForgotPw() throws ClassNotFoundException, SQLException {

		
		refDAO = new ATMDAOImpl(); // creates ATMDAOImpl object

		boolean loopcheckFPW = true;
		Scanner sc = new Scanner(System.in);

		while (loopcheckFPW) {

			// logical check to see if the forgot password retrieval process was successful
			// or not
			String UserID = null;

			boolean loopcheckLoginUID = true;
			while (loopcheckLoginUID) {
				System.out.println("Enter User ID: ");
				UserID = sc.nextLine();
				System.out.println("======================================================================================");
				if (ATMDAOImpl.checkIDExists(UserID)) {
					loopcheckLoginUID = false;
					// breaks out of the loop prompting valid user ID
				}// end of if case for valid user ID
				// if does not exist, will loop back prompting user

			} // end of loopcheckLoginUID loop. Checking for valid user account that exists
			
			boolean loopcheckSK = true;
			while (loopcheckSK) {
				System.out.println("Enter Security Key : ");
				String SK = sc.nextLine();
				System.out.println("======================================================================================");

				if (ATMDAOImpl.checkSK(SK, UserID)) {
					
					// logical check to see if the security key entered matches the database
					
					OnlineLoginAccount ExistingAcc = new OnlineLoginAccount(); 
					
					// creates new OnlineLoginAcc Obj
					ExistingAcc.setUserID(UserID);

					
					
					refDAO.ForgotPassword(ExistingAcc);
					//retrieves the password from the DB


					loopcheckFPW = false; 
					// closes the forgot password service
					loopcheckSK = false;
					// closes the loop that continues to prompt the user to enter the correct
											// security key
				}// end of if case 
				else {
					System.out.println("Security Key does not match");
					System.out.println("======================================================================================");
				}// end of else case
				
				
			}// end of loopcheckSK

			

		} // end of loopcheckFPW
	}// end of FPW method

}// end of ForgotPwServiceImpl class
