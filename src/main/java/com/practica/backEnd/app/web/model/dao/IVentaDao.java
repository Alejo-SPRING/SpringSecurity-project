package com.practica.backEnd.app.web.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.practica.backEnd.app.web.model.entity.Venta;

public interface IVentaDao extends CrudRepository<Venta, Integer>{

	
}
