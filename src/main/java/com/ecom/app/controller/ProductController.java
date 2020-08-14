package com.ecom.app.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ecom.app.payload.ApiResponse;
import com.ecom.app.payload.ProductInput;
import com.ecom.app.service.FileStorageService;
import com.ecom.app.service.ProductService;
import com.ecom.app.utils.ValidationUtils;

@RestController
@RequestMapping("/api")
public class ProductController {

	@Autowired
	ProductService productService;

	@Autowired
	FileStorageService fileStorageService;

	@PostMapping("/product")
	public ResponseEntity<?> SaveProduct(@RequestBody ProductInput input) {
		
		if(ValidationUtils.validateProduct(input)) {
			return new ResponseEntity<>(productService.saveProduct(input), HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<>(new ApiResponse(false, "Input is Incorrect"), HttpStatus.BAD_REQUEST);
		}
		
		
		
	}

	@PostMapping("/product/{id}/upload")
	public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file, @PathVariable("id") String id)
			throws IOException {

		String fileDir = fileStorageService.uploadFile(file);
		productService.updateProductImage(fileDir, Long.parseLong(id));
		return ResponseEntity.status(HttpStatus.OK).body(new String(fileDir));

	}

	@GetMapping("/product")
	public ResponseEntity<?> getAL() {
		return ResponseEntity.status(HttpStatus.OK).body(productService.getAll());

	}

	
}