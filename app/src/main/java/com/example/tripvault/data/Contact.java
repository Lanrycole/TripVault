package com.example.tripvault.data;

public class Contact {
	private int userId;
	private String userName;
	private String password;
	private String emailAddress;
	private String city;


	public Contact(int userId,String userName,String password, String emailAddress, String city) {
		this.userId = userId;
		this.userName = userName;
		this.password=password;
		this.emailAddress = emailAddress;
		this.city=city;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return password;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Contact() {}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}




}
