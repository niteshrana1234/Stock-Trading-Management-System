package com.takeo.controller;

import com.takeo.service.TradingAccountService;
import com.takeo.service.impl.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController()
@RequestMapping("account")
public class TradingAccController {
    @Autowired
    private AccountServiceImpl accountService;

    @PostMapping("/fund/{id}/{amount}")
    public ResponseEntity<Map<String,String>> fundBalance(@PathVariable int id, @PathVariable int amount){

        String deposit = accountService.depositFund(id,amount);
        Map<String,String> response = new HashMap<>();

        response.put("message",deposit);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/withdraw/{id}/{amount}")
    public ResponseEntity<Map<String,String>> withdraw(@PathVariable int id, @PathVariable int amount){

        String withdraw = accountService.withdraw(id,amount);
        Map<String, String> response = new HashMap<>();

        response.put("message",withdraw);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("balance/{id}")
    public ResponseEntity<Map<String,String>> viewBalance(@PathVariable int id){
       String balance = "$"+accountService.viewBalance(id);
       String message = "Balance";

       Map<String,String> response = new HashMap<>();
       response.put(message,balance);

       return new ResponseEntity<>(response,HttpStatus.OK);

    }



}
