package com.practica.backEnd.app.web.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "usuario_datos")
public class UsuarioDato implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "usuariodatos_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer usuarioDatosId;
	@Column(name = "usuario_nombre")
	@NotEmpty
	@Size(min = 3, max = 20)
	private String usuarioNombre;
	@Column(name = "usuario_apellido")
	@NotEmpty
	@Size(min = 3, max = 20)
	private String usuarioApellido;
	@Column(name = "usuario_fechana")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotNull
	private Date usuarioFecha;
	@OneToMany(mappedBy = "usuarioId")
	private List<UsuarioHasVenta> lista;
	
	public UsuarioDato() {
		usuarioDatosId = 0;
		usuarioNombre = "";
		usuarioApellido = "";
		usuarioFecha = new Date();
	}

	public int getUsuarioDatosId() {
		return usuarioDatosId;
	}

	public void setUsuarioDatosId(int usuarioDatosId) {
		this.usuarioDatosId = usuarioDatosId;
	}

	public String getUsuarioNombre() {
		return usuarioNombre;
	}

	public void setUsuarioNombre(String usuarioNombre) {
		this.usuarioNombre = usuarioNombre;
	}

	public String getUsuarioApellido() {
		return usuarioApellido;
	}

	public void setUsuarioApellido(String usuarioApellido) {
		this.usuarioApellido = usuarioApellido;
	}

	public Date getUsuarioFecha() {
		return usuarioFecha;
	}

	public void setUsuarioFecha(Date usuarioFecha) {
		this.usuarioFecha = usuarioFecha;
	}

}
