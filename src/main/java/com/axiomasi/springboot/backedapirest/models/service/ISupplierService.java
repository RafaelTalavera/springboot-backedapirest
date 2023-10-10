package com.axiomasi.springboot.backedapirest.models.service;

import java.util.List;

import com.axiomasi.springboot.backedapirest.models.entity.Supplier;

public interface ISupplierService  {
	
	public List<Supplier> findAll();
	
	public Supplier findById(Long id);
	
	public Supplier save (Supplier supplier);
	
	public void delete (Supplier supplier);
	

}
