package com.fdm.wealthnow.common;

import java.util.Date;

public class Order {

	Integer user_id;
	Integer order_id;
	String currency_code;
	String order_type;
	Integer quantity;
	String stock_symbol;
	String price_type;
	Date place_order_date;
	Float limit_price;
	String term;
	Date order_completion_date;
	String status;
	Float closing_price;
	Float open_market_price;

	/*
	 * Order constructor is to be called to create an OpenOrder
	 */
	public Order(Integer user_id, Integer order_id, String currency_code, String order_type, Integer quantity,
			String stock_symbol, String price_type, Date opening_order_date, Float limit_price, String term,
			String status) {
		this.user_id = user_id;
		this.order_id = order_id;
		this.currency_code = currency_code;
		this.order_type = order_type;
		this.quantity = quantity;
		this.stock_symbol = stock_symbol;
		this.price_type = price_type;
		this.place_order_date = opening_order_date;
		this.limit_price = limit_price;
		this.term = term;
		this.status = status;
	}
	/*
	 * Order constructor is to be called to create a ProcessedOrder
	 */
	public Order(Integer user_id, Integer order_id, String currency_code, String order_type, Integer quantity,
			String stock_symbol, String price_type, Date place_order_date, Float limit_price,
			Date order_completion_date, String status, Float closing_price, Float open_market_price) {
		super();
		this.user_id = user_id;
		this.order_id = order_id;
		this.currency_code = currency_code;
		this.order_type = order_type;
		this.quantity = quantity;
		this.stock_symbol = stock_symbol;
		this.price_type = price_type;
		this.place_order_date = place_order_date;
		this.limit_price = limit_price;
		this.order_completion_date = order_completion_date;
		this.status = status;
		this.closing_price = closing_price;
		this.open_market_price = open_market_price;
	}
	

	public Integer getUser_id() {
		return user_id;
	}

	
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Integer getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}

	public String getCurrency_code() {
		return currency_code;
	}

	public void setCurrency_code(String currency_code) {
		this.currency_code = currency_code;
	}

	public String getOrder_type() {
		return order_type;
	}

	public void setOrder_type(String order_type) {
		this.order_type = order_type;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getStock_symbol() {
		return stock_symbol;
	}

	public void setStock_symbol(String stock_symbol) {
		this.stock_symbol = stock_symbol;
	}

	public String getPrice_type() {
		return price_type;
	}

	public void setPrice_type(String price_type) {
		this.price_type = price_type;
	}

	public Date getPlace_order_date() {
		return place_order_date;
	}

	public void setPlace_order_date(Date place_order_date) {
		this.place_order_date = place_order_date;
	}

	public Float getClosing_price() {
		return closing_price;
	}

	public void setClosing_price(Float closing_price) {
		this.closing_price = closing_price;
	}

	public Float getOpen_market_price() {
		return open_market_price;
	}

	public void setOpen_market_price(Float open_market_price) {
		this.open_market_price = open_market_price;
	}

	public Float getLimit_price() {
		return limit_price;
	}

	public void setLimit_price(Float limit_price) {
		this.limit_price = limit_price;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public Date getOrder_completion_date() {
		return order_completion_date;
	}

	public void setOrder_completion_date(Date order_completion_date) {
		this.order_completion_date = order_completion_date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Order [user_id=" + user_id + ", order_id=" + order_id + ", currency_code=" + currency_code
				+ ", order_type=" + order_type + ", quantity=" + quantity + ", stock_symbol=" + stock_symbol
				+ ", price_type=" + price_type + ", opening_order_date=" + place_order_date + ", limit_price="
				+ limit_price + ", term=" + term + ", order_completion_date=" + order_completion_date + ", status="
				+ status + "]";
	}

}
