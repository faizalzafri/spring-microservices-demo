package com.example.projects.stockdbservice.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.projects.stockdbservice.model.Quote;
import com.example.projects.stockdbservice.model.Quotes;
import com.example.projects.stockdbservice.repository.QuoteRepository;

@RestController
@RequestMapping("/rest/db")
public class DbServiceController {

	@Autowired
	private QuoteRepository quoteRepository;

	@GetMapping("/{username}")
	public List<String> getQuotes(@PathVariable("username") String username) {

		return getQuotesByUsername(username);
	}

	private List<String> getQuotesByUsername(String username) {
		return quoteRepository.findByUsername(username)
				.stream()
				.map(Quote::getQuote)
				 .collect(Collectors.toList());
	}

	@PostMapping("/add")
	public List<String> addQuotes(@RequestBody Quotes quotes) {

		quotes.getQuotes()
			.stream()
			.forEach(quote -> quoteRepository.save(new Quote(quotes.getUsername(), quote)));
		
		return getQuotesByUsername(quotes.getUsername());
	}
	
	 @PostMapping("/delete/{username}")
	    public List<String> delete(@PathVariable("username") final String username) {

	        List<Quote> quotes = quoteRepository.findByUsername(username);
	        quoteRepository.deleteAll(quotes);

	        return getQuotesByUsername(username);
	    }
}
