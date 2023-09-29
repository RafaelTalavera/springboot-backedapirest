package com.axiomasi.springboot.backedapirest.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.axiomasi.springboot.backedapirest.models.entity.Employee;
import com.axiomasi.springboot.backedapirest.models.service.IEmployeeService;

import jakarta.validation.Valid;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class EmployeeRestContoller {

	@Autowired
	private IEmployeeService employeeService;

	@GetMapping("/employee")
	public List<Employee> index() {
		return employeeService.findAll();
	}

	@GetMapping("/employee/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {

		Employee employee = null;
		Map<String, Object> response = new HashMap<>();

		try {
			employee= employeeService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "<error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (employee == null) {

			response.put("mensaje", "El empleado ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}

	@PostMapping("/employee")
	public ResponseEntity<?> create(@Valid @RequestBody Employee employee, BindingResult result) { // Agrega la anotación @Valid y BindingResult
		Employee employeeNew = null;
		Map<String, Object> response = new HashMap<>();
		
		if (result.hasErrors()) {
			// Manejar los errores de validación
			List<String> errors = new ArrayList<>();
			
			for(FieldError err: result.getFieldErrors()) {
				errors.add("El campo " + err.getField() + " "+ err.getDefaultMessage());
			}
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}



		try {
			employeeNew  = employeeService.save(employee);
			response.put("mensaje", "El cliente fue guardado correctamente");
			response.put("cliente", employeeNew);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		} catch (DataAccessException e) {
			response.put("error", "Error al realizar el insert en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/employee/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Employee employee,BindingResult result, @PathVariable Long id) {

		Employee currentEmployee = employeeService.findById(id);

		Map<String, Object> response = new HashMap<>();
		
		if (result.hasErrors()) {
			// Manejar los errores de validación
			List<String> errors = new ArrayList<>();
			
			for(FieldError err: result.getFieldErrors()) {
				errors.add("El campo " + err.getField() + " "+ err.getDefaultMessage());
			}
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		if (currentEmployee == null) {
			response.put("mensaje", "Error, no se pudo editar el empleado ID: " + id + " no existe en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			currentEmployee.setDni(employee.getDni());
			currentEmployee.setCuit(employee.getCuit());
			currentEmployee.setName(employee.getName());
			currentEmployee.setLastname(employee.getLastname());
			currentEmployee.setBirth(employee.getBirth());
			currentEmployee.setAddress(employee.getAddress());
			currentEmployee.setJob(employee.getJob());
				
			employeeUpdated = customerService.save(currentCustomer);

			response.put("mensaje", "El cliente ha sido actualizado con éxito");
			response.put("cliente", customerUpdated);

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/employee/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Employee currentEmployee = this.employeeService.findById(id);

		Map<String, Object> response = new HashMap<>();
		try {

			this.employeeService.delete(currentEmployee);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "Cliente eliminado con exito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}
}
