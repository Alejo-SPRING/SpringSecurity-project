package com.practica.backEnd.app.web.model.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.practica.backEnd.app.web.model.dao.IUsuarioLoginDao;
import com.practica.backEnd.app.web.model.entity.UsuarioHasRol;
import com.practica.backEnd.app.web.model.entity.UsuarioLogin;

@Transactional(readOnly = true)
@Service("jpaUserDetailsService")
public class JpaUserDetailsService implements UserDetailsService{

	@Autowired
	private IUsuarioLoginDao usuarioLoginDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UsuarioLogin user = usuarioLoginDao.findForCorreo(username);
		List<UsuarioHasRol> roles = user.getUsuarioHasRol();
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for(UsuarioHasRol rol : roles) {
			authorities.add(new SimpleGrantedAuthority(rol.getRol()));
		}
		return new User(user.getUsuarioDatos().getUsuarioNombre(), user.getUsuarioPass(), true, true, true, true, authorities);
	}
	
}
