var tokenGL;

$(document).ready(function() {

	var dataFromlocalStorage = JSON.parse(localStorage.getItem("data"));
	tokenGL = dataFromlocalStorage.token;

	buscarClientes();
});


function deleteCliente(id){

  var mensaje = confirm("¿Seguro que deseas continuar?");
  if (mensaje) {
	const token = tokenGL;

	$.ajax({
	  url: 'http://apps01.forteinnovation.mx:8590/api/cliente/'+id,
	  method: 'delete',
	  beforeSend(request) {
	    request.setRequestHeader('Authorization', `Bearer ${token}`);
	  },
	  success(data, text) {
	    console.log(data);
        if(data.code==200){
                  alert("¡Se eliminó cliente!");
                  //setTimeout(location.reload.bind(location), 400);
                  buscarClientes();
	     }else {
	          alert("Error al borrar cliente");
	     }
	  },
	  error(request, status, error) {
	    console.log(error)
	  }
	});
	
  }
  else {
  alert("¡No se eliminó cliente!");
  }
}

function agregarCliente(){

	$("#btnEditarCliente").hide();
	$("#btnGuardarCliente").show();

	 $("#myModalCliente").modal({                    
      "backdrop"  : "static",
      "keyboard"  : true,
      "show"      : true                    
    });

}

function buscarClientes(){ 

  	$('#contentClientes').empty();

	const token = tokenGL;
	// Datos de la petición
	const data = JSON.stringify({
	  var1: 'value',
	});
	
	$.ajax({
	  url: 'http://apps01.forteinnovation.mx:8590/api/clientes',
	  method: 'get',
	  beforeSend(request) {
	    request.setRequestHeader('Authorization', `Bearer ${token}`);
	  },
	  success(data, text) {
	    console.log(data);
        if(data.code==200){
            $.each(data.data, function( index, value ) {
              $('#contentClientes').append(
				'<tr>'+
				'<td hidden style="vertical-align : middle;">'+value.clienteId+'</td>'+
                '<td width=25% style="vertical-align : middle;">'+value.nombreCompleto+'</td>'+
                '<td width=25% style="vertical-align : middle;">'+value.correoElectronico+'</td>'+
                '<td style="vertical-align : middle;">'+value.edad+'</td>'+
                '<td style="vertical-align : middle;">'+value.estatusCliente+'</td>'+
                '<td style="vertical-align : middle;">'+value.limiteCredito+'</td>'+
                '<td width=25% style="vertical-align : middle;">'+
                    '<button onclick="mostrarDatosCliente('+value.clienteId+');" style="background-color: transparent;border: none;">'+
                        '<span id="" class="glyphicon glyphicon-pencil" style="color: #018179;margin-right: 5px; font-size: 150%;"></span></button>'+
                    '<button onclick="deleteCliente('+value.clienteId+');" id="" style="background-color: transparent;border: none;">'+
                        '<span class="glyphicon glyphicon-trash" style="color: #018179; font-size: 150%;"></span></button>'+
                '</td></tr>');
            });
            $('#tabla_clientes').DataTable();

          }else {
            $('#resulterror').slideDown("slow");
            $('#resulterror').html('<div class="alert alert-danger" role="alert">'+data.msg_error+'</div>');
            $('#resulterror').delay(2000).slideUp("slow");
          }
	  },
	  error(request, status, error) {
	    console.log(error)
	  }
	});

  
}