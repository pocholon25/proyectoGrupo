package com.idat.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.idat.model.Producto;

public interface ProductRepository extends JpaRepository<Producto, Integer>{
	
	List<Producto> findByCategoria(String categoria);

	Page<Producto> findByCategoria(Pageable pageable, String categoria);

}
