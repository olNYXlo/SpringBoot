package dbPOJO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="Account")
public class OnlineLoginAccount {
	// Account details of userID, Password, SecurityKey & Bank Balance
	@Id
	@Column(name="UserID")
	private String userID;
	@Column(name="pw")
	private String password;
	@Column(name="SecurityKey")
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
	
	public OnlineLoginAccount() {
		
	}
	
public OnlineLoginAccount(String UID, String PW, String SK) {
	userID = UID;
	password=PW;
	SecurityKey = SK;
		
	}
@Override
public String toString() {
	return "OnlineLoginAccount [userID=" + userID + ", password=" + password + ", SecurityKey=" + SecurityKey + "]";
}


	

}
