package dbPOJO;

public class OnlineLoginAccount {
	// Account details of userID, Password, SecurityKey & Bank Balance
	private String userID;
	private String password;
	private String SecurityKey;	
	
	
	
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSecurityKey() {
		return SecurityKey;
	}

	public void setSecurityKey(String securityKey) {
		SecurityKey = securityKey;
	}

	

}
