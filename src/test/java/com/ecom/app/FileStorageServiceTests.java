package com.ecom.app;


import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ecom.app.service.FileStorageService;


@RunWith(SpringJUnit4ClassRunner.class)
public class FileStorageServiceTests {

	@InjectMocks
	FileStorageService fileStorageService;
	
	@Test
	public void testFileUpload() throws IOException {
		
		final InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("test.png");
        MockMultipartFile firstFile = new MockMultipartFile("file", "tes.png", "image/png", inputStream);
		assertNotNull(fileStorageService.uploadFile(firstFile));
		
	}
}
