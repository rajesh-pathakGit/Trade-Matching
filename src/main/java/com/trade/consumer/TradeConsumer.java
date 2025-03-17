package com.trade.consumer;

import com.trade.dto.TradeRequest;
import com.trade.entities.TradeDetails;
import com.trade.enums.TradeStatus;
import com.trade.enums.TradeType;
import com.trade.repository.TradeRepository;
import com.trade.service.TradeService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TradeConsumer {

    private final TradeService tradeService;
    private final TradeRepository tradeRepository;

    public TradeConsumer(TradeService tradeService,TradeRepository tradeRepository) {
        this.tradeService = tradeService;
        this.tradeRepository = tradeRepository;
    }
    @KafkaListener(topics = "trade-topic", groupId = "trade-matching-group")
    public void consumeTrade(TradeRequest tradeRequest) {
        System.out.println("Received trade from Kafka: " + tradeRequest);

        // Run trade matching algorithm
        List<TradeDetails> buyTradeDetailsList=tradeRepository.findPendingTradesByType(TradeType.BUY, TradeStatus.PENDING);
        List<TradeDetails> sellTradeDetailsList=tradeRepository.findPendingTradesByType(TradeType.SELL, TradeStatus.PENDING);
        System.out.println("before trade match in Kafka consumer: " + tradeRequest);
        tradeService.tradeMatch(buyTradeDetailsList, sellTradeDetailsList);
        System.out.println("after trade match in Kafka consumer: " + tradeRequest);
    }
}
