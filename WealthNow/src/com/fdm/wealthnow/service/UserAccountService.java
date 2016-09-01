package com.fdm.wealthnow.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.fdm.wealthnow.common.UserAccount;
import com.fdm.wealthnow.dao.UserAccountDAO;
import com.fdm.wealthnow.util.DBUtil;

public class UserAccountService extends DBUtil {

	public UserAccount getAccountBalance(int userId) {

		Connection connect = null;

		try {
			connect = getConnection();
			UserAccountDAO userAccountDAO = new UserAccountDAO();
			return userAccountDAO.getAccountBalance(connect, userId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (connect != null)
				try {
					connect.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return null;

	}

}
