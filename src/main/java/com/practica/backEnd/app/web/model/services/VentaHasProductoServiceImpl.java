package com.practica.backEnd.app.web.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practica.backEnd.app.web.model.dao.IVentaHasProductoDao;
import com.practica.backEnd.app.web.model.entity.Producto;
import com.practica.backEnd.app.web.model.entity.Venta;
import com.practica.backEnd.app.web.model.entity.VentaHasProducto;

@Service
public class VentaHasProductoServiceImpl implements IVentaHasProductoService {

	@Autowired
	private IVentaHasProductoDao ventaHasProductoDAO;
	private VentaHasProducto ventaHasProductoDTO = new VentaHasProducto();
	
	@Override
	public List<VentaHasProducto> findAllProducto() {
		return (List<VentaHasProducto>) ventaHasProductoDAO.findAll();
	}

	@Override
	public void create(VentaHasProducto ventaHasProducto) {
		ventaHasProductoDAO.save(ventaHasProducto);
	}

	@Override
	public void remove(VentaHasProducto ventaHasProducto) {
		ventaHasProductoDAO.delete(ventaHasProducto);
	}

	@Override
	public void update(VentaHasProducto ventaHasProducto) {
		ventaHasProductoDAO.save(ventaHasProducto);
	}

	@Override
	public VentaHasProducto findForId(Integer id) {
		return ventaHasProductoDAO.findById(id).orElse(null);
	}

	@Override
	public void regProductosSelected(List<Producto> productosList, Venta venta) {
		for (Producto producto : productosList) {
			ventaHasProductoDTO.setVentaId(venta);
			ventaHasProductoDTO.setProductoId(producto);
			ventaHasProductoDAO.save(ventaHasProductoDTO);
			ventaHasProductoDTO = new VentaHasProducto();
		}
		ventaHasProductoDTO = new VentaHasProducto();
	}

	@Override
	public List<VentaHasProducto> findProductosForVenta(Venta venta) {
		return ventaHasProductoDAO.findProductosForVenta(venta);
	}

}
