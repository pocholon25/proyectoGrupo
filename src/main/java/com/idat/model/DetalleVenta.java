package com.idat.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class DetalleVenta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idDetalleVenta;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name ="id_venta",nullable = false)
	private Venta venta;
	@ManyToOne
	@JoinColumn(name = "id_producto",nullable = false)
	private Producto producto;
	@Column(precision = 5)
	private double cantidad;
	@Column(precision = 5)
	private double subtotal;

}
