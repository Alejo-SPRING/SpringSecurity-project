package com.practica.backEnd.app.web.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.practica.backEnd.app.web.model.entity.UsuarioDato;
import com.practica.backEnd.app.web.model.entity.UsuarioHasVenta;

public interface IUsuarioHasVentaDao extends CrudRepository<UsuarioHasVenta, Integer>{

	@Query("select v from UsuarioHasVenta v where v.usuarioId = :usuario")
	List<UsuarioHasVenta> findVentasForCliente(@Param("usuario") UsuarioDato usuarioDato);
	
}
