package com.takeo.repo;

import com.takeo.model.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PortfolioRepo extends JpaRepository<Portfolio,Integer> {

}
