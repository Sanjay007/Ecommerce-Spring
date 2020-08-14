package com.ecom.app.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileStorageService {

	Logger logger = LoggerFactory.getLogger(FileStorageService.class);

	private final Path root = Paths.get("uploads");

	public void init() {
		try {
			Files.createDirectory(root);
		} catch (IOException e) {
			logger.info("Folder Already Exists");
		}
	}

	public String uploadFile(MultipartFile file) throws IOException {
		init();
		String fileExtension = getFileExtension(file);
		String filename = getRandomString();

		File targetFile = getTargetFile(fileExtension, filename);

		byte[] bytes = file.getBytes();

		file.transferTo(targetFile);

		String UploadedDirectory = targetFile.getAbsolutePath();
		logger.info("File Saved {}", UploadedDirectory);

		return Base64.getEncoder().encodeToString(UploadedDirectory.getBytes());

	}

	private String getRandomString() {
		return new Random().nextInt(999999) + "_" + System.currentTimeMillis();
	}

	private File getTargetFile(String fileExtn, String fileName) {
		File targetFile = new File(root + fileName + fileExtn);
		return targetFile;
	}

	private String getFileExtension(MultipartFile inFile) {
		String fileExtention = inFile.getOriginalFilename().substring(inFile.getOriginalFilename().lastIndexOf('.'));
		return fileExtention;
	}

}
