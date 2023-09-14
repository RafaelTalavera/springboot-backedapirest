package com.axiomasi.springboot.backedapirest.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.axiomasi.springboot.backedapirest.models.dao.IClienteDao;
import com.axiomasi.springboot.backedapirest.models.entity.Cliente;


@Service
public class ClienteServiceImplemt implements IClienteService {

    @Autowired
    private IClienteDao clienteDao;

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> findAll() {
        return (List<Cliente>) clienteDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente findById(long id) {
        // Verifica que el ID no sea nulo antes de buscar en la base de datos
        if (id <= 0) {
            throw new IllegalArgumentException("El ID proporcionado no es válido: " + id);
        }

        return clienteDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Cliente save(Cliente cliente) {
        return clienteDao.save(cliente);
    }

    @Override
    @Transactional
    public void delete(Cliente cliente) {
        clienteDao.delete(cliente);
    }
}
