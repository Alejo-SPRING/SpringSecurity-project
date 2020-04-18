package com.practica.backEnd.app.web.model.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practica.backEnd.app.web.model.dao.IProductoDao;
import com.practica.backEnd.app.web.model.entity.Producto;

@Service
public class ProductoServiceImpl implements IProductoService{

	private List<Producto> listProductos = new ArrayList<>();
	
	@Autowired
	private IProductoDao productoDAO;
	
	@Override
	public List<Producto> findAllProducto() {
		return (List<Producto>) productoDAO.findAll();
	}

	@Override
	public void create(Producto producto) {
		productoDAO.save(producto);
	}

	@Override
	public void remove(Producto producto) {
		productoDAO.delete(producto);
	}

	@Override
	public void update(Producto producto) {
		productoDAO.save(producto);
	}

	@Override
	public Producto findForId(Integer id) {
		return productoDAO.findById(id).orElse(null);
	}

	@Override
	public List<Producto> getProductosSelected() {
		return listProductos;
	}

	@Override
	public Integer totalProductos() {
		Integer total = 0;
		for (Producto producto : listProductos) {
			total += producto.getProductoValor();
		}
		return total;
	}

	@Override
	public boolean eliminarProductoSelect(Integer id) {
		for (Producto producto : listProductos) {
			if (producto.getProductoId() == id) {
				listProductos.remove(producto);
				return true;
			}
		}
		return false;
	}

	@Override
	public void agregarProducto(Producto producto) {
		listProductos.add(producto);
	}

	@Override
	public void removeProductosSelected() {
		listProductos = new ArrayList<>();
	}

}
