package com.trade.entities;

import com.trade.enums.MatchStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="trade_matches")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatchTrade {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "match_id")
        private Long matchId;

        @Column(name = "stock_id", length = 50)
        private String stockId;

        @Column(name = "buy_trade_id", nullable = false)
        private String buyTradeId;

        @Column(name = "sell_trade_id", nullable = false)
        private String sellTradeId;

        @Column(name = "matched_quantity")
        private Integer matchedQuantity;

        @Column(name = "matched_amount", precision = 10, scale = 2)
        private BigDecimal matchedAmount;

        @Column(name = "matched_date")
        private LocalDateTime matchedDate;

        @Enumerated(EnumType.STRING)
        @Column(name = "status", length = 20)
        private MatchStatus status;

        public MatchTrade(Builder builder) {
                this.stockId = builder.stockId;
                this.buyTradeId = builder.buyTradeId;
                this.sellTradeId = builder.sellTradeId;
                this.matchedQuantity = builder.matchedQuantity;
                this.matchedAmount = builder.matchedAmount;
                this.matchedDate = builder.matchedDate;
                this.status = builder.status;
        }

        public static class Builder {
                private String stockId;
                private String buyTradeId;
                private String sellTradeId;
                private Integer matchedQuantity;
                private BigDecimal matchedAmount;
                private LocalDateTime matchedDate;
                private MatchStatus status;

                public Builder stockId(String stockId) {
                        this.stockId = stockId;
                        return this;
                }

                public Builder buyTradeId(String buyTradeId) {
                        this.buyTradeId = buyTradeId;
                        return this;
                }

                public Builder sellTradeId(String sellTradeId) {
                        this.sellTradeId = sellTradeId;
                        return this;
                }

                public Builder matchedQuantity(Integer matchedQuantity) {
                        this.matchedQuantity = matchedQuantity;
                        return this;
                }

                public Builder matchedAmount(BigDecimal matchedAmount) {
                        this.matchedAmount = matchedAmount;
                        return this;
                }

                public Builder matchedDate(LocalDateTime matchedDate) {
                        this.matchedDate = matchedDate;
                        return this;
                }

                public Builder status(MatchStatus status) {
                        this.status = status;
                        return this;
                }

                public MatchTrade build() {
                        return new MatchTrade(this);
                }
        }
}
