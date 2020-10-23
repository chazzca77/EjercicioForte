
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
      <meta charset="UTF-8">
            
<meta http-equiv="Last-Modified" content="0">
 
<meta http-equiv="Cache-Control" content="no-cache, mustrevalidate">
 
<meta http-equiv="Pragma" content="no-cache">
    
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Forte</title>


    
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
<link href="https://use.fontawesome.com/releases/v5.0.7/css/all.css" rel="stylesheet">
<link href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/css/bootstrap-datetimepicker.css" rel="stylesheet"/>


<link rel="stylesheet" href="public/css/dataTables.bootstrap.min.css">
<!--  <script type="text/javascript" src="public/js/jquery-1.9.1.js"></script> -->
 <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script src="//cdnjs.cloudflare.com/ajax/libs/moment.js/2.15.2/moment.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/js/bootstrap-datetimepicker.min.js"></script>

<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js" integrity="sha384-aJ21OjlMXNL5UyIl/XNwTMqvzeRMZH2w8c5cRVpzpU8Y5bApTppSuUkhZXN0VxHd" crossorigin="anonymous"></script>

<script type="text/javascript" src="public/js/Principal/principal.js"></script>
	
<link type="text/css" rel="stylesheet" href="public/css/principal.css" />

    
    </head>
    <body>
    
<%--         <label id="usuarioIdtxt">${user.nombre}</label> --%>
<%--         <label id="nivelIdtxt">${user.nivel}</label> --%>
<%--         <label id="coordinadorIdtxt">${user.coordinador}</label> --%>
        
    <div id="contenido"  class="imagen-fondo" >
         
	    <div style="padding-right: 0px; padding-left: 0px;">
	 
	
			<div class="col-md-9 col-md-offset-1" style="margin-top: 30px; background:white">	
				<div class="row" style="    text-align: right; background:white"">
					<button class="btn" style="background-color: #018179; color: white;" onclick="agregarCliente();">Agregar cliente</button>
				</div>
			
	  		</div>
	
			<div class="col-md-9 col-md-offset-1" style="background:white;">
				<p id="resultado">
			</div>
	
	  		<div class="col-md-9 col-md-offset-1" style="margin-top: 0px; background:white">
				<p id="resulterror"/>
				<table class="table table-striped" id="tabla_clientes">
			  	<thead style="background-color: #018179;color: white;">
				  <tr>
				  	<th hidden>Id</th>
				    <th>Nombre</th>
				    <th>Correo electrónico</th> 
				    <th>Edad</th>
				    <th>Estatus</th>
				    <th>Límite de crédito</th>
				    <th>Acciones</th>
				  </tr>
				  </thead>
				  <tbody id="contentClientes" style="">
	
				  </tbody>
	
			  </table>	
			</div>
		</div>
         
		<!-- Modal -->
		<div class="modal fade" id="myModalCliente" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="myModalLabel">Agregar Cliente</h4>
		      </div>
		      <div class="modal-body">
		
		
		       	<div class="row" style="margin-top: 0px;">
						<form id="" enctype="multipart/form-data">
						<input type="text" id="id_cliente" name="id_cliente" hidden="true">
						<div class="col-md-12">
							<div class="form-group">
							    <label class="control-label col-sm-3" for="email">Nombre Completo:</label>
							     <input type="text" class="form-control" id="nombreE" name="nombreE" placeholder="">
							</div>
							<div class="form-group">
							    <label class="control-label col-sm-3" for="email">RFC:</label>
							     <input type="text" class="form-control" id="rfcE" name="rfcE" placeholder="">
							</div>
							<div class="form-group">
							    <label class="control-label col-sm-3" for="email">Correo electrónico:</label>
							     <input type="text" class="form-control" id="correoE" name="correoE" placeholder="">
							</div>
							<div class="form-group">
							    <label class="control-label col-sm-3" for="email">Teléfono:</label>
							     <input type="text" class="form-control" id="telefonoE" name="telefonoE" placeholder="">
							</div>
							<div class="form-group">
						        <label class="col-md-5 control-label">Fecha de Nacimiento:</label>
						        <input type="text" class="form-control date" name="fechaE" id="fechaE" />
						  	</div>		
						  	<div class="form-group">
							    <label class="control-label col-sm-3" for="">Estatus persona:</label>
						     	 <select class="form-control" id="selectTipoE" name="selectTipoE">
									  <option class="form-control" value="1">ACTIVO</option>
								  	  <option class="form-control" value="0">INACTIVO</option>
									  
								</select>
							</div>	
							<div class="form-group">
							    <label class="control-label col-sm-3" for="email">Domicilio:</label>
							     <input type="text" class="form-control" id="domicilioE" name="domicilioE" placeholder="">
							</div>
														<div class="form-group">
							    <label class="control-label col-sm-3" for="email">Limite de Crédito:</label>
							     <input type="text" class="form-control" id="limiteE" name="limiteE" placeholder="">
							</div>
						</div>
						</form>
		
			  		</div>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
		        <button id="btnGuardarCliente" hidden type="button" onclick="guardarCliente()" class="btn btn-primary">Guardar</button>
		        <button id="btnEditarCliente" hidden type="button" onclick="editCliente()" class="btn btn-primary">Editar</button>
		      </div>
		    </div>
		  </div>
		</div>
         
	</div>
              
            
    </body>

    <script src="public/js/jquery.dataTables.min.js" type="text/javascript"></script>
 	<script src="public/js/dataTables.bootstrap.min.js" type="text/javascript"></script> 
</html>
