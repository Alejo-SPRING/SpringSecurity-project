$(document).ready(function () {
	$("#buscarBtn").click(function () {
		$.ajax({
			type: "get",
			dataType: "json",
			url: "/cliente/buscar",
			data: $("#formBuscar").serialize(),
			success: function (response) {
				console.log(response);
			},
			error: function (response) {
				console.log(response);
			}
		});
	});
});