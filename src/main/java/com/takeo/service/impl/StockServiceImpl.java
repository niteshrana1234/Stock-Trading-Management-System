package com.takeo.service.impl;

import com.takeo.model.Stock;
import com.takeo.model.TradingAccount;
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

import javax.swing.text.html.Option;
import java.util.ArrayList;
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
               List<Stock> stockList = account.getList();
               double total = 0;
               for(StockDTO stockDTO:stockDto){
                   Stock stock = new Stock();
                   BeanUtils.copyProperties(stockDTO,stock);
                   double totalPrice =StockCalculator.calculate(stockDTO.getCurrentPrice(),stockDTO.getQuantity());
                   total =total+ totalPrice;
                   stockList.add(stock);
               }
               if(account.getBalance()>=total){
                   account.setList(stockList);
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

                List<Stock> stockList = account.getList();

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
                    account.setList(stockList);
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
    public String viewALlStock(int userId) {
        return null;
    }
}
