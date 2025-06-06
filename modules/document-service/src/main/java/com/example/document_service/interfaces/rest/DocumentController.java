package com.example.document_service.interfaces.rest;

import com.example.document_service.application.DocumentProcessingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/documents")
@Slf4j
public class DocumentController {

	private final DocumentProcessingService documentProcessingService;

	public DocumentController(DocumentProcessingService documentProcessingService) {
		this.documentProcessingService = documentProcessingService;
	}

	@PostMapping("/{id}/process")
	public String process(@PathVariable String id) {
		log.info("Received request to process document with id={}", id);
		documentProcessingService.handle(id);
		log.info("Processing finished for documentId: " + id);
		return "Processing finished for documentId: " + id;
	}
}
