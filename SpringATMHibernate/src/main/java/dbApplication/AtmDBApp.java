
package dbApplication;

import java.sql.SQLException;

import dbController.BaseMenu;

public class AtmDBApp {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		BaseMenu BM = new BaseMenu();
		BM.launch();

	}
}

