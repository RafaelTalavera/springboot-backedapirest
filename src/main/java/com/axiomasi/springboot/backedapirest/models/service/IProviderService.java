package com.axiomasi.springboot.backedapirest.models.service;

import java.util.List;

import com.axiomasi.springboot.backedapirest.models.entity.Provider;

public interface IProviderService  {
	
	public List<Provider> findAll();
	
	public Provider findById(Long id);
	
	public Provider save (Provider provider);
	
	public void delete (Provider provider);
	

}
