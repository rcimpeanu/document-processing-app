package com.example.sharedkernel.event;

public record DocumentValidatedEvent(String documentId, String validationId) {}
