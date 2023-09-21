package com.axiomasi.springboot.backedapirest.models.service;

import java.util.List;

import com.axiomasi.springboot.backedapirest.models.entity.Customer;

public interface ICustomerService {
	
	public List<Customer> findAll();
	
	public Customer findById(Long id);
	
	public Customer save (Customer customer);
	
	public void delete(Customer customer);

}
