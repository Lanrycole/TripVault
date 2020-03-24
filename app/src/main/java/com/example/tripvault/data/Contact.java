package com.example.tripvault.data;

public class Contact {
	private int userId;
	private String userName;
	private String password;
	private String emailAddress;
	private String city;
	private String phone_num;

	public Contact() {}

	public String getPhone_num() {
		return phone_num;
	}

	public void setPhone_num(String phone_num) {
		this.phone_num = phone_num;
	}

	public Contact(int userId, String userName,String password,String emailAddress,String city,String phone_num) {
		this.userId = userId;
		this.userName = userName;
		this.password=password;
		this.emailAddress = emailAddress;
		this.city=city;
		this.phone_num = phone_num;
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


	@Override
	public String toString() {
		return "Contact{" +
				"userId=" + userId +
				", userName='" + userName + '\'' +
				", password='" + password + '\'' +
				", emailAddress='" + emailAddress + '\'' +
				", city='" + city + '\'' +
				", phone_num='" + phone_num + '\'' +
				'}';
	}
}
