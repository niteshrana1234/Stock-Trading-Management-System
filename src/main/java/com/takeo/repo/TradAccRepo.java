package com.takeo.repo;

import com.takeo.model.TradingAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TradAccRepo extends JpaRepository<TradingAccount, Integer> {
    public Optional<TradingAccount> findByUserId(int userId);

}
