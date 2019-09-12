package pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="----")
public class GuestAccounts {

	@Id
	@Column(name="------")
	private int guestId;
	// primary key generated in DB table
	@Column(name="------")
	private String nric;
	@Column(name="------")
	private String name;
	@Column(name="------")
	private String email;
	@Column(name="------")
	private String mobileNo;
	@Column(name="------")
	private String educationLevel;
	@Column(name="------")
	private String gpa;
	@Column(name="------")
	private int graduationYear;
	
	public int getGuestId() {
		return guestId;
	}
	public void setGuestId(int guestID) {
		this.guestId = guestID;
	}
	public String getNric() {
		return nric;
	}
	public void setNric(String nric) {
		this.nric = nric;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getEducationLevel() {
		return educationLevel;
	}
	public void setEducationLevel(String educationLevel) {
		this.educationLevel = educationLevel;
	}
	public String getGpa() {
		return gpa;
	}
	public void setGpa(String gpa) {
		this.gpa = gpa;
	}
	public int getGraduationYear() {
		return graduationYear;
	}
	public void setGraduationYear(int graduationYear) {
		this.graduationYear = graduationYear;
	}
	
	
	
}
