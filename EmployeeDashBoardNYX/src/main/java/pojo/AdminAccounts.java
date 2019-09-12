package pojo;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="----")
public class AdminAccounts {

	@Id
	@Column(name="------")
	private int employeeId;
	// primary key
	@Column(name="------")
	private String password;
	@Column(name="------")
	private String email;
	@Column(name="------")
	private String mobileNo;
	@Column(name="------")
	private Blob profileImg;
	// contains URL String to the image
	// nullable???
	
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeID) {
		this.employeeId = employeeID;
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
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public Blob getProfileImg() {
		return profileImg;
	}
	public void setProfileImg(Blob profileImg) {
		this.profileImg = profileImg;
	}
	
	
}
