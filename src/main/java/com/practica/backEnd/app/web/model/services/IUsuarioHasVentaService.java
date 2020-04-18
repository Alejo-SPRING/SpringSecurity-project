package com.practica.backEnd.app.web.model.services;

import java.util.List;

import com.practica.backEnd.app.web.model.entity.UsuarioDato;
import com.practica.backEnd.app.web.model.entity.UsuarioHasVenta;

public interface IUsuarioHasVentaService {

	public List<UsuarioHasVenta> findAllProducto();
	public void create(UsuarioHasVenta usuarioHasVenta);
	public void remove(UsuarioHasVenta usuarioHasVenta);
	public void update(UsuarioHasVenta usuarioHasVenta);
	public UsuarioHasVenta findForId(Integer id);
	public List<UsuarioHasVenta> findVentasForUsuario(UsuarioDato usuarioDato);
	
}
