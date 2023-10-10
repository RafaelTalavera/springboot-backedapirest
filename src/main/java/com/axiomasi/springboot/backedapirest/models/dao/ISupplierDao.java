package com.axiomasi.springboot.backedapirest.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.axiomasi.springboot.backedapirest.models.entity.Supplier;

public interface ISupplierDao extends CrudRepository<Supplier,Long>{
	
	
	

}
