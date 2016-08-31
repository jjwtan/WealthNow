package com.fdm.wealthnow.common;

public class SecurityQnAndAns {
	int qnId;
	String ans;
		
	public SecurityQnAndAns() {
	}

	public SecurityQnAndAns(int qnId, String ans) {
		this.qnId = qnId;
		this.ans = ans;
	}

	public int getQnId() {
		return qnId;
	}

	public void setQnId(int qnId) {
		this.qnId = qnId;
	}

	public String getAns() {
		return ans;
	}

	public void setAns(String ans) {
		this.ans = ans;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ans;
	}
	
	
	
	
}
