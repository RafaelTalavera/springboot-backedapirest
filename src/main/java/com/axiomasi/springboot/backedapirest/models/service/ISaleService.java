package com.axiomasi.springboot.backedapirest.models.service;

import java.util.List;
import com.axiomasi.springboot.backedapirest.models.entity.Sale;

public interface ISaleService {
	
	public List<Sale>findAll();
	
	public Sale findById(Long id);
	
	public Sale save (Sale sale);
	
	public void delete (Sale sale);
	
	

}
