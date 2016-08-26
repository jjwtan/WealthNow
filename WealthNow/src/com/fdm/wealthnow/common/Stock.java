package com.fdm.wealthnow.common;

import java.util.Date;

public class Stock {
	String stockSymbol, company;
	Float ask, bid, change;
	String percentChange;
	Float daysValueChange;
	Date lastTradeDate, tradeDate, tradeTime, modifiedDate;
	
	public Stock() {
		
	}
	
	public Stock(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}
	public Stock(String stockSymbol, String company, float ask, float bid) {
		this.stockSymbol = stockSymbol;
		this.company = company;
		this.ask = ask;
		this.bid = bid;
	}

	public String getStockSymbol() {
		return stockSymbol;
	}
	public void setStockSymbol(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public Float getAsk() {
		return ask;
	}
	public void setAsk(Float ask) {
		this.ask = ask;
	}
	public Float getBid() {
		return bid;
	}
	public void setBid(Float bid) {
		this.bid = bid;
	}
	public Float getChange() {
		return change;
	}
	public void setChange(Float change) {
		this.change = change;
	}
	public String getPercentChange() {
		return percentChange;
	}
	public void setPercentChange(String percentChange) {
		this.percentChange = percentChange;
	}
	public Float getDaysValueChange() {
		return daysValueChange;
	}
	public void setDaysValueChange(Float daysValueChange) {
		this.daysValueChange = daysValueChange;
	}
	public Date getLastTradeDate() {
		return lastTradeDate;
	}
	public void setLastTradeDate(Date lastTradeDate) {
		this.lastTradeDate = lastTradeDate;
	}
	public Date getTradeDate() {
		return tradeDate;
	}
	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
	}
	public Date getTradeTime() {
		return tradeTime;
	}
	public void setTradeTime(Date tradeTime) {
		this.tradeTime = tradeTime;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		float price = ask+bid/2.0f;
		return "Stock:" + stockSymbol + "\tCompany: " + company + " $" + price;
	}

}
