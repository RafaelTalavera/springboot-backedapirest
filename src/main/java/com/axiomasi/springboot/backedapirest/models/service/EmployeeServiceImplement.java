package com.axiomasi.springboot.backedapirest.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.axiomasi.springboot.backedapirest.models.dao.IEmployeeDao;
import com.axiomasi.springboot.backedapirest.models.entity.Employee;

@Service
public class EmployeeServiceImplement implements IEmployeeService {
	
	@Autowired
	private IEmployeeDao employeeDao;

	@Override
	@Transactional(readOnly = true)
	public List<Employee> findAll() {
		return (List<Employee>) employeeDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Employee findById(Long id) {
        // Verifica que el ID no sea nulo antes de buscar en la base de datos
        if (id <= 0) {
            throw new IllegalArgumentException("El ID proporcionado no es válido: " + id);
        }

        return employeeDao.findById(id).orElse(null);
    }
	

	@Override
	@Transactional
	public Employee save(Employee employee) {
		return employeeDao.save(employee);
	}

	@Override
	  @Transactional
	public void delete(Employee employee) {
		employeeDao.delete(employee);
		
	}

}
