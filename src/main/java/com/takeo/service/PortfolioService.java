package com.takeo.service;

import com.takeo.model.Stock;
import com.takeo.model.Transaction;
import com.takeo.payloads.PortfolioDTO;

import java.util.List;

public interface PortfolioService {

    public String createPortfolio(int userId, PortfolioDTO portfolioDTO);
    public String updatePortfolio(int userId);

    public List<Stock> viewAllStock(int userId);

    public List<Transaction> viewTransactions(int userId);

}
