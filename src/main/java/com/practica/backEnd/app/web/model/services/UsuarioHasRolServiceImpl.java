package com.practica.backEnd.app.web.model.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practica.backEnd.app.web.model.dao.IUsuarioLoginHasRolesDao;
import com.practica.backEnd.app.web.model.entity.UsuarioHasRol;

@Service
public class UsuarioHasRolServiceImpl implements IUsuarioHasRolService{

	@Autowired
	private IUsuarioLoginHasRolesDao rolDao;
	
	@Override
	public void create(UsuarioHasRol rol) {
		rolDao.save(rol);
	}
	
}
