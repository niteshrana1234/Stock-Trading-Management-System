package com.takeo.repo;

import com.takeo.model.TradingAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TradAccRepo extends JpaRepository<TradingAccount, Integer> {
    public TradingAccount findByUserId(int userId);

}
