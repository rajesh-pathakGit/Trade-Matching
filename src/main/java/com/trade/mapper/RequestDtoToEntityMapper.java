package com.trade.mapper;

import com.trade.dto.StockRequest;
import com.trade.dto.TradeRequest;
import com.trade.entities.MatchTrade;
import com.trade.entities.StockDetails;
import com.trade.entities.TradeDetails;
import com.trade.enums.TradeStatus;

import java.time.LocalDateTime;

public class RequestDtoToEntityMapper {

    public static TradeDetails convertToEntity(TradeRequest tradeDto, String tradeId) {
        return new TradeDetails.Builder()
                .tradeId(tradeId)
                .stockId(tradeDto.getStockId())
                .buyerId(tradeDto.getBuyerId())
                .sellerId(tradeDto.getSellerId())
                .tradeType(tradeDto.getTradeType())
                .tradeAmount(tradeDto.getTradeAmount())
                .tradeQuantity(tradeDto.getTradeQuantity())
                .tradeDate(LocalDateTime.now())
                .status(TradeStatus.PENDING)
                .build();
    }

    public static StockDetails convertToEntity(StockRequest stockRequest, String stockId) {
        return new StockDetails.Builder()
                .stockId(stockId)
                .stockName(stockRequest.getStockName())
                .build();
    }
}
