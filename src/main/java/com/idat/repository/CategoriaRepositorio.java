package com.idat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.idat.model.Categoria;

public interface CategoriaRepositorio extends JpaRepository<Categoria, Long>{
	
	public Boolean existsByNombre(String nombre);

}
