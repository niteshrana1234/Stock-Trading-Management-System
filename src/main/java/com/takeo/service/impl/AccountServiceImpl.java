package com.takeo.service.impl;

import com.takeo.model.TradingAccount;
import com.takeo.model.User;
import com.takeo.repo.TradAccRepo;
import com.takeo.repo.UserRepo;
import com.takeo.service.TradingAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImpl implements TradingAccountService {

    @Autowired
    private TradAccRepo accRepo;
    @Autowired
    private UserRepo userRepo;
    @Override
    public String openAccount(TradingAccount account) {

        TradingAccount account1 = accRepo.save(account);
        if(account1!=null){
            return "Success";
        }

        return "Failed";
    }

    @Override
    public String depositFund(int userId, int amount) {

       Optional<User> user = userRepo.findById(userId);
        if(user.isPresent()){
           Optional<TradingAccount> acc = accRepo.findByUserId(userId);
           if (acc.isPresent()){
               TradingAccount account = acc.get();
               double balance = account.getBalance();
               balance = balance+amount;
               account.setBalance(balance);
               TradingAccount savedAcc = accRepo.save(account);
               if(savedAcc!=null) {
                   return "Deposited successful";
               }
           }

        }

        return "Failed to deposit";
    }

    @Override
    public String withdraw(int userId, int amount) {
        Optional<User> user = userRepo.findById(userId);
        if(user.isPresent()){
            Optional<TradingAccount> acc = accRepo.findByUserId(userId);
            if(acc.isPresent()){
                TradingAccount account = acc.get();
                double balance = account.getBalance();
                balance = balance-amount;
                account.setBalance(balance);
                TradingAccount savedAcc = accRepo.save(account);
                if(savedAcc!=null){
                    return "Withdrawn successful";
                }
            }
        }
        return "Withdrawn failed";
    }

    @Override
    public String viewBalance(int userId) {
        Optional<User> user  =userRepo.findById(userId);
        if(user.isPresent()){
           Optional<TradingAccount> acc = accRepo.findByUserId(userId);
           if(acc.isPresent()){
               TradingAccount account = acc.get();
               return Double.toString(account.getBalance());
           }
        return "Error";
        }


        return null;
    }
}
