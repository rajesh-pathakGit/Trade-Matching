package com.trade.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="stocks", uniqueConstraints = {@UniqueConstraint(columnNames = "stock_name")})
public class StockDetails {

        @Id
        @Column(name = "stock_id", length = 50, unique = true)
        private String stockId;

        @Column(name = "stock_name", length = 255, unique = true)
        private String stockName;

        public StockDetails() {
        }

        public StockDetails(Builder builder) {
                this.stockId = builder.stockId;
                this.stockName = builder.stockName;
        }

        public static class Builder {
                private String stockId;
                private String stockName;

                public StockDetails.Builder stockName(String stockName) {
                        this.stockName = stockName;
                        return this;
                }

                public StockDetails.Builder stockId(String stockId) {
                        this.stockId = stockId;
                        return this;
                }

                public StockDetails build() {
                        return new StockDetails(this);
                }
        }
}
