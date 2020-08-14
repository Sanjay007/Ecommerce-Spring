package com.ecom.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ecom.app.model.Gender;
import com.ecom.app.model.Product;
import com.ecom.app.payload.ProductInput;
import com.ecom.app.payload.ProductOutPut;
import com.ecom.app.repository.ProductRepository;



@Component
public class ProductServiceImpl implements ProductService {
	Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	
	private ProductRepository productRepository;

	ProductServiceImpl( @Autowired ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	@Override
	public ProductOutPut saveProduct(ProductInput input) {
		Product entityToSave=new Product();
		
		if(input.getGender().equals(Gender.MALE)) {
			entityToSave.setGender(Gender.MALE);
		}else {
			entityToSave.setGender(Gender.FEMALE);
		}
		
		
		logger.info("saving product with {} {}",input.getProductName(),input.getProductDescription());
		BeanUtils.copyProperties(input, entityToSave);
		Product savedObject=productRepository.save(entityToSave);
		ProductOutPut outPut=new ProductOutPut();
		BeanUtils.copyProperties(savedObject, outPut);
		return outPut;
		
	}
	
	@Override
	public ProductOutPut updateProductImage(String imageID,Long id) {
		
		Optional<Product> savedTarget=productRepository.findById(id);
		
		Product target=savedTarget.get();
		target.setImagePath(imageID);
		ProductOutPut outPut=new ProductOutPut();
		target=productRepository.save(target);
		
		BeanUtils.copyProperties(target, outPut);
		logger.info("returning  product with ID {} {}",target.getId());
		return outPut;
		
	}

	@Override
	public List<ProductOutPut> getAll() {
		List<Product> allList=(List<Product>) productRepository.findAll();
		List<ProductOutPut> outList=new ArrayList<ProductOutPut>();
		for(Product product:allList) {
			ProductOutPut out=new ProductOutPut();
			
			BeanUtils.copyProperties(product, out);
			outList.add(out);
			
		}
		return outList;
	}

	
}
