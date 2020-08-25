<%@page import="generador.modelo.PregTemas"%>
<%@page import="generador.modelo.PregLegislacion"%>
<%@page import="generador.modelo.Pregunta"%>
<%@page import="generador.modelo.Ley"%>
<%@page import="generador.modelo.Tema"%>
<%@page import="generador.modelo.Grupo"%>
<%@page import="generador.modelo.Autor"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Buscar Preguntas - Academia Postal</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.0.0/animate.min.css"/>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<script src="https://kit.fontawesome.com/a485401a24.js" crossorigin="anonymous"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script>
var contador = 1;


function anhadirCampo(){
	var elemento = document.getElementById("elemento1");
	var termino = document.getElementById("termino1");
	var valor = document.getElementById("valor1");
	
	var elemento2 = document.getElementById("elemento2");
	var termino2 = document.getElementById("termino2");
	var valor2 = document.getElementById("valor2");
	
	var elemento3 = document.getElementById("elemento3");
	var termino3 = document.getElementById("termino3");
	var valor3 = document.getElementById("valor3");

	switch(contador){
	
		case 1:
			elemento.style.display = "flex";
			termino.disabled = false;
			valor.disabled = false;
			
			elemento2.style.display = "flex";
			termino2.disabled = false;
			valor2.disabled = false;
			
			elemento3.style.display = "none";
			termino3.disabled = true;
			valor3.disabled = true;
			
			contador++;
			break;
		case 2:
			elemento.style.display = "flex";
			termino.disabled = false;
			valor.disabled = false;
			
			elemento2.style.display = "flex";
			termino2.disabled = false;
			valor2.disabled = false;
			
			elemento3.style.display = "flex";
			termino3.disabled = false;
			valor3.disabled = false;
			
			contador++;
			break;
	}
}

function quitarCampo(id){
	var elemento = document.getElementById("elemento"+id);
	var termino = document.getElementById("termino"+id);
	var valor = document.getElementById("valor"+id);
	if(contador>1){
		elemento.style.display = "none";
		termino.disabled = true;
		valor.disabled = true;
	}
	contador--;
}

function validarFormulario(){

	document.formulario.submit();
}
function mostrarPagina(id){
	var cuerpo1 = document.getElementById("cuerpo");
	var cuerpo2 = document.getElementById("cuerpo2");
	var cuerpo3 = document.getElementById("cuerpo3");
	var cuerpo4 = document.getElementById("cuerpo4");
	switch(id){
		case "cuerpo":
			cuerpo1.style.display = "table-row-group";
			cuerpo2.style.display = "none";
			cuerpo3.style.display = "none";
			cuerpo4.style.display = "none";
			break;
			
		case "cuerpo2":
			cuerpo1.style.display = "none";
			cuerpo2.style.display = "table-row-group";
			cuerpo3.style.display = "none";
			cuerpo4.style.display = "none";
			break;
			
		case "cuerpo3":
			cuerpo1.style.display = "none";
			cuerpo2.style.display = "none";
			cuerpo3.style.display = "table-row-group";
			cuerpo4.style.display = "none";
			break;
			
		case "cuerpo4":
			cuerpo1.style.display = "none";
			cuerpo2.style.display = "none";
			cuerpo3.style.display = "none";
			cuerpo4.style.display = "table-row-group";
			break;
	}
}

	function mostrarError(){
		<%if(request.getAttribute("error")!=null){%>
			<%boolean error = (boolean) request.getAttribute("error");%>
			<%if(error!=true){ %>
				swal({
				  title: "Error",
				  text: "Insercion incorrecta!",
				  icon: "error",
				  button: "Aceptar",
				});
			<%}else{%>
			swal({
				  title: "Éxito",
				  text: "Insercion correcta!",
				  icon: "success",
				  button: "Aceptar",
				});
		<%}}%>
	}


</script>
</head>
<body onload="mostrarError()">
	<nav class="navbar navbar-dark bg-dark" style="margin:auto">
  		<a class="navbar-brand" href="inicio.jsp">Tests Oposiciones</a>
	</nav>
	<form method="get" action="BuscarPregunta" name="formulario" id="formulario"  style="width:70vw;margin:auto;margin-top:5vh">
		<div id="campos">
			<div class="row mt-3" id="elemento1">
				<div class="col-4">
					<select class="form-control" id="termino1" name="termino1">
						<option value="nombre">Autor</option>
						<option value="enunciado">Enunciado</option>
						<option value="nombreoposicion">Grupo</option>
						<option value="nombretema">Tema</option>
						<option value="subtema">Subtema</option>
						<option value="ley">Ley</option>
						<option value="art">Articulo</option>
						<option value="disp">Disposicion adicional</option>
					</select>
				</div>
				<div class="col-4">
					<input type="text" class="form-control" name="valor1" id="valor1">
				</div>
				<div class="col-1">
					<button type="button" class="btn btn-success" onClick="anhadirCampo();">
						<i class="far fa-plus-square"></i>
					</button>
				</div>
				<div class="col-1">
					<button type="button" class="btn btn-danger" onClick="quitarCampo('1')" >
						<i class="fas fa-trash-alt"></i>
					</button>
				</div>
			</div>
			<div class="row mt-3" id="elemento2" style="display:none">
				<div class="col-4">
					<select class="form-control" id="termino2" name="termino2" disabled>
						<option value="nombre">Autor</option>
						<option value="enunciado">Enunciado</option>
						<option value="nombreoposicion">Grupo</option>
						<option value="nombretema">Tema</option>
						<option value="subtema">Subtema</option>
						<option value="ley">Ley</option>
						<option value="art">Articulo</option>
						<option value="disp">Disposicion adicional</option>
					</select>
				</div>
				<div class="col-4">
					<input type="text" class="form-control" name="valor2" id="valor2">
				</div>
				<div class="col-1">
					<button type="button" class="btn btn-success" onClick="anhadirCampo();">
						<i class="far fa-plus-square"></i>
					</button>
				</div>
				<div class="col-1">
					<button type="button" class="btn btn-danger" onClick="quitarCampo('2')" >
						<i class="fas fa-trash-alt"></i>
					</button>
				</div>
			</div>
			<div class="row mt-3" id="elemento3" style="display:none">
				<div class="col-4">
					<select class="form-control" id="termino3" name="termino3" disabled>
						<option value="nombre">Autor</option>
						<option value="enunciado">Enunciado</option>
						<option value="nombreoposicion">Grupo</option>
						<option value="nombretema">Tema</option>
						<option value="subtema">Subtema</option>
						<option value="ley">Ley</option>
						<option value="art">Articulo</option>
						<option value="disp">Disposicion adicional</option>
					</select>
				</div>
				<div class="col-4">
					<input type="text" class="form-control" name="valor3" id="valor3">
				</div>
				<div class="col-1">
					<button type="button" class="btn btn-success" onClick="anhadirCampo();">
						<i class="far fa-plus-square"></i>
					</button>
				</div>
				<div class="col-1">
					<button type="button" class="btn btn-danger" onClick="quitarCampo('3')" >
						<i class="fas fa-trash-alt"></i>
					</button>
				</div>
			</div>
			
		</div>
		
		<div class="row">
			<button class="btn btn-primary btn-lg mt-3" onClick="validarFormulario();" style="text-align:center;margin:auto;">Buscar</button>
		</div>
		
		
	</form>
	<hr/>
	<div id="resultados">
		<%if(request.getAttribute("listaPreguntas")!=null){ %>
		<% List<Pregunta> listaPreguntas = (List<Pregunta>)request.getAttribute("listaPreguntas");%>
		<table class="table" style="width:80%;margin:auto;border:black solid 1px;">
		  <thead class="thead-dark">
		    <tr>
		      <th scope="col">Enunciado</th>
		      <th scope="col">Tema/Ley</th>
		      <th scope="col">Autor</th>
		      <th scope="col">Fecha</th>
		      <th style="text-align:center" scope="col">#</th>
		    </tr>
		  </thead>
		  <tbody id="cuerpo" class="animate_animated animate__bounce">
		  	  <%for(int i=0; i<listaPreguntas.size() && i<10;i++) {%>
			  <%if(listaPreguntas.get(i) instanceof PregLegislacion){%>
				<%PregLegislacion pregL = (PregLegislacion)listaPreguntas.get(i); %>	 
					  	<tr>
					      <td><%=pregL.getEnunciado() %></td>
					      <td><%=pregL.getLey().getNombre() %></td>
					      <td><%=pregL.getAutor().getNombre() %></td>
					      <td><%=pregL.getFecha() %></td>
					      <td><form method="get" action="BuscarPreguntaPorId"><input type="hidden" name="tipo" value="ley" /><input name="idPregunta" type="hidden" value="<%=pregL.getIdPregunta()%>"><button class="btn btn-primary">Editar</button></form></td>
					      
					    </tr>
			   
			   <%} if(listaPreguntas.get(i) instanceof PregTemas){%>
					  <% PregTemas pregT = (PregTemas)listaPreguntas.get(i); %>
					  <tr>
					      <td><%=pregT.getEnunciado() %></td>
					      <td><%=pregT.getTema().getNombre() %></td>
					      <td><%=pregT.getAutor().getNombre() %></td>
					      <td><%=pregT.getFecha()%></td>
					      <td><form method="get" action="BuscarPreguntaPorId"><input type="hidden" name="tipo" value="temas" /><input name="idPregunta" type="hidden" value="<%=pregT.getIdPregunta()%>"><button class="btn btn-primary">Editar</button></form></td>
				
					    </tr>
				  <%} %>
			    
			   <%} %>
		  </tbody>
		  <tbody id="cuerpo2" style="display:none" class="animate_animated animate__fadeIn">	
		  	  <%for(int i=10; i<listaPreguntas.size() && i<20;i++) {%>
			  <%if(listaPreguntas.get(i) instanceof PregLegislacion){%>
				  <%  PregLegislacion pregL = (PregLegislacion)listaPreguntas.get(i); %>	 
					  	<tr>
					      <td><%=pregL.getEnunciado() %></td>
					      <td><%=pregL.getLey().getNombre() %></td>
					      <td><%=pregL.getAutor().getNombre() %></td>
					      <td><%=pregL.getFecha() %></td>
					      <td><form method="get" action="BuscarPreguntaPorId"><input type="hidden" name="tipo" value="ley" /><input name="idPregunta" type="hidden" value="<%=pregL.getIdPregunta()%>"><button class="btn btn-primary">Editar</button></form></td>
					      
					    </tr>
			   
				  <%} if(listaPreguntas.get(i) instanceof PregTemas){%>
					  <% PregTemas pregT = (PregTemas)listaPreguntas.get(i); %>
					  <tr>
					      <td><%=pregT.getEnunciado() %></td>
					      <td><%=pregT.getTema().getNombre() %></td>
					      <td><%=pregT.getAutor().getNombre() %></td>
					      <td><%=pregT.getFecha() %></td>
					      <td><form method="get" action="BuscarPreguntaPorId"><input type="hidden" name="tipo" value="temas" /><input name="idPregunta" type="hidden" value="<%=pregT.getIdPregunta()%>"><button class="btn btn-primary">Editar</button></form></td>
					    </tr>
				  <%} %>
			    
			   <%} %>
		  </tbody>
		  <tbody id="cuerpo3" style="display:none" class="animate_animated animate__fadeIn">	
		  	  <%for(int i=20; i<listaPreguntas.size() && i<30;i++) {%>
			  <%if(listaPreguntas.get(i) instanceof PregLegislacion){%>
				  <%  PregLegislacion pregL = (PregLegislacion)listaPreguntas.get(i); %>	 
					  	<tr>
					      <td><%=pregL.getEnunciado() %></td>
					      <td><%=pregL.getLey().getNombre() %></td>
					      <td><%=pregL.getAutor().getNombre() %></td>
					      <td><%=pregL.getFecha() %></td>
					      <td><form method="get" action="BuscarPreguntaPorId"><input type="hidden" name="tipo" value="ley" /><input name="idPregunta" type="hidden" value="<%=pregL.getIdPregunta()%>"><button class="btn btn-primary">Editar</button></form></td>
					      
					    </tr>
			   
				  <%} if(listaPreguntas.get(i) instanceof PregTemas){%>
					  <% PregTemas pregT = (PregTemas)listaPreguntas.get(i); %>
					  <tr>
					      <td><%=pregT.getEnunciado() %></td>
					      <td><%=pregT.getTema().getNombre() %></td>
					      <td><%=pregT.getAutor().getNombre() %></td>
					      <td><%=pregT.getFecha() %></td>
					      <td><form method="get" action="BuscarPreguntaPorId"><input type="hidden" name="tipo" value="temas" /><input name="idPregunta" type="hidden" value="<%=pregT.getIdPregunta()%>"><button class="btn btn-primary">Editar</button></form></td>
					    </tr>
				  <%} %>
			    
			   <%} %>
		  </tbody>
		  <tbody id="cuerpo4" style="display:none" class="animate_animated animate__fadeIn">	
		  	  <%for(int i=30; i<listaPreguntas.size() && i<40;i++) {%>
			  <%if(listaPreguntas.get(i) instanceof PregLegislacion){%>
				  <%  PregLegislacion pregL = (PregLegislacion)listaPreguntas.get(i); %>	 
					  	<tr>
					      <td><%=pregL.getEnunciado() %></td>
					      <td><%=pregL.getLey().getNombre() %></td>
					      <td><%=pregL.getAutor().getNombre() %></td>
					      <td><%=pregL.getFecha() %></td>
					      <td><form method="get" action="BuscarPreguntaPorId"><input type="hidden" name="tipo" value="ley" /><input name="idPregunta" type="hidden" value="<%=pregL.getIdPregunta()%>"><button class="btn btn-primary">Editar</button></form></td>
					      
					    </tr>
			   
				  <%} if(listaPreguntas.get(i) instanceof PregTemas){%>
					  <% PregTemas pregT = (PregTemas)listaPreguntas.get(i); %>
					  <tr>
					      <td><%=pregT.getEnunciado() %></td>
					      <td><%=pregT.getTema().getNombre() %></td>
					      <td><%=pregT.getAutor().getNombre() %></td>
					      <td><%=pregT.getFecha() %></td>
					      <td><form method="get" action="BuscarPreguntaPorId"><input type="hidden" name="tipo" value="temas" /><input name="idPregunta" type="hidden" value="<%=pregT.getIdPregunta()%>"><button class="btn btn-primary">Editar</button></form></td>
					    </tr>
				  <%} %>
			    
			   <%} %>
		  </tbody>
		  </table>
		  <div aria-label="Page navigation example" class="mt-3 mb-4" style="margin:auto;width:10%;">
			  <ul class="pagination" >
			    <li class="page-item"><a class="page-link" onClick="mostrarPagina('cuerpo')">1</a></li>
			    <%if(listaPreguntas.size()>10){%>
			    <li class="page-item"><a class="page-link" onClick="mostrarPagina('cuerpo2')">2</a></li>
			    <%} %>
			    <%if(listaPreguntas.size()>20){%>
			    <li class="page-item"><a class="page-link" onClick="mostrarPagina('cuerpo3')">3</a></li>
			    <%} %>
			    <%if(listaPreguntas.size()>30){%>
			    <li class="page-item"><a class="page-link" onClick="mostrarPagina('cuerpo4')">4</a></li>
			    <%} %>
			   
			  </ul>
		</div>
		<%}%>
		  
		
				
		
	</div>
</body>
</html>