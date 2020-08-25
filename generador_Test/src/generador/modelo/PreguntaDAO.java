package generador.modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import generador.controlador.TemaPorcentajeAux;
import generador.controlador.TerminoValorAux;

public class PreguntaDAO {

	public List<Pregunta> generarExamenGenAleatorio(String grupo, int numPreguntas) throws ClassNotFoundException, SQLException {
		
		
		Class.forName("com.mysql.jdbc.Driver");
		
		Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/generadorTest?user=root");
		
		PreparedStatement sentencia = conexion.prepareStatement("select * from grupo join grupoLey on grupo.idGrupo = grupoLey.idGrupo join pregLegislacion on grupoLey.nombreLey = pregLegislacion.ley where grupo.nombreOposicion = ?");
		
		sentencia.setString(1, grupo);
		ResultSet resultado = sentencia.executeQuery();
		
		List<Pregunta> listaPreguntas = new ArrayList<Pregunta>();
		List<Pregunta> listaPreguntas2 = new ArrayList<Pregunta>();

		while(resultado.next()) {
			
	
			String enunciado = resultado.getString("enunciado");
			int idLegislacion = resultado.getInt("idLegislacion");
			Pregunta p = new Pregunta(enunciado, null, null, null);
			PreparedStatement sentencia2 = conexion.prepareStatement("select * from respuestas where idLegislacion = ?");
			sentencia2.setInt(1, idLegislacion);
			ResultSet resultado2 = sentencia2.executeQuery();

			while(resultado2.next()) {
				
				String texto = resultado2.getString("texto");
				boolean esCorrecta = resultado2.getBoolean("correcta");
				Respuesta r = new Respuesta(texto, esCorrecta);
				p.anhadirListaRespuestas(r);
				
			}
			Collections.shuffle(p.getListaRespuestas());
			listaPreguntas.add(p);
			
		}
		
		PreparedStatement sentencia3 = conexion.prepareStatement("select * from grupo join grupoTema on grupo.idGrupo = grupoTema.idGrupo join pregTemas on grupoTema.idTema = pregTemas.idTema where grupo.nombreOposicion = ?");
		
		sentencia3.setString(1, grupo);
		ResultSet resultado3 = sentencia3.executeQuery();
		
		while(resultado3.next()) {
			
			String enunciado = resultado3.getString("enunciado");
			int idPreguntaTemas = resultado3.getInt("idPreguntaTemas");
			Pregunta p = new Pregunta(enunciado, null, null, null);
			
			PreparedStatement sentencia4 = conexion.prepareStatement("select * from respuestas where idPreguntaTemas = ?");
			sentencia4.setInt(1, idPreguntaTemas);
			ResultSet resultado4 = sentencia4.executeQuery();
			
			while(resultado4.next()) {
				String texto = resultado4.getString("texto");
				boolean esCorrecta = resultado4.getBoolean("correcta");
				Respuesta r = new Respuesta(texto, esCorrecta);
				p.anhadirListaRespuestas(r);
				
			}
			Collections.shuffle(p.getListaRespuestas());
			listaPreguntas.add(p);
			
		}
		
		System.out.println(listaPreguntas.size());
		System.out.println(listaPreguntas);
		
		for(int i = 0; i<numPreguntas&&i<listaPreguntas.size();i++) {
			listaPreguntas2.add(listaPreguntas.get(i));
		}
		
		Collections.shuffle(listaPreguntas2);
		
		return listaPreguntas2;
	}

	public List<Pregunta> generarExamenGenPorcentajes(String grupoNombre, int numPreguntas,
			List<TemaPorcentajeAux> listaPorcentajes) throws ClassNotFoundException, SQLException {
		
		int contador = 0;
		for(int i=0;i<listaPorcentajes.size();i++) {
			if(i==(listaPorcentajes.size()-1)) {
				listaPorcentajes.get(i).setNumPreguntas(numPreguntas-contador);
			}else {
				contador = contador + Math.round((listaPorcentajes.get(i).getPorcentaje() * numPreguntas) /100);
				listaPorcentajes.get(i).setNumPreguntas((listaPorcentajes.get(i).getPorcentaje() * numPreguntas) /100);
			}
		}
		
		Class.forName("com.mysql.jdbc.Driver");
		
		Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/generadorTest?user=root");
		
		PreparedStatement sentencia = conexion.prepareStatement("select * from grupo join grupoLey on grupo.idGrupo = grupoLey.idGrupo join pregLegislacion on grupoLey.nombreLey = pregLegislacion.ley join ley on pregLegislacion.ley = ley.nombreLey where grupo.nombreOposicion = ?");
		
		sentencia.setString(1, grupoNombre);
		ResultSet resultado = sentencia.executeQuery();
		
		List<Pregunta> listaPreguntas = new ArrayList<Pregunta>();
		List<Pregunta> listaPreguntas2 = new ArrayList<Pregunta>();

		while(resultado.next()) {
			
	
			String enunciado = resultado.getString("enunciado");
			int idLegislacion = resultado.getInt("idLegislacion");
			Pregunta p = new Pregunta(enunciado, null, null, null);
			String nombreLey = resultado.getString("nombreLey");
			p.setTemaLey(nombreLey);
			
			PreparedStatement sentencia2 = conexion.prepareStatement("select * from respuestas where idLegislacion = ?");
			sentencia2.setInt(1, idLegislacion);
			ResultSet resultado2 = sentencia2.executeQuery();

			while(resultado2.next()) {
				
				String texto = resultado2.getString("texto");
				boolean esCorrecta = resultado2.getBoolean("correcta");
				Respuesta r = new Respuesta(texto, esCorrecta);
				p.anhadirListaRespuestas(r);
				
			}
			Collections.shuffle(p.getListaRespuestas());
			listaPreguntas.add(p);
			
		}
		
		PreparedStatement sentencia3 = conexion.prepareStatement("select * from grupo join grupoTema on grupo.idGrupo = grupoTema.idGrupo join pregTemas on grupoTema.idTema = pregTemas.idTema join tema on pregTemas.idTema = tema.idTema where grupo.nombreOposicion = ?");
		
		sentencia3.setString(1, grupoNombre);
		ResultSet resultado3 = sentencia3.executeQuery();
		
		while(resultado3.next()) {
			
			String enunciado = resultado3.getString("enunciado");
			int idTema = resultado3.getInt("idTema");
			Pregunta p = new Pregunta(enunciado, null, null, null);
			String nombreTema = resultado3.getString("nombreTema");
			p.setTemaLey(nombreTema);
			
			PreparedStatement sentencia4 = conexion.prepareStatement("select * from respuestas where idTema = ?");
			sentencia4.setInt(1, idTema);
			ResultSet resultado4 = sentencia4.executeQuery();
			
			while(resultado4.next()) {
				String texto = resultado4.getString("texto");
				boolean esCorrecta = resultado4.getBoolean("correcta");
				Respuesta r = new Respuesta(texto, esCorrecta);
				p.anhadirListaRespuestas(r);
				
			}
			Collections.shuffle(p.getListaRespuestas());
			listaPreguntas.add(p);
			
		}
		
	
		
		for(TemaPorcentajeAux aux : listaPorcentajes) {
			List<Pregunta> listaProv = new ArrayList<Pregunta>();
			
			for(Pregunta pregunta : listaPreguntas) {
				
				if(pregunta.getTemaLey() != null) {
					if(pregunta.getTemaLey().equals(aux.getTema())) {
						listaProv.add(pregunta);
					}
				}
				
			}
			
			Collections.shuffle(listaProv);
			
			for(int j=0; j < aux.getNumPreguntas() && j < listaProv.size(); j++) {
				
				listaPreguntas2.add(listaProv.get(j));
			}
			
		}
	
		Collections.shuffle(listaPreguntas2);
		
		return listaPreguntas2;
	}

	public List<Pregunta> buscarPreguntas(List<TerminoValorAux> listaParametros) throws SQLException, ClassNotFoundException {
		
		Class.forName("com.mysql.jdbc.Driver");
		
		Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/generadorTest?user=root");
		List<Pregunta> listaPreguntas = new ArrayList<Pregunta>();
		PreparedStatement sentencia = conexion.prepareStatement("") ;
		TerminoValorAux aux;
		TerminoValorAux aux2;
		TerminoValorAux aux3;

		try {
			switch(listaParametros.size()) {
			
			case 1:
				aux = listaParametros.get(0);
				sentencia = conexion.prepareStatement("select * from pregTemas join tema on  pregTemas.idTema = tema.idTema join autor on autor.idAutor = pregTemas.idAutor join grupoTema on grupoTema.idTema = tema.idTema join grupo on grupoTema.idGrupo = grupo.idGrupo  where " + aux.getTermino() + " LIKE ? group by idPreguntaTemas");
				sentencia.setString(1, "%"+aux.getValor()+"%");
				break;
				
			case 2:
				aux = listaParametros.get(0);
				aux2 = listaParametros.get(1);
				sentencia = conexion.prepareStatement("select * from pregTemas join tema on  pregTemas.idTema = tema.idTema join autor on autor.idAutor = pregTemas.idAutor join grupoTema on grupoTema.idTema = tema.idTema join grupo on grupoTema.idGrupo = grupo.idGrupo where "+ aux.getTermino() + " LIKE ? AND "+aux2.getTermino() + " LIKE ? group by idPreguntaTemas");
				sentencia.setString(1, "%"+aux.getValor()+"%");
				sentencia.setString(2, "%"+aux2.getValor()+"%");
				break;
				
			case 3:
				aux = listaParametros.get(0);
				aux2 = listaParametros.get(1);
				aux3 = listaParametros.get(2);
				sentencia = conexion.prepareStatement("select * from pregTemas join tema on  pregTemas.idTema = tema.idTema join autor on autor.idAutor = pregTemas.idAutor join grupoTema on grupoTema.idTema = tema.idTema join grupo on grupoTema.idGrupo = grupo.idGrupo where "+ aux.getTermino() + " LIKE ? AND "+aux2.getTermino() + " LIKE ? AND " + aux3.getTermino() + " LIKE ? group by idPreguntaTemas");
				sentencia.setString(1, "%"+aux.getValor()+"%");
				sentencia.setString(2, "%"+aux2.getValor()+"%");
				sentencia.setString(3, "%"+aux3.getValor()+"%");
				break;
			}
			
		ResultSet resultado = sentencia.executeQuery();
		while(resultado.next()) {
				
				String enunciado = resultado.getString("enunciado");
				String autorStr = resultado.getString("autor.nombre");
				Autor autor = new Autor(autorStr, 0);
				Date fecha = resultado.getDate("fecha");
				
				String nombreTema = resultado.getString("nombretema");
				int idTema = resultado.getInt("idPreguntaTemas");
				Tema tema = new Tema(nombreTema, idTema);
				
				PregTemas p = new PregTemas(enunciado, autor, fecha, null, tema);
				p.setIdPregunta(idTema);
				
				PreparedStatement sentencia2 = conexion.prepareStatement("select * from respuestas where idPreguntaTemas = ?");
				sentencia2.setInt(1, idTema);
				ResultSet resultado2 = sentencia2.executeQuery();
				
				while(resultado2.next()) {
					String texto = resultado2.getString("texto");
					boolean esCorrecta = resultado2.getBoolean("correcta");
					Respuesta r = new Respuesta(texto, esCorrecta);
					p.anhadirListaRespuestas(r);
					
				}
				
				
				listaPreguntas.add(p);
				
			}
				
		}
	
		catch(Exception e) {
			e.printStackTrace();
		}
		
		PreparedStatement sentencia3 = conexion.prepareStatement("") ;
		
	try {
		switch(listaParametros.size()) {
		
			case 1:
				aux = listaParametros.get(0);
				
				sentencia3 = conexion.prepareStatement("select * from pregLegislacion join ley on ley.nombreley =  pregLegislacion.ley join autor on autor.idAutor = pregLegislacion.idAutor join grupoLey on grupoLey.nombreLey = ley.nombreLey join grupo on grupo.idgrupo = grupoley.idgrupo where "+ aux.getTermino() + " LIKE ? group by idLegislacion");
				sentencia3.setString(1, "%"+aux.getValor()+"%");
				break;
				
			case 2:
				aux = listaParametros.get(0);
				aux2 = listaParametros.get(1);
				sentencia3 = conexion.prepareStatement("select * from pregLegislacion join ley on ley.nombreley =  pregLegislacion.ley join autor on autor.idAutor = pregLegislacion.idAutor join grupoLey on grupoLey.nombreLey = ley.nombreLey join grupo on grupo.idgrupo = grupoley.idgrupo where "+ aux.getTermino() + " LIKE ? AND "+aux2.getTermino() + " LIKE ? group by idLegislacion");
				sentencia3.setString(1, "%"+aux.getValor()+"%");
				sentencia3.setString(2, "%"+aux2.getValor()+"%");
				break;
				
			case 3:
				aux = listaParametros.get(0);
				aux2 = listaParametros.get(1);
				aux3 = listaParametros.get(2);
				sentencia3 = conexion.prepareStatement("select * from pregLegislacion join ley on ley.nombreley =  pregLegislacion.ley join autor on autor.idAutor = pregLegislacion.idAutor join grupoLey on grupoLey.nombreLey = ley.nombreLey join grupo on grupo.idgrupo = grupoley.idgrupo where "+ aux.getTermino() + " LIKE ? AND "+aux2.getTermino() + " LIKE ? AND " + aux3.getTermino() + " LIKE ? group by idLegislacion");
				sentencia3.setString(1, "%"+aux.getValor()+"%");
				sentencia3.setString(2, "%"+aux2.getValor()+"%");
				sentencia3.setString(3, "%"+aux3.getValor()+"%");
				break;
	}
	

	ResultSet resultado3 = sentencia3.executeQuery();
	while(resultado3.next()) {
		
		String enunciado = resultado3.getString("enunciado");
		String autorStr = resultado3.getString("autor.nombre");
		Autor autor = new Autor(autorStr, 0);
		Date fecha = resultado3.getDate("fecha");
		int art = resultado3.getInt("art");
		int idLegislacion = resultado3.getInt("idLegislacion");
		boolean disposicion = resultado3.getBoolean("disposicion");
		
		String nombreLey = resultado3.getString("ley");
		int numMaxArt = resultado3.getInt("numMaxArticulo");
		Ley ley = new Ley(nombreLey, numMaxArt);
		
		PregLegislacion p = new PregLegislacion(enunciado, autor, fecha, null, ley, art, disposicion);
		p.setIdPregunta(idLegislacion);
		
		PreparedStatement sentencia4 = conexion.prepareStatement("select * from respuestas where idLegislacion = ?");
		sentencia4.setInt(1, idLegislacion);
		ResultSet resultado2 = sentencia4.executeQuery();
		
		while(resultado2.next()) {
			String texto = resultado2.getString("texto");
			boolean esCorrecta = resultado2.getBoolean("correcta");
			Respuesta r = new Respuesta(texto, esCorrecta);
			p.anhadirListaRespuestas(r);
			
		}
		
	
		listaPreguntas.add(p);
		
		}
	}
	
	catch(Exception e) {
		e.printStackTrace();
	}

	return listaPreguntas;
	}

}
