package com.fdm.wealthnow.service;

import java.util.Date;
import java.util.List;

import com.fdm.wealthnow.common.User;

@SuppressWarnings("unused")
public class UserRegisterService {
	
	User user;
	
	public Integer registerUser(User user){
		return 1;
	}
	
	private boolean validatePassword(User user, String password){
		return true;
	}
	
	private boolean validateBirthday(Date birthday, User user){
		return true;
	}
	
	private boolean validateUserName(String userName, User user){
		return true;
	}
	
	private User createUserObject(String userName, String firstName, 
			String lastName, Date birthday, String email, String address, 
			String phoneNumber, String maidenName, Integer failedLoginCount, 
			Date lastFailedLogin, Integer watchlistId, List<String> questionList, List<String> answerList){
		
		User newUser = new User();
		
		return newUser;
	}
	
	private void setSecurityQuestions(List<String> answers, List<String> questions){
		
	}
}
