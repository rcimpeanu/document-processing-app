package com.example.document_service.application;

import com.example.processing_service.application.ProcessingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DocumentProcessingService {

	private final ProcessingService processingService;

	public DocumentProcessingService(ProcessingService processingService) {
		this.processingService = processingService;
	}

	public void handle(String documentId) {
		log.info("Document processing initiated for documentId={}", documentId);
		processingService.process(documentId);
	}
}
