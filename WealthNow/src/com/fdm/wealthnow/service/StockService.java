package com.fdm.wealthnow.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

import org.apache.jasper.tagplugins.jstl.core.Catch;

import com.fdm.wealthnow.common.InfoType;
import com.fdm.wealthnow.common.Stock;

public class StockService {
	static List<String> rawStockList;
	static List<Stock> requestStock;

	public List<Stock> getStocksFromExchange(List<Stock> stocks, InfoType type) {
		this.requestStock = stocks;
		rawStockList  = new ArrayList<>();
		
		String url = generateRequestURL(stocks, type);
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
			sb.append("&f=nabpod1d2c1p2");
			break;
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
				Stock stock = new Stock(requestStock.get(counter).getStockSymbol(), st.nextToken().replace("\"", ""), Float.parseFloat(st.nextToken()), Float.parseFloat(st.nextToken()));
				counter++;
				stockList.add(stock);
			}
			return stockList;
			
		case FULL:
			counter = 0;
			for (String item: stocksFromEx) {
				st = new StringTokenizer(item, ",");
				Stock stock = new Stock(requestStock.get(counter).getStockSymbol(), 
										st.nextToken().replace("\"", ""), 
										Float.parseFloat(st.nextToken()), 
										Float.parseFloat(st.nextToken()),
										Float.parseFloat(st.nextToken()), 
										Float.parseFloat(st.nextToken()),
										getDate(st.nextToken()),
										new Date(),
										st.nextToken().replace("\"", ""),
										st.nextToken().replace("\"", ""));
				counter++;
				stockList.add(stock);
			}
			return stockList;
		default:
			return null;
		}
		
	}

	private Date getDate(String nextToken) {
		DateFormat format = new SimpleDateFormat("dd/mm/yyyy", Locale.ENGLISH);
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
