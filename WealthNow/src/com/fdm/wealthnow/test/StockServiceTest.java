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
	public void testValidateStock() {
		assertFalse(stockService.validateStock("S307"));
		assertTrue(stockService.validateStock("Z74"));
	}
	
	@Test
	public void testValidationOfGet() {
		assertNull(stockService.getStockFromExchange("zzzz1", InfoType.BASIC));
	}
	
	@Test
	public void testBasicURL() {
		String url = stockService.generateRequestURL(stockList, InfoType.BASIC);
		System.out.println(url);
	}
	
	@Test
	public void testBasicStock() {
		stockService = new StockService();
		List<Stock> stocks = stockService.getStocksFromExchange(stockList, InfoType.BASIC);
		for(Stock stock: stocks) {
			System.out.println(stock);
		}
	}
	
	@Test
	public void testFullURL() {
		String url = stockService.generateRequestURL(stockList, InfoType.FULL);
		System.out.println(url);
		showFullRawData();
		
	}
	
	public void showFullRawData() {
		List<Stock> stocks = stockService.getStocksFromExchange(stockList, InfoType.FULL);
		List<String> list = stockService.getRawData();
		for (String item: list) {
			System.out.println(item);
		}
		
		for(Stock stock: stocks) {
			System.out.println(stock.getMktPrice() + " " + stock.getPercentChange());
		}
	}
	
	@Test
	public void testFullStock() {
		stockService = new StockService();
		List<Stock> stocks = stockService.getStocksFromExchange(stockList, InfoType.FULL);
		for(Stock stock: stocks) {
			System.out.println(stock);
		}
	}

}
