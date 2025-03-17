package com.trade.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTpoicConfig {

    @Bean
    public NewTopic tradeTopic() {
        return new NewTopic("trade-topic", 3, (short) 1); // 3 partitions, 1 replica
    }
}
