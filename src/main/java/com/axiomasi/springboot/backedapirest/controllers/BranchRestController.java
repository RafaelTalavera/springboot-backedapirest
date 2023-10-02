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

import com.axiomasi.springboot.backedapirest.models.entity.Branch;
import com.axiomasi.springboot.backedapirest.models.service.IBranchService;

import jakarta.validation.Valid;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class BranchRestController {
	
	@Autowired
	private IBranchService branchService;
	
	@GetMapping("/branch")
	public List<Branch> index(){
		return branchService.findAll();
	}
	
	@GetMapping("/branch/{id}")
	public ResponseEntity<?> show(@PathVariable Long id){
		
		Branch branch = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			branch = branchService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (branch == null) {

			response.put("mensaje", "La sucursal ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Branch>(branch, HttpStatus.OK);
	}
		
	@PostMapping("/branch")
	public ResponseEntity<?> create(@Valid @RequestBody Branch branch, BindingResult result) { // Agrega la anotación @Valid y BindingResult
		Branch branchNew = null;
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
			branchNew = branchService.save(branch);
			response.put("mensaje", "La sucursal fue guardado correctamente");
			response.put("Sucursal", branchNew);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		} catch (DataAccessException e) {
			response.put("error", "Error al realizar el insert en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/branch/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Branch branch,BindingResult result, @PathVariable Long id) {

		Branch currentBranch = branchService.findById(id);

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

		if (currentBranch == null) {
			response.put("mensaje", "Error, no se pudo editar la Sucursa ID: " + id + " no existe en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
		
			currentBranch.setName(branch.getName());
			currentBranch.setAddress(branch.getAddress());
			currentBranch.setCity(branch.getCity());
			currentBranch.setState(branch.getState());
			currentBranch.setPhone(branch.getPhone());

			

			Branch branchUpdated = branchService.save(currentBranch);

			response.put("mensaje", "La Sucursal ha sido actualizado con éxito");
			response.put("sucursal", branchUpdated);

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/branch/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Branch currentBranch = this.branchService.findById(id);

		Map<String, Object> response = new HashMap<>();
		try {

			this.branchService.delete(currentBranch);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "Cliente eliminado con exito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}
}

	


