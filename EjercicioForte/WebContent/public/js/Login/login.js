$(document).ready(function() {

});

function inicioSesion(){

const data = JSON.stringify({
  Usuario: $("#user").val(),
  Password: $("#pass").val()
  
});

$.ajax({
	  url: 'http://apps01.forteinnovation.mx:8590/api/auth/login',
	  method: 'post',
	  contentType: 'application/json; charset=utf-8',
	  data: data,
	  success(data, text) {
	    console.log(data);
	    var data = {
		  "code": data.code,
		  "token": data.data.token
		};

		// Lo parseamos a texto para guardarlo en el localStorage
		localStorage.setItem("data", JSON.stringify(data));

	    window.location.href=("principal");
//	     $.post('principal', function(data) { 
//	     	
//	    }); 
	  },
	  error(request, status, error) {
	    console.log(error)
	  }
});



}