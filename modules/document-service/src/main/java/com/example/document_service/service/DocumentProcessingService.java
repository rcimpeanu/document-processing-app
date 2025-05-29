package com.example.document_service.service;

import com.example.processing_service.ProcessingService;
import org.springframework.stereotype.Service;

@Service
public class DocumentProcessingService {

	private final ProcessingService processingService;

	public DocumentProcessingService(ProcessingService processingService) {
		this.processingService = processingService;
	}

	public void handle(String documentId) {
		processingService.process(documentId);

	}
}
