package com.amazon.s3.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amazon.s3.service.FileService;
import com.amazonaws.HttpMethod;

@RestController
public class FileController {

	@Autowired
	private FileService fileService;
	
	@PostMapping("/upload")
	public ResponseEntity<String> generateUrl(@RequestParam String extension){
		return ResponseEntity.ok(fileService.generatePreSignedUrl(UUID.randomUUID()+"."+extension, HttpMethod.PUT));
	}
	
	@GetMapping("/download")
	public ResponseEntity<String> getUrl(@RequestParam String filename){
		return ResponseEntity.ok(fileService.generatePreSignedUrl(filename, HttpMethod.GET));
	}
	
}
