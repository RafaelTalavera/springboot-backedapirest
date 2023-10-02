package com.axiomasi.springboot.backedapirest.models.service;

import java.util.List;

import com.axiomasi.springboot.backedapirest.models.entity.Buy;


public interface IBuyService {

	public List<Buy>findAll();
	
	public Buy findById(Long id);
	
	public Buy save (Buy buy);
	
	public void delete (Buy buy);
}
