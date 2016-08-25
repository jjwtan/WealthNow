package com.fdm.wealthnow.dao;

import com.fdm.wealthnow.common.InfoType;
import com.fdm.wealthnow.common.User;

public class UserDAO {
	
	public User getUser(int userId, InfoType type) {
		
		return null;
	}
	
	public int addUser(User user) {
		
		return 0;
	}
	
	public boolean updateUser(User user) {
		
		return true;
	}
	
	public boolean softDeleteUser(int userId) {
		return false;
		
	}

}
