package com.fdm.wealthnow.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fdm.wealthnow.common.SecurityQnAndAns;
import com.fdm.wealthnow.dao.SecurityQuestionDAO;
import com.fdm.wealthnow.util.DatabaseConnectionFactory.ConnectionType;

public class SecurityQuestionTest {
	static Connection connect;

	@Before
	public void setUp() throws Exception {
		SecurityQuestionDAO.setConnectionType(ConnectionType.LOCAL_CONNECTION);
		connect = SecurityQuestionDAO.getConnection();
		connect.setAutoCommit(false);
	}

	@After
	public void tearDown() throws Exception {
		try {
			connect.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			connect.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetAllQuestion() {
		System.out.println("All security bank questions");
		SecurityQuestionDAO sqDAO = new SecurityQuestionDAO();
		List<String> questions = sqDAO.getAllQuestions(connect);
		for(String question: questions) {
			System.out.println(question);
		}
		
	}
	
	@Test
	public void testGetAllUserQuestion() {
		int userId = 2;
		System.out.println("User "+userId+ " Question and answers");
		SecurityQuestionDAO sqDao = new SecurityQuestionDAO();
		List<SecurityQnAndAns> responses = sqDao.getUserQnAndAns(connect, userId);
		for(SecurityQnAndAns item : responses) {
			String question  = sqDao.getQuestion(connect, item.getQnId());
			System.out.println(question + " " + item);
		}
		
	}
	
	@Test
	public void testInsertQuestionAns() {
		SecurityQuestionDAO sqDao = new SecurityQuestionDAO();
		sqDao.addSecurityAnswer(connect, 3, new SecurityQnAndAns(2, "whuttttttttt"));
		
		List<SecurityQnAndAns> responses = sqDao.getUserQnAndAns(connect, 3);
		
		for(SecurityQnAndAns ans : responses) {
			String question  = sqDao.getQuestion(connect, ans.getQnId());
			System.out.println(question + " " + ans);
		}
		
	}

}
