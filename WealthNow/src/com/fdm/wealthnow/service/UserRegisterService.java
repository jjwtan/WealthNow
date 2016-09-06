package com.fdm.wealthnow.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.fdm.wealthnow.common.User;
import com.fdm.wealthnow.dao.SecurityQuestionDAO;
import com.fdm.wealthnow.util.DBUtil;

@SuppressWarnings("unused")
public class UserRegisterService extends DBUtil {
	
	User user;
	
	private static final String USER_TABLE = "user1";
	
	public List<String> getAllSecurityQuestions() {
		try {
			Connection connect = getConnection();
			SecurityQuestionDAO sDAO = new SecurityQuestionDAO();
		} catch (Exception e) {
			
		}
		return null;
	}
	
	public UserRegisterService() {
		try {
			Connection connect = getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Integer registerUser(User user){
		return 1;
	}
	
	private boolean validatePassword(User user, String password){
		return true;
	}
	
	private boolean validateBirthday(Date birthday, User user){
		return true;
	}
	
	/**
	 * validate that only one of this username exist, i.e there are not same username
	 * @param userName
	 * @param user
	 * @return
	 */
	private boolean validateUserName(String userName, User user){
		try {
			Connection connect = getConnection();
			PreparedStatement ps = connect.prepareStatement("Select * from " + USER_TABLE + " where user_name = ?");
			ps.setString(1, userName);
			
			ResultSet rs = ps.executeQuery();
			
			return rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
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
