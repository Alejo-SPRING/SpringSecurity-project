package com.practica.backEnd.app.web.model.services;

import java.util.List;

import com.practica.backEnd.app.web.model.entity.Producto;

public interface IProductoService {
	
	public List<Producto> findAllProducto();
	public void create(Producto producto);
	public void remove(Producto producto);
	public void update(Producto producto);
	public Producto findForId(Integer id);
	public List<Producto> getProductosSelected();
	public Integer totalProductos();
	public boolean eliminarProductoSelect(Integer id);
	public void agregarProducto(Producto producto);
	public void removeProductosSelected();
	
}
