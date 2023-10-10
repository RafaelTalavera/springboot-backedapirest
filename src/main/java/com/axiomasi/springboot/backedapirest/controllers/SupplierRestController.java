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


import com.axiomasi.springboot.backedapirest.models.entity.Supplier;
import com.axiomasi.springboot.backedapirest.models.service.ISupplierService;

import jakarta.validation.Valid;

@CrossOrigin(origins = { "http://localhost:4200"})
@RestController
@RequestMapping("/api")

public class SupplierRestController {
	
	@Autowired
	private ISupplierService supplierService;
	
	@GetMapping("/supplier")
	public List<Supplier> index(){
		return supplierService.findAll();
	}
	
	@GetMapping("/supplier/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {

		Supplier supplier = null;
		Map<String, Object> response = new HashMap<>();

		try {
			supplier = supplierService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "<error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (supplier == null) {

			response.put("mensaje", "El proveedor ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Supplier>(supplier, HttpStatus.OK);
	}

	@PostMapping("/supplier")
	public ResponseEntity<?> create(@Valid @RequestBody Supplier supplier, BindingResult result) { // Agrega la anotación @Valid y BindingResult
		Supplier providerNew = null;
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
			providerNew = supplierService.save(supplier);
			response.put("mensaje", "El proveedor fue guardado correctamente");
			response.put("proveedor", providerNew);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		} catch (DataAccessException e) {
			response.put("error", "Error al realizar el insert en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/supplier/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Supplier supplier,BindingResult result, @PathVariable Long id) {

		Supplier currentSupplier = supplierService.findById(id);

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

		if (currentSupplier == null) {
			response.put("mensaje", "Error, no se pudo editar el proveedor ID: " + id + " no existe en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			currentSupplier.setName(supplier.getName());
			currentSupplier.setEmail(supplier.getEmail());
			currentSupplier.setDateAt(supplier.getDateAt());

			Supplier supplierUpdated = supplierService.save(currentSupplier);

			response.put("mensaje", "El proveedor ha sido actualizado con éxito");
			response.put("proveedor", supplierUpdated);

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/proveedor/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Supplier currentSupplier = this.supplierService.findById(id);

		Map<String, Object> response = new HashMap<>();
		try {

			this.supplierService.delete(currentSupplier);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "Proveedor fue eliminado con exito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}
}

	
	


