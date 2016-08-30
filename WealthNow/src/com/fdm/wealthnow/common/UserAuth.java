package com.fdm.wealthnow.common;

public class UserAuth {
	boolean authenticationSuccess = false;

	public UserAuth() {
		
	}
	
	public UserAuth(boolean authenticationStatus) {
		this.authenticationSuccess = authenticationStatus;
	}
	
	
	public void setAuthenticationSuccess(boolean authenticationSuccess) {
		this.authenticationSuccess = authenticationSuccess;
	}


	public boolean isAuthenticationSuccess() {
		// TODO Auto-generated method stub
		return authenticationSuccess;
	}

}
