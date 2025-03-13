package com.trade.scheduler;

import com.trade.entities.TradeDetails;
import com.trade.enums.TradeStatus;
import com.trade.enums.TradeType;
import com.trade.repository.TradeRepository;
import com.trade.service.TradeService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class TradeMatchingScheduler {

    private final TradeService tradeService;
    private final TradeRepository tradeRepository;

    public TradeMatchingScheduler(TradeService tradeMatchingService, TradeRepository tradeRepository) {
        this.tradeService = tradeMatchingService;
        this.tradeRepository = tradeRepository;
    }

    @Scheduled(fixedRate = 1800000) // Runs every 30 minutes
    @Transactional
    public void runTradeMatchingBatch() {
        System.out.println("Running trade matching batch job at: " + LocalDateTime.now());

        // Fetch pending buy and sell trades
        List<TradeDetails> buyTradeDetailsList=tradeRepository.findPendingTradesByType(TradeType.BUY, TradeStatus.PENDING);
        List<TradeDetails> sellTradeDetailsList=tradeRepository.findPendingTradesByType(TradeType.SELL, TradeStatus.PENDING);

        // Call the trade matching logic
        tradeService.tradeMatch(buyTradeDetailsList, sellTradeDetailsList);
    }
}
