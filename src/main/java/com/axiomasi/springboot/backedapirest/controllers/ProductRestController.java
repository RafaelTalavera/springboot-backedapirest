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


import com.axiomasi.springboot.backedapirest.models.entity.Product;
import com.axiomasi.springboot.backedapirest.models.service.IProductService;

import jakarta.validation.Valid;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class ProductRestController {

	@Autowired
	private IProductService productService;

	@GetMapping("/product")
	public List<Product> index() {
		return productService.findAll();
	}

	@GetMapping("/product/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {

		Product product = null;
		Map<String, Object> response = new HashMap<>();

		try {
			product = productService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "<error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (product== null) {

			response.put("mensaje", "El cliente ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}

	@PostMapping("/product")
	public ResponseEntity<?> create(@Valid @RequestBody Product product, BindingResult result) { // Agrega la anotación @Valid y BindingResult
		Product productNew = null;
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
			productNew = productService.save(product);
			response.put("mensaje", "El cliente fue guardado correctamente");
			response.put("product", productNew);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		} catch (DataAccessException e) {
			response.put("error", "Error al realizar el insert en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/product/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Product product,BindingResult result, @PathVariable Long id) {

		Product currentProduct = productService.findById(id);

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

		if (currentProduct == null) {
			response.put("mensaje", "Error, no se pudo editar el producto ID: " + id + " no existe en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			currentProduct.setCode(product.getCode());
			currentProduct.setName(product.getName());
			currentProduct.setBrand(product.getBrand());
			currentProduct.setPriceSale(product.getPriceSale());
			currentProduct.setStock(product.getStock());
			currentProduct.setExpiration(product.getExpiration());
			currentProduct.setPriceBuy(product.getPriceBuy());
			currentProduct.setBranch(product.getBranch());
		

			Product productUpdated = productService.save(currentProduct);

			response.put("mensaje", "El producto ha sido actualizado con éxito");
			response.put("product", productUpdated);

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/product/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Product currentProduct = this.productService.findById(id);

		Map<String, Object> response = new HashMap<>();
		try {

			this.productService.delete(currentProduct);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "Cliente eliminado con exito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}
}
