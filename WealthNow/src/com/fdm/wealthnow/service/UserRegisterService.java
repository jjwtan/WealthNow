package com.fdm.wealthnow.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.fdm.wealthnow.common.SecurityQnAndAns;
import com.fdm.wealthnow.common.User;
import com.fdm.wealthnow.dao.SecurityQuestionDAO;
import com.fdm.wealthnow.dao.UserAccountDAO;
import com.fdm.wealthnow.dao.UserDAO;
import com.fdm.wealthnow.util.DBUtil;
import com.fdm.wealthnow.util.DatabaseConnectionFactory.ConnectionType;

@SuppressWarnings("unused")
public class UserRegisterService extends DBUtil {
	
	private static final String USER_TABLE = "user1";
	
	public Integer registerUser(User user){
		return 1;
	}
	
	public List<String> getAllSecurityQuestions() {
		Connection connect = null;
		try {
			this.setConnectionType(ConnectionType.LOCAL_CONNECTION);
			connect = getConnection();
			SecurityQuestionDAO sDAO = new SecurityQuestionDAO();
			return sDAO.getAllQuestions(connect);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (connect != null)
				try {
					connect.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		System.out.println("returning null");
		return null;
	}
	
	public Connection registerUser(User user, SecurityQnAndAns sqa, String password, Float balance, boolean toCommit) {
		Connection connect = null;
		try {
			this.setConnectionType(ConnectionType.LOCAL_CONNECTION);
			connect = getConnection();
			connect.setAutoCommit(false);
//			int id = getSequenceID("");
//			user.setUserId(id);
			UserDAO userDAO = new UserDAO();
			System.out.println("going to add");
			userDAO.addUser(connect, user, password);
			System.out.println("added user " + user.getUserId() );
			SecurityQuestionDAO securityQuestionDAO = new SecurityQuestionDAO();
			securityQuestionDAO.addSecurityAnswer(connect, user.getUserId(), sqa);
			
			UserAccountDAO userAccountDAO = new UserAccountDAO();
			userAccountDAO.addUserAccount(connect, user.getUserId(), balance, "SGD");
			
			if(toCommit) {
				connect.commit();
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			try {
				connect.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}finally {
			if (connect != null)
				try {
					connect.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		return connect;
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
	public boolean validateUserName(String userName){
		Connection connect = null;
		try {
			this.setConnectionType(ConnectionType.LOCAL_CONNECTION);
			connect = getConnection();
			PreparedStatement ps = connect.prepareStatement("Select * from " + USER_TABLE + " where user_name = ?");
			ps.setString(1, userName);
			
			ResultSet rs = ps.executeQuery();
			
			return !rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
