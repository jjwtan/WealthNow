package com.fdm.wealthnow.common;

import java.util.Date;
import java.util.List;

public class Watchlist {
	int watchlistId;
	String watchlistName, visibility;
	int userId;
	List<Stock> stocks;
	Date dateCreated, dateLastEdited;
	
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
