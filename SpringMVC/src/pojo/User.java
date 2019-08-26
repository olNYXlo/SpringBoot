package pojo;

import java.util.LinkedHashMap;

public class User {

	private String firstName;
	private String lastName;
	private String address;
	private String ID;
	private String country;
	private LinkedHashMap<String,String> languageOptions;
	private String favouriteLanguage;
	
	
	public User() {
		languageOptions = new LinkedHashMap<>();
		languageOptions.put("English", "English");
		languageOptions.put("Chinese", "Chinese");
		languageOptions.put("Japanese", "Japanese");
		languageOptions.put("Korean", "Korean");
		languageOptions.put("Thai", "Thai");
	}
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getFavouriteLanguage() {
		return favouriteLanguage;
	}
	public void setFavouriteLanguage(String favouriteLanguage) {
		this.favouriteLanguage = favouriteLanguage;
	}


	public LinkedHashMap<String, String> getLanguageOptions() {
		return languageOptions;
	}
	
	
	
	
	
	
}
