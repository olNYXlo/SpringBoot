package dbService;

import java.sql.SQLException;


public interface RegisterService {
	//checks if given username already exists in the records. If exists, throw an error message and prompt another input.
		//if given username does not exist, continue with registration process
	void InvokeRegister() throws ClassNotFoundException, SQLException;

}
