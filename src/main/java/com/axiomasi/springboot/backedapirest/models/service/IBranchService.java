package com.axiomasi.springboot.backedapirest.models.service;

import java.util.List;

import com.axiomasi.springboot.backedapirest.models.entity.Branch;



public interface IBranchService {
	
	public List<Branch>findAll();
	
	public Branch findById(Long id);
	
	public Branch save (Branch branch);
	
	public void delete (Branch branch);
	

}
