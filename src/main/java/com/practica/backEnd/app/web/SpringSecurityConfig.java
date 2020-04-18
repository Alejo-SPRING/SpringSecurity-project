package com.practica.backEnd.app.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.practica.backEnd.app.web.auth.handler.LoginSuccessHandler;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private LoginSuccessHandler successHandler;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/img/**", "/css/**", "/js/**", "/registrar").permitAll().
		antMatchers("/cliente/crearProducto/**", "/cliente/productos/**", "/cliente/eliminarProd/**").hasAnyRole("ADMIN").
		antMatchers("/cliente/verFac/**", "/cliente/inicio", "/").hasAnyRole("USER", "ADMIN").
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
		PasswordEncoder encoder = passwordEncoder();
		UserBuilder users =  User.builder().passwordEncoder(encoder::encode);
		builder.inMemoryAuthentication().withUser(users.username("alejo").password("1234").roles("ADMIN")).
		withUser(users.username("steve").password("123").roles("USER"));
	}

}
