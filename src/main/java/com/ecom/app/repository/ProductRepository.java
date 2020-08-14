package com.ecom.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ecom.app.model.Product;


@Repository
public interface ProductRepository extends CrudRepository<Product,Long>{

}
