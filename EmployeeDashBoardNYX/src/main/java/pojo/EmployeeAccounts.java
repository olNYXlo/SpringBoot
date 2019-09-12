package pojo;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="----")
public class EmployeeAccounts {

	@Id
	@Column(name="------")	
	private int employeeId;
	// primary key
	
	@Column(name="------")
	private String password;
	@Column(name="------")
	private String email;
	@Column(name="------")
	private String name;
	@Column(name="------")
	private String department;
	@Column(name="------")
	private String mobileNo;
	@Column(name="------")
	private Blob profileImg;
	// contains URL String to the image
	// nullable???
	@Column(name="------")
	private Boolean isActive;
	// used to check if account is Activated or Deactived
	@Column(name="------")
	private Boolean isApproved;
	// used to check if account has been approved
	// upon any login, will change this value to be true
	// as employee will only get password from Amit if he approves
	// the registration. 
	// So can consider any logins to be valid
	// if isApproved is false, prevent running of forgetPassword method
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
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
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public Boolean getIsApproved() {
		return isApproved;
	}
	public void setIsApproved(Boolean isApproved) {
		this.isApproved = isApproved;
	}
	
	
	
	
}
