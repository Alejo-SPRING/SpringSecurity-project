package com.practica.backEnd.app.web.model.services;

import java.util.List;

import com.practica.backEnd.app.web.model.entity.UsuarioDato;

public interface IUsuarioDatoService {

	public List<UsuarioDato> findAll();
	public void create(UsuarioDato usuarioDato);
	public void remove(UsuarioDato usuarioDato);
	public void update(UsuarioDato usuarioDato);
	public UsuarioDato findForId(Integer id);
	
}
