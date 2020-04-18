package com.practica.backEnd.app.web.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practica.backEnd.app.web.model.dao.IUsuarioLoginDao;
import com.practica.backEnd.app.web.model.entity.UsuarioLogin;

@Service
public class UsuarioLoginServiceImpl implements IUsuarioLoginService {

	@Autowired
	private IUsuarioLoginDao usuarioLoginDAO;
	
	@Override
	public List<UsuarioLogin> findAllProducto() {
		return (List<UsuarioLogin>) usuarioLoginDAO.findAll();
	}

	@Override
	public void create(UsuarioLogin usuarioLogin) {
		usuarioLoginDAO.save(usuarioLogin);
	}

	@Override
	public void remove(UsuarioLogin usuarioLogin) {
		usuarioLoginDAO.delete(usuarioLogin);
	}

	@Override
	public void update(UsuarioLogin usuarioLogin) {
		usuarioLoginDAO.save(usuarioLogin);
	}

	@Override
	public UsuarioLogin findForId(Integer id) {
		return usuarioLoginDAO.findById(id).orElse(null);
	}

}
