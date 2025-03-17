package com.trade.config;

import com.trade.dto.TradeRequest;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.transaction.KafkaTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class TransactionConfig {

    @Bean
    public KafkaTransactionManager<String, TradeRequest> kafkaTransactionManager(
            ProducerFactory<String, TradeRequest> producerFactory) {
        KafkaTransactionManager<String, TradeRequest> kafkaTransactionManager = new KafkaTransactionManager<>(producerFactory);
        return kafkaTransactionManager;
    }

    @Primary
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
