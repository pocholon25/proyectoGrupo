package com.idat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;

import com.idat.model.Categoria;
import com.idat.repository.CategoriaRepositorio;
import com.idat.service.CategoriaServicio;

import jakarta.servlet.http.HttpSession;

@Service
public class CategoriaServicioImpl implements CategoriaServicio{
	
	@Autowired
	private CategoriaRepositorio categoriaRepositorio;

	@Override
	public Categoria saveCategoria(Categoria categoria) {
		return categoriaRepositorio.save(categoria);
	}

	@Override
	public List<Categoria> getAllCategoria() {
		return categoriaRepositorio.findAll();
	}

	@Override
	public Boolean existsByNombre(String nombre) {
		return categoriaRepositorio.existsByNombre(nombre);
	}

	@Override
	public Boolean deleteCategoria(Long id) {
		Categoria categoria = categoriaRepositorio.findById(id).orElse(null);
		
		if(!ObjectUtils.isEmpty(categoria)) {
			categoriaRepositorio.delete(categoria);
			return true;
		}
		return false;
	}

	@Override
	public Categoria getCategoriaById(Long id) {
		Categoria categoria = categoriaRepositorio.findById(id).orElse(null);
		return categoria;
	}
	
}
