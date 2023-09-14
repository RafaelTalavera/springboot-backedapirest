package com.axiomasi.springboot.backedapirest.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.axiomasi.springboot.backedapirest.models.entity.Cliente;

public interface IClienteDao extends CrudRepository<Cliente,Long >{

}
