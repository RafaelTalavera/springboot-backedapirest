package com.axiomasi.springboot.backedapirest.models.dao;

import org.springframework.data.repository.CrudRepository;
import com.axiomasi.springboot.backedapirest.models.entity.Employee;

public interface IEmployeeDao extends CrudRepository<Employee,Long>{

}
