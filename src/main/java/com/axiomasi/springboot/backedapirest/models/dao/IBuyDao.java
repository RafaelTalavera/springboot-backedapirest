package com.axiomasi.springboot.backedapirest.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.axiomasi.springboot.backedapirest.models.entity.Buy;

public interface IBuyDao extends CrudRepository<Buy,Long> {

}
