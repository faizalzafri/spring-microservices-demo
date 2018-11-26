package com.example.projects.stockdbservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.projects.stockdbservice.model.Quote;

public interface QuoteRepository extends JpaRepository<Quote, Long> {

	List<Quote> findByUsername(String username);

}
