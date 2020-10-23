
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
<!--        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">-->
     <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com

/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
<link type="text/css" rel="stylesheet" href="public/css/principal.css" />
<script type="text/javascript" src="public/js/jquery-1.9.1.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<script type="text/javascript" src="public/js/Principal/principal.js"></script>

    <link type="text/css" rel="stylesheet" href="public/css/dataTables.bootstrap.min.css" />
    
    </head>
    <body>
    
<%--         <label id="usuarioIdtxt">${user.nombre}</label> --%>
<%--         <label id="nivelIdtxt">${user.nivel}</label> --%>
<%--         <label id="coordinadorIdtxt">${user.coordinador}</label> --%>
        
    <div id="contenido">
         
	    <div class="main" style="padding-right: 0px; padding-left: 0px;">
	 
	
			<div class="row">
			  <div class="col-md-offset-10 col-md-2 col-xs-offset-9 col-xs-3">
			  	<div class="col-md-offset-5" onclick="" align="right" id="mySidenav" class="sidenav" style="margin-right:10px; position: absolute; z-index: 1;">
	                 <img style="background-size: cover; width: 80%;" class="img-preview" src="../images/phonebook.png" >
	                 
				</div>
			  </div>
			</div>	
	
	
			<div class="col-md-10" style="margin-top: 30px;">	
				<div class="row">
					<center>
						<button class="btn" style="background-color: #018179; color: white;" onclick="agregarCliente();">Agregar cliente</button>
					</center>
				</div>
			
	  		</div>
	
			<div class="col-md-11" style="margin-top: 10px;">
				<p id="resultado">
			</div>
	
	  		<div class="col-md-9 col-md-offset-1" style="margin-top: 0px">
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
									  <option class="form-control" value="ACTIVO">ACTIVO</option>
								  	  <option class="form-control" value="INACTIVO">INACTIVO</option>
									  
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

 	<script
  src="https://code.jquery.com/jquery-3.4.1.min.js"
  integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
  crossorigin="anonymous"></script>
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
         <script src="public/js/jquery.dataTables.min.js" type="text/javascript"></script>
 	<script src="public/js/dataTables.bootstrap.min.js" type="text/javascript"></script> 
</html>
