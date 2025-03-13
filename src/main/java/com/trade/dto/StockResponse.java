package com.trade.dto;

import com.trade.enums.TradeStatus;

public class StockResponse {

    private String stockId;
    private String message;
    private String stockName;

    public StockResponse(String stockId, String message, String stockName) {
        this.stockId = stockId;
        this.message = message;
        this.stockName = stockName;
    }

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }
}
