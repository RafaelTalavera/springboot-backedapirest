package com.axiomasi.springboot.backedapirest.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.axiomasi.springboot.backedapirest.models.entity.Provider;

public interface IProviderDao extends CrudRepository<Provider,Long>{
	
	
	

}
