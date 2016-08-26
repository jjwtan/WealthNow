package com.fdm.wealthnow.common;

import java.util.Date;

public class Watchlist {
	int watchlistId;
	String watchlistName, visibility;
	//int userId;
	//List<Stock> stocks;
	Date dateCreated, dateLastEdited;
	
	public Watchlist(){
		
	}
	
	public Watchlist(int watchlistId, String watchlistName, String visibility, Date dateCreated, Date dateLastEdited) {
		super();
		this.watchlistId = watchlistId;
		this.watchlistName = watchlistName;
		this.visibility = visibility;
		this.dateCreated = dateCreated;
		this.dateLastEdited = dateLastEdited;
	}
	
	public int getWatchlistId() {
		return watchlistId;
	}
	public void setWatchlistId(int watchlistId) {
		this.watchlistId = watchlistId;
	}
	public String getWatchlistName() {
		return watchlistName;
	}
	public void setWatchlistName(String watchlistName) {
		this.watchlistName = watchlistName;
	}
	public String getVisibility() {
		return visibility;
	}
	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}
	
	/*
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId; 
	}
	public List<Stock> getStocks() {
		return stocks;
	}
	public void setStocks(List<Stock> stocks) {
		this.stocks = stocks;
	}
	*/
	
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public Date getDateLastEdited() {
		return dateLastEdited;
	}
	public void setDateLastEdited(Date dateLastEdited) {
		this.dateLastEdited = dateLastEdited;
	}
	
	
}
