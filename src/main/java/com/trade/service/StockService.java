package com.trade.service;

import com.trade.dto.StockRequest;
import com.trade.dto.StockResponse;

public interface StockService {
    public StockResponse createNewStock(StockRequest request);

}
