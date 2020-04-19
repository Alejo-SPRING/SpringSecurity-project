package com.practica.backEnd.app.web.model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "usuario_login")
public class UsuarioLogin implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "idusuario_login_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer usuarioLoginId;
	@Column(name = "usuario_login_correo")
	@NotEmpty
	@Email
	@Size(min = 10, max = 45)
	private String usuarioCorreo;
	@Column(name = "usuario_login_pass")
	@NotEmpty
	private String usuarioPass;
	@JoinColumn(name = "usuario_datos_id", referencedColumnName = "usuariodatos_id")
	@OneToOne(cascade = CascadeType.ALL)
	private UsuarioDato usuarioDatos;
	@OneToMany(mappedBy = "usuarioLogin")
	private List<UsuarioHasRol> usuarioHasRol;

	public List<UsuarioHasRol> getUsuarioHasRol() {
		return usuarioHasRol;
	}

	public void setUsuarioHasRol(List<UsuarioHasRol> usuarioHasRol) {
		this.usuarioHasRol = usuarioHasRol;
	}

	public UsuarioLogin(String correo, String pass) {
		this.usuarioCorreo = correo;
		this.usuarioPass = pass;
	}

	public UsuarioLogin() {
		this.usuarioLoginId = 0;
		this.usuarioCorreo = "";
		this.usuarioPass = "";
		this.usuarioDatos = new UsuarioDato();
	}

	public int getUsuarioLoginId() {
		return usuarioLoginId;
	}

	public void setUsuarioLoginId(int usuarioLoginId) {
		this.usuarioLoginId = usuarioLoginId;
	}

	public String getUsuarioCorreo() {
		return usuarioCorreo;
	}

	public void setUsuarioCorreo(String usuarioCorreo) {
		this.usuarioCorreo = usuarioCorreo;
	}

	public String getUsuarioPass() {
		return usuarioPass;
	}

	public void setUsuarioPass(String usuarioPass) {
		this.usuarioPass = usuarioPass;
	}

	public UsuarioDato getUsuarioDatos() {
		return usuarioDatos;
	}

	public void setUsuarioDatos(UsuarioDato usuarioDatos) {
		this.usuarioDatos = usuarioDatos;
	}

}
