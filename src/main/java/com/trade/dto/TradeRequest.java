package com.trade.dto;


import com.trade.enums.TradeStatus;
import com.trade.enums.TradeType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TradeRequest {
    private String tradeId;
    private String stockId;
    private String buyerId;
    private String sellerId;
    private TradeType tradeType;
    private BigDecimal tradeAmount;
    private int tradeQuantity;
    private LocalDateTime tradeDate;
    private TradeStatus status;

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public TradeType getTradeType() {
        return tradeType;
    }

    public void setTradeType(TradeType tradeType) {
        this.tradeType = tradeType;
    }

    public BigDecimal getTradeAmount() {
        return tradeAmount;
    }

    public void setTradeAmount(BigDecimal tradeAmount) {
        this.tradeAmount = tradeAmount;
    }

    public int getTradeQuantity() {
        return tradeQuantity;
    }

    public void setTradeQuantity(int tradeQuantity) {
        this.tradeQuantity = tradeQuantity;
    }

    public LocalDateTime getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(LocalDateTime tradeDate) {
        this.tradeDate = tradeDate;
    }

    public TradeStatus getStatus() {
        return status;
    }

    public void setStatus(TradeStatus status) {
        this.status = status;
    }

    public TradeRequest(String tradeId, String stockId, String buyerId, String sellerId, TradeType tradeType, BigDecimal tradeAmount, int tradeQuantity, LocalDateTime tradeDate, TradeStatus status) {
        this.tradeId = tradeId;
        this.stockId = stockId;
        this.buyerId = buyerId;
        this.sellerId = sellerId;
        this.tradeType = tradeType;
        this.tradeAmount = tradeAmount;
        this.tradeQuantity = tradeQuantity;
        this.tradeDate = tradeDate;
        this.status = status;
    }

    public TradeRequest() {
    }
}
