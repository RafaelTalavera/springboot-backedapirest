package com.axiomasi.springboot.backedapirest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.axiomasi.springboot.backedapirest.models.entity.Buy;
import com.axiomasi.springboot.backedapirest.models.entity.Supplier;
import com.axiomasi.springboot.backedapirest.models.service.IBuyService;
import com.axiomasi.springboot.backedapirest.models.service.ISupplierService;


import jakarta.validation.Valid;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class BuyResController {
	
	
	@Autowired
	private IBuyService buyService;
	
	@Autowired
	private ISupplierService supplierService;

	@GetMapping("/buy")
	public List<Buy> index() {
		return buyService.findAll();
	}

	@GetMapping("/buy/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {

		Buy buy = null;

		Map<String, Object> response = new HashMap<>();

		try {
			buy = buyService.findById(id);
		} catch (DataAccessException e) {
			response.put("menseje", "error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		if (buy == null) {

			response.put("mensaje", "La compra ID: ".concat(id.toString().concat("no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);

		}

		return new ResponseEntity<Buy>(buy, HttpStatus.OK);

	}
	
	@PostMapping("/buy/{customerId}")
	public ResponseEntity<?> create(@Valid @RequestBody Buy buy, @PathVariable("providerId") Long providerId, BindingResult result) {
	    // Aquí customerId es un parámetro de ruta que se recibe desde la URL

	    Supplier supplier = supplierService.findById(providerId);
	    Map<String, Object> response = new HashMap<>();

	    if (supplier == null) {
	        response.put("mensaje", "El cliente con ID: " + providerId + " no existe en la base de datos");
	        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	    }

	    Buy newBuy;

	    if (result.hasErrors()) {
	        response.put("errores", result.getAllErrors());
	        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	    }

	    try {
	        // Puedes asociar el cliente (customer) a la venta (sale) antes de guardarla en la base de datos
	        buy.setProvider(supplier);
	        newBuy = buyService.save(buy);
	    } catch (DataAccessException e) {
	        response.put("mensaje", "Error al crear la venta en la base de datos");
	        response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
	        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }

	    response.put("mensaje", "La venta ha sido creada con éxito");
	    response.put("venta", newBuy);
	    return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

}


