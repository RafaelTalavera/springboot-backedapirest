package com.axiomasi.springboot.backedapirest.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.axiomasi.springboot.backedapirest.models.dao.IBuyDao;
import com.axiomasi.springboot.backedapirest.models.entity.Buy;

@Service
public class BuyImplementService implements IBuyService {

	@Autowired
	private IBuyDao buyDao;

	@Override
	@Transactional(readOnly = true)
	public List<Buy> findAll() {
		return (List<Buy>) buyDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Buy findById(Long id) {
		if (id <= 0) {
			throw new IllegalArgumentException("El Id proporcionado no es válido: " + id);
		}

		return buyDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Buy save(Buy buy) {

		return buyDao.save(buy);
	}

	@Override
	@Transactional
	public void delete(Buy buy) {
		buyDao.delete(buy);

	}

}
