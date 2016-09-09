package com.fdm.wealthnow.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.fdm.wealthnow.common.InfoType;
import com.fdm.wealthnow.common.Stock;
import com.fdm.wealthnow.service.StockService;

public class StockServiceTest {
	
	static StockService stockService = new StockService();
	static List<Stock> stockList = new ArrayList<>();
	static {
		stockList.add(new Stock("S07"));
		stockList.add(new Stock("Q5T"));
		stockList.add(new Stock("Z74"));
	}

	@Test
	public void testCache(){
		//Stock stock = stockService.getStockFromExchange("Z74", InfoType.FULL);
		//stockService.populateDBCache(stock);
		//System.out.println(stock);
		stockService.populateMapCache();
		System.out.println(stockService.getCache().size());
	}
	
//	@Test
//	public void testFullURL() {
//		String url = stockService.generateRequestURL(stockList, InfoType.WATCHLIST);
//		System.out.println(url);
//		showFullRawData();
//		
//	}
//	
//	public void showFullRawData() {
//		List<Stock> stocks = stockService.getStocksFromExchange(stockList, InfoType.FULL);
//		List<String> list = stockService.getRawData();
//		for (String item: list) {
//			System.out.println(item);
//		}
//		
//		for(Stock stock: stocks) {
//			System.out.println(stock.getMktPrice() + " " + stock.getPercentChange());
//		}
//	}
//	
//	@Test
//	public void testFullStock() {
//		stockService = new StockService();
//		List<Stock> stocks = stockService.getStocksFromExchange(stockList, InfoType.FULL);
//		for(Stock stock: stocks) {
//			System.out.println(stock);
//		}
//	}
//	
//	@Test
//	public void testGetUsingSymbol() {
//		List<String> stockList = new ArrayList<>();
//		stockList.add("BTP");
//		stockList.add("Z74");
//		
//		stockService = new StockService();
//		List<Stock> stocks = stockService.getStocksFromExchangeString(stockList, InfoType.FULL);
//		for(Stock stock: stocks) {
//			System.out.println(stock);
//		}
//	}
//	
//	@Test
//	public void testWatchlistStock() {
//		stockService = new StockService();
//		List<Stock> stocks = stockService.getStocksFromExchange(stockList, InfoType.WATCHLIST);
//		for(Stock stock: stocks) {
//			System.out.println(stock);
//		}
//	}

}
