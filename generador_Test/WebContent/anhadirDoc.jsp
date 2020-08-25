<%@page import="generador.modelo.Respuesta"%>
<%@page import="generador.modelo.Pregunta"%>
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
<style>
	.rojo{
		 color:red;
	}
	.rojo-input{
		border: 2px solid red;
	}
	.rojo-check{
		outline: 1px solid red;
	}
</style>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bs-custom-file-input/dist/bs-custom-file-input.js"></script>
<script src="https://kit.fontawesome.com/a485401a24.js" crossorigin="anonymous"></script>
<script>

	function actualizar(word){
		bsCustomFileInput.init();
	}
	
	function cargarTemas(){
		
		
		var grupo = document.getElementById("grupo");
		var temaSel = document.getElementById("tema");
		var subtemaSel = document.getElementById("subtema");
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
		var ley = document.getElementById("leyDiv");
		var botonSeleccionado;
		<%if(request.getAttribute("listaPreguntas") != null){ %>
			<%List<Pregunta> listaPregunta = (List<Pregunta>) request.getAttribute("listaPreguntas");  %>
			<%for(int b=0; b<=listaPregunta.size();b++){%>
				var colArticulo<%=b%> = document.getElementById("colArticulo<%=b%>");
				var colDisposicion<%=b%> = document.getElementById("colDisposicion<%=b%>");
			<%}%>
		<%}%>
		
		
		for(var button of radio){
			if(button.checked){
				 botonSeleccionado = button.value;
			}
		}
		
		if(botonSeleccionado == "temas"){
			tema.style.display = "block";
			subtema.style.display = "block";
			ley.style.display = "none";
			<%if(request.getAttribute("listaPreguntas") != null){ %>
			<%List<Pregunta> listaPregunta = (List<Pregunta>) request.getAttribute("listaPreguntas");  %>
			<%for(int c=0; c<=listaPregunta.size();c++){%>
				colArticulo<%=c%>.style.display="none" ;
				colDisposicion<%=c%>.style.display="none";
			<%}%>
		<%}%>
			
			
		}else{
			ley.style.display = "block";
			tema.style.display = "none";
			subtema.style.display = "none";
			<%if(request.getAttribute("listaPreguntas") != null){ %>
			<%List<Pregunta> listaPregunta = (List<Pregunta>) request.getAttribute("listaPreguntas");  %>
			<%for(int d=0; d<=listaPregunta.size();d++){%>
				colArticulo<%=d%>.style.display="table-cell" ;
				colDisposicion<%=d%>.style.display="table-cell";
			<%}%>
		<%}%>
			
		}
	}
	
// 	function mostrarArtoDisp(){
		
// 		var radio = document.getElementsByName("tipoArt");

// 		var artDisp = document.getElementById("artDisp");
// 		var art = document.getElementById("artDiv");
// 		var disp = document.getElementById("dispDiv");

// 		var botonSeleccionado;
		
		
// 		for(var button of radio){
// 			if(button.checked){
// 				 botonSeleccionado = button.value;
// 			}
// 		}
		
// 		if(botonSeleccionado == "art"){
// 			art.style.display = "block";
// 			disp.style.display = "none";

// 		}else{
// 			art.style.display = "none";
// 			disp.style.display = "block";
// 		}
// 	}
	
	function validarFormulario(){
		
		var tamanho = 0;
		var nombreAutor = document.getElementById("nombreAutor");
		var grupo = document.getElementById("grupo");
		var tipoLegisTemas = document.getElementsByName("legisTemas");
		var legislacion = document.getElementById("legislacion");
		var tema = document.getElementById("tema");
		var subtema = document.getElementById("subtema");
		var ley = document.getElementById("ley");
		
		<%if(request.getAttribute("listaPreguntas") != null){ %>
		<%List<Pregunta> listaPregunta = (List<Pregunta>) request.getAttribute("listaPreguntas");  %>
		<%for(int e=0; e<=listaPregunta.size();e++){%>
			var colArticulo<%=e%> = document.getElementById("inputArt<%=e%>");
			var colDisposicion<%=e%> = document.getElementById("inputDisp<%=e%>");
			var resp<%=e%> = document.getElementsByName("respRadio<%=e%>");
			<%}%>
			tamanho = <%=listaPregunta.size()%>*2;
		<%}%>
		
		
		var tipoSeleccionado="";
		var respuestaSeleccionada="";
		var validos = [];
		for(var ab =0 ; ab < (tamanho +9); ab++){
			validos[ab]=false;
		}
		
		validos[1] = true;
		validos[2] = true;
		validos[3] = true;
		
		
		nombreAutor.options[nombreAutor.selectedIndex].text == "" ? (nombreAutor.classList.add("is-invalid"),validos[0]=false) : (nombreAutor.classList.remove("is-invalid"),validos[0]=true) ;
		grupo.options[grupo.selectedIndex].text == "" || grupo.options[grupo.selectedIndex].value == ""  ? (grupo.classList.add("is-invalid"),validos[4]=false) : (grupo.classList.remove("is-invalid"),validos[4]=true);
		legislacion.checked == false && temas.checked == false ? (temas.classList.add("is-invalid"),legislacion.classList.add("is-invalid"),validos[5]=false) : (temas.classList.remove("is-invalid"),legislacion.classList.remove("is-invalid"),validos[5]=true);
		
		<%if(request.getAttribute("listaPreguntas") != null){ %>
		<%List<Pregunta> listaPregunta = (List<Pregunta>) request.getAttribute("listaPreguntas");  %>
		<%for(int h=0; h<=listaPregunta.size();h++){%>
			for(boton of resp<%=h%>){
				if(boton.checked){
					respuestaSeleccionada = boton.value;
				}
			}
			var elements = document.getElementsByClassName("labelResp<%=h%>");
			if(respuestaSeleccionada == ""){
				validos[9+<%=h%>] = false;
				for(elemento of elements){
					elemento.classList.add("rojo");
				}
			}else{
				validos[9+<%=h%>] = true;
				for(elemento of elements){
					elemento.classList.remove("rojo");
				}
			}
			respuestaSeleccionada = "";
		<%}%>
			
		<%}%>
	
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
			<%if(request.getAttribute("listaPreguntas") != null){ %>
			<%List<Pregunta> listaPregunta = (List<Pregunta>) request.getAttribute("listaPreguntas");  %>
			
			<%for(int f=0; f<listaPregunta.size();f++){%>
				validos[<%=f%>+9+<%=listaPregunta.size()%>]=true;
				
			<%}%>
		<%}%>
			
		
			
		}if(tipoSeleccionado == 'legislacion'){
			
			tipoSeleccionado = "";
			ley.options[ley.selectedIndex].text == "" || ley.options[ley.selectedIndex].value == ""  ? (ley.classList.add("is-invalid"),validos[8]=false) : (ley.classList.remove("is-invalid"),validos[8]=true);
			//artRadio.checked == false && dispoRadio.checked == false ? (artRadio.classList.add("is-invalid"),dispoRadio.classList.add("is-invalid"),validos[9]=false) : (artRadio.classList.remove("is-invalid"),dispoRadio.classList.remove("is-invalid"),validos[9]=true);
			
			tema.classList.remove("is-invalid");
			validos[6]=true;
			subtema.classList.remove("is-invalid");
			validos[7]=true;
			<%if(request.getAttribute("listaPreguntas") != null){ %>
				<%List<Pregunta> listaPregunta = (List<Pregunta>) request.getAttribute("listaPreguntas");  %>
				
				<%for(int f=0; f<listaPregunta.size();f++){%>
					colArticulo<%=f%>.value == "" ? (colArticulo<%=f%>.classList.add("rojo-input"),validos[<%=f%>+9+<%=listaPregunta.size()%>]=false) : (colArticulo<%=f%>.classList.remove("rojo-input"),validos[<%=f%>+9+<%=listaPregunta.size()%>]=true);
					
					
				<%}%>
			<%}%>
	
		
			
		}
		
		

		for(var valido of validos){
			if(valido == false){
				return false;
			}
		}
		
		return true;

	}
	
	function mostrarPagina(id){
		<%if(request.getAttribute("listaPreguntas") != null){ %>
   			<%List<Pregunta> listaPregunta = (List<Pregunta>) request.getAttribute("listaPreguntas");  %>
   			<%for(int p = 0 ; p<listaPregunta.size();p+=10) {%>
   				var cuerpo<%=p/10%> = document.getElementById('cuerpo<%=p/10%>');
   			<%}%>
   			<%int k = listaPregunta.size()/10;%>
   			switch(id){
   			<%for(int l=0;l<=k;l++){%>
				case "cuerpo<%=l%>":
					cuerpo<%=l%>.style.display = "table-row-group";
					<%while(k>=0){%>
						<%if(k!=l){%>
							cuerpo<%=k%>.style.display = "none";
						<%}%>
						<%k--;%>
					<%}%>
					<% k = listaPregunta.size()/10;%>
					break;
   			}
			<%}}%>
			
	}

	function eliminar(id){
		var elemento = document.getElementById(id);
		elemento.parentNode.removeChild(elemento);
	}
	
	

</script>
</head>
<body>
	<nav class="navbar navbar-dark bg-dark" style="margin:auto">
  		<a class="navbar-brand" href="inicio.jsp">Tests Oposiciones</a>
	</nav>
   <form method="post" action="ParsearWord" name="formulario" id="formulario"  enctype="multipart/form-data"  style="width:30vw;margin:auto;margin-top:5vh">
   		<div class="custom-file">
  			<input type="file" class="custom-file-input" id="customFileLang" lang="es" name="word" onChange="actualizar(this);">
  			<label class="custom-file-label" for="customFileLang">Seleccionar Archivo</label>
  		</div>
  		<input class="btn btn-primary btn-lg btn-block mt-5" type="submit" value="Introducir documento"/>
   </form>
   <hr/>
   <div id="cuerpo">
   		<%if(request.getAttribute("listaPreguntas") != null){ %>
   			<%List<Pregunta> listaPregunta = (List<Pregunta>) request.getAttribute("listaPreguntas");  %>
   		<form metod="get" action="InsertarDoc" onSubmit="return validarFormulario()">
   			<div id="parteGeneral" style="width:60vw;margin:auto">
	   			<h1 style="text-align:center;font-weight: normal;font-size:2vw;">Datos generales</h1>
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
				  <div class="form-group" style="display:none" id="temaDiv">
					    <label for="tema">Tema</label>
					    <select class="form-control" id="tema" name="tema" onChange="cargarSubtemas()">
					      <option selected disabled value="">Selecciona el tema</option>
					    </select>
					    <div class="invalid-feedback">Por favor, introduzca el tema</div>
		  		  </div>
				  	<div class="form-group" style="display:none" id="subtemaDiv">
					    <label for="subtema">Subtema</label>
					    <select class="form-control" id="subtema" name="subtema">
					      <option value="" selected disabled>Selecciona el subtema</option>
					    </select>
					      <div class="invalid-feedback">Por favor, introduzca un subtema</div>
					</div>
		  
				  <div class="form-group" style="display:none" id="leyDiv">
				    <label for="ley">Ley</label>
				    <select class="form-control" id="ley" name="ley">
				      <option value="" selected disabled>Selecciona la ley</option>
				    </select>
				    <div class="invalid-feedback">Por favor, introduzca la ley</div>
				  </div>
			  </div>
			  <hr/>
			 
			 <h1 style="text-align:center;font-weight: normal;font-size:2vw;margin-bottom:1vh;">Preguntas</h1>
			 <h2 style="text-align:center;font-weight: normal;font-style: italic;font-size:1.2vw;margin-bottom:3vh;">Seleccione la respuesta correcta en cada caso</h2>
			 <table class="table" style="width:80%;margin:auto;border:black solid 1px;">
			 	<thead class="thead-dark">
				    <tr>
				      <th scope="col" colspan="3">Enunciado</th>
				      <th></th>
				      <th></th>
				      <th scope="col">Respuesta 1</th>
				      <th scope="col">Respuesta 2</th>
				      <th scope="col">Respuesta 3</th>
				      <th scope="col">Respuesta 4</th>
				      <th id="colArticulo0" style="display:none;text-align:center">Número artículo/disposición</th>
	   				  <th id="colDisposicion0" style="display:none">¿Es una disposición?</th>
				      <th style="text-align:center" scope="col">#</th>
				    </tr>
		  		</thead>
		 		
   				<%for(int i = 0 ; i<listaPregunta.size();i+=10) {%>
   					<%if(i==0){%>
   						<tbody id="cuerpo<%=i/10 %>">
   					<%}else{ %>
   						<tbody id="cuerpo<%=i/10 %>" style="display:none">
   					<%} %>
	   				<%for(int a = i; a<(i+10)&&a<listaPregunta.size();a++){ %>
	   					<tr id="columna<%=a%>">
	   						<td colspan="3"><input type="hidden" name="enunciado<%=a%>" value="<%=listaPregunta.get(a).getEnunciado()%>"/><%=listaPregunta.get(a).getEnunciado() %></td>
	   						<td></td>
	   						<td></td>
	   						<%for(int x=0; x<listaPregunta.get(a).getListaRespuestas().size(); x++ ){ %>
	   							<td>
	   								<label class="labelResp<%=a%>" for="resp<%=x%>"><%=listaPregunta.get(a).getListaRespuestas().get(x).getTexto() %></label>
	   								<input type="hidden" name="resp<%=a%><%=x%>" value="<%=listaPregunta.get(a).getListaRespuestas().get(x).getTexto() %>"/>
	   								<input type="radio" name="respRadio<%=a%>" value="resp<%=x%>"/>
	   							</td>
	   							
	   						<%} %>
	   						<td id="colArticulo<%=a+1%>" style="text-align:center;display:none"><input class="form-check-input" id="inputArt<%=a %>" type="number" name="art<%=a%>" style="width:3vw;text-align:center;"/></td>
	   						<td id="colDisposicion<%=a+1%>" style="text-align:center;display:none"><input id="inputDisp<%=a %>" type="checkbox" name="check<%=a%>" value="disposicion"/></td>
	   						<td><button type="button" class="btn btn-danger" onClick="eliminar('columna<%=a%>');"><i class="fas fa-trash"></i></button></td>
	   					</tr>
	   				
	   					<%}%> 
	   					</tbody>
	   				<%}%>
	   				
   			</table>
   			  <div aria-label="Page navigation example" class="mt-3 mb-4" style="margin:auto;width:10%;">
			  <ul class="pagination" >
			  	<%for(int j = 0 ; j<listaPregunta.size();j+=10) {%>
			    <li class="page-item"><a class="page-link" onClick="mostrarPagina('cuerpo<%=j/10%>')"><%=(j/10)+1 %></a></li>
			    <%} %>
			   
			  </ul>
		</div>
			<input type="hidden" value="<%=listaPregunta.size() %>" name="tamanho"/>
			<input type="submit" class="btn btn-primary btn-lg btn-block mt-5" value="Enviar Preguntas"/>
   		</form>
   		<%} %>
   
   </div>
</body>
</html>