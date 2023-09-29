package com.takeo.service.impl;

import com.takeo.model.Stock;
import com.takeo.model.TradingAccount;
import com.takeo.model.Transaction;
import com.takeo.model.User;
import com.takeo.payloads.SellStockDTO;
import com.takeo.payloads.StockCalculator;
import com.takeo.payloads.StockDTO;
import com.takeo.repo.StockRepo;
import com.takeo.repo.TradAccRepo;
import com.takeo.repo.UserRepo;
import com.takeo.service.StockService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StockServiceImpl implements StockService {

@Autowired
 TradAccRepo accRepo;
@Autowired
 StockRepo stockRepo;
@Autowired
 UserRepo userRepo;
    @Override
    public String buyStock(int userId, List<StockDTO> stockDto) {
        String message = "error";
        Optional<User> usr = userRepo.findById(userId);
        if(usr.isPresent()){
            User user = usr.get();
            Optional<TradingAccount> acc =  accRepo.findByUserId(user.getId());
           if(acc.isPresent()){
               message="Not sufficient balance";
               TradingAccount account = acc.get();
               List<Stock> stockList = account.getStockList();
               List<Transaction> transaction = account.getTransactionList();

               double total = 0;
               for(StockDTO stockDTO:stockDto){
                  Stock stock = new Stock();
                   BeanUtils.copyProperties(stockDTO,stock);
                   double totalPrice =StockCalculator.calculate(stockDTO.getCurrentPrice(),stockDTO.getQuantity());
                   total =total+ totalPrice;
                   stockList.add(stock);

                   Transaction transaction1 = new Transaction();
                   transaction1.setTransactionType("Buy");
                   transaction1.setAmount(stockDTO.getCurrentPrice()*stockDTO.getQuantity());
                   transaction1.setStockName(stockDTO.getStockName());
                   transaction1.setQuantity(stockDTO.getQuantity());
                   transaction.add(transaction1);


               }
               if(account.getBalance()>=total){
                   account.setTransactionList(transaction);
                   account.setStockList(stockList);
                   account.setBalance(account.getBalance()-total);
                   accRepo.save(account);
                   message="Stock bought successfully";
               }

           }
        }
       return message;
    }

    @Override
    public String sellStock(SellStockDTO sellStockDTO) {

        Optional<User> usr = userRepo.findById(sellStockDTO.getUserId());
        if(usr.isPresent()){
           Optional<TradingAccount> acc = accRepo.findByUserId(sellStockDTO.getUserId());
           Optional<Stock> stk = stockRepo.findByStockName(sellStockDTO.getStockName());
           if(acc.isPresent()&&stk.isPresent()){
               TradingAccount account = acc.get();
                Stock stock = stk.get();

                List<Transaction> transactions = account.getTransactionList();
                List<Stock> stockList = account.getStockList();
               Transaction transaction1 = new Transaction();
               transaction1.setTransactionType("Sell");
               transaction1.setAmount(stock.getCurrentPrice()*sellStockDTO.getQuantity());
               transaction1.setStockName(sellStockDTO.getStockName());
               transaction1.setQuantity(sellStockDTO.getQuantity());
               transactions.add(transaction1);
               account.setTransactionList(transactions);

               int quantity = StockCalculator.stockQuantity(stock.getQuantity(),sellStockDTO.getQuantity());
               if(stock.getQuantity()<sellStockDTO.getQuantity()){
                   return "Not enough "+sellStockDTO.getStockName()+" stock to sell";
               }

                if(sellStockDTO.getQuantity()>=0 && quantity!=0){
                    stock.setQuantity(quantity);
                    double soldTotal = StockCalculator.calculate(stock.getCurrentPrice(),sellStockDTO.getQuantity());
                    account.setBalance(account.getBalance()+soldTotal);
                }
                else if(quantity==0){
                    stock.setQuantity(quantity);
                    stockList.remove(stock);
                    account.setStockList(stockList);
                    stockRepo.delete(stock);
                    double soldTotal = StockCalculator.calculate(stock.getCurrentPrice(),sellStockDTO.getQuantity());
                    account.setBalance(account.getBalance()+soldTotal);
                }


                accRepo.save(account);

                return "Stock sold successfully";
           }
        }
        return "Failed to sell stock";
    }

    @Override
    public List<Stock> viewALlStock(int userId) {
        Optional<User> optionalUser = userRepo.findById(userId);
        if(optionalUser.isPresent()){
            Optional<TradingAccount> acc = accRepo.findByUserId(userId);
            if(acc.isPresent()){
                TradingAccount account = acc.get();
                return account.getStockList();
            }
        }
        return null;
    }
}
