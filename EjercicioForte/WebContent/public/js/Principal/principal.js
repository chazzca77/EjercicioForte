
var tokenGL;
var listadoClientes = [];

$(function(){

	/* $("#txtFechaRep").datepicker({
	    dateFormat: 'dd / MM / yy',
	   monthNames: ["ENE", "FEB", "MAR","ABR", "MAY", "JUN", "JUL", "AGO", "SEP", "OCT", "NOV", "DIC"],   
	    firstDay: 1
	}).datepicker("setDate", new Date()); */

	$("#fechaE").datepicker({
	    dateFormat: 'dd/mm/yy',
	    firstDay: 1
	});


});

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

	$("#id_cliente").val(0);
	$("#nombreE").val('');
	$("#rfcE").val('');
	$('#correoE').val('');
	$('#telefonoE').val('');
	$('#fechaE').val('');
	//$("#selectTipoE").val('');  estatusClienteId
	$("#domicilioE").val('');
	$("#limiteE").val('');

}

function validarCorreo(correo){
	var dato = listadoClientes.findIndex(objeto => objeto.correoElectronico == correo);
	return (dato<0)?true:false;
}

function guardarCliente(){
		
	var clienteId = 0;
	var nombreCompleto = $("#nombreE").val();
	var rfc = $("#rfcE").val();
	var correoElectronico = $('#correoE').val();
	var telefonoMovil = $('#telefonoE').val();
	var fechaNacimiento = $('#fechaE').val();
	var estatusClienteId = $("#selectTipoE").val();  
	var domicilio = $("#domicilioE").val();
	var limiteCredito = $("#limiteE").val();
	
	
	if(!validarCorreo(correoElectronico)){
		alert("El correo ya existe, intente con uno nuevo");
		return;
	}
	
	const token = tokenGL;
	// Datos de la petición
	const data = JSON.stringify({
	    "clienteId": parseInt(clienteId),
		"nombreCompleto": nombreCompleto,
		"rfc": rfc,
		"fechaNacimiento": stringToDate($("#fechaE").val(),"dd/MM/yyyy","/").toISOString(),
		"correoElectronico": correoElectronico,
		"telefonoMovil": telefonoMovil,
		"domicilio": domicilio,
		"limiteCredito": parseInt(limiteCredito),
		"estatusClienteId": parseInt(estatusClienteId)
	});
	console.log(data);
	$.ajax({
	 type: 'POST',
     url: 'http://apps01.forteinnovation.mx:8590/api/cliente',
 	 contentType: 'application/json',

	 beforeSend: function (xhr) {
	    xhr.setRequestHeader('Authorization', 'Bearer '+token);
	 },
  	  data: data,
	  success(data, text) {
	    console.log(data);
        if(data.code==200){
         	$('#myModalCliente').modal('toggle');
			$('#resulterror').slideDown("slow");
            $('#resulterror').html('<div class="alert alert-success" role="alert">Cliente Agregado Correctamente</div>');
            $('#resulterror').delay(2000).slideUp("slow");

			buscarClientes();
          }else {
           alert("ocurrió un error");
          }
	  },
	  error(request, status, error) {
	    console.log(error)
	  }
	});
	
}

function editCliente(){
	
	var clienteId = $("#id_cliente").val();
	var nombreCompleto = $("#nombreE").val();
	var rfc = $("#rfcE").val();
	var correoElectronico = $('#correoE').val();
	var telefonoMovil = $('#telefonoE').val();
	var fechaNacimiento = $('#fechaE').val();
	var estatusClienteId = $("#selectTipoE").val();  
	var domicilio = $("#domicilioE").val();
	var limiteCredito = $("#limiteE").val();
	
	
	const token = tokenGL;
	// Datos de la petición
	const data = JSON.stringify({
	    "clienteId": parseInt(clienteId),
		"nombreCompleto": nombreCompleto,
		"rfc": rfc,
		"fechaNacimiento": stringToDate($("#fechaE").val(),"dd/MM/yyyy","/").toISOString(),
		"correoElectronico": correoElectronico,
		"telefonoMovil": telefonoMovil,
		"domicilio": domicilio,
		"limiteCredito": parseInt(limiteCredito),
		"estatusClienteId": parseInt(estatusClienteId)
	});
	console.log(data);
	$.ajax({
	 type: 'PUT',
     url: 'http://apps01.forteinnovation.mx:8590/api/cliente/'+clienteId,
 	 contentType: 'application/json',

	 beforeSend: function (xhr) {
	    xhr.setRequestHeader('Authorization', 'Bearer '+token);
	 },
  	  data: data,
	  success(data, text) {
	    console.log(data);
        if(data.code==200){
         	$('#myModalCliente').modal('toggle');
			$('#resulterror').slideDown("slow");
            $('#resulterror').html('<div class="alert alert-success" role="alert">Cliente Editado Correctamente</div>');
            $('#resulterror').delay(2000).slideUp("slow");

			buscarClientes();
          }else {
           alert("ocurrió un error");
          }
	  },
	  error(request, status, error) {
	    console.log(error)
	  }
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
			listadoClientes = data.data;
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

function mostrarDatosCliente(clienteId){ 


	$("#btnEditarCliente").show();
	$("#btnGuardarCliente").hide();

	$("#id_cliente").val('');
	$("#nombreE").val('');
	$("#rfcE").val('');
	$('#correoE').val('');
	$('#telefonoE').val('');
	$('#fechaE').val('');
	//$("#selectTipoE").val('');  estatusClienteId
	$("#domicilioE").val('');
	$("#limiteE").val('');

	const token = tokenGL;

	$.ajax({
	  url: 'http://apps01.forteinnovation.mx:8590/api/cliente/'+clienteId,
	  method: 'get',
	  beforeSend(request) {
	    request.setRequestHeader('Authorization', `Bearer ${token}`);
	  },
	  success(data, text) {
	    console.log(data);
        if(data.code==200){
			
	       	$("#id_cliente").val(data.data.clienteId);
			$("#nombreE").val(data.data.nombreCompleto);
			$("#rfcE").val(data.data.rfc);
			$('#correoE').val(data.data.correoElectronico);
			$('#telefonoE').val(data.data.telefonoMovil);
			
			var date = new Date(data.data.fechaNacimiento);
			var anio = date.getFullYear();
			var mes = date.getMonth()+1;
			var dia = date.getDate();
			
			if (dia < 10) {
			diadt = '0' + dia;
			}
			if (mes < 10) {
			  mes = '0' + mes;
}			
			$('#fechaE').val(dia+'/'+mes+"/"+anio);
			$("#selectTipoE option[value="+data.data.estatusClienteId+"]").attr("selected",true);
			$("#domicilioE").val(data.data.domicilio);
			$("#limiteE").val(data.data.limiteCredito);

				 $("#myModalCliente").modal({                    
			      "backdrop"  : "static",
			      "keyboard"  : true,
			      "show"      : true                    
			    });

          }else {
            $('#resulterror').slideDown("slow");
            $('#resulterror').html('<div class="alert alert-danger" role="alert">Error traer los datos</div>');
            $('#resulterror').delay(2000).slideUp("slow");
          }
	  },
	  error(request, status, error) {
	    console.log(error)
	  }
	});
  
}

function stringToDate(_date,_format,_delimiter)
{
        var formatLowerCase=_format.toLowerCase();
        var formatItems=formatLowerCase.split(_delimiter);
        var dateItems=_date.split(_delimiter);
        var monthIndex=formatItems.indexOf("mm");
        var dayIndex=formatItems.indexOf("dd");
        var yearIndex=formatItems.indexOf("yyyy");
        var month=parseInt(dateItems[monthIndex]);
        month-=1;
        var formatedDate = new Date(dateItems[yearIndex],month,dateItems[dayIndex]);
        return formatedDate;
}