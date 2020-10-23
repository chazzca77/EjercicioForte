
<%-- <c:if test="${not empty user}"> --%>
<!-- 	<c:redirect url="http://localhost:8080/EjercicioForte/inicio"></c:redirect> -->
<!-- </c:if> -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Forte</title>
	
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com

/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
<link type="text/css" rel="stylesheet" href="public/css/login.css" />
<script type="text/javascript" src="public/js/jquery-1.9.1.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<script type="text/javascript" src="public/js/Login/login.js"></script>

</head>
<body>
 
<div class="container-fluid" align="center"> 
  <div class="row">
		<div class="col-sm-12 col-md-12" style="padding-right: 0px; padding-left: 0px;">
		 		<div class="imagen-fondo">
		 			
					<div class="absolute-center" >
						<h3 style="font-weight: bold;"class="text-login">Forte</h3>
						<br><br>
<!-- 						<form id="formLogin" class="" name="formLogin" -->
<!-- 							action="./login" method="post"> -->
							<div class="form-group">	
								<label style="font-weight: bold;" class="text-login" for="user">Usuario </label>
								<input id="user" name="user" style="background: transparent;text-align: center;" type="text" class="inCenter form-control" placeholder="Usuario" value="FORTEDEV">	
							</div>
							<div class="form-group">	
								<label style="font-weight: bold;" class="text-login" for="pass" id="labelPass">Inserte contraseña </label>
								<input id="pass" name="pass" style="background: transparent; text-align: center;" type="password" class="inCenter form-control" placeholder="Contraseña" value="Apply2019@pass">
							</div>
							<button id="entrar" class="btn" value="Entrar" title="Entrar" onclick="inicioSesion();"></button>
<!-- 						</form> -->
						<br>
						
<%-- 						<c:if test="${not empty param.errorLogin}"> --%>
<!-- 							<div> -->
<!-- 								<h5 class="aviso">Error en los datos o usuario no registrado</h5> -->
<!-- 							</div> -->
<!-- 						</c:if> -->
						<div class="version blanco">v1.0</div>
					</div>			
				</div>
  		</div>

	</div>
</div>
<script
  src="https://code.jquery.com/jquery-3.4.1.min.js"
  integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
  crossorigin="anonymous"></script>
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<!--  <script type="text/javascript" src="jquery-1.11.0.js"> -->
</script>

</body>
   

</html>

