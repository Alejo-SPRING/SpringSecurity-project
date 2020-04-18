package com.practica.backEnd.app.web.model.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.practica.backEnd.app.web.model.entity.UsuarioLogin;

public interface IUsuarioLoginDao extends CrudRepository<UsuarioLogin, Integer>{
	
	@Query("select u from UsuarioLogin u where u.usuarioCorreo = :correo")
	UsuarioLogin findForCorreo(@Param("correo") String correo);
	
}
