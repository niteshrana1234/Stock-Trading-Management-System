package com.takeo.controller;

import com.takeo.service.TradingAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController("/user")
public class TradingAccController {
    @Autowired
    private TradingAccountService accountService;


}
