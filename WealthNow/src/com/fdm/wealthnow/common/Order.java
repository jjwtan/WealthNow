package com.fdm.wealthnow.common;

import java.util.Date;

public class Order {

	Integer user_id;
	Integer order_id;
	String currency_code;
	OrderTypeEnum order_type;
	Integer quantity;
	String stock_symbol;
	PriceTypeEnum price_type;
	Date place_order_date;
	Double limit_price;
	TermEnum term;
	Date order_completion_date;
	String status;
	Double closing_price;
	Double total_price_deducted; // price deducted at confirmation page
	Integer old_order_id;

	/*
	 * Order constructor is to be called to create an OpenOrder
	 */
	public Order(Integer user_id, Integer order_id, String currency_code, OrderTypeEnum order_type, Integer quantity,
			String stock_symbol, PriceTypeEnum price_type, Date opening_order_date, Double limit_price, TermEnum term2,
			String status, Integer old_order_id) {
		this.user_id = user_id;
		this.order_id = order_id;
		this.currency_code = currency_code;
		this.order_type = order_type;
		this.quantity = quantity;
		this.stock_symbol = stock_symbol;
		this.price_type = price_type;
		this.place_order_date = opening_order_date;
		this.limit_price = limit_price;
		this.term = term2;
		this.status = status;
		this.old_order_id = old_order_id;
	}
	
	/*
	 * New Order constructor for OpenOrder - now store the total price deducted from confirmation page. 
	 */
	public Order(Integer user_id, Integer order_id, String currency_code, OrderTypeEnum order_type, Integer quantity,
			String stock_symbol, PriceTypeEnum price_type, Date opening_order_date, Double limit_price, TermEnum term2,
			String status, Double total_price_deducted, Integer old_order_id) {
		this.user_id = user_id;
		this.order_id = order_id;
		this.currency_code = currency_code;
		this.order_type = order_type;
		this.quantity = quantity;
		this.stock_symbol = stock_symbol;
		this.price_type = price_type;
		this.place_order_date = opening_order_date;
		this.limit_price = limit_price;
		this.term = term2;
		this.status = status;
		this.total_price_deducted = total_price_deducted;
		this.old_order_id = old_order_id;
	}
	
	/*
	 * Order constructor is to be called to create a ProcessedOrder
	 */
	public Order(Integer user_id, Integer order_id, String currency_code, OrderTypeEnum order_type, Integer quantity,
			String stock_symbol, PriceTypeEnum price_type, Date place_order_date, Double limit_price,
			Date order_completion_date, String status, Double closing_price, Double total_price_deducted,
			Integer old_order_id) {
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
		this.total_price_deducted = total_price_deducted;
		this.old_order_id = old_order_id;
	}
	

	public Integer getOld_order_id() {
		return old_order_id;
	}

	public void setOld_order_id(Integer old_order_id) {
		this.old_order_id = old_order_id;
	}

	public Order(Integer user_id2, Integer order_id2, String currency_code2, OrderTypeEnum order_type2, Integer quantity2,
			String stock_symbol2, PriceTypeEnum price_type2, Date opening_order_date, Double limit_price2, TermEnum term2,
			Double closing_price2, String status2) {
		// TODO Auto-generated constructor stub
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

	public OrderTypeEnum getOrder_type() {
		return order_type;
	}

	public void setOrder_type(OrderTypeEnum order_type) {
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

	public PriceTypeEnum getPrice_type() {
		return price_type;
	}

	public void setPrice_type(PriceTypeEnum price_type) {
		this.price_type = price_type;
	}

	public Date getPlace_order_date() {
		return place_order_date;
	}

	public void setPlace_order_date(Date place_order_date) {
		this.place_order_date = place_order_date;
	}

	public Double getClosing_price() {
		return closing_price;
	}

	public void setClosing_price(Double closing_price) {
		this.closing_price = closing_price;
	}

	public Double getTotal_price_deducted() {
		return total_price_deducted;
	}
	public void setTotal_price_deducted(Double total_price_deducted) {
		this.total_price_deducted = total_price_deducted;
	}
	public Double getLimit_price() {
		return limit_price;
	}

	public void setLimit_price(Double limit_price) {
		this.limit_price = limit_price;
	}

	public TermEnum getTerm() {
		return term;
	}

	public void setTerm(TermEnum term) {
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
