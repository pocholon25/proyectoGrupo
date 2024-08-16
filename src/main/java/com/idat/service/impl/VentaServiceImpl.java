package com.idat.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.idat.model.DetalleVenta;
import com.idat.model.Venta;
import com.idat.repository.ProductRepository;
import com.idat.repository.VentaDao;
import com.idat.service.VentaService;

@Service
public class VentaServiceImpl implements VentaService{
	
	@Autowired
	private VentaDao dao;
	@Autowired
	private ProductRepository daoProducto;

	@Override
	public Venta registrar(Venta t) {
		t.setIdVenta(0L);
		double importe =0;
		for(DetalleVenta d: t.getDetalleVenta()) {
			d.setVenta(t);
			d.setProducto(daoProducto.findById(d.getProducto().getId()).get());
			d.setSubtotal(d.getCantidad()*d.getProducto().getPrecio());
			importe+=d.getSubtotal();
		}
		t.setImporte(importe);
		t=dao.save(t);
		return t;	
	}

	@Override
	public Venta modificar(Venta t) {
		return dao.save(t);
	}

	@Override
	public boolean eliminar(Long id) {
		Optional<Venta>opt = dao.findById(id);
		if(opt!=null) {
			dao.delete(opt.get());
			return true;
		}return false;
	}

	@Override
	public Venta buscar(Long id) {
		return dao.findById(id).get();
	}

	@Override
	public List<Venta> listar() {
		return dao.findAll();
	}

	@Override
	public Page<Venta> listarPagina(Pageable pagina) {
		return dao.findAll(pagina);
	}
	
	

}
