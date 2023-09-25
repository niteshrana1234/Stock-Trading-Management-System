package com.takeo.service;

import com.takeo.model.TradingAccount;

public interface TradingAccountService {

    public String openAccount(TradingAccount account);
    public String depositFund(int userId);
    public String withdraw(int userId);
    public String viewBalance(int userId);
}
