package com.idat.service.impl;


import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import com.idat.model.Producto;
import com.idat.repository.ProductRepository;
import com.idat.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public Producto saveProducto(Producto producto) {
		return productRepository.save(producto);
	}

	@Override
	public List<Producto> getAllProductos() {
		return productRepository.findAll();
	}

	@Override
	public Boolean deleteProducto(Integer id) {
		Producto product = productRepository.findById(id).orElse(null);

		if (!ObjectUtils.isEmpty(product)) {
			productRepository.delete(product);
			return true;
		}
		return false;
	}

	@Override
	public Producto getProductoById(Integer id) {
		Producto product = productRepository.findById(id).orElse(null);
		return product;
	}

	@Override
	public Producto updateProducto(Producto producto, MultipartFile file) {
		Producto dbProduct = getProductoById(producto.getId());

		String imageName = file.isEmpty() ? dbProduct.getImage() : file.getOriginalFilename();

		dbProduct.setNombre(producto.getNombre());
		dbProduct.setDescripcion(producto.getDescripcion());
		dbProduct.setCategoria(producto.getCategoria());
		dbProduct.setPrecio(producto.getPrecio());
		dbProduct.setStock(producto.getStock());
		dbProduct.setImage(imageName);
		

		Producto updateProduct = productRepository.save(dbProduct);

		if (!ObjectUtils.isEmpty(updateProduct)) {

			if (!file.isEmpty()) {

				try {
					File saveFile = new ClassPathResource("static/img").getFile();

					Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + "product_img" + File.separator
							+ file.getOriginalFilename());
					Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return producto;
		}
		return null;
	}

	@Override
	public List<Producto> getProductoByCategoria(String categoria) {
		return productRepository.findByCategoria(categoria);
	}

}
