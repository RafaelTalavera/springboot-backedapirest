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

import com.axiomasi.springboot.backedapirest.models.entity.Customer;
import com.axiomasi.springboot.backedapirest.models.entity.Sale;
import com.axiomasi.springboot.backedapirest.models.service.ICustomerService;
import com.axiomasi.springboot.backedapirest.models.service.ISaleService;


import jakarta.validation.Valid;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class SaleRestController {

	@Autowired
	private ISaleService saleService;
	
	@Autowired
	private ICustomerService customerService;
	

	@GetMapping("/sale")
	public List<Sale> index() {
		return saleService.findAll();
	}

	@GetMapping("/sale/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {

		Sale sale = null;

		Map<String, Object> response = new HashMap<>();

		try {
			sale = saleService.findById(id);
		} catch (DataAccessException e) {
			response.put("menseje", "error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		if (sale == null) {

			response.put("mensaje", "La compra ID: ".concat(id.toString().concat("no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);

		}

		return new ResponseEntity<Sale>(sale, HttpStatus.OK);

	}
	
	@PostMapping("/sale/{customerId}")
	public ResponseEntity<?> create(@Valid @RequestBody Sale sale, @PathVariable("customerId") Long customerId, BindingResult result) {
	    // Aquí customerId es un parámetro de ruta que se recibe desde la URL

	    Customer customer = customerService.findById(customerId);
	    Map<String, Object> response = new HashMap<>();

	    if (customer == null) {
	        response.put("mensaje", "El cliente con ID: " + customerId + " no existe en la base de datos");
	        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	    }

	    Sale newSale;

	    if (result.hasErrors()) {
	        response.put("errores", result.getAllErrors());
	        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	    }

	    try {
	        // Puedes asociar el cliente (customer) a la venta (sale) antes de guardarla en la base de datos
	        sale.setCustomer(customer);
	        newSale = saleService.save(sale);
	    } catch (DataAccessException e) {
	        response.put("mensaje", "Error al crear la venta en la base de datos");
	        response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
	        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }

	    response.put("mensaje", "La venta ha sido creada con éxito");
	    response.put("venta", newSale);
	    return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

}


