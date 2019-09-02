package controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;

import service.EmailServiceImpl;

public class HomePageController {
	
	@Autowired
	EmailServiceImpl ESI;

	public void main() throws ClassNotFoundException, SQLException {
		
		ESI = new EmailServiceImpl();

	}
}
