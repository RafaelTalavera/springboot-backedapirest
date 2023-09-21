package com.axiomasi.springboot.backedapirest.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.axiomasi.springboot.backedapirest.models.dao.IProviderDao;
import com.axiomasi.springboot.backedapirest.models.entity.Provider;

@Service
public class ProviderServiceImplement implements IProviderService {

	@Autowired
	private IProviderDao providerDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Provider> findAll() {
		return (List<Provider>) providerDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Provider findById(Long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID proporcionado no es válido: " + id);
        }
        return providerDao.findById(id).orElse(null);
    }

	@Override
	@Transactional
	public Provider save(Provider provider) {
		return providerDao.save(provider);
	}

	@Override
    @Transactional
	public void delete(Provider provider) {
		providerDao.delete(provider);
		
	}

}
