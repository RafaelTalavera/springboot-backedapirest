package com.axiomasi.springboot.backedapirest.models.service;

import java.util.List;

import com.axiomasi.springboot.backedapirest.models.entity.Employee;

public interface IEmployeeService {

	public List<Employee> findAll();
	
	public Employee findById(Long id);
	
	public Employee save (Employee employee);
	
	public void delete(Employee employee);
		
}
