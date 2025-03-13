package com.trade.service;

import com.trade.dto.TradeRequest;
import com.trade.dto.TradeResponse;
import com.trade.entities.TradeDetails;

import java.util.List;

public interface TradeService {

    public TradeResponse submitTrade(TradeRequest tradeRequest);
    public void tradeMatch(List<TradeDetails> buyTradeDetailsList, List<TradeDetails> sellTradeDetailsList);
}
