package com.takeo.service;

import com.takeo.model.TradingAccount;

public interface TradingAccountService {

    public String openAccount(TradingAccount account);
    public String depositFund(int userId, int amount);
    public String withdraw(int userId, int amount);
    public String viewBalance(int userId);
}
