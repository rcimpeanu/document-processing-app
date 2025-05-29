package com.example.notification_service;

import com.example.sharedkernel.event.DocumentProcessedEvent;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DocumentProcessedListener {

    private final NotificationService notificationService;
    private static final Logger logger = LoggerFactory.getLogger(DocumentProcessedListener.class);

    @Async
    @EventListener
    public void handle(DocumentProcessedEvent event) {
        logger.info("Received DocumentProcessedEvent for documentId={}", event.documentId());

        try {
            Thread.sleep(3000); // simulate delay
            notificationService.sendNotification(event.documentId());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        logger.info("Finished async handling for documentId={}", event.documentId());
    }
}