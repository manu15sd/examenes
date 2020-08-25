<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Generador de tests - Academia Postal</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>
<script>
	function advertencia(){
		swal({
			  title: "Función no disponible",
			  text: "Por favor, compre la versión completa para disfrutar de todas las funcionalidades de la aplicación",
			  icon: "info",
			  button: "Aceptar",
			});
	}
</script>
<body>
	<nav class="navbar navbar-dark bg-dark" style="margin:auto">
  		<a class="navbar-brand" href="inicio.jsp">Tests Oposiciones</a>
	</nav>
		<div class="row" style="width:90%;margin:auto;margin-top:5%;height:20vh">
		
		  <div class="col-sm-6">
		    <div class="card" style="height:23vh">
		      <div class="card-body">
		        <h5 class="card-title">Generar Examen</h5>
		        <p class="card-text">Generar un examen personalizado pudiendo escoger si hacer un test general o por temas de cualquier oposicion disponible en la Academia Postal</p>
		        <form action="cargarDatos">
		        	<input type="hidden" name="nombre" value="generar"/>
		        	<input type="submit" class="btn btn-primary" value="Ir"/>
		        </form>
		      </div>
		    </div>
		  </div>
		 
		  <div class="col-sm-6" >
		    <div class="card" style="height:23vh">
		      <div class="card-body">
		        <h5 class="card-title">Añadir Pregunta</h5>
		        <p class="card-text" style="margin-bottom:7vh">Añadir una pregunta de examen a nuestra base de datos</p>
		        <form action="cargarDatos">
		        	<input type="hidden" name="nombre" value="anhadir"/>
		        	<input type="submit" class="btn btn-primary" value="Ir"/>
		        </form>
		      </div>
		    </div>
		  </div>
		  
		</div>
		
		<div class="row" style="width:90%;margin:auto;margin-top:10%;height:20vh">
		
		  <div class="col-sm-6">
		    <div class="card" style="height:23vh">
		      <div class="card-body">
		        <h5 class="card-title">Editar Preguntas</h5>
		        <p class="card-text">Buscar preguntas para editar el enunciado, las respuestas, el autor o los grupos de las mismas</p>
		        <form action="cargarDatos">
		        	<input type="hidden" name="nombre" value="editar"/>
		        	<input type="submit" class="btn btn-primary" value="Ir"/>
		        </form>
		      </div>
		    </div>
		  </div>
		 
		  <div class="col-sm-6" >
		    <div class="card" style="height:23vh">
		      <div class="card-body">
		        <h5 class="card-title">Añadir Preguntas desde un documento word</h5>
		        <p class="card-text" style="margin-bottom:7vh">Añadir una pregunta de examen a nuestra base de datos</p>
		        <form action="cargarDatos">
		        	<input type="hidden" name="nombre" value="anhadirDoc"/>
		        	<input type="submit" class="btn btn-primary" value="Ir"/>
		        </form>
		      </div>
		    </div>
		  </div>
		  
		</div>
		
		

</body>
</html>