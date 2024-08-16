package com.idat.controller;
import java.net.URI;
import java.util.List;

import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idat.model.Cliente;
import com.idat.model.ClienteUpdate;
import com.idat.model.LoginMessage;
import com.idat.model.LoginRequest;
import com.idat.model.ResponseMessage;
import com.idat.service.ClienteService;



@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteService service;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Cliente> listar(){
		return service.listar();
	}
	
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cliente> registrar(@RequestBody Cliente pN) {
	
		Cliente clienteRegistrado = service.registrar(pN);
	    if (clienteRegistrado != null) {
	        return ResponseEntity.ok(clienteRegistrado);
	    } else {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	    }
	}
	
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public Cliente modificar( @RequestBody Cliente pM) {
		return service.modificar(pM);
	}
	
	@DeleteMapping("/{id}")
	public boolean eliminar(@PathVariable("id") Long id) {
		return service.eliminar(id);
	}
	
	@GetMapping("/listarPagina")
	public Page<Cliente> listarPagina(Pageable pag){
		return service.listarPagina(pag);
	}
	
	 @PostMapping("/login")
	    public ResponseEntity<LoginMessage> login(@RequestBody LoginRequest loginRequest) {
		 System.out.println("Received login request: " + loginRequest);
	        LoginMessage response = service.loginUsuario(loginRequest);
	        if (response.isSuccess()) {
	            return ResponseEntity.ok(response);
	        } else {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
	        }
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<Cliente> obtenerClientePorId(@PathVariable("id") Long id) {
	        Cliente cliente = service.obtenerClientePorId(id);
	        if (cliente != null) {
	            return ResponseEntity.ok(cliente);
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	        }
	    }

	    @PutMapping("/update/{id}")
	    public ResponseEntity<?> actualizarCliente(@PathVariable("id") Long id, @RequestBody ClienteUpdate clienteUpdate) {
	        Cliente updatedCliente = service.actualizarCliente(id, clienteUpdate);
	        if (updatedCliente != null) {
	            ResponseMessage mensaje = new ResponseMessage("Cliente Actualizado");
	            return new ResponseEntity<>(mensaje, HttpStatus.OK); // Usar constructor con cuerpo y estado HTTP
	        } else {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Client not found");
	        }
	    }

	
}
