package com.idat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idat.model.Producto;
import com.idat.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductoContoller {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/all")
    public List<Producto> getAllProducts() {
        return productService.getAllProductos();
    }

    // Obtener productos por categoría
    @GetMapping("/productos/{categoria}")
    public List<Producto> getProductsByCategory(@PathVariable String categoria) {
        return productService.getProductoByCategoria(categoria);
    }

    // Obtener producto por ID
    @GetMapping("/{id}")
    public Producto getProductById(@PathVariable Integer id) {
        return productService.getProductoById(id);
    }

}
