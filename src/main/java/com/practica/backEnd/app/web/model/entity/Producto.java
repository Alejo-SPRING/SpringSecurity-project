package com.practica.backEnd.app.web.model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "productos")
public class Producto implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "producto_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer productoId;
	@Column(name = "producto_nombre")
	@NotEmpty
	private String productoNombre;
	@Column(name = "producto_valor")
	@NotNull
	private int productoValor;
	@Column(name = "producto_img")
	private String productoImg;
	@OneToMany(mappedBy = "productoId", fetch = FetchType.LAZY)
	private List<VentaHasProducto> listProductos;

	public Integer getProductoId() {
		return productoId;
	}

	public void setProductoId(Integer productoId) {
		this.productoId = productoId;
	}

	public String getProductoNombre() {
		return productoNombre;
	}

	public void setProductoNombre(String productoNombre) {
		this.productoNombre = productoNombre;
	}

	public int getProductoValor() {
		return productoValor;
	}

	public void setProductoValor(int productoValor) {
		this.productoValor = productoValor;
	}

	public String getProductoImg() {
		return productoImg;
	}

	public void setProductoImg(String productoImg) {
		this.productoImg = productoImg;
	}

}
