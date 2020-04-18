package com.practica.backEnd.app.web.model.services;

import java.util.List;

import com.practica.backEnd.app.web.model.entity.Producto;
import com.practica.backEnd.app.web.model.entity.Venta;
import com.practica.backEnd.app.web.model.entity.VentaHasProducto;

public interface IVentaHasProductoService {

	public List<VentaHasProducto> findAllProducto();
	public void create(VentaHasProducto ventaHasProducto);
	public void remove(VentaHasProducto ventaHasProducto);
	public void update(VentaHasProducto ventaHasProducto);
	public VentaHasProducto findForId(Integer id);
	public void regProductosSelected(List<Producto> productosList, Venta venta);
	public List<VentaHasProducto> findProductosForVenta(Venta venta);
	
}
