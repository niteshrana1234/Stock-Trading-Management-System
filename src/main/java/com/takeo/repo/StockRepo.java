package com.takeo.repo;

import com.takeo.model.Stock;
import com.takeo.model.TradingAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockRepo extends JpaRepository<Stock,Integer> {
public Optional<Stock> findByStockName(String name);

}
