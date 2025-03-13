package com.trade.repository;

import com.trade.entities.StockDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<StockDetails,String> {

    boolean existsByStockName(String stockName);
}
