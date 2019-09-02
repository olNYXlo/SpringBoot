package service;

import java.sql.SQLException;

import pojo.LoginAccount;

public interface LoginService {

	void invokeLogin(LoginAccount LA) throws ClassNotFoundException, SQLException;

}
