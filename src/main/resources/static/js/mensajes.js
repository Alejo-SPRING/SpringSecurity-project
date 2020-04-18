function error(mensaje) {
	swal({
		title : mensaje,
		icon : 'error',
		button : 'Cerrar'
	});
}

function alerta(mensaje) {
	swal({
		title: mensaje,
		icon: "info",
		button: "Cerrar"
	});
}

function success(mensaje) {
	swal({
		title : mensaje,
		icon : 'success',
		button : 'Cerrar'
	});
}

function confirm(obj) {
	swal({
		icon : "warning",
		title : '¿Agregar producto?',
		text : 'Estas seguro de agregar el producto',
		buttons : {
			cancel:'Cerrar',
			catch : {
				text : "Agregar!",
				value : "agregar"
			}
		}
	}).then((value) => {
		if (value == "agregar") {
			window.location.href = "http://localhost:8080/cliente/agregarPro/" + obj;
		}
	});
}

function eliminarProducto(id) {
	swal({
		icon:"warning",
		title:"Eliminar producto!",
		text:"Estas segur de eliminar el producto!",
		buttons: {
			cancel:"Cerrar",
			catch: {
				text:"Eliminar!",
				value:"eliminar"
			}
		}
	}).then((value) => {
		if (value == "eliminar") {
			window.location.href = "http://localhost:8080/cliente/eliminarProd/" + id;
		}
	});
}