package com.axiomasi.springboot.backedapirest.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.axiomasi.springboot.backedapirest.models.entity.Branch;



public interface IBranchDao extends CrudRepository<Branch,Long > {

}
