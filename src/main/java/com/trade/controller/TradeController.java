package com.trade.controller;

import com.trade.dto.TradeRequest;
import com.trade.dto.TradeResponse;
import com.trade.service.TradeService;
import com.trade.validator.Validator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/trade")
public class TradeController {

    private final TradeService tradeService;

    public TradeController(TradeService tradeService) {
        this.tradeService = tradeService;
    }

    @PostMapping("/submitTrade")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> submitTrade(@Valid @RequestBody TradeRequest tradeRequest) {
        TradeResponse tradeResponse = tradeService.submitTrade(tradeRequest);
        return ResponseEntity.ok(tradeResponse);
    }

}
