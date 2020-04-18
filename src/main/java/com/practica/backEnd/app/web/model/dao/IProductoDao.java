package com.practica.backEnd.app.web.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.practica.backEnd.app.web.model.entity.Producto;

public interface IProductoDao extends CrudRepository<Producto, Integer>{
	
}
