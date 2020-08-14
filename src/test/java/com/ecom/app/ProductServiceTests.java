package com.ecom.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ecom.app.model.Colors;
import com.ecom.app.model.Gender;
import com.ecom.app.model.Product;
import com.ecom.app.payload.ProductInput;
import com.ecom.app.repository.ProductRepository;
import com.ecom.app.service.ProductServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
public class ProductServiceTests {

	@InjectMocks
	ProductServiceImpl productService;

	@Mock
	ProductRepository productRepository;

	@Mock
	Product dataPersisted;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		when(dataPersisted.getProductName()).thenReturn("test");
	}

	@Test
	public void testSaveSuccess() {
		ProductInput inp = new ProductInput();
		inp.setColor(Colors.RED);
		inp.setGender(Gender.MALE);
		inp.setPrice(122);
		inp.setProductDescription("sdsd");
		when(dataPersisted.getProductName()).thenReturn("test");

		when(productRepository.save(Mockito.any(Product.class))).thenReturn(dataPersisted);

		productService.saveProduct(inp);

	}

	@Test
	public void testSaveImage() {
		ProductInput inp = new ProductInput();
		inp.setColor(Colors.RED);
		inp.setPrice(33434);
		inp.setProductDescription("sdsd");
		inp.setProductName("test");

		Optional<Product> a = Optional.ofNullable(dataPersisted);

		when(productRepository.findById(Mockito.anyLong())).thenReturn(a);

		when(productRepository.save(Mockito.any(Product.class))).thenReturn(dataPersisted);

		assertEquals("test", productService.updateProductImage("ID1", 1L).getProductName());

	}

	@Test
	public void testGetAll() {

		List<Product> all = new ArrayList<Product>();
		all.add(dataPersisted);

		when(productRepository.findAll()).thenReturn(all);

		productService.getAll();

	}

}
