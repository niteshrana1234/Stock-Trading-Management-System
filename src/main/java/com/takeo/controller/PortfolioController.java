package com.takeo.controller;

import com.takeo.model.Stock;
import com.takeo.model.Transaction;
import com.takeo.service.impl.PortfolioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("portfolio")
public class PortfolioController {

    @Autowired
    PortfolioServiceImpl portfolioService;

    @GetMapping("/view-stock/{id}")
    public ResponseEntity<List<Stock>> listStocks(@PathVariable("id") int id){

     List<Stock> list = portfolioService.viewAllStock(id);
     return new ResponseEntity<>(list, HttpStatus.OK);

    }

    @GetMapping("/view-transaction/{id}")
    public ResponseEntity<List<Transaction>> listOfTransaction(@PathVariable int id){

        List<Transaction> list = portfolioService.viewTransactions(id);
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

}
