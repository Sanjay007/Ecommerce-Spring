package com.ecom.app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import org.junit.Before;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.ecom.app.model.Colors;
import com.ecom.app.model.Gender;
import com.ecom.app.model.Product;
import com.ecom.app.payload.LoginRequest;
import com.ecom.app.payload.SignUpRequest;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@SpringBootTest
@AutoConfigureMockMvc
class DemoApplicationTests {

	@Autowired
	protected MockMvc mvc;
	@Autowired
	WebApplicationContext webApplicationContext;

	@Before
	protected void setUp() {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	protected String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}

	protected <T> T mapFromJson(String json, Class<T> clazz)
			throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, clazz);
	}

	@Test

	public void testDuplicateSignup() throws Exception {

		String uri = "/api/auth/signup";
		SignUpRequest req = new SignUpRequest();
		req.setEmail("bangarsanju12@gmail.com");
		req.setUsername("bangarsanju12");
		req.setPassword("sanjay");
		req.setName("Sanjau");

		String inputJson = mapToJson(req);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(400, status);

	}

	@Test
	@Order(1)
	public void signup() throws Exception {

		String uri = "/api/auth/signup";
		SignUpRequest req = new SignUpRequest();
		req.setEmail("bangarsanju12@gmail.com");
		req.setUsername("bangarsanju12");
		req.setPassword("sanjay");
		req.setName("Sanjau");

		String inputJson = mapToJson(req);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);

	}

	@Test
	@Order(2)
	public void login() throws Exception {

		String uri = "/api/auth/signin";
		LoginRequest req = new LoginRequest();
		req.setPassword("sanjay");
		req.setUsernameOrEmail("bangarsanju12");

		String inputJson = mapToJson(req);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);

	}

	@Test
	@Order(3)
	public void createProduct() throws Exception {

		String uri = "/api/product";
		Product product = new Product();
		product.setColor(Colors.RED);
		product.setProductDescription("test");
		product.setGender(Gender.FEMALE);
		product.setProductName("Test");
		product.setPrice(122);

		String inputJson = mapToJson(product);
		MvcResult mvcResult = mvc
				.perform(MockMvcRequestBuilders.post(uri).header(HttpHeaders.AUTHORIZATION, "Bearer " + getToken())
						.contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();

		assertEquals(201, status);
		updateImage();
	}

	@Test
	@Order(4)
	public void createProductNulltest() throws Exception {

		String uri = "/api/product";
		Product product = null;

		String inputJson = mapToJson(product);
		MvcResult mvcResult = mvc
				.perform(MockMvcRequestBuilders.post(uri).header(HttpHeaders.AUTHORIZATION, "Bearer " + " ")
						.contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();

		assertEquals(401, status);
		updateImage();
	}

	public void updateImage() throws Exception {

		String uri = "/api/product/1/upload";
		Product product = new Product();
		product.setColor(Colors.RED);
		product.setProductDescription("test");
		final InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("test.png");

		MockMultipartFile firstFile = new MockMultipartFile("file", "tes.png", "image/png", inputStream);

		String inputJson = mapToJson(product);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.multipart(uri).file(firstFile)
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + getToken())

		).andReturn();

		int status = mvcResult.getResponse().getStatus();

	}

	@Test
	public void test404() throws Exception {

		String uri = "/api/test/log";

		MvcResult mvcResult = mvc
				.perform(MockMvcRequestBuilders.get(uri).header(HttpHeaders.AUTHORIZATION, "Bearer " + getToken())
						.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(401, status);

	}

	private String getToken() {
		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + 604800000);

		String token = Jwts.builder().setSubject(Long.toString(1L)).setIssuedAt(new Date()).setExpiration(expiryDate)
				.signWith(SignatureAlgorithm.HS512, "JWTSuperSecretKey ").compact();
		return token;
	}

}
