package com.fdm.wealthnow.common;

public class UserAccount {
	int userId;
	Double balance;
	String currency;
	
	public UserAccount(int userId, Double balance, String currency) {
		this.userId = userId;
		this.balance = balance;
		this.currency = currency;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	
	
	
}
