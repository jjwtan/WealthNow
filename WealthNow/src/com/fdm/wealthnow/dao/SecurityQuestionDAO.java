package com.fdm.wealthnow.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fdm.wealthnow.common.SecurityQnAndAns;
import com.fdm.wealthnow.util.DBUtil;


public class SecurityQuestionDAO extends DBUtil{
	private static final String USER_TABLE = "user1";
	
	public List<SecurityQnAndAns> getUserQnAndAns(Connection connect, int userId) {
		List<SecurityQnAndAns> qnIdList = new ArrayList<>();
		
		PreparedStatement ps = null;
		try {
			ps = connect.prepareStatement("Select * from SecurityQuestion where user_id = ?");
			ps.setInt(1, userId);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				qnIdList.add(new SecurityQnAndAns(rs.getInt("question_id"), rs.getString("answer")));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return qnIdList;
	}
	
	public String getQuestion(Connection connect, int questionId) {
		PreparedStatement ps = null;
		try {
			ps = connect.prepareStatement("Select * from questionbank where question_id = ?");
			ps.setInt(1, questionId);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				return rs.getString("question");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public List<String> getAllQuestions(Connection connect) {
		ArrayList<String> qnList = new ArrayList<>();
		PreparedStatement ps = null;
		
		try {

			ps = connect.prepareStatement("Select * from QuestionBank");

			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				qnList.add(rs.getString("question"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return qnList;
	}
	
	public int addSecurityAnswer(Connection connect, int userId, SecurityQnAndAns response) {
		PreparedStatement ps = null;
		try {
			int nextId = getSequenceID("security_id_seq");
			ps = connect.prepareStatement("INSERT INTO SecurityQuestion (security_id, user_id, answer , question_id ) VALUES (" + nextId + " , ?, ?, ?)");
			ps.setInt(1, userId);
			ps.setString(2, response.getAns());
			ps.setInt(3, response.getQnId());
			
			ps.executeUpdate();

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 1;
	}
	
}
