package com.axiomasi.springboot.backedapirest.models.service;

import java.util.List;

import com.axiomasi.springboot.backedapirest.models.entity.Cliente;

public interface IClienteService {
	
	public List<Cliente> findAll();
	
	public Cliente findById(long id);
	
	public Cliente save (Cliente cliente);
	
	public void delete(Cliente cliente);

}
