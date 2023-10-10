package com.axiomasi.springboot.backedapirest.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.axiomasi.springboot.backedapirest.models.dao.ISupplierDao;
import com.axiomasi.springboot.backedapirest.models.entity.Supplier;

@Service
public class SupplierServiceImplement implements ISupplierService {

	@Autowired
	private ISupplierDao supplierDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Supplier> findAll() {
		return (List<Supplier>) supplierDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Supplier findById(Long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID proporcionado no es válido: " + id);
        }
        return supplierDao.findById(id).orElse(null);
    }

	@Override
	@Transactional
	public Supplier save(Supplier supplier) {
		return supplierDao.save(supplier);
	}

	@Override
    @Transactional
	public void delete(Supplier supplier) {
		supplierDao.delete(supplier);
		
	}

}
