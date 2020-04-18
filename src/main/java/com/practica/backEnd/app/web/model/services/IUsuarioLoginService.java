package com.practica.backEnd.app.web.model.services;

import java.util.List;

import com.practica.backEnd.app.web.model.entity.UsuarioLogin;

public interface IUsuarioLoginService {

	public List<UsuarioLogin> findAllProducto();
	public void create(UsuarioLogin usuarioLogin);
	public void remove(UsuarioLogin usuarioLogin);
	public void update(UsuarioLogin usuarioLogin);
	public UsuarioLogin findForId(Integer id);
	
}
