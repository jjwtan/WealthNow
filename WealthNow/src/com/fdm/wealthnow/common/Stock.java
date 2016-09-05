package com.fdm.wealthnow.common;

import java.util.Date;

public class Stock {
	String stockSymbol, company;
	Float mktPrice, change, open, close;
	Float ask, bid;
	String percentChange;
	String daysValueChange;
	Date lastTradeDate, tradeDate, tradeTime, modifiedDate;
	String bidSize, askSize;
	float dayHigh, dayLow; 
	
	public Stock() {
		
	}
	
	public Stock(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}
	
	// BASIC
	public Stock(String stockSymbol, String company, float ask, float bid) {
		this.stockSymbol = stockSymbol;
		this.company = company;
		this.mktPrice = (ask+bid)/2;
		this.ask = ask;
		this.bid = bid;
	}
	
	// FULL 
	public Stock(String stockSymbol, String company, float ask, float bid, float open, float close, 
				 Date lastTradeDate, Date tradeDate, String daysValueChange, String percentChange) {
		this(stockSymbol, company, ask, bid);
		this.open = open;
		this.close = close;
		this.lastTradeDate = lastTradeDate;
		this.tradeDate = tradeDate;
		this.daysValueChange = daysValueChange;
		this.percentChange = percentChange;
	}
	
	// WATCHLIST n a b o p d1 DD c1 p2 b6a5hg
	public Stock(String stockSymbol, String company, float ask, float bid, float open, float close, 
			 Date lastTradeDate, Date tradeDate, String daysValueChange, String percentChange,
			 Integer bidSize, Integer askSize, float dayHigh, float dayLow, Date modifiedDate) {
		this(stockSymbol, company, ask, bid, open, close, lastTradeDate, tradeDate, daysValueChange, percentChange);
		this.bidSize = setSize(bidSize);
		this.askSize = setSize(askSize);
		this.dayHigh = dayHigh;
		this.dayLow = dayLow;
		this.modifiedDate = modifiedDate;
		
	}
	
	

	private String setSize(Integer size) {
		if(size == null) {
			return "-";
		}else {
			return size.toString();
		}
	}


	public String getBidSize() {
		return bidSize;
	}

	public void setBidSize(String bidSize) {
		this.bidSize = bidSize;
	}

	public String getAskSize() {
		return askSize;
	}

	public void setAskSize(String askSize) {
		this.askSize = askSize;
	}

	public void setAsk(Float ask) {
		this.ask = ask;
	}

	public void setBid(Float bid) {
		this.bid = bid;
	}

	public float getDayHigh() {
		return dayHigh;
	}

	public void setDayHigh(float dayHigh) {
		this.dayHigh = dayHigh;
	}

	public float getDayLow() {
		return dayLow;
	}

	public void setDayLow(float dayLow) {
		this.dayLow = dayLow;
	}

	public float getAsk() {
		return ask;
	}

	public void setAsk(float ask) {
		this.ask = ask;
	}

	public float getBid() {
		return bid;
	}

	public void setBid(float bid) {
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
