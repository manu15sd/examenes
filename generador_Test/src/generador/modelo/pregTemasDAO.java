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



public class pregTemasDAO {


	public boolean insertarPregTemas(PregTemas preguntaTema) throws ClassNotFoundException, SQLException {
		
		
		Class.forName("com.mysql.jdbc.Driver");
		
		Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/generadorTest?user=root");
		
		PreparedStatement sentenciaPrev = conexion.prepareStatement("select idtema from tema where subtema like ?");
		
		sentenciaPrev.setString(1, preguntaTema.getTema().getSubTema().get(0));
		
		int idTema =0;
		
		ResultSet resultadoPrev = sentenciaPrev.executeQuery();
		
		if(resultadoPrev.next()) {
			idTema = resultadoPrev.getInt("idTema");
		}
		
		PreparedStatement sentencia = conexion.prepareStatement("insert into pregTemas(fecha, enunciado, idAutor, idTema) values (?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
		
		int idPregunta = 0;
		
		sentencia.setDate(1, preguntaTema.getFecha());
		sentencia.setString(2, preguntaTema.getEnunciado());
		sentencia.setInt(3, preguntaTema.getAutor().getIdAutor());
		sentencia.setInt(4, idTema);
		

		if(sentencia.executeUpdate() == 1) {
			ResultSet resultado = sentencia.getGeneratedKeys();
			if(resultado.next()) {
				idPregunta = resultado.getInt(1);
			}else {
				return false;
			}
		}else {
			return false;
		}
		
		for(Respuesta respuesta : preguntaTema.getListaRespuestas()) {
			
			PreparedStatement sentencia2 = conexion.prepareStatement("insert into respuestas(texto, correcta,idPreguntaTemas) values(?,?,?)");
			
			sentencia2.setString(1, respuesta.getTexto());
			sentencia2.setBoolean(2, respuesta.isEsCorrecta());
			sentencia2.setInt(3, idPregunta);

			
			if(sentencia2.executeUpdate() == 1) {
				
			}else {
				return false;
			}
		}
		
		
		return true;
	}

	public List<Pregunta> generarExamenPorTemas(Tema tema, int numPreguntas) throws ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.jdbc.Driver");
		
		Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/generadorTest?user=root");
		
		List<Pregunta> listaPreguntas = new ArrayList<Pregunta>();
		List<Pregunta> listaPreguntas2 = new ArrayList<Pregunta>();
		PreparedStatement sentencia;
		
		if(tema.getSubTema().get(0).equals("todos")) {
			
			sentencia = conexion.prepareStatement("select * from pregTemas join tema on  pregTemas.idTema = tema.idTema where nombreTema = (select nombreTema from tema where tema.idTema = ?)");
			sentencia.setInt(1, tema.getIdTema());
	
			}
			
		else {
	
			sentencia = conexion.prepareStatement("select * from pregTemas join tema on  pregTemas.idTema = tema.idTema where subtema = ?");
			sentencia.setString(1, tema.getSubTema().get(0));
			
		}
		
		ResultSet resultado = sentencia.executeQuery();
		
		while(resultado.next()) {
			String enunciado = resultado.getString("enunciado");
			int idPreguntaTema = resultado.getInt("idPreguntaTemas");
			Pregunta p = new Pregunta(enunciado, null, null, null);
			
			PreparedStatement sentencia2 = conexion.prepareStatement("select * from respuestas where idPreguntaTemas = ?");
			sentencia2.setInt(1, idPreguntaTema);
			ResultSet resultado2 = sentencia2.executeQuery();
			
			while(resultado2.next()) {
				String texto = resultado2.getString("texto");
				boolean esCorrecta = resultado2.getBoolean("correcta");
				int idRespuesta = resultado.getInt("idRespuesta");
				Respuesta r = new Respuesta(texto, esCorrecta);
				r.setIdRespuesta(idRespuesta);
				p.anhadirListaRespuestas(r);
			}
			Collections.shuffle(p.getListaRespuestas());
			listaPreguntas.add(p);
		}
		
		Collections.shuffle(listaPreguntas);
		for(int i = 0; i<numPreguntas && i<listaPreguntas.size();i++) {
			listaPreguntas2.add(listaPreguntas.get(i));
		}
		
		return listaPreguntas2;
	}

	public PregTemas buscarPreguntaPorId(int id) throws ClassNotFoundException, SQLException {
		
		
		Class.forName("com.mysql.jdbc.Driver");
		
		Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/generadorTest?user=root");
		
		PreparedStatement sentencia = conexion.prepareStatement("select * from pregTemas join tema on  pregTemas.idTema = tema.idTema join autor on autor.idAutor = pregTemas.idAutor join grupoTema on grupoTema.idTema = tema.idTema join grupo on grupoTema.idGrupo = grupo.idGrupo  where idPreguntaTemas = ?");
		sentencia.setInt(1, id);
		ResultSet resultado = sentencia.executeQuery();
		PregTemas p = null;
		
		while(resultado.next()) {
			
			String enunciado = resultado.getString("enunciado");
			String autorStr = resultado.getString("autor.nombre");
			Autor autor = new Autor(autorStr, 0);
			Date fecha = resultado.getDate("fecha");
			String nombreOposicion = resultado.getString("nombreOposicion");
			String subtema = resultado.getString("subtema");
			
			String nombreTema = resultado.getString("nombretema");
			int idTema = resultado.getInt("idTema");
			Tema tema = new Tema(nombreTema, idTema);
			tema.anhadirSubtema(subtema);
					
			p = new PregTemas(enunciado, autor, fecha, null, tema);
			p.setIdPregunta(id);
			p.setNombreOposicion(nombreOposicion);
			
			
			PreparedStatement sentencia2 = conexion.prepareStatement("select * from respuestas where idPreguntaTemas = ?");
			sentencia2.setInt(1, id);
			ResultSet resultado2 = sentencia2.executeQuery();
			
			while(resultado2.next()) {
				String texto = resultado2.getString("texto");
				boolean esCorrecta = resultado2.getBoolean("correcta");
				int idRespuesta = resultado2.getInt("idRespuesta");
				Respuesta r = new Respuesta(texto, esCorrecta);
				r.setIdRespuesta(idRespuesta);
				p.anhadirListaRespuestas(r);
				
			}
		}
		
		
		return p;
	}

	public boolean actualizarPregTemas(PregTemas preguntaTema) throws ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.jdbc.Driver");
		
		Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/generadorTest?user=root");
		
		PreparedStatement sentenciaPrev = conexion.prepareStatement("select idtema from tema where subtema like ?");
		
		sentenciaPrev.setString(1, preguntaTema.getTema().getSubTema().get(0));
		
		int idTema =0;
		
		ResultSet resultadoPrev = sentenciaPrev.executeQuery();
		
		if(resultadoPrev.next()) {
			idTema = resultadoPrev.getInt("idTema");
		}
		
		PreparedStatement sentencia = conexion.prepareStatement("update pregTemas set idAutor=?, idTema=?, enunciado=?  where idPreguntaTemas = ?");
		
		sentencia.setInt(1, preguntaTema.getAutor().getIdAutor());
		sentencia.setInt(2, idTema);
		sentencia.setString(3, preguntaTema.getEnunciado());
		sentencia.setInt(4, preguntaTema.getIdPregunta());
		
		
		if(sentencia.executeUpdate() == 1) {
			
		}else {
			return false;
		}
		
		for(Respuesta respuesta : preguntaTema.getListaRespuestas()) {
			
			PreparedStatement sentencia2 = conexion.prepareStatement("update respuestas set texto=?, correcta=? where idRespuesta =?");
			
			sentencia2.setString(1, respuesta.getTexto());
			sentencia2.setBoolean(2, respuesta.isEsCorrecta());
			sentencia2.setInt(3, respuesta.getIdRespuesta());

			
			if(sentencia2.executeUpdate() == 1) {
				
			}else {
				return false;
			}
		}
		
		
		return true;
		
		
	}

	public boolean insertarMuchasPreguntas(List<PregTemas> listaPregTemas) throws SQLException, ClassNotFoundException {
		
		Class.forName("com.mysql.jdbc.Driver");
		
		Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/generadorTest?user=root");
		
		for(PregTemas preguntaTema : listaPregTemas) {
			PreparedStatement sentenciaPrev = conexion.prepareStatement("select idtema from tema where subtema like ?");
			
			sentenciaPrev.setString(1, preguntaTema.getTema().getSubTema().get(0));
			
			int idTema =0;
			
			ResultSet resultadoPrev = sentenciaPrev.executeQuery();
			
			if(resultadoPrev.next()) {
				idTema = resultadoPrev.getInt("idTema");
			}
			
			PreparedStatement sentencia = conexion.prepareStatement("insert into pregTemas(fecha, enunciado, idAutor, idTema) values (?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
			
			int idPregunta = 0;
			
			sentencia.setDate(1, preguntaTema.getFecha());
			sentencia.setString(2, preguntaTema.getEnunciado());
			sentencia.setInt(3, preguntaTema.getAutor().getIdAutor());
			sentencia.setInt(4, idTema);
			

			if(sentencia.executeUpdate() == 1) {
				ResultSet resultado = sentencia.getGeneratedKeys();
				if(resultado.next()) {
					idPregunta = resultado.getInt(1);
				}else {
					return false;
				}
			}else {
				return false;
			}
			
			for(Respuesta respuesta : preguntaTema.getListaRespuestas()) {
				
				PreparedStatement sentencia2 = conexion.prepareStatement("insert into respuestas(texto, correcta,idPreguntaTemas) values(?,?,?)");
				
				sentencia2.setString(1, respuesta.getTexto());
				sentencia2.setBoolean(2, respuesta.isEsCorrecta());
				sentencia2.setInt(3, idPregunta);

				
				if(sentencia2.executeUpdate() == 1) {
					
				}else {
					return false;
				}
			}
		}
		

		
		
		return true;
	}
}
