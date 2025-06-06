package com.example.validation_service.application;

import com.example.sharedkernel.event.DocumentValidatedEvent;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ValidationEventPublisher {

    private static final Logger logger = LoggerFactory.getLogger(ValidationEventPublisher.class);
    private static final String TOPIC_NAME = "document.validated";

    private final ApplicationEventPublisher applicationEventPublisher;
    private final KafkaTemplate<String, DocumentValidatedEvent> kafkaTemplate;

    public void publish(DocumentValidatedEvent event) {
        // Spring event
        applicationEventPublisher.publishEvent(event);

        // Kafka publish
        logger.info("Publishing to Kafka topic '{}': {}", TOPIC_NAME, event);
        kafkaTemplate.send(TOPIC_NAME, event.documentId(), event);
    }
}

