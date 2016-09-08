package com.fdm.wealthnow.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;


import com.fdm.wealthnow.common.InfoType;
import com.fdm.wealthnow.common.Stock;
import com.fdm.wealthnow.util.DBUtil;

public class StockService extends DBUtil{
	HashMap<String, Stock> StockCache = new HashMap<>();
	static List<String> rawStockList;
	static List<Stock> requestStock;

	
	public StockService() {
		//populateCache();
	}

	private void populateCache() {
		try {
			Connection connect = getConnection();
			PreparedStatement ps = connect.prepareStatement("select * from stockcache");
			ResultSet rs = ps.executeQuery();
			SimpleDateFormat sdf = new SimpleDateFormat("");
			Date lastTradeDate = sdf.parse(rs.getString("last_trade_date"));
			Date tradeDate = sdf.parse(rs.getString("trade_date"));
			while(rs.next()) {
				Stock stock = new Stock(rs.getString("stock_symbol"), rs.getString("company"), 
										rs.getFloat("ask"), rs.getFloat("bid"), rs.getFloat("opening"),
										rs.getFloat("closing"), lastTradeDate, tradeDate, 
										rs.getString("days_change_value"), rs.getString("percent_change"));
				StockCache.put(rs.getString("stock_symbol"), stock);
			}
		} catch (Exception e) {
			
		}
		
	}

	public boolean validateStock(String stockSymbol) {
		if(StockCache.containsKey(stockSymbol)) {
			return true;
		}
		if(stockSymbol==null || stockSymbol.equals("")) {return false;}
		Stock stock = getStockFromExchange(stockSymbol, InfoType.BASIC);
		return (stock==null) ? false : true ;
	}

	public Stock getStockFromExchange(String stockSymbol, InfoType type) {
		if(StockCache.containsKey(stockSymbol)) {
			return createStock(stockSymbol);
		}
		if(stockSymbol==null || stockSymbol.equals("")) {return null;}
		List<Stock> wrapper = new ArrayList<>();
		this.requestStock = wrapper;
		rawStockList = new ArrayList<>();
		
		wrapper.add(new Stock(stockSymbol));
		String url = generateRequestURL(wrapper, type);
		getFromExhange(url);
		
		
		Stock result = null;
		if (createListStockObj(rawStockList, type) != null){
			result = createListStockObj(rawStockList, type).get(0);
		}
		if (result.getCompany().equals("N/A")) {
			result = null;
		}
		
		return result;
	}
	
	private Stock createStock(String stockSymbol) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Stock> getStocksFromExchange(List<Stock> stocks, InfoType type) {
		this.requestStock = stocks;
		rawStockList  = new ArrayList<>();
		if(stocks==null) {return null;}
		String url = generateRequestURL(stocks, type);
		getFromExhange(url);
		
		return createListStockObj(rawStockList, type);

	}
	
	public List<Stock> getStocksFromExchangeString(List<String> stocks, InfoType type) {
		rawStockList  = new ArrayList<>();
		requestStock = new ArrayList<>();
		if(stocks==null) {return null;}
		for(String stock_symbol: stocks) {
			requestStock.add(new Stock(stock_symbol));
		}
		
		String url = generateRequestURL(requestStock, type);
		getFromExhange(url);
		
		return createListStockObj(rawStockList, type);

	}


	public String generateRequestURL(List<Stock> stocks, InfoType type) {
		StringBuilder sb = new StringBuilder("http://finance.yahoo.com/d/quotes.csv?s=");
		for(Stock stock : stocks) {
			sb.append(stock.getStockSymbol()).append(".SI").append("+");
		}
		sb.deleteCharAt(sb.length()-1);
		
		switch (type) {
		case BASIC:
			sb.append("&f=nab");
			break;
		case FULL:
			sb.append("&f=nabopd1c1p2");
			break;
		case WATCHLIST:
			sb.append("&f=nabopd1c1p2b6a5hg");
		default:
			break;
		}
		
		return sb.toString();
	}

	private void getFromExhange(String url) {
		try {
			InputStream input = new URL(url).openStream();
			Reader reader = new InputStreamReader(input, "UTF-8");
			StringBuilder sb = new StringBuilder();

			int data;
			do {
				data = reader.read();
				if (data == 10) { // if it is a new line
					rawStockList.add(sb.toString());
					sb.setLength(0);
					continue;
				}
				char dataChar = (char) data;
				sb.append(dataChar);
			} while (data != -1);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public List<String> getRawData() {
		return rawStockList;
	}
	
	
	private List<Stock> createListStockObj(List<String> stocksFromEx, InfoType type) {
		StringTokenizer st;
		List<Stock> stockList = new ArrayList<>();
		int counter = 0;
		
		switch (type) {
		case BASIC:
			counter = 0;
			for (String item: stocksFromEx) {
				st = new StringTokenizer(item, ",");
				if(st.countTokens()< 3) {
					return null;
				}
				String company = st.nextToken().replace("\"", "");
				if(!company.equals("N/A")){
					Stock stock = new Stock(requestStock.get(counter).getStockSymbol(), company, Float.parseFloat(st.nextToken()), Float.parseFloat(st.nextToken()));
					stockList.add(stock);
				} else {
					Stock stock = new Stock(requestStock.get(counter).getStockSymbol(), "N/A", 0, 0);
					stockList.add(stock);
				}
				counter++;
			}
			return stockList;
			
		case FULL:
			counter = 0;
			for (String item: stocksFromEx) {
				st = new StringTokenizer(item, ",");
				if(st.countTokens()<8) {
					return null;
				}
				Stock stock = new Stock(requestStock.get(counter).getStockSymbol(), 
										st.nextToken().replace("\"", ""), 
										getFloatAmount(st.nextToken()), 
										getFloatAmount(st.nextToken()),
										getFloatAmount(st.nextToken()), 
										getFloatAmount(st.nextToken()),
										getDate(st.nextToken().replace("\"", "")),
										new Date(),
										st.nextToken(),
										st.nextToken().replace("\"", ""));
				counter++;
				stockList.add(stock);
			}
			return stockList;
		case WATCHLIST:
			counter = 0;
			for (String item: stocksFromEx) {
				st = new StringTokenizer(item, ",");
				if(st.countTokens() < 12) {
					return null;
				}
				Stock stock = new Stock(requestStock.get(counter).getStockSymbol(), 
										st.nextToken().replace("\"", ""), 
										getFloatAmount(st.nextToken()), 
										getFloatAmount(st.nextToken()),
										getFloatAmount(st.nextToken()), 
										getFloatAmount(st.nextToken()),
										getDate(st.nextToken().replace("\"", "")),
										new Date(),
										st.nextToken(),
										st.nextToken().replace("\"", ""),
										getAmount(st.nextToken()),
										getAmount(st.nextToken()),
										getFloatAmount(st.nextToken()),
										getFloatAmount(st.nextToken()),
										new Date());
				counter++;
				stockList.add(stock);
			}
			return stockList;
		default:
			return null;
		}
		
	}

	private Integer getAmount(String nextToken) {
		if(nextToken.equals("N/A")) {
			return null;
		}
		else return Integer.parseInt(nextToken);
	}
	
	private Float getFloatAmount(String nextToken) {
		if(nextToken.equals("N/A")) {
			return new Float(0);
		}
		else return Float.parseFloat(nextToken);
	}

	private Date getDate(String nextToken) {
		if(nextToken.equals("N/A")){
			return new Date();
		}
		DateFormat format = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
		Date date = null;
		try {
			date = format.parse(nextToken);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

	// printStockList();

}
