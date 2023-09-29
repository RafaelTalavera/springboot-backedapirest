package com.axiomasi.springboot.backedapirest.models.service;

import java.util.List;

import com.axiomasi.springboot.backedapirest.models.entity.Product;

public interface IProductService {
	
	public List<Product>findAll();
	
	public Product findById(Long id);
	
	public Product save (Product product);
	
	public void delete (Product product);
	
}
