package com.trade.repository;

import com.trade.entities.MatchTrade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchTradeRepository extends JpaRepository<MatchTrade,Long> {
}
