package com.example.validation_service.configuration;

import com.example.sharedkernel.event.DocumentValidatedEvent;
import jakarta.annotation.PostConstruct;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic documentValidatedTopic() {
        return TopicBuilder.name("document.validated")
                           .partitions(1)
                           .replicas(1)
                           .build();
    }

    @Bean
    public ProducerFactory<String, DocumentValidatedEvent> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, DocumentValidatedEvent> kafkaTemplate(
            ProducerFactory<String, DocumentValidatedEvent> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }

    @PostConstruct
    public void init() {
        System.out.println("KafkaTopicSetup initialized.");
    }
}