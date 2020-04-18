package com.practica.backEnd.app.web.model.services;

import java.util.List;

import com.practica.backEnd.app.web.model.entity.Venta;

public interface IVentaService {
	
	public List<Venta> findAllProducto();
	public void create(Venta venta);
	public void remove(Venta venta);
	public void update(Venta venta);
	public Venta findForId(Integer id);
	
}
