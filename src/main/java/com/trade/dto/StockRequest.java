package com.trade.dto;
import jakarta.validation.constraints.NotBlank;
public class StockRequest {

    @NotBlank(message = "Stock ID cannot be empty")
    private String stockId;
    @NotBlank(message = "Stock Name cannot be empty")
    private String stockName;

    public StockRequest(String stockId, String stockName) {
        this.stockId = stockId;
        this.stockName = stockName;
    }
    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }
}
