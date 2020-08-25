<%@page import="generador.modelo.Tema"%>
<%@page import="generador.modelo.Ley"%>
<%@page import="generador.modelo.Autor"%>
<%@page import="java.util.List"%>
<%@page import="jdk.nashorn.internal.runtime.ListAdapter"%>
<%@page import="generador.modelo.Grupo"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Generador de tests - Academia Postal</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script>

	function cargarTemas(){
		
		
		var grupo = document.getElementById("grupo");
		var temaSel = document.getElementById("tema");
		var subtemaSel = document.getElementById("subtema");
		var artSel = document.getElementById("art");
		var leySel = document.getElementById("ley");
		var grupoSeleccionado = grupo[grupo.selectedIndex].value;
		
		
		leySel.innerHTML = "<option selected disabled value=''>Selecciona la ley</option>";
		
		temaSel.innerHTML = "<option selected disabled value=''>Selecciona el tema</option>";
		
		
		
		<%if(request.getSession().getServletContext().getAttribute("listaGrupos") != null){ %>
		<% 	List<Grupo> listaGrupos = (List<Grupo>) request.getSession().getServletContext().getAttribute("listaGrupos");%>
		<%  for(Grupo grupo : listaGrupos){%>
		
				if(<%= grupo.getIdOposicion()%> == grupoSeleccionado){
					<%for(Ley ley : grupo.getListaLey() ) {%>  
						leySel.innerHTML = leySel.innerHTML + "<option value='<%= ley.getNombre()%>'> <%= ley.getNombre() %></option>" ;
					<% } %>
					<%for(Tema tema : grupo.getListaTemas() ) {%>  
						temaSel.innerHTML = temaSel.innerHTML + "<option value='<%= tema.getIdTema()%>'> <%= tema.getNombre() %></option>";
						
				<% } %>
				}
		
		<%} }%>
	}
	
	function cargarSubtemas(){
		
		var grupo = document.getElementById("grupo");
		var temaSel = document.getElementById("tema");
		var subtemaSel = document.getElementById("subtema");

		var grupoSeleccionado = grupo[grupo.selectedIndex].value;
		var temaSeleccionado = temaSel[temaSel.selectedIndex].value;
		
		subtemaSel.innerHTML = "<option selected disabled value=''>Selecciona el subtema</option>";
		
		
		
		<%if(request.getSession().getServletContext().getAttribute("listaGrupos") != null){ %>
		<% 	List<Grupo> listaGrupos = (List<Grupo>)  request.getSession().getServletContext().getAttribute("listaGrupos");%>
		<%  for(Grupo grupo : listaGrupos){%>
		
				if(<%= grupo.getIdOposicion()%> == grupoSeleccionado){
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

		var nombreAutor = document.getElementById("nombreAutor");
		var enunciado = document.getElementById("enunciado");
		var respuestaCorrecta = document.getElementById("respuestaCorrecta");
		var respuesta2 = document.getElementById("respuesta2");
		var grupo = document.getElementById("grupo");
		var tipoLegisTemas = document.getElementsByName("legisTemas");
		var legislacion = document.getElementById("legislacion");
		var tema = document.getElementById("tema");
		var tipoArt = document.getElementsByName("tipoArt");
		var artRadio = document.getElementById("art");
		var dispoRadio = document.getElementById("dispo");
		var tema = document.getElementById("tema");
		var ley = document.getElementById("ley");
		var articulo = document.getElementById("articulo");
		var disposicion = document.getElementById("disposicion");
		var subtema = document.getElementById("subtema");
		var tipoSeleccionado="";
		var validos = [false, false, false, false, false, false, false, false, false, false, false, false];
		
		alert(validos);
		nombreAutor.options[nombreAutor.selectedIndex].text == "" ? (nombreAutor.classList.add("is-invalid"),validos[0]=false) : (nombreAutor.classList.remove("is-invalid"),validos[0]=true) ;
		enunciado.value == "" ? (enunciado.classList.add("is-invalid"),validos[1]=false) : (enunciado.classList.remove("is-invalid"),validos[1]=true);
		respuestaCorrecta.value == "" ? (respuestaCorrecta.classList.add("is-invalid"),validos[2]=false) : (respuestaCorrecta.classList.remove("is-invalid"),validos[2]=true);
		respuesta2.value == "" ? (respuesta2.classList.add("is-invalid"),validos[3]=false) : (respuesta2.classList.remove("is-invalid"),validos[3]=true);
		grupo.options[grupo.selectedIndex].text == "" || grupo.options[grupo.selectedIndex].value == ""  ? (grupo.classList.add("is-invalid"),validos[4]=false) : (grupo.classList.remove("is-invalid"),validos[4]=true);
		legislacion.checked == false && temas.checked == false ? (temas.classList.add("is-invalid"),legislacion.classList.add("is-invalid"),validos[5]=false) : (temas.classList.remove("is-invalid"),legislacion.classList.remove("is-invalid"),validos[5]=true);

		for(var button of tipoLegisTemas){
			
			if(button.checked){
				 tipoSeleccionado = button.value;
			}
			
		}
		
		if(tipoSeleccionado == 'temas'){
		
			tema.options[tema.selectedIndex].text == "" || tema.options[tema.selectedIndex].value == ""  ? (tema.classList.add("is-invalid"),validos[6]=false) : (tema.classList.remove("is-invalid"),validos[6]=true);
			subtema.options[subtema.selectedIndex].text == "" || subtema.options[subtema.selectedIndex].value == ""  ? (subtema.classList.add("is-invalid"),validos[7]=false) : (subtema.classList.remove("is-invalid"),validos[7]=true);
			
			ley.classList.remove("is-invalid");
			validos[8]=true;
			artRadio.classList.remove("is-invalid");
			dispoRadio.classList.remove("is-invalid");
			validos[9]=true;
			articulo.classList.remove("is-invalid");
			validos[10]=true;
			disposicion.classList.remove("is-invalid");
			validos[11]=true;
			
			
			
		}if(tipoSeleccionado == 'legislacion'){
			
			tipoSeleccionado = "";
			ley.options[ley.selectedIndex].text == "" || ley.options[ley.selectedIndex].value == ""  ? (ley.classList.add("is-invalid"),validos[8]=false) : (ley.classList.remove("is-invalid"),validos[8]=true);
			artRadio.checked == false && dispoRadio.checked == false ? (artRadio.classList.add("is-invalid"),dispoRadio.classList.add("is-invalid"),validos[9]=false) : (artRadio.classList.remove("is-invalid"),dispoRadio.classList.remove("is-invalid"),validos[9]=true);
			
			tema.classList.remove("is-invalid");
			validos[6]=true;
			subtema.classList.remove("is-invalid");
			validos[7]=true;
			
			for(var button of tipoArt){
				if(button.checked){
					 tipoSeleccionado = button.value;
				}
			}
			if(tipoSeleccionado == 'art'){
				
				articulo.value == "" ? (articulo.classList.add("is-invalid"),validos[10]=false) : (articulo.classList.remove("is-invalid"),validos[10]=true);
				
				disposicion.classList.remove("is-invalid");
				validos[11]=true;
				
			}if(tipoSeleccionado == 'dispo'){
				
				disposicion.value == "" ? (disposicion.classList.add("is-invalid"),validos[11]=false) : (disposicion.classList.remove("is-invalid"),validos[11]=true);
				
				articulo.classList.remove("is-invalid");
				validos[10]=true;
				
			}
		}
		
		alert(validos);

		for(var valido of validos){
			if(valido == false){
				return false;
			}
		}
		
		return true;

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
<body onload="mostrarError()" >
	<nav class="navbar navbar-dark bg-dark" style="margin:auto">
  		<a class="navbar-brand" href="inicio.jsp">Tests Oposiciones</a>
	</nav>
   <form name="formulario" method="post" action="InsertarPregunta"  onSubmit="return validarFormulario()" style="width:70vw;margin:auto;border:solid 1px black;padding:2vw;margin-top:5vh">
   
	  <div class="form-group">
	    <label for="nombreAutor">Nombre</label>
	    <select class="form-control " id="nombreAutor" name="nombreAutor">
	 		<%if(request.getSession().getServletContext().getAttribute("listaAutor") != null){ %>
		      <% 	List<Autor> listaAutor = (List<Autor>) request.getSession().getServletContext().getAttribute("listaAutor");%>
		      <%  	for(Autor autor: listaAutor){ %> 
		    		  <option value="<%= autor.getIdAutor() %>"><%= autor.getNombre() %></option>
		      <%   }
		      }%>
	    </select>
	    <div class="invalid-feedback">Por favor, selecciona una opción</div>
	  </div>
	  
	 <div class="form-group">
	    <label for="enunciado" >Enunciado</label>
	    <textarea class="form-control" id="enunciado" name="enunciado" rows="3"></textarea>
	    <div class="invalid-feedback">Por favor, añade un enunciado</div>
	  </div>
	  
	  <div class="form-group">
	    <label for="respuestaCorrecta">Respuesta Correcta</label>
	    <textarea class="form-control" id="respuestaCorrecta" name="respuestaCorrecta" rows="2"></textarea>
	    <div class="invalid-feedback">Por favor, introduce una respuesta correcta</div>
	  </div>
	  
	  <div class="form-group">
	    <label for="respuesta2">Respuesta 2</label>
	    <textarea class="form-control" id="respuesta2" name="respuesta2" rows="2"></textarea>
	    <div class="invalid-feedback">Por favor, introduce una respuesta incorrecta</div>
	  </div>
	  
	   <div class="form-group">
	    <label for="respuesta3">Respuesta 3</label>
	    <textarea class="form-control" id="respuesta3" name="respuesta3" rows="2"></textarea>
	  </div>
	  
	   <div class="form-group">
	    <label for="respuesta4">Respuesta 4</label>
	    <textarea class="form-control" id="respuesta4" name="respuesta4" rows="2"></textarea>
	  </div>
	  
	  <div class="form-group">
	    <label for="grupo">Oposicion</label>
	    <select class="form-control" id="grupo" name="grupo" onChange="cargarTemas()" >
	    	<option selected disabled value="">Selecciona un grupo</option>
	      <%if(request.getSession().getServletContext().getAttribute("listaGrupos") != null){ %>
			<% 	List<Grupo> listaGrupos2 = (List<Grupo>)  request.getSession().getServletContext().getAttribute("listaGrupos");%>
			<%  for(Grupo grupo : listaGrupos2){%>
	    		  <option value="<%= grupo.getIdOposicion() %>"><%= grupo.getNombreOposicion() %></option>
	      <% } 
	      }%>
	    </select>
	    <div class="invalid-feedback">Por favor, seleccione el grupo de la oposicion</div>
	  </div>
	  
	  <div class="form-group" onChange="mostrarSubapartados()">
	    <label for="exampleFormControlSelect2">Tipo:</label>
			<div class="form-check form-check-inline ml-5" >
			  <input class="form-check-input" type="radio" name="legisTemas" id="legislacion" value="legislacion">
			  <label class="form-check-label" for="inlineRadio1">Legislacion</label>
			</div>
			<div class="form-check form-check-inline">
			  <input class="form-check-input" type="radio" name="legisTemas" id="temas" value="temas">
			  <label class="form-check-label" for="inlineRadio2">Temas</label>
			  <div class="invalid-feedback ml-5">Por favor, seleccione una opción</div>
			</div>
	  </div>
	  
	  <div class="form-group" style="display:none" id="leyDiv">
	    <label for="ley">Ley</label>
	    <select class="form-control" id="ley" name="ley">
	      <option value="" selected disabled>Selecciona la ley</option>
	    </select>
	    <div class="invalid-feedback">Por favor, introduzca la ley</div>
	  </div>
	  
	  
	  <div class="form-group" style="display:none" id="temaDiv">
	    <label for="tema">Tema</label>
	    <select class="form-control" id="tema" name="tema" onChange="cargarSubtemas()">
	      <option selected disabled value="">Selecciona el tema</option>
	    </select>
	    <div class="invalid-feedback">Por favor, introduzca el tema</div>
	  </div>
	  
	  <div class="form-group" onChange="mostrarArtoDisp()" id="artDisp" style="display:none">
	    <label for="tipoArt">Tipo de artículo:</label>
			<div class="form-check form-check-inline ml-5">
			  <input class="form-check-input" type="radio" name="tipoArt" id="art" value="art">
			  <label class="form-check-label" for="inlineRadio1">Artículo</label>
			  
			</div>
			<div class="form-check form-check-inline">
			  <input class="form-check-input" type="radio" name="tipoArt" id="dispo" value="dispo">
			  <label class="form-check-label" for="inlineRadio2">Disposición</label>
			  <div class="invalid-feedback ml-5">Por favor, seleccione una opción</div>
			</div>
			
		    <div class="form-group mt-3" style="display:none" id="artDiv">
			    <label for="articulo">Articulo</label>
			    <input class="form-control" type="number" name="art" id="articulo" />
			    <div class="invalid-feedback">Por favor, introduzca el número de artículo</div>
			 </div>
			  
			 <div class="form-group mt-3" style="display:none" id="dispDiv" >
			    <label for="disposicion">Disposicion</label>
			    <input class="form-control" type="number" name="disp" id="disposicion"/>
			    <div class="invalid-feedback">Por favor, introduzca el número de la disposición</div>
			 </div>
	
	  </div>
	  

	  
	  <div class="form-group" style="display:none" id="subtemaDiv">
	    <label for="subtema">Subtema</label>
	    <select class="form-control" id="subtema" name="subtema">
	      <option value="" selected disabled>Selecciona el subtema</option>
	    </select>
	      <div class="invalid-feedback">Por favor, introduzca un subtema</div>
	  </div>
	  
	  <hr class="mb-4 mt-5">
      <button class="btn btn-primary btn-lg btn-block" >Enviar Pregunta</button>
	  
	</form>

</body>
</html>