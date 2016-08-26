package com.fdm.wealthnow.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.jasper.tagplugins.jstl.core.Catch;

import com.fdm.wealthnow.common.Stock;

public class StockService {
	static List<String> rawStockList;
	static List<Stock> requestStock;

	public List<Stock> getStocksFromExchange(List<Stock> stocks) {
		this.requestStock = stocks;
		rawStockList  = new ArrayList<>();
		
		String url = generateRequestURL(stocks);
		getFromExhange(url);
		
		return createListStockObj(rawStockList);

	}

	public String generateRequestURL(List<Stock> stocks) {
		StringBuilder sb = new StringBuilder("http://finance.yahoo.com/d/quotes.csv?s=");
		for(Stock stock : stocks) {
			sb.append(stock.getStockSymbol()).append(".SI").append("+");
		}
		sb.deleteCharAt(sb.length()-1);
		sb.append("&f=nab");
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
	
	
	private List<Stock> createListStockObj(List<String> stocksFromEx) {
		StringTokenizer st;
		List<Stock> stockList = new ArrayList<>();
		int counter = 0;
		for (String item: stocksFromEx) {
			st = new StringTokenizer(item, ",");
			Stock stock = new Stock(requestStock.get(counter).getStockSymbol(), st.nextToken().replace("\"", ""), Float.parseFloat(st.nextToken()), Float.parseFloat(st.nextToken()));
			counter++;
			stockList.add(stock);
		}
		return stockList;
	}

	// printStockList();

}
