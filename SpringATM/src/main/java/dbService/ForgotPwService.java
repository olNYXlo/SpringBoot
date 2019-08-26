package dbService;

import java.sql.SQLException;

public interface ForgotPwService {
	//checks if given security key matches records. if match, will give user the password to his account
	public void InvokeForgotPw() throws ClassNotFoundException, SQLException;

}
