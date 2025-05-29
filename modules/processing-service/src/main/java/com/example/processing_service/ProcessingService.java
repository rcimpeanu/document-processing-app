package com.example.processing_service;

import com.example.sharedkernel.event.DocumentProcessedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class ProcessingService {

    private final ApplicationEventPublisher eventPublisher;

    public ProcessingService(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void process(String documentId) {
        // Simulate some processing
        System.out.println("Processing document: " + documentId);

        // Once done, publish the event
        eventPublisher.publishEvent(new DocumentProcessedEvent(documentId));
    }
}

