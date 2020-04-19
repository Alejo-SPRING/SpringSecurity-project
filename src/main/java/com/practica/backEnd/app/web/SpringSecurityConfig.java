package com.practica.backEnd.app.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.practica.backEnd.app.web.auth.handler.LoginSuccessHandler;
import com.practica.backEnd.app.web.model.services.JpaUserDetailsService;

@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private LoginSuccessHandler successHandler;
	@Autowired
	private JpaUserDetailsService userDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/img/**", "/css/**", "/js/**", "/registrar", "/springLogin2").permitAll().
		//antMatchers("/cliente/crearProducto/**", "/cliente/productos/**", "/cliente/eliminarProd/**").hasAnyRole("ADMIN").
		//antMatchers("/cliente/verFac", "/cliente/inicio", "/").hasAnyRole("USER", "ADMIN").
		anyRequest().authenticated().and().
		formLogin().successHandler(successHandler)
		.loginPage("/springLogin2").permitAll().and().logout().permitAll().and().exceptionHandling().accessDeniedPage("/error_403");
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	public void configurerGlobal(AuthenticationManagerBuilder builder) throws Exception{
		builder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

}
