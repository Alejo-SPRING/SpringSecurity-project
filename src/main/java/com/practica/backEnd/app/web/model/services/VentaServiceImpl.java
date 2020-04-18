package com.practica.backEnd.app.web.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practica.backEnd.app.web.model.dao.IVentaDao;
import com.practica.backEnd.app.web.model.entity.Venta;

@Service
public class VentaServiceImpl implements IVentaService {

	@Autowired
	private IVentaDao ventaDAO;
	
	@Override
	public List<Venta> findAllProducto() {
		return (List<Venta>) ventaDAO.findAll();
	}

	@Override
	public void create(Venta venta) {
		ventaDAO.save(venta);
	}

	@Override
	public void remove(Venta venta) {
		ventaDAO.delete(venta);
	}

	@Override
	public void update(Venta venta) {
		ventaDAO.save(venta);
	}

	@Override
	public Venta findForId(Integer id) {
		return ventaDAO.findById(id).orElse(null);
	}

}
