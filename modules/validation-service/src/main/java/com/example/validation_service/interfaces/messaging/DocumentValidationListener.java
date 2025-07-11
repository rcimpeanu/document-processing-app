package com.example.validation_service.interfaces.messaging;

import com.example.sharedkernel.event.DocumentProcessedEvent;
import com.example.sharedkernel.event.DocumentValidatedEvent;
import com.example.validation_service.application.ValidationService;
import com.example.validation_service.application.ValidationEventPublisher;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DocumentValidationListener {
    private static final Logger logger = LoggerFactory.getLogger(DocumentValidationListener.class);

    private final ValidationService validationService;

    private final ValidationEventPublisher validationEventPublisher;

    @PostConstruct
    public void init() {
        logger.info("DocumentValidationListener initialized");
    }

    @Async
    @EventListener
    @Retryable(
            value = { RuntimeException.class },
            maxAttempts = 3,
            backoff = @Backoff(delay = 2000)
    )
    public void handle(DocumentProcessedEvent event) {
        logger.info("Received DocumentProcessedEvent with validation intent for documentId={}", event.documentId());

        try {
            Thread.sleep(3000); // simulate delay
            String validationId = validationService.validateDocument(event.documentId());

            validationEventPublisher.publish(
                new DocumentValidatedEvent(event.documentId(), validationId)
            );
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        logger.info("Finished async validation for documentId={}", event.documentId());
    }

    @Recover
    public void recover(RuntimeException ex, DocumentProcessedEvent event) {
        logger.error("Final failure validating documentId={}, error={}", event.documentId(), ex.getMessage());
    }
}