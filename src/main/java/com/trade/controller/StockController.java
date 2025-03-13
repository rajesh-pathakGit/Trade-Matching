package com.trade.controller;

import com.trade.dto.StockRequest;
import com.trade.dto.StockResponse;
import com.trade.dto.TradeRequest;
import com.trade.service.StockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stock")
public class StockController {

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @PostMapping("/addStock")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> submitTrade(@Valid @RequestBody StockRequest stockRequest) {
           StockResponse stockResponse = stockService.createNewStock(stockRequest);
            return ResponseEntity.ok(stockResponse);
    }
}
