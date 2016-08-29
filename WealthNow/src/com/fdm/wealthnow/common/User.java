package com.fdm.wealthnow.common;

import java.util.Date;
import java.util.List;

public class User {
	int userId;
	String username;
	String firstName;
	String lastName;
	Date birthday;
	String email;
	String address;
	String phoneNumber;
	String maidenName;
	int failedLoginCount;
	List<String> questionList;
	List<String> answerList;
	
	public User() {
		
	}
	
	
	public User(int userId, String username, String firstName, String lastName) {
		this.userId = userId;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	
	public User(int userId, String username, String firstName, String lastName, Date birthday, String email,
			String phoneNumber, String address) {
		this.userId = userId;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthday = birthday;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
	}


	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getMaidenName() {
		return maidenName;
	}
	public void setMaidenName(String maidenName) {
		this.maidenName = maidenName;
	}
	public int getFailedLoginCount() {
		return failedLoginCount;
	}
	public void setFailedLoginCount(int failedLoginCount) {
		this.failedLoginCount = failedLoginCount;
	}

	public List<String> getQuestionList() {
		return questionList;
	}
	public void setQuestionList(List<String> questionList) {
		this.questionList = questionList;
	}
	public List<String> getAnswerList() {
		return answerList;
	}
	public void setAnswerList(List<String> answerList) {
		this.answerList = answerList;
	}
	
	
	
	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return firstName + " " + lastName;
	}
	
	
}
