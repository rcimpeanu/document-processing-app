package com.example.validation_service;

import com.example.sharedkernel.event.DocumentValidatedEvent;
import com.example.validation_service.publisher.ValidationEventPublisher;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ValidationService {
    private static final Logger logger = LoggerFactory.getLogger(ValidationService.class);
    private final ValidationEventPublisher  validationEventPublisher;

    public void validateDocument(String documentId) {
        logger.info("Validating document: {}", documentId);
        try {
            Thread.sleep(2000);

            String validationStatus = "SUCCESS";// Simulate delay

            logger.info("Document {} validated successfully, notifying audit", documentId);
            validationEventPublisher.publish(new DocumentValidatedEvent(documentId, validationStatus));

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
//        throw new IllegalStateException("Simulated failure for testing retry mechanism");
    }
}
