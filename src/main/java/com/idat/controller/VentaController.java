package com.idat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import com.idat.model.Venta;
import com.idat.service.VentaService;

@RestController
@RequestMapping("/venta")
public class VentaController {
	
	@Autowired
	private VentaService service;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Venta> listar(){
		return service.listar();
	}
	
	@GetMapping("/{id}")
	public Venta buscar(@PathVariable("id")Long id) {
		return service.buscar(id);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Venta> registrar(@RequestBody Venta vN){
		Venta _V = service.registrar(vN);
		return ResponseEntity.ok(_V);
	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public Venta modificar(@RequestBody Venta vM) {
		return service.modificar(vM);
	}
	
	@DeleteMapping("/{id}")
	public boolean eliminar(@PathVariable Long id) {
		return service.eliminar(id);
	}
	
	@GetMapping("/listaPagina")
	public Page<Venta>listarPagina(Pageable pag){
		return service.listarPagina(pag);
	}

}
