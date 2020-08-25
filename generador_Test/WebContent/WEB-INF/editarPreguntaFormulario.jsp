<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Formulario de editar Pregunta</title>
<script>
	function obtenerParametro(){
		var pregunta = getParameterByName('pregunta');
		alert(pregunta);
		var preguntaObj = Json.parse(pregunta);
		alert(preguntaObj);
	}
</script>
</head>
<body onload="obtenerParametro();">

</body>
</html>