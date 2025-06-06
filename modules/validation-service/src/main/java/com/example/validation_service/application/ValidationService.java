package com.example.validation_service.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ValidationService {
    private static final Logger logger = LoggerFactory.getLogger(ValidationService.class);

    public String validateDocument(String documentId) {
        logger.info("Validating document: {}", documentId);
        try {
            Thread.sleep(2000);

            logger.info("Document {} validated successfully, notifying audit", documentId);


        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
//        throw new IllegalStateException("Simulated failure for testing retry mechanism");
        return UUID.randomUUID().toString();
    }
}
