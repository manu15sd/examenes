<%@page import="java.io.File"%>
<%@page import="generador.modelo.Pregunta"%>
<%@page import="generador.modelo.Tema"%>
<%@page import="generador.modelo.Ley"%>
<%@page import="generador.modelo.Grupo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Generador de tests - Academia Postal</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<script>

	function cargarTemas(){
	
		
		var grupo = document.getElementById("grupo");
		var temaSel = document.getElementById("tema");
		var subtemaSel = document.getElementById("subtema");
		var artSel = document.getElementById("art");
		var leySel = document.getElementById("ley");
		var grupoSeleccionado = grupo[grupo.selectedIndex].value;
		var generalPorDiv = document.getElementById("generalPorDiv");
		
		
		leySel.innerHTML = "<option selected disabled>Selecciona la ley</option>";
		
		temaSel.innerHTML = "<option selected disabled>Selecciona el tema</option>";
		
		generalPorDiv.innerHTML = "<div id='porcentajes'>"
		
	
		
		<%if(request.getSession().getServletContext().getAttribute("listaGrupos") != null){ %>
		<% 	List<Grupo> listaGrupos = (List<Grupo>)  request.getSession().getServletContext().getAttribute("listaGrupos");%>
		<%  for(Grupo grupo : listaGrupos){%>
				
				if("<%= grupo.getNombreOposicion() %>" == grupoSeleccionado){
					<%for(Ley ley : grupo.getListaLey() ) {%>  
						leySel.innerHTML = leySel.innerHTML + "<option value='<%= ley.getNombre()%>'> <%= ley.getNombre() %></option>" ;
						generalPorDiv.innerHTML = generalPorDiv.innerHTML + "<div class='form-group' id='porcentaje<%=ley.getNombre()%>' ><label class='mr-3' for='<%=ley.getNombre()%>'><%=ley.getNombre()%>:</label><input type='number' name='<%=ley.getNombre()%>'/></div>"
					<% } %>
					<%for(Tema tema : grupo.getListaTemas() ) {%>  
						temaSel.innerHTML = temaSel.innerHTML + "<option value='<%= tema.getIdTema()%>'> <%= tema.getNombre()%></option>";
						generalPorDiv.innerHTML = generalPorDiv.innerHTML + "<div class='form-group' id='porcentaje<%=tema.getIdTema()%>' ><label class='mr-3' for='<%=tema.getNombre()%>'><%=tema.getNombre()%>:</label><input type='number' name='<%=tema.getNombre()%>'/></div>"
						
				<% } %>
				}
			generalPorDiv.innerHTML = generalPorDiv.innerHTML + "</div>"
		<%} }%>
}
	function cargarSubtemas(){
		
		var grupo = document.getElementById("grupo");
		var temaSel = document.getElementById("tema");
		var subtemaSel = document.getElementById("subtema");
		

		var grupoSeleccionado = grupo[grupo.selectedIndex].value;
		var temaSeleccionado = temaSel[temaSel.selectedIndex].value;
		
		subtemaSel.innerHTML = "<option selected disabled>Selecciona el subtema</option><option value='todos'>Todos</option>";
		
		
		
		<%if(request.getSession().getServletContext().getAttribute("listaGrupos") != null){ %>
		<% 	List<Grupo> listaGrupos = (List<Grupo>)  request.getSession().getServletContext().getAttribute("listaGrupos");%>
		
		<%  for(Grupo grupo : listaGrupos){%>
		
				if("<%= grupo.getNombreOposicion()%>" == grupoSeleccionado){
					<%for(Tema tema : grupo.getListaTemas() ) {%>  
						if("<%=tema.getIdTema()%>" == temaSeleccionado) {
							<% for(String subtema : tema.getSubTema()){%>
							subtemaSel.innerHTML = subtemaSel.innerHTML + "<option value='<%= subtema%>'> <%= subtema %></option>";
							
						<% }%>
						}
						
						
				<% } %>
				}
		
		<%} }%>
	}
	
	function mostrarSubapartados(){
		var radio = document.getElementsByName("legisTemas");
		var tema = document.getElementById("temaDiv");
		var subtema = document.getElementById("subtemaDiv");
		var artDisp = document.getElementById("artDisp");
		var ley = document.getElementById("leyDiv");
		var botonSeleccionado;
		
		
		for(var button of radio){
			if(button.checked){
				 botonSeleccionado = button.value;
			}
		}
		
		if(botonSeleccionado == "temas"){
			tema.style.display = "block";
			subtema.style.display = "block";
			artDisp.style.display = "none";
			ley.style.display = "none";
		}else{
			artDisp.style.display = "block";
			ley.style.display = "block";
			tema.style.display = "none";
			subtema.style.display = "none";
		}
	}
	
	function mostrarArtoDisp(){
		
		var radio = document.getElementsByName("tipoArt");

		var artDisp = document.getElementById("artDisp");
		var art = document.getElementById("artDiv");
		var disp = document.getElementById("dispDiv");

		var botonSeleccionado;
		
		
		for(var button of radio){
			if(button.checked){
				 botonSeleccionado = button.value;
			}
		}
		
		if(botonSeleccionado == "art"){
			art.style.display = "block";
			disp.style.display = "none";

		}else{
			art.style.display = "none";
			disp.style.display = "block";
		}
	}
	
	function validarFormulario(){
		alert("hey");
		document.formulario.submit();
	}
	
	function mostrarTipo(){
		var radio = document.getElementsByName("genTematico");
		var tipoTematicoDiv = document.getElementById("tipoTematicoDiv");
		var tipoGeneralDiv = document.getElementById("tipoGeneralDiv");
		var botonSeleccionado;
		
		for(var button of radio){
			if(button.checked){
				 botonSeleccionado = button.value;
			}
		}
		
		if(botonSeleccionado == "tematico"){
			tipoTematicoDiv.style.display="block";
			tipoGeneralDiv.style.display="none";
		}else{
			tipoTematicoDiv.style.display="none";
			tipoGeneralDiv.style.display="block";
		}
	}
	
	function mostrarPorcentajes(){
		
		var porDiv = document.getElementById("generalPorDiv");
		var radio = document.getElementsByName("aleatorioPorcentaje");
		var botonSeleccionado;
		
		for(var button of radio){
			if(button.checked){
				
				 botonSeleccionado = button.value;
			}
		}

		if(botonSeleccionado == "porcentaje"){
			
			porDiv.style.display="block";

		}else{
			
			porDiv.style.display="none";

		}
	}
	
	function validarFormulario(){

		document.formulario.submit();
	}
	
	function clear(){
		localStorage.clear();
	}
	

	

</script>
</head>
<body>
	<nav class="navbar navbar-dark bg-dark" style="margin:auto">
  		<a class="navbar-brand" href="inicio.jsp">Tests Oposiciones</a>
	</nav>
	
	
	<div id="prueba"></div>
	<form action="GenerarExamen" method="post" name="formu" style="width:70vw;margin:auto;border:solid 1px black;padding:2vw;margin-top:5vh">
	
		<div class="form-group">
		    <label for="grupo">Oposicion</label>
		    <select class="form-control" id="grupo" name="grupo" onChange="cargarTemas()">
		    	<option selected disabled>Selecciona un grupo</option>
		      <%if(request.getSession().getServletContext().getAttribute("listaGrupos") != null){ %>
				<% 	List<Grupo> listaGrupos2 = (List<Grupo>)  request.getSession().getServletContext().getAttribute("listaGrupos");%>
				<%  for(Grupo grupo : listaGrupos2){%>
		    		  <option value="<%= grupo.getNombreOposicion() %>"><%= grupo.getNombreOposicion() %></option>
		      <% } 
		      }%>
		    </select>
	  	</div>
	  	
	    <div class="form-group mt-3" id="numPreguntasDiv">
			    <label for="numPreguntas">Número de preguntas: </label>
			    <input type="number" name="numPreguntas" id="numPreguntas"/>
	   </div>
	   
	   <div class="form-group" onChange="mostrarTipo()" id="tipoExamen">
	    	<label for="exampleFormControlSelect2">Examen:</label>
			<div class="form-check form-check-inline ml-5">
			  <input class="form-check-input" type="radio" name="genTematico" id="general" value="general">
			  <label class="form-check-label" for="inlineRadio1">General</label>
			</div>
			<div class="form-check form-check-inline">
			  <input class="form-check-input" type="radio" name="genTematico" id="tematico" value="tematico">
			  <label class="form-check-label" for="inlineRadio2">Temático</label>
			</div>
	   </div>
	   
	   
	   <div id="tipoGeneralDiv" style="display:none" >
	   
	   	 <div class="form-group" onChange="mostrarPorcentajes();" id="aleatorioPorcentaje">
	    	<label for="exampleFormControlSelect2">Selección de preguntas:</label>
			<div class="form-check form-check-inline ml-5">
			  <input class="form-check-input" type="radio" name="aleatorioPorcentaje" id="aleatorio" value="aleatorio">
			  <label class="form-check-label" for="inlineRadio1">Porcentajes aleatorios</label>
			</div>
			<div class="form-check form-check-inline">
			  <input class="form-check-input" type="radio" name="aleatorioPorcentaje" id="porcentaje" value="porcentaje">
			  <label class="form-check-label" for="inlineRadio2">Seleccionar porcentajes por temas y leyes</label>
			</div>
	   	 </div>
		<div id="generalPorDiv" style='display:none'>
			
		</div>
	   
	   </div>
	   
	   <div id="tipoTematicoDiv"  style="display:none">
	  	
		   <div class="form-group" onChange="mostrarSubapartados()">
		    	<label for="exampleFormControlSelect2">Tipo:</label>
				<div class="form-check form-check-inline ml-5">
				  <input class="form-check-input" type="radio" name="legisTemas" id="legislacion" value="legislacion">
				  <label class="form-check-label" for="inlineRadio1">Legislacion</label>
				</div>
				<div class="form-check form-check-inline">
				  <input class="form-check-input" type="radio" name="legisTemas" id="temas" value="temas">
				  <label class="form-check-label" for="inlineRadio2">Temas</label>
				</div>
		   </div>
		  
		  <div class="form-group" style="display:none" id="leyDiv">
		    <label for="ley">Ley</label>
		    <select class="form-control" id="ley" name="ley">
		      <option selected disabled>Selecciona la ley</option>
		    </select>
		  </div>
		  
		  <div class="form-group" style="display:none" id="temaDiv">
		    <label for="tema">Tema</label>
		    <select class="form-control" id="tema" name="tema" onChange="cargarSubtemas()">
		      <option selected disabled>Selecciona el tema</option>
		    </select>
		  </div>
		  
		  <div class="form-group" id="artDisp" style="display:none">
				
			    <div class="form-group mt-3" id="artDiv">
				    <label for="articulo">Desde el articulo número</label>
				    <input class="mr-5" type="number" name="artMin" id="articuloMin"/>
				    <label  for="articulo">Hasta el articulo número</label>
				    <input type="number" name="artMax" id="articuloMax"/>
				 </div>
				 <small id="artError" class="form-text text-muted">
	  				
				 </small>
				  
				 <div class="form-group mt-3" id="dispDiv">
				    <label class="mr-3" for="disposicion">Añadir disposiciones adicionales: </label>
				    <input type="checkBox" name="disp" id="disposicion"/>
				 </div>
				 <small id="dispError" class="form-text text-muted">
	  				
				 </small>
		  </div>
		  
	
		  
		  <div class="form-group" style="display:none" id="subtemaDiv">
		    <label for="subtema">Subtema</label>
		    <select class="form-control" id="subtema" name="subtema">
		      <option selected disabled>Selecciona el subtema</option>
	
		    </select>
		  </div>
	  </div>
	  
	  <hr class="mb-4 mt-5">
      <button class="btn btn-primary btn-lg btn-block" onClick="validarFormulario();">Generar Examen</button>
	  
	
	</form>


</body>
</html>