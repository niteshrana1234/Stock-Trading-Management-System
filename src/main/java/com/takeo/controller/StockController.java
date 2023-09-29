package com.takeo.controller;

import com.takeo.model.Stock;
import com.takeo.payloads.SellStockDTO;
import com.takeo.payloads.StockDTO;
import com.takeo.service.impl.StockServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("stock")
public class StockController {

    @Autowired
    private StockServiceImpl stockService;

    @PostMapping("/buy/{id}")
    public ResponseEntity<Map<String,String>> buyStock(@PathVariable int id, @RequestBody List<StockDTO> list){
       String buy = stockService.buyStock(id,list);

        Map<String,String> response = new HashMap<>();
        response.put("message",buy);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PostMapping("/sell")
    public ResponseEntity<Map<String,String>> sellStock(@RequestBody SellStockDTO stockDTO){
        String sell = stockService.sellStock(stockDTO);

        Map<String,String> response = new HashMap<>();
        response.put("message",sell);

        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @GetMapping("/view-stock/{id}")
    public ResponseEntity<List<Stock>> getAllStocks(@PathVariable("id") int userId){

        List<Stock> stockList = stockService.viewALlStock(userId);

        return new ResponseEntity<>(stockList,HttpStatus.OK);

    }

}
