package com.trade.service.impl;

import com.trade.dto.TradeRequest;
import com.trade.dto.TradeResponse;
import com.trade.entities.MatchTrade;
import com.trade.entities.TradeDetails;
import com.trade.enums.MatchStatus;
import com.trade.enums.TradeStatus;
import com.trade.enums.TradeType;
import com.trade.mapper.RequestDtoToEntityMapper;
import com.trade.repository.MatchTradeRepository;
import com.trade.repository.TradeRepository;
import com.trade.service.TradeService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
public class TradeServiceImpl implements TradeService {

   private final TradeRepository tradeRepository;
    private final MatchTradeRepository matchTradeRepository;

    public TradeServiceImpl(TradeRepository tradeRepository, MatchTradeRepository matchTradeRepository) {
        this.tradeRepository = tradeRepository;
        this.matchTradeRepository=matchTradeRepository;
    }

    @Override
    @Transactional
    public TradeResponse submitTrade(TradeRequest tradeRequest) {
        String tradeId = UUID.randomUUID().toString();
        TradeDetails tradeDetails = RequestDtoToEntityMapper.convertToEntity(tradeRequest,tradeId);
        tradeRepository.save(tradeDetails);
        //trade matching logic
        List<TradeDetails> buyTradeDetailsList=tradeRepository.findPendingTradesByType(TradeType.BUY, TradeStatus.PENDING);
        List<TradeDetails> sellTradeDetailsList=tradeRepository.findPendingTradesByType(TradeType.SELL, TradeStatus.PENDING);
        tradeMatch(buyTradeDetailsList,sellTradeDetailsList);
        return new TradeResponse("Trade submitted successfully",tradeId);
    }

    @Override
    @Transactional
    public void tradeMatch(List<TradeDetails> buyTradeDetailsList, List<TradeDetails> sellTradeDetailsList){
        int buyIndex = 0, sellIndex = 0;
        while (buyIndex < buyTradeDetailsList.size() && sellIndex < sellTradeDetailsList.size()) {
            TradeDetails buyTrade=buyTradeDetailsList.get(buyIndex);
            TradeDetails sellTrade=sellTradeDetailsList.get(sellIndex);

            if (!isMatchingTrade(buyTrade, sellTrade)) {
                sellIndex++; // Skip non-matching sell trade
                continue;
            }
            int matchedQuantity = Math.min(buyTrade.getTradeQuantity(), sellTrade.getTradeQuantity());
            BigDecimal matchedAmount = buyTrade.getTradeAmount().multiply(BigDecimal.valueOf(matchedQuantity));
            MatchTrade matchTrade=new MatchTrade.Builder()
                    .stockId(buyTrade.getStockId())
                    .buyTradeId(buyTrade.getTradeId())
                    .sellTradeId(sellTrade.getTradeId())
                    .matchedQuantity(matchedQuantity)
                    .matchedAmount(matchedAmount)
                    .matchedDate(LocalDateTime.now())
                    .status(matchedQuantity == buyTrade.getTradeQuantity() ? MatchStatus.MATCHED : MatchStatus.PARTIALLY_MATCHED)
                    .build();
            matchTradeRepository.save(matchTrade);
            buyTrade.setTradeQuantity(buyTrade.getTradeQuantity() - matchedQuantity);
            sellTrade.setTradeQuantity(sellTrade.getTradeQuantity() - matchedQuantity);

            if (buyTrade.getTradeQuantity() == 0) {
                buyTrade.setStatus(TradeStatus.MATCHED);
                buyIndex++;
            } else {
                buyTrade.setStatus(TradeStatus.PENDING);
            }

            if (sellTrade.getTradeQuantity() == 0) {
                sellTrade.setStatus(TradeStatus.MATCHED);
                sellIndex++;
            } else {
                sellTrade.setStatus(TradeStatus.PENDING);
            }
            tradeRepository.save(buyTrade);
            tradeRepository.save(sellTrade);
        }
    }

    private boolean isMatchingTrade(TradeDetails buyTrade, TradeDetails sellTrade) {
        return buyTrade.getStockId().equals(sellTrade.getStockId()) &&
                buyTrade.getTradeAmount().compareTo(sellTrade.getTradeAmount()) == 0;
    }

}
