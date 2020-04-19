package com.practica.backEnd.app.web.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.practica.backEnd.app.web.model.entity.UsuarioHasRol;

public interface IUsuarioLoginHasRolesDao extends CrudRepository<UsuarioHasRol, Integer> {

}
