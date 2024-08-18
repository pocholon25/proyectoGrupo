package com.idat.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.idat.model.Producto;

public interface ProductService {
	
	public Producto saveProducto(Producto producto);

	public List<Producto> getAllProductos();

	public Boolean deleteProducto(Integer id);

	public Producto getProductoById(Integer id);

	public Producto updateProducto(Producto producto, MultipartFile file);
	
	public List<Producto>getProductoByCategoria(String categoria);
	
	public Page<Producto> getProductosPaginados(Pageable pageable);
    public Page<Producto> getProductosPaginadosPorCategoria(Pageable pageable, String categoria);


}
