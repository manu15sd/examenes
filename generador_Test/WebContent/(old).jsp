<%@page import="generador.modelo.pregLegislacionDAO"%>
<%@page import="generador.modelo.Grupo"%>
<%@page import="generador.modelo.Respuesta"%>
<%@page import="generador.modelo.Pregunta"%>
<%@page import="generador.modelo.PregTemas"%>
<%@page import="generador.modelo.PregLegislacion"%>
<%@page import="generador.modelo.Autor"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Editar Pregunta</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
</head>
<body>

	<nav class="navbar navbar-dark bg-dark" style="margin:auto">
  		<a class="navbar-brand" href="inicio.jsp">Tests Oposiciones</a>
	</nav>
   <form name="formulario" method="post" action="InsertarPregunta" style="width:70vw;margin:auto;border:solid 1px black;padding:2vw;margin-top:5vh">
   	 <%  Pregunta p = (Pregunta)request.getAttribute("pregunta");%>
	  <div class="form-group">
	    <label for="nombreAutor">Nombre</label>
	    <select class="form-control" id="nombreAutor" name="nombreAutor">
	 		<%if(request.getAttribute("listaAutor") != null){ %>
		      <% 	List<Autor> listaAutor = (List<Autor>) request.getAttribute("listaAutor");%>
		      <%  	for(Autor autor: listaAutor){ %>
		      		<% if(p.getAutor().equals(autor)){ %> 
		    		  <option value="<%= autor.getIdAutor() %>" selected><%= autor.getNombre() %></option>
		    		 <%}else{ %>
		    		 <option value="<%= autor.getIdAutor() %>"><%= autor.getNombre() %></option>
		      <%   }
		      }}%>
	    </select>
	  </div>
	<div class="form-group">
	    <label for="enunciado">Enunciado</label>
	    <textarea class="form-control" id="enunciado" name="enunciado" rows="3"><%= p.getEnunciado() %></textarea>
	  </div>
	  <%for(Respuesta r: p.getListaRespuestas()){ %>
	  	<%=r.getLetra() %>
	  	<%if(r.isEsCorrecta()){ %>
	  		<div class="form-group">
	    		<label for="respuestaCorrecta">Respuesta Correcta</label>
	    		<textarea class="form-control" id="respuestaCorrecta" name="respuestaCorrecta" rows="2"><%= r.getTexto() %></textarea>
	  		</div>
	  <%}else {%>
	  		<div class="form-group">
	    		<label for="respuesta2">Respuesta:</label>
	    		<textarea class="form-control" id="respuesta2" name="respuesta2" rows="2"><%= r.getTexto() %></textarea>
	 	 	</div>
	  <%} }%>
	  
	  <div class="form-group">
	    <label for="grupo">Oposicion</label>
	    <select class="form-control" id="grupo" name="grupo" onChange="cargarTemas()">
	    	
	      <%if(request.getSession().getServletContext().getAttribute("listaGrupos") != null){ %>
			<% 	List<Grupo> listaGrupos = (List<Grupo>)  request.getSession().getServletContext().getAttribute("listaGrupos");%>
			<%  for(Grupo grupo : listaGrupos){%>
				  <% if(grupo.getNombreOposicion().equals(p.getNombreOposicion())){ %>
				  <option value="<%= grupo.getIdOposicion() %>" selected><%= grupo.getNombreOposicion() %></option>
				  <%}else{ %>
	    		  <option value="<%= grupo.getIdOposicion() %>"><%= grupo.getNombreOposicion() %></option>
	      <% } } 
	      }%>
	    </select>
	  </div>
	  <% if(p instanceof PregLegislacion){ %>
	  
		  <div class="form-group" style="display:none" id="leyDiv">
		    <label for="ley">Ley</label>
		    <select class="form-control" id="ley" name="ley">
		      <option selected disabled>Selecciona la ley</option>
		    </select>
		  </div>
		  
		  <div class="form-group" style="display:none" id="tipoArtDiv">
		    <label for="tipoArt">Tipo:</label>
		    <select class="form-control" id="tipoArt" name="tipoArt">
		      <%if(((PregLegislacion)p).isDisposicion() != true) {%>
		      <option name="art" selected >Articulo</option>
		      <%}else{ %>
		      <option name="disp" selected >Disposicion</option>
		      <%} %>
		    </select>
		  </div>
		 <%if(((PregLegislacion)p).isDisposicion() != true) {%>
		  <div class="form-group mt-3" style="display:none" id="artDiv">
			    <label for="articulo">Articulo</label>
			    <input type="number" name="art" id="articulo"/>
		  </div>
		<%}else{ %>
		 <div class="form-group mt-3" style="display:none" id="dispDiv">
		    <label for="disposicion">Disposicion</label>
		    <input type="number" name="disp" id="disposicion"/>
		 </div>
	  <%}}if(p instanceof PregTemas){ %>
	  	
	  	<div class="form-group" style="display:none" id="temaDiv">
		    <label for="tema">Tema</label>
		    <select class="form-control" id="tema" name="tema" onChange="cargarSubtemas()">
		      <option selected disabled><%= ((PregTemas)p).getTema().getNombre() %></option>
		    </select>
	  	</div>
	  	<div class="form-group" style="display:none" id="subtemaDiv">
		    <label for="subtema">Subtema</label>
		    <select class="form-control" id="subtema" name="subtema">
	      		<option selected disabled><%=((PregTemas)p).getTema().getSubTema() %></option>
			</select>
	  </div>
	  <%} %>
	 


</body>
</html>