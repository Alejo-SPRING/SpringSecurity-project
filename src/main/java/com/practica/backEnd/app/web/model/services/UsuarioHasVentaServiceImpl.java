package com.practica.backEnd.app.web.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practica.backEnd.app.web.model.dao.IUsuarioHasVentaDao;
import com.practica.backEnd.app.web.model.entity.UsuarioDato;
import com.practica.backEnd.app.web.model.entity.UsuarioHasVenta;

@Service
public class UsuarioHasVentaServiceImpl implements IUsuarioHasVentaService{

	@Autowired
	private IUsuarioHasVentaDao usuarioHasVentaDAO;
	
	@Override
	public List<UsuarioHasVenta> findAllProducto() {
		return (List<UsuarioHasVenta>) usuarioHasVentaDAO.findAll();
	}

	@Override
	public void create(UsuarioHasVenta usuarioHasVenta) {
		usuarioHasVentaDAO.save(usuarioHasVenta);
	}

	@Override
	public void remove(UsuarioHasVenta usuarioHasVenta) {
		usuarioHasVentaDAO.delete(usuarioHasVenta);
	}

	@Override
	public void update(UsuarioHasVenta usuarioHasVenta) {
		usuarioHasVentaDAO.save(usuarioHasVenta);
	}

	@Override
	public UsuarioHasVenta findForId(Integer id) {
		return usuarioHasVentaDAO.findById(id).orElse(null);
	}

	@Override
	public List<UsuarioHasVenta> findVentasForUsuario(UsuarioDato usuarioDato) {
		return usuarioHasVentaDAO.findVentasForCliente(usuarioDato);
	}

}
