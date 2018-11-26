package com.example.projects.stockservice.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.projects.stockservice.model.Quote;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

@RestController
@RequestMapping("/rest/stock")
public class StockResourceController {

	@Autowired
	RestTemplate restTemplate;

	@GetMapping("/{username}")
	public List<Quote> getStock(@PathVariable("username") final String username) {

		ResponseEntity<List<String>> quoteResponse = restTemplate.exchange("http://db-service/rest/db/" + username,
				HttpMethod.GET, null, new ParameterizedTypeReference<List<String>>() {
				});

		List<String> quotes = quoteResponse.getBody();
		return quotes.stream().map(quote -> {
			//Stock stock = getStockPrice(quote);
			return new Quote(quote, new BigDecimal(500));
		}).collect(Collectors.toList());
		
		
	}

	private Stock getStockPrice(String quote) {
		try {
			return YahooFinance.get(quote);
		} catch (IOException e) {
			e.printStackTrace();
			return new Stock(quote);
		}
	}
}
