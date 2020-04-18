package com.practica.backEnd.app.web.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practica.backEnd.app.web.model.dao.IUsuarioDatoDao;
import com.practica.backEnd.app.web.model.entity.UsuarioDato;

@Service
public class UsuarioDatoServiceImpl implements IUsuarioDatoService {

	@Autowired
	private IUsuarioDatoDao usuarioDatoDAO;
	
	@Override
	public List<UsuarioDato> findAll() {
		return (List<UsuarioDato>) usuarioDatoDAO.findAll();
	}

	@Override
	public void create(UsuarioDato usuarioDato) {
		usuarioDatoDAO.save(usuarioDato);
	}

	@Override
	public void remove(UsuarioDato usuarioDato) {
		usuarioDatoDAO.delete(usuarioDato);
	}

	@Override
	public void update(UsuarioDato usuarioDato) {
		usuarioDatoDAO.save(usuarioDato);
	}

	@Override
	public UsuarioDato findForId(Integer id) {
		return usuarioDatoDAO.findById(id).orElse(null);
	}

}
