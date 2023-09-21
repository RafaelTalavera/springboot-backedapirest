package com.axiomasi.springboot.backedapirest.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.axiomasi.springboot.backedapirest.models.entity.Customer;

public interface ICustomerDao extends CrudRepository<Customer,Long >{

}
