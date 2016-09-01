package com.fdm.wealthnow.service;

import java.sql.Connection;
import java.util.Date;

import com.fdm.wealthnow.common.User;
import com.fdm.wealthnow.common.UserAuth;
import com.fdm.wealthnow.dao.AuthDAO;
import com.fdm.wealthnow.util.DBUtil;

public class UserService extends DBUtil{

	
	public static UserAuth userLogin(String username, String password){
		try {
			Connection connect = getConnection();
			return AuthDAO.authenticate(connect, username, password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void updateUserInfo(User user, String fieldToChange, String newData){
		
	}
	
	public void inactivityLogout(User user, Date lastActive){
		
	}
	
	public void exceedPasswordAttemptsLogout(User user, Integer attempts){
		
	}
}
