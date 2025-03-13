package com.trade.entities;

import com.trade.enums.TradeStatus;
import com.trade.enums.TradeType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="trades")
public class TradeDetails {

        @Id
        @Column(name = "trade_id", length = 50, unique = true)
        private String tradeId;

        @Column(name = "stock_id" , nullable = false)
        private String stockId;

        @Column(name = "buyer_id", length = 50, nullable = true)
        private String buyerId;

        @Column(name = "seller_id", length = 50, nullable = true)
        private String sellerId;

        @Enumerated(EnumType.STRING)
        @Column(name = "trade_type", length = 10)
        private TradeType tradeType;

        @Column(name = "trade_amount", precision = 10, scale = 2)
        private BigDecimal tradeAmount;

        @Column(name = "trade_quantity")
        private Integer tradeQuantity;

        @Column(name = "trade_date")
        private LocalDateTime tradeDate;

        @Enumerated(EnumType.STRING)
        @Column(name = "status", length = 10)
        private TradeStatus status;

        @Column(name = "created_at")
        private LocalDateTime createdAt;

        @Column(name = "updated_at")
        private LocalDateTime updatedAt;
        @PrePersist
        protected void onCreate() {
                this.createdAt = LocalDateTime.now();
                this.updatedAt = LocalDateTime.now();
        }

        @PreUpdate
        protected void onUpdate() {
                this.updatedAt = LocalDateTime.now();
        }

        public String getTradeId() {
                return tradeId;
        }

        public String getStockId() {
                return stockId;
        }

        public String getBuyerId() {
                return buyerId;
        }

        public String getSellerId() {
                return sellerId;
        }

        public TradeType getTradeType() {
                return tradeType;
        }

        public BigDecimal getTradeAmount() {
                return tradeAmount;
        }

        public Integer getTradeQuantity() {
                return tradeQuantity;
        }

        public LocalDateTime getTradeDate() {
                return tradeDate;
        }

        public TradeStatus getStatus() {
                return status;
        }

        public LocalDateTime getCreatedAt() {
                return createdAt;
        }

        public LocalDateTime getUpdatedAt() {
                return updatedAt;
        }

        public void setTradeId(String tradeId) {
                this.tradeId = tradeId;
        }

        public void setStockId(String stockId) {
                this.stockId = stockId;
        }

        public void setBuyerId(String buyerId) {
                this.buyerId = buyerId;
        }

        public void setSellerId(String sellerId) {
                this.sellerId = sellerId;
        }

        public void setTradeType(TradeType tradeType) {
                this.tradeType = tradeType;
        }

        public void setTradeAmount(BigDecimal tradeAmount) {
                this.tradeAmount = tradeAmount;
        }

        public void setTradeQuantity(Integer tradeQuantity) {
                this.tradeQuantity = tradeQuantity;
        }

        public void setTradeDate(LocalDateTime tradeDate) {
                this.tradeDate = tradeDate;
        }

        public void setStatus(TradeStatus status) {
                this.status = status;
        }

        public void setCreatedAt(LocalDateTime createdAt) {
                this.createdAt = createdAt;
        }

        public void setUpdatedAt(LocalDateTime updatedAt) {
                this.updatedAt = updatedAt;
        }

        public TradeDetails() {
        }

        private TradeDetails(Builder builder) {
                this.tradeId = builder.tradeId;
                this.stockId = builder.stockId;
                this.buyerId = builder.buyerId;
                this.sellerId = builder.sellerId;
                this.tradeType = builder.tradeType;
                this.tradeAmount = builder.tradeAmount;
                this.tradeQuantity = builder.tradeQuantity;
                this.tradeDate = builder.tradeDate;
                this.status = builder.status;
                this.createdAt = builder.createdAt;
                this.updatedAt = builder.updatedAt;
        }

        public static class Builder {
                private String tradeId;
                private String stockId;
                private String buyerId;
                private String sellerId;
                private TradeType tradeType;
                private BigDecimal tradeAmount;
                private Integer tradeQuantity;
                private LocalDateTime tradeDate;
                private TradeStatus status;
                private LocalDateTime createdAt;
                private LocalDateTime updatedAt;

                public Builder tradeId(String tradeId) {
                        this.tradeId = tradeId;
                        return this;
                }

                public Builder stockId(String stockId) {
                        this.stockId = stockId;
                        return this;
                }

                public Builder buyerId(String buyerId) {
                        this.buyerId = buyerId;
                        return this;
                }

                public Builder sellerId(String sellerId) {
                        this.sellerId = sellerId;
                        return this;
                }

                public Builder tradeType(TradeType tradeType) {
                        this.tradeType = tradeType;
                        return this;
                }

                public Builder tradeAmount(BigDecimal tradeAmount) {
                        this.tradeAmount = tradeAmount;
                        return this;
                }

                public Builder tradeQuantity(Integer tradeQuantity) {
                        this.tradeQuantity = tradeQuantity;
                        return this;
                }

                public Builder tradeDate(LocalDateTime tradeDate) {
                        this.tradeDate = tradeDate;
                        return this;
                }

                public Builder status(TradeStatus status) {
                        this.status = status;
                        return this;
                }

                public Builder createdAt(LocalDateTime createdAt) {
                        this.createdAt = createdAt;
                        return this;
                }

                public Builder updatedAt(LocalDateTime updatedAt) {
                        this.updatedAt = updatedAt;
                        return this;
                }

                public TradeDetails build() {
                        return new TradeDetails(this);
                }
        }

}
