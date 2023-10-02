package com.takeo.service.impl;

import com.takeo.model.*;

import com.takeo.payloads.PortfolioDTO;
import com.takeo.repo.PortfolioRepo;
import com.takeo.repo.TradAccRepo;
import com.takeo.repo.UserRepo;
import com.takeo.service.PortfolioService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PortfolioServiceImpl implements PortfolioService {

    @Autowired
    PortfolioRepo portfolioRepo;

    @Autowired
    UserRepo userRepo;
    @Autowired
    TradAccRepo tradAccRepo;

    @Override
    public String createPortfolio(int userId, PortfolioDTO portfolioDTO) {

        Optional<User> usr =  userRepo.findById(userId);
        if(usr.isPresent()){
            User user = usr.get();
            Portfolio portfolio = new Portfolio();
            BeanUtils.copyProperties(portfolioDTO,portfolio);
            List<Portfolio> portfolioList = new ArrayList<>();
            portfolioList.add(portfolio);
            user.setPortfolios(portfolioList);
            return "Portfolio Created";
        }

        return null;
    }

    @Override
    public String updatePortfolio(int userId) {
        return null;
    }

    @Override
    public List<Stock> viewAllStock(int userId) {
        Optional<User> user = userRepo.findById(userId);
        Optional<TradingAccount> account =  tradAccRepo.findByUserId(userId);
        if(user.isPresent() && account.isPresent()){
          TradingAccount tradingAccount = account.get();

             return tradingAccount.getStockList();

        }

        return null;
    }

    @Override
    public List<Transaction> viewTransactions(int userId) {
        Optional<User> usr = userRepo.findById(userId);
        Optional<TradingAccount> acc = tradAccRepo.findByUserId(userId);
        if(usr.isPresent() && acc.isPresent()){
          TradingAccount account = acc.get();
          return account.getTransactionList();

        }
        return null;
    }
}
