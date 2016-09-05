package com.fdm.wealthnow.common;

public class UserAuth {
	boolean authenticationSuccess = false;
	User user;
	String errorMsg;
	
	
	public UserAuth() {
		
	}
	public UserAuth(boolean authenticationStatus) {
		this.authenticationSuccess = authenticationStatus;
	}
	public UserAuth(User user, boolean authenticationStatus) {
		this.user = user;
		this.authenticationSuccess = authenticationStatus;
	}
	
	public UserAuth(boolean authenticationSuccess, User user, String errorMsg) {
		this.authenticationSuccess = authenticationSuccess;
		this.user = user;
		this.errorMsg = errorMsg;
	}
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public void setAuthenticationSuccess(boolean authenticationSuccess) {
		this.authenticationSuccess = authenticationSuccess;
	}


	public boolean isAuthenticationSuccess() {
		// TODO Auto-generated method stub
		return authenticationSuccess;
	}

}
