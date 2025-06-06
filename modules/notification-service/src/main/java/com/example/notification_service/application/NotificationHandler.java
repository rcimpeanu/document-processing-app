package com.example.notification_service.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NotificationHandler {
    private static final Logger logger = LoggerFactory.getLogger(NotificationHandler.class);

    public void sendNotification(String documentId) {
        logger.info("Sending notification for document: {}", documentId);
        try {
            Thread.sleep(2000); // Simulate delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        logger.info("Notification sent for document: {}", documentId);
    }
}
