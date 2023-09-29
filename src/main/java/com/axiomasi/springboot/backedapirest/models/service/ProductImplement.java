package com.axiomasi.springboot.backedapirest.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.axiomasi.springboot.backedapirest.models.dao.IProductDao;
import com.axiomasi.springboot.backedapirest.models.entity.Product;

@Service
public class ProductImplement implements IProductService {
	
	@Autowired
	private IProductDao productDao;

	@Override
	@Transactional(readOnly = true)
	public List<Product> findAll() {
		return (List<Product>) productDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Product findById(Long id) {
		if(id<= 0)
			throw new IllegalArgumentException("El ID proporcionado no es válido: " + id);{				
			}
		
		return productDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Product save(Product product) {
		return productDao.save(product);
	}

	@Override
	@Transactional
	public void delete(Product product) {
		productDao.delete(product);
		
	}

}
