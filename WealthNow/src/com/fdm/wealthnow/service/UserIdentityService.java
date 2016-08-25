package com.fdm.wealthnow.service;

import java.util.List;

import com.fdm.wealthnow.common.User;

@SuppressWarnings("unused")
public class UserIdentityService {
	
	public boolean validateUserIdentity(User user){
		return true;
	}
	
	private boolean validateMaidenName(User user, String maidenName){
		return true;
	}
	
	private boolean validateSecurityQuestions(List<String> answers, User user){
		return true;
	}
	
	private boolean validateEmail(String email, User user){
		return true;
	}
	
	private boolean validateUserName(String userName, User user){
		return true;
	}

}
