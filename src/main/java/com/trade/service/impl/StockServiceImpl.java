package com.trade.service.impl;

import com.trade.dto.StockRequest;
import com.trade.dto.StockResponse;
import com.trade.entities.StockDetails;
import com.trade.exceptions.StockException;
import com.trade.mapper.RequestDtoToEntityMapper;
import com.trade.repository.StockRepository;
import com.trade.service.StockService;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;

    public StockServiceImpl(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    public StockResponse createNewStock(StockRequest request) {
        String stockId = UUID.randomUUID().toString();
        if(stockRepository.existsByStockName(request.getStockName())){
            throw new StockException(request.getStockName());
        }
        StockDetails stockDetails = RequestDtoToEntityMapper.convertToEntity(request,stockId);
        stockRepository.save(stockDetails);
        return new StockResponse(stockId,"Stock Added Successfully",request.getStockName());

    }
}
