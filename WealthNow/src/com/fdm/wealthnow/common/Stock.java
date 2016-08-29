package com.fdm.wealthnow.common;

import java.util.Date;

public class Stock {
	String stockSymbol, company;
	Float mktPrice, change, open, close;
	String percentChange;
	String daysValueChange;
	Date lastTradeDate, tradeDate, tradeTime, modifiedDate;
	
	public Stock() {
		
	}
	
	public Stock(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}
	public Stock(String stockSymbol, String company, float ask, float bid) {
		this.stockSymbol = stockSymbol;
		this.company = company;
		this.mktPrice = (ask+bid)/2;
	}
	public Stock(String stockSymbol, String company, float ask, float bid, float open, float close, 
				 Date lastTradeDate, Date tradeDate, String daysValueChange, String percentChange) {
		this.stockSymbol = stockSymbol;
		this.company = company;
		this.mktPrice = (ask+bid)/2;
		this.open = open;
		this.close = close;
		this.lastTradeDate = lastTradeDate;
		this.tradeDate = tradeDate;
		this.daysValueChange = daysValueChange;
		this.percentChange = percentChange;
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
	
	public Float getMktPrice() {
		return mktPrice;
	}

	public void setMktPrice(Float mktPrice) {
		this.mktPrice = mktPrice;
	}

	public Float getOpen() {
		return open;
	}

	public void setOpen(Float open) {
		this.open = open;
	}

	public Float getClose() {
		return close;
	}

	public void setClose(Float close) {
		this.close = close;
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
	public String getDaysValueChange() {
		return daysValueChange;
	}
	public void setDaysValueChange(String daysValueChange) {
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
		return "Stock:" + stockSymbol + "\tCompany: " + company + " $" + mktPrice;
	}

}
