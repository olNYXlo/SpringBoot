package pojo;

import java.sql.Blob;
import java.util.Date;

public class EmployeeAccount {

	private int id;
	private int role;
	// 0 == guest account which will redirect to guest Table for users to register
	// 1 == normal employee
	// 2 == HR
	// 3 == Admin
	private String gender;
	private String name;
	private Date dob;
	private Blob profileImage;
	private String email;
	private int accountStatus;
	
	private double TechnicalTestCompletion;
	private String javaTestId;
	private int javaTestStatus;
	private int javaTestScore;
	
	private String mySQLTestId;
	private int mySQLTestStatus;
	private int mySQLTestScore;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public Blob getProfileImage() {
		return profileImage;
	}
	public void setProfileImage(Blob profileImage) {
		this.profileImage = profileImage;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getAccountStatus() {
		return accountStatus;
	}
	public void setAccountStatus(int accountStatus) {
		this.accountStatus = accountStatus;
	}
	public double getTechnicalTestCompletion() {
		return TechnicalTestCompletion;
	}
	public void setTechnicalTestCompletion(double technicalTestCompletion) {
		TechnicalTestCompletion = technicalTestCompletion;
	}
	public String getJavaTestId() {
		return javaTestId;
	}
	public void setJavaTestId(String javaTestId) {
		this.javaTestId = javaTestId;
	}
	public int getJavaTestStatus() {
		return javaTestStatus;
	}
	public void setJavaTestStatus(int javaTestStatus) {
		this.javaTestStatus = javaTestStatus;
	}
	public int getJavaTestScore() {
		return javaTestScore;
	}
	public void setJavaTestScore(int javaTestScore) {
		this.javaTestScore = javaTestScore;
	}
	public String getMySQLTestId() {
		return mySQLTestId;
	}
	public void setMySQLTestId(String mySQLTestId) {
		this.mySQLTestId = mySQLTestId;
	}
	public int getMySQLTestStatus() {
		return mySQLTestStatus;
	}
	public void setMySQLTestStatus(int mySQLTestStatus) {
		this.mySQLTestStatus = mySQLTestStatus;
	}
	public int getMySQLTestScore() {
		return mySQLTestScore;
	}
	public void setMySQLTestScore(int mySQLTestScore) {
		this.mySQLTestScore = mySQLTestScore;
	}
	
	
}
