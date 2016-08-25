package com.fdm.wealthnow.common;

import java.util.Date;
import java.util.List;

public class User {
	int userId;
	String username;
	String firstName, lastName;
	Date birthday;
	String email;
	String phoneNumber;
	String maidenName;
	int failedLoginCount;
	int watchlistId;
	List<String> questionList;
	List<String> answerList;
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
	public int getWatchlistId() {
		return watchlistId;
	}
	public void setWatchlistId(int watchlistId) {
		this.watchlistId = watchlistId;
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
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return firstName;
	}
	
	
}
