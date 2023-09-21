package com.axiomasi.springboot.backedapirest.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.axiomasi.springboot.backedapirest.models.dao.ICustomerDao;
import com.axiomasi.springboot.backedapirest.models.entity.Customer;


@Service
public class CustomerServiceImplemt implements ICustomerService {

    @Autowired
    private ICustomerDao customerDao;

    @Override
    @Transactional(readOnly = true)
    public List<Customer> findAll() {
        return (List<Customer>) customerDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Customer findById(Long id) {
        // Verifica que el ID no sea nulo antes de buscar en la base de datos
        if (id <= 0) {
            throw new IllegalArgumentException("El ID proporcionado no es válido: " + id);
        }

        return customerDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Customer save(Customer customer) {
        return customerDao.save(customer);
    }

    @Override
    @Transactional
    public void delete(Customer customer) {
        customerDao.delete(customer);
    }
}
