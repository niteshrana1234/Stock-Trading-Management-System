package com.takeo.service.impl;

import com.takeo.model.TradingAccount;
import com.takeo.repo.TradAccRepo;
import com.takeo.service.TradingAccountService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImpl implements TradingAccountService {

    private TradAccRepo accRepo;
    @Override
    public String openAccount(TradingAccount account) {

        TradingAccount account1 = accRepo.save(account);
        if(account1!=null){
            return "Success";
        }

        return "Failed";
    }

    @Override
    public String depositFund(int userId) {
        return null;
    }

    @Override
    public String withdraw(int userId) {
        return null;
    }

    @Override
    public String viewBalance(int userId) {
        return null;
    }
}
