package com.fdm.wealthnow.common;

public class UserAuth {
	boolean authenticationSuccess = false;
	User user;
	
	
	public UserAuth() {
		
	}
	public UserAuth(boolean authenticationStatus) {
		this.authenticationSuccess = authenticationStatus;
	}
	public UserAuth(User user, boolean authenticationStatus) {
		this.user = user;
		this.authenticationSuccess = authenticationStatus;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	
	
	public void setAuthenticationSuccess(boolean authenticationSuccess) {
		this.authenticationSuccess = authenticationSuccess;
	}


	public boolean isAuthenticationSuccess() {
		// TODO Auto-generated method stub
		return authenticationSuccess;
	}

}
