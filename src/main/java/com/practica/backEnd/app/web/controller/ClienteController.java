package com.practica.backEnd.app.web.controller;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.practica.backEnd.app.web.model.entity.Producto;
import com.practica.backEnd.app.web.model.entity.UsuarioDato;
import com.practica.backEnd.app.web.model.entity.UsuarioHasVenta;
import com.practica.backEnd.app.web.model.entity.Venta;
import com.practica.backEnd.app.web.model.services.IProductoService;
import com.practica.backEnd.app.web.model.services.IUploadFilesService;
import com.practica.backEnd.app.web.model.services.IUsuarioDatoService;
import com.practica.backEnd.app.web.model.services.IUsuarioHasVentaService;
import com.practica.backEnd.app.web.model.services.IVentaHasProductoService;
import com.practica.backEnd.app.web.model.services.IVentaService;

@Controller
@RequestMapping("/cliente")
@SessionScope
public class ClienteController {

	private UsuarioDato usuarioDato = new UsuarioDato();
	@Autowired
	private IUsuarioDatoService usuarioDatoDAO;
	@Autowired
	private IUsuarioHasVentaService ventaDAO;
	@Autowired
	private IVentaService venta2DAO;
	private UsuarioHasVenta usuarioHasVentaDTO = new UsuarioHasVenta();
	private Venta ventaDTO = new Venta();
	@Autowired
	private IProductoService productoServiceDAO;
	@Autowired
	private IVentaHasProductoService ventaHasProductoDAO;
	private Producto productoDTO = new Producto();
	@Autowired
	private IUploadFilesService uploadFilesDAO;
	private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);

	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping("/buscar")
	public @ResponseBody List<UsuarioDato> buscar(@RequestParam String text) {
		System.out.println(text);
		return usuarioDatoDAO.findAll();
	}
	
	public boolean hasRole(String rol) {
		//inicializamos el contexto de Spring Security
		SecurityContext context = SecurityContextHolder.getContext();
		//validamos si el contexto se inicio (osea traemos el logeo de la persona)
		if (context == null) {
			return false;
		}
		//inicializamos la Authentication con el context donde traemos los datos de la persona logeada
		Authentication auth = context.getAuthentication();
		if(auth == null) {
			return false;
		}
		Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
		/*for (GrantedAuthority grantedAuthority : authorities) {
			if(rol.equals(grantedAuthority.getAuthority())) {
				logger.info(auth.getName() + " tu rol es: " + grantedAuthority.getAuthority());
				return true;
			}
		}
		return false;*/
		//otra manera simplificada de buscar el rol del usuario logeado 
		return authorities.contains(new SimpleGrantedAuthority(rol));
	}
	
	@Secured("ROLE_ADMIN")
	@PostMapping("/crearProducto")
	public String crearProducto(@Valid Producto producto, BindingResult result, @RequestParam(name = "file") MultipartFile file, RedirectAttributes redirect, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Crear producto");
			return "cliente/productoForm";
		}
		if(file != null) {
			producto.setProductoImg(uploadFilesDAO.uploadFile(file));
			productoServiceDAO.create(producto);
			redirect.addFlashAttribute("mensaje", "success('Producto creado!');");
			return "redirect:/cliente/verFac";
		}
		redirect.addFlashAttribute("mensaje", "error('Por favor selecciona una imagen!');");
		return "redirect:/cliente/crearProducto";
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping("/crearProducto")
	public String crearProducto(Model model) {
		model.addAttribute("titulo", "Crear producto");
		model.addAttribute("producto", productoDTO);
		return "cliente/productoForm";
	}
	
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	@GetMapping("/productosForVenta/{id}")
	public String productosForVenta(@PathVariable Integer id, Model model) {
		if (id > 0 && id != null) {
			model.addAttribute("titulo", "Productos de la venta: " + id);
			model.addAttribute("productos", ventaHasProductoDAO.findProductosForVenta(venta2DAO.findForId(id)));
		}
		return "cliente/productosForVenta";
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/eliminarProd/{id}")
	public String eliminarProducto(@PathVariable Integer id, RedirectAttributes redirect) {
		if (id > 0 && id != null) {
			if (productoServiceDAO.eliminarProductoSelect(id)) {
				redirect.addFlashAttribute("mensaje", "success('Producto eliminado!');");
			} else {
				redirect.addFlashAttribute("mensaje", "error('El producto ha eliminar no es correcto!');");
			}
		} else {
			redirect.addFlashAttribute("mensaje", "error('El producto ha eliminar no es correcto!');");
		}
		return "redirect:/cliente/productos";
	}

	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	@GetMapping("/productos")
	public String productos(Model model) {
		model.addAttribute("listProductos", productoServiceDAO.getProductosSelected());
		model.addAttribute("totalListProduct", "Total productos seleccionados:" + productoServiceDAO.getProductosSelected().size());
		model.addAttribute("productos", productoServiceDAO.findAllProducto());
		model.addAttribute("titulo", "Productos");
		model.addAttribute("totalProduct", productoServiceDAO.totalProductos());
		return "cliente/productos";
	}

	@Secured("ROLE_ADMIN")
	@GetMapping("/agregarPro/{id}")
	public String agregarProduct(@PathVariable Integer id, RedirectAttributes redirect) {
		if (id > 0 && id != null) {
			productoServiceDAO.agregarProducto(productoServiceDAO.findForId(id));
			redirect.addFlashAttribute("mensaje", "success('El producto se agrego con exito!');");
		} else {
			redirect.addFlashAttribute("mensaje", "error('El producto seleccionado es incorrecto!');");
		}
		return "redirect:/cliente/productos";
	}

	@Secured("ROLE_ADMIN")
	@PostMapping("/regVenta")
	public String crearVenta(@Valid Venta venta, Model model, BindingResult result, RedirectAttributes redirect) {
		if (result.hasErrors()) {
			model.addAttribute("totalListProduct", "Total productos seleccionados:" + productoServiceDAO.getProductosSelected().size());
			model.addAttribute("titulo", "Registrar venta");
			return "cliente/form";
		} else {
			if (productoServiceDAO.getProductosSelected().isEmpty()) {
				redirect.addFlashAttribute("mensaje", "error('Por favor selecciona productos!');");
				return "redirect:/cliente/productos";
			}
			venta.setVentaTotal(productoServiceDAO.totalProductos());
			venta2DAO.create(venta);
			ventaHasProductoDAO.regProductosSelected(productoServiceDAO.getProductosSelected(), venta);
			productoServiceDAO.removeProductosSelected();
			usuarioHasVentaDTO.setUsuarioId(usuarioDato);
			usuarioHasVentaDTO.setVentaId(venta);
			ventaDAO.create(usuarioHasVentaDTO);
			usuarioHasVentaDTO = new UsuarioHasVenta();
			redirect.addFlashAttribute("mensaje", "success('Venta creada!');");
			return "redirect:/cliente/verFac";
		}
	}

	@Secured("ROLE_ADMIN")
	@GetMapping("/regVenta")
	public String crearVenta(Model model, RedirectAttributes redirect) {
		if (productoServiceDAO.getProductosSelected().isEmpty()) {
			redirect.addFlashAttribute("mensaje", "error('Por favor selecciona productos!');");
			return "redirect:/cliente/productos";
		}
		model.addAttribute("totalListProduct", "Total productos seleccionados:" + productoServiceDAO.getProductosSelected().size());
		model.addAttribute("venta", ventaDTO);
		model.addAttribute("titulo", "Registrar venta");
		return "cliente/form";
	}

	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping("/verFac")
	public String verFacturas(Model model, Authentication auth, HttpServletRequest request) {
		if(this.hasRole("ROLE_USER")) {
			logger.info("Hola " + auth.getName().concat("-Tienes acceso: USER"));
		} else {
			logger.info("Hola " + auth.getName().concat("-No Tienes acceso"));
		}
		
		//segunda manera para validar el rol de un usuario logeado
		SecurityContextHolderAwareRequestWrapper securityContext = new SecurityContextHolderAwareRequestWrapper(request, "ROLE_");
		if(securityContext.isUserInRole("ADMIN")) {
			logger.info("Hola2 " + auth.getName().concat("-Tienes acceso: ADMIN"));
		} else {
			logger.info("Hola2 " + auth.getName().concat("-No Tienes acceso"));
		}
		
		//tercer forma de validar el rol del usuario logeado
		if(request.isUserInRole("ROLE_ADMIN")) {
			logger.info("Hola3 " + auth.getName().concat("-Tienes acceso: ADMIN"));
		} else {
			logger.info("Hola3 " + auth.getName().concat("-No Tienes acceso"));
		}
		
		if(usuarioDato.getUsuarioDatosId() > 0) {
			model.addAttribute("ventas", ventaDAO.findVentasForUsuario(usuarioDato));
			model.addAttribute("titulo", "Ver ventas");
			return "cliente/menu";
		} else {
			return "redirect:/cliente/inicio";
		}
	}

	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping("/inicio")
	public String init(RedirectAttributes redirect, Model model, Authentication authentication) {
		usuarioDato = usuarioDatoDAO.findForId(5);
		if (usuarioDato != null) {
			model.addAttribute("usuario", usuarioDato);
			if(authentication != null) {
				model.addAttribute("mensaje", "Bienvenido " + authentication.getName());
			}
			return "redirect:/cliente/verFac";
		} else {
			redirect.addFlashAttribute("mensaje", "error('Por favor intenta ingresar de nuevo!');");
			return "redirect:/SpringApp/springLogin2";
		}
	}

}
