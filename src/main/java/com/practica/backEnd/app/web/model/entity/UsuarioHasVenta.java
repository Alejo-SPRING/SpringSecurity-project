package com.practica.backEnd.app.web.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "usuario_has_venta")
public class UsuarioHasVenta implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "idusuario_has_venta")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer usuarioHasVentaId;
	@JoinColumn(name = "usuario_id", referencedColumnName = "usuariodatos_id")
	@ManyToOne
	private UsuarioDato usuarioId;
	@JoinColumn(name = "venta_id", referencedColumnName = "venta_id")
	@ManyToOne
	private Venta ventaId;

	public Integer getUsuarioHasVentaId() {
		return usuarioHasVentaId;
	}

	public void setUsuarioHasVentaId(Integer usuarioHasVentaId) {
		this.usuarioHasVentaId = usuarioHasVentaId;
	}

	public UsuarioDato getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(UsuarioDato usuarioId) {
		this.usuarioId = usuarioId;
	}

	public Venta getVentaId() {
		return ventaId;
	}

	public void setVentaId(Venta ventaId) {
		this.ventaId = ventaId;
	}

}
