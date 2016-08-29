package com.fdm.wealthnow.common;

import java.util.Date;

public class StockHolding {
	Integer stockholding_id;
	Integer user_id;
	Integer order_id;
	String stock_symbol;
	Integer purchase_quantity;
	Integer remaining_quantity;
	Double purchase_price;
	String purchase_date;
	
	
	
	
	public StockHolding(String stock_symbol, Integer remaining_quantity, Double purchase_price) {
		this.stock_symbol = stock_symbol;
		this.remaining_quantity = remaining_quantity;
		this.purchase_price = purchase_price;
	}
	
	
	public StockHolding(Integer stockholding_id, Integer user_id, Integer order_id, String stock_symbol, Integer remaining_quantity, Double purchase_price) {
		this.stockholding_id = stockholding_id;
		this.user_id = user_id;
		this.order_id = order_id;
		this.stock_symbol = stock_symbol;
		this.remaining_quantity = remaining_quantity;
		this.purchase_price = purchase_price;
	}


	public StockHolding(Integer stockholding_id, Integer user_id, Integer order_id, String stock_symbol,
			Integer purchase_quantity, Integer remaining_quantity, Double purchase_price, String purchase_date) {
		this.stockholding_id = stockholding_id;
		this.user_id = user_id;
		this.order_id = order_id;
		this.stock_symbol = stock_symbol;
		this.purchase_quantity = purchase_quantity;
		this.remaining_quantity = remaining_quantity;
		this.purchase_price = purchase_price;
		this.purchase_date = purchase_date;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Integer getStockholding_id() {
		return stockholding_id;
	}
	public void setStockholding_id(Integer stockholding_id) {
		this.stockholding_id = stockholding_id;
	}
	public Integer getOrder_id() {
		return order_id;
	}
	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}
	public String getStock_symbol() {
		return stock_symbol;
	}
	public void setStock_symbol(String stock_symbol) {
		this.stock_symbol = stock_symbol;
	}
	public Integer getPurchase_quantity() {
		return purchase_quantity;
	}
	public void setPurchase_quantity(Integer purchase_quantity) {
		this.purchase_quantity = purchase_quantity;
	}
	public Integer getRemaining_quantity() {
		return remaining_quantity;
	}
	public void setRemaining_quantity(Integer remaining_quantity) {
		this.remaining_quantity = remaining_quantity;
	}
	public Double getPurchase_price() {
		return purchase_price;
	}
	public void setPurchase_price(Double purchase_price) {
		this.purchase_price = purchase_price;
	}
	public String getPurchase_date() {
		return purchase_date;
	}
	public void setPurchase_date(String purchase_date) {
		this.purchase_date = purchase_date;
	}
	
	@Override
	public String toString() {
		return "StockHolding [stockholding_id=" + stockholding_id + ", user_id=" + user_id + ", order_id=" + order_id
				+ ", stock_symbol=" + stock_symbol + ", purchase_quantity=" + purchase_quantity
				+ ", remaining_quantity=" + remaining_quantity + ", purchase_price=" + purchase_price
				+ ", purchase_date=" + purchase_date + "]";
	}


}
