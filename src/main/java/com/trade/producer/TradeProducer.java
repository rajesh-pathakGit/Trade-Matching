package com.trade.producer;

import com.trade.dto.TradeRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class TradeProducer {

    private final KafkaTemplate<String, TradeRequest> kafkaTemplate;
    private static final String TRADE_TOPIC = "trade-topic";

    public TradeProducer(KafkaTemplate<String, TradeRequest> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Transactional("kafkaTransactionManager")
    public void sendTrade(TradeRequest tradeRequest,String tradeId) {
        tradeRequest.setTradeId(tradeId);
        kafkaTemplate.send(TRADE_TOPIC, tradeRequest.getStockId(), tradeRequest);
    }
}
