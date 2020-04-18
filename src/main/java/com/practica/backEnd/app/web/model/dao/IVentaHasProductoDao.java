package com.practica.backEnd.app.web.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.practica.backEnd.app.web.model.entity.Venta;
import com.practica.backEnd.app.web.model.entity.VentaHasProducto;

public interface IVentaHasProductoDao extends CrudRepository<VentaHasProducto, Integer>{

	@Query("select p from VentaHasProducto p where p.ventaId = :venta")
	List<VentaHasProducto> findProductosForVenta(@Param("venta") Venta venta);
	
}
