package com.trade.validator;

import com.trade.dto.StockRequest;
import com.trade.dto.TradeRequest;

import java.math.BigDecimal;

public class Validator {

    public static void validateTrade(TradeRequest tradeRequest) {
        if (tradeRequest.getBuyerId().equals(tradeRequest.getSellerId())) {
            throw new IllegalArgumentException("Buyer and Seller cannot be the same");
        }
        if (tradeRequest.getTradeAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Trade amount must be greater than zero");
        }
        if (tradeRequest.getTradeQuantity() <= 0) {
            throw new IllegalArgumentException("Trade quantity must be greater than zero");
        }
    }

    public static void validateStockDetails(StockRequest stockRequest){


    }
}
