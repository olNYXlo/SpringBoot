package pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="----")
public class GuestLoginAccount {
	
	@Id
	@Column(name="------")
	private int guestLoginId;
	// Primary key
	// initialized in DB table but not used
	
	@Column(name="------")
	private String username;
	@Column(name="------")
	private String password;
	@Column(name="------")
	private String email;
	
	public int getGuestLoginId() {
		return guestLoginId;
	}
	public void setGuestLoginId(int guestLoginID) {
		this.guestLoginId = guestLoginID;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
