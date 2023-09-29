package com.takeo.service.impl;

import com.takeo.model.TradingAccount;
import com.takeo.model.Transaction;
import com.takeo.model.User;
import com.takeo.repo.TradAccRepo;
import com.takeo.repo.TransactionRepo;
import com.takeo.repo.UserRepo;
import com.takeo.service.TradingAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements TradingAccountService {

    @Autowired
    private TradAccRepo accRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    TransactionRepo transactionRepo;

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

               List<Transaction> list = account.getTransactionList();
               Transaction transaction = new Transaction();
               transaction.setAmount(amount);
               transaction.setTransactionType("Deposit");
               list.add(transaction);

               account.setTransactionList(list);

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

                List<Transaction> list = account.getTransactionList();
                Transaction transaction = new Transaction();
                transaction.setAmount(amount);
                transaction.setTransactionType("Withdraw");
                list.add(transaction);

                account.setTransactionList(list);

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
