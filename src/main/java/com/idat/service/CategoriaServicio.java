package com.idat.service;

import java.util.List;

import com.idat.model.Categoria;

public interface CategoriaServicio {
	
	public Categoria saveCategoria(Categoria categoria);
	
	public List<Categoria> getAllCategoria();
	
	public Boolean existsByNombre(String nombre);
	
	public Boolean deleteCategoria(Long id);
	
	public Categoria getCategoriaById(Long id);

}
