package com.axiomasi.springboot.backedapirest.models.dao;

import org.springframework.data.repository.CrudRepository;
import com.axiomasi.springboot.backedapirest.models.entity.Product;

public interface IProductDao extends CrudRepository<Product, Long> {

}
