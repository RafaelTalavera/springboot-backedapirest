package com.axiomasi.springboot.backedapirest.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.axiomasi.springboot.backedapirest.models.dao.ISaleDao;
import com.axiomasi.springboot.backedapirest.models.entity.Sale;

@Service
public class SaleServiceImplement implements ISaleService {
	
	@Autowired
	private ISaleDao saleDao;

	@Override
	@Transactional(readOnly = true)
	public List<Sale> findAll() {
	 return (List<Sale>) saleDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Sale findById(Long id) {
        // Verifica que el ID no sea nulo antes de buscar en la base de datos
        if (id <= 0) {
            throw new IllegalArgumentException("El ID proporcionado no es válido: " + id);
        }

        return saleDao.findById(id).orElse(null);
    }
	

	@Override
	@Transactional
	public Sale save(Sale sale) {
		return saleDao.save(sale);
	}

	@Override
	@Transactional
	public void delete(Sale sale) {
	   saleDao.delete(sale);
		
	}

}
