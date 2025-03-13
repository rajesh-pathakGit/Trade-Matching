package com.trade.repository;

import com.trade.entities.TradeDetails;
import com.trade.enums.TradeStatus;
import com.trade.enums.TradeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TradeRepository extends JpaRepository<TradeDetails,String> {

    @Query("SELECT t FROM TradeDetails t WHERE t.tradeType = :tradeType AND t.status = :status ORDER BY t.tradeDate ASC")
    public List<TradeDetails> findPendingTradesByType(TradeType tradeType, TradeStatus status);
}
