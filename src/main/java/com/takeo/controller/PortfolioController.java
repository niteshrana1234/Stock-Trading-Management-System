package com.takeo.controller;

import com.takeo.model.Stock;
import com.takeo.model.TradingAccount;
import com.takeo.model.Transaction;
import com.takeo.payloads.PortfolioDTO;
import com.takeo.service.impl.PortfolioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("portfolio")
public class PortfolioController {

    @Autowired
    PortfolioServiceImpl portfolioService;


    @PostMapping("/create/{id}")
    public ResponseEntity<Map<String,String>> createPortfolio(@PathVariable int id,@RequestBody PortfolioDTO portfolioDTO){

      String create =  portfolioService.createPortfolio(id,portfolioDTO);
      Map<String,String> response = new HashMap<>();
      response.put("message",create);
      return new ResponseEntity<>(response,HttpStatus.CREATED);

    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Map<String,String>> updatePortfolio(@PathVariable int id,@RequestBody PortfolioDTO portfolioDTO){

        String create =  portfolioService.createPortfolio(id,portfolioDTO);
        Map<String,String> response = new HashMap<>();
        response.put("message",create);
        return new ResponseEntity<>(response,HttpStatus.CREATED);

    }



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

    @GetMapping("/view-account/{id}")
    public ResponseEntity<TradingAccount> listAccount(@PathVariable int id){
      TradingAccount account =  portfolioService.viewAccount(id);
        return new ResponseEntity<>(account,HttpStatus.OK);
    }

}
