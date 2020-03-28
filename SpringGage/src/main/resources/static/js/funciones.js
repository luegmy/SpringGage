
/*busqueda por filtro*/
$("#busqueda").on(
		"keyup",
		function() {
			var inputBusqueda = $(this).val().toLowerCase();
			$("#tablaCliente tr").filter(
					function() {
						$(this).toggle(
								$(this).text().toLowerCase().indexOf(
										inputBusqueda) > -1)
					});
		});

$("#busqueda").on(
		"keyup",
		function() {
			var inputBusqueda = $(this).val().toLowerCase();
			$("#tablaProducto tr").filter(
					function() {
						$(this).toggle(
								$(this).text().toLowerCase().indexOf(
										inputBusqueda) > -1)
					});
		});



/*Modal para eliminar confirmacion*/
$('.btn-eliminar').on('click', function(e) {
	e.preventDefault();
	var href = $(this).attr('href');
	$('#modalEliminar #btnEliminarSi').attr('href', href);
	$('#modalEliminar').modal();
});


