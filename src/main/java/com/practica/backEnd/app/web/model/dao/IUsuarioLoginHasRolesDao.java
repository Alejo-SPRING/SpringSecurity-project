package com.practica.backEnd.app.web.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.practica.backEnd.app.web.model.entity.UsuarioHasRol;

public interface IUsuarioLoginHasRolesDao extends CrudRepository<UsuarioHasRol, Integer>{
	
}
