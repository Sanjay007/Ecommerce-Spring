package com.ecom.app.service;

import java.util.List;

import com.ecom.app.payload.ProductInput;
import com.ecom.app.payload.ProductOutPut;


public interface ProductService {
	public ProductOutPut saveProduct(ProductInput input);
	public ProductOutPut updateProductImage(String imageID,Long id);
	public List<ProductOutPut> getAll();
}
