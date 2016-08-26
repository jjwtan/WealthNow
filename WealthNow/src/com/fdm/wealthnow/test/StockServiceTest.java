package com.fdm.wealthnow.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

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
	public void testURL() {
		String url = stockService.generateRequestURL(stockList);
		System.out.println(url);
	}
	
	@Test
	public void testStock() {
		stockService = new StockService();
		List<Stock> stocks = stockService.getStocksFromExchange(stockList);
		for(Stock stock: stocks) {
			System.out.println(stock);
		}
	}

}
