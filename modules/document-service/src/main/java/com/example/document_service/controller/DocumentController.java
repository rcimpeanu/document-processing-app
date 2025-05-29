package com.example.document_service.controller;

import com.example.document_service.service.DocumentProcessingService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/documents")
public class DocumentController {

	private final DocumentProcessingService documentProcessingService;

	public DocumentController(DocumentProcessingService documentProcessingService) {
		this.documentProcessingService = documentProcessingService;
	}

	@PostMapping("/{id}/process")
	public String process(@PathVariable String id) {
		documentProcessingService.handle(id);
		return "Processing started for document: " + id;
	}
}
