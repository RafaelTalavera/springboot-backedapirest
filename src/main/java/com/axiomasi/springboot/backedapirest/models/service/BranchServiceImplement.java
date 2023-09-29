package com.axiomasi.springboot.backedapirest.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.axiomasi.springboot.backedapirest.models.dao.IBranchDao;
import com.axiomasi.springboot.backedapirest.models.entity.Branch;

@Service
public class BranchServiceImplement implements IBranchService {

	@Autowired
	private IBranchDao branchDao;

	@Override
	@Transactional(readOnly = true)
	public List<Branch> findAll() {
		return (List<Branch>) branchDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Branch findById(Long id) {
		if (id <= 0) {
			throw new IllegalArgumentException("El Id proporcionado no es válido: " + id);
		}
		return branchDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Branch save(Branch branch) {
		return branchDao.save(branch);
	}

	@Override
	@Transactional
	public void delete(Branch branch) {
		branchDao.delete(branch);

	}

}
