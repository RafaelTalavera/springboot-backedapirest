package com.axiomasi.springboot.backedapirest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.axiomasi.springboot.backedapirest.models.entity.Cliente;
import com.axiomasi.springboot.backedapirest.models.service.IClienteService;



@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class ClienteRestController {

	@Autowired
	private IClienteService clienteService;

	@GetMapping("/clientes")
	public List<Cliente> index() {
		return clienteService.findAll();
	}

	@GetMapping("/clientes/{id}")
	public ResponseEntity <?> show(@PathVariable Long id) {
	
		Cliente cliente = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			cliente = clienteService.findById(id);
		} catch(DataAccessException e) {
			response.put("mensaje", "<error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
					
		if(cliente == null) {
			
			response.put("mensaje", "El cliente ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
				
		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK); 
	}

	@PostMapping("/clientes")

	public ResponseEntity <?>  create(@RequestBody Cliente cliente) {
	    Cliente clienteNew = null;
	    Map<String, Object> response = new HashMap<>();
	    
	    try {
	        clienteNew = clienteService.save(cliente);
	        response.put("mensaje", "El cliente fue guardado correctamente");
	        response.put("cliente", clienteNew);
	        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	    } catch(DataAccessException e) {
	        response.put("error", "Error al realizar el inset en la base de datos");
	        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

	@PutMapping("/clientes/{id}")
	public ResponseEntity <?> update(@RequestBody Cliente cliente, @PathVariable Long id) {
	   
		Cliente currentCliente = clienteService.findById(id);
	    
	    Map<String, Object> response = new HashMap<>();
	    
	    if (currentCliente == null) {
	        response.put("mensaje", "Error, no se pudo editar el cliente ID: " + id + " no existe en la base de datos");
	        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
	    }

	    try {
	        currentCliente.setNombre(cliente.getNombre());
	        currentCliente.setApellido(cliente.getApellido());
	        currentCliente.setEmail(cliente.getEmail());
	        currentCliente.setFecha(cliente.getFecha());

	        Cliente clienteUpdated = clienteService.save(currentCliente);

	        response.put("mensaje", "El cliente ha sido actualizado con éxito");
	        response.put("cliente", clienteUpdated);

	        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	    } catch (DataAccessException e) {
	        response.put("mensaje", "Error al actualizar en la base de datos");
	        response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
	        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}


	@DeleteMapping("/clientes/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		Cliente currentCliente = this.clienteService.findById(id);
		this.clienteService.delete(currentCliente);
	}
}
