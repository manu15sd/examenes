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



public class pregLegislacionDAO {


	public boolean insertarPregLey(PregLegislacion preguntaLey) throws SQLException, ClassNotFoundException {
		
		Class.forName("com.mysql.jdbc.Driver");
		
		Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/generadorTest?user=root");
		
		PreparedStatement sentencia = conexion.prepareStatement("insert into pregLegislacion(fecha, ley, art, disposicion, enunciado, idAutor) values (?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
		
		int idLegislacion = 0;
		
		sentencia.setDate(1, preguntaLey.getFecha());
		sentencia.setString(2, preguntaLey.getLey().getNombre());
		sentencia.setInt(3, preguntaLey.getArt());
		sentencia.setBoolean(4, preguntaLey.isDisposicion());
		sentencia.setString(5, preguntaLey.getEnunciado());
		sentencia.setInt(6, preguntaLey.getAutor().getIdAutor());
		
		
		

		if(sentencia.executeUpdate() == 1) {
			ResultSet resultado = sentencia.getGeneratedKeys();
			
			if(resultado.next()) {
				idLegislacion = resultado.getInt(1);
				
			}else {
				
				return false;
			}
		}else {
			
			return false;
			
		}

		for(Respuesta respuesta : preguntaLey.getListaRespuestas()) {
			
			PreparedStatement sentencia2 = conexion.prepareStatement("insert into respuestas(texto, correcta, idLegislacion) values(?,?,?)");
			
			sentencia2.setString(1, respuesta.getTexto());
			sentencia2.setBoolean(2, respuesta.isEsCorrecta());
			sentencia2.setInt(3, idLegislacion);
			
			if(sentencia2.executeUpdate() == 1) {
				
			}else {
				
				return false;
			}
		}
		
		
		return true;
	}

	public List<Pregunta> generarExamenPorLey(Ley ley, int minArt, int maxArt, int numPreguntas) throws ClassNotFoundException, SQLException {
		
		List<Pregunta> listaPreguntas = new ArrayList<Pregunta>();
		List<Pregunta> listaPreguntas2 = new ArrayList<Pregunta>();
		
		Class.forName("com.mysql.jdbc.Driver");
		
		Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/generadorTest?user=root");
		
		PreparedStatement sentencia = conexion.prepareStatement("select * from pregLegislacion where ley = ? AND art between ? and ?");
		
		sentencia.setString(1, ley.getNombre());
		sentencia.setInt(2, minArt);
		sentencia.setInt(3, maxArt);
		
		ResultSet resultado = sentencia.executeQuery();
		
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
		
		Collections.shuffle(listaPreguntas);
		
		for(int i = 0; i<numPreguntas && i<listaPreguntas.size();i++) {
			listaPreguntas2.add(listaPreguntas.get(i));
		}
		
		return listaPreguntas2;

	}

	public PregLegislacion buscarPreguntaPorId(int id) throws ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.jdbc.Driver");
		
		Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/generadorTest?user=root");
		
		PreparedStatement sentencia = conexion.prepareStatement("select * from pregLegislacion join ley on ley.nombreley =  pregLegislacion.ley join autor on autor.idAutor = pregLegislacion.idAutor join grupoLey on grupoLey.nombreLey = ley.nombreLey join grupo on grupo.idgrupo = grupoley.idgrupo  where idLegislacion = ?");
		sentencia.setInt(1, id);
		ResultSet resultado = sentencia.executeQuery();
		PregLegislacion p = null;
		
		while(resultado.next()) {
			
			String enunciado = resultado.getString("enunciado");
			String autorStr = resultado.getString("autor.nombre");
			Autor autor = new Autor(autorStr, 0);
			Date fecha = resultado.getDate("fecha");
			String nombreOposicion = resultado.getString("nombreOposicion");
			int art = resultado.getInt("art");
			boolean disposicion = resultado.getBoolean("disposicion");
			
			String nombreLey = resultado.getString("ley");
			int numMaxArt = resultado.getInt("numMaxArticulo");
			Ley ley = new Ley(nombreLey, numMaxArt);
			
			p = new PregLegislacion(enunciado, autor, fecha, null, ley, art, disposicion);
			p.setIdPregunta(id);
			p.setNombreOposicion(nombreOposicion);
			
			PreparedStatement sentencia2 = conexion.prepareStatement("select * from respuestas where idLegislacion = ?");
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

	public boolean actualizarPregLey(PregLegislacion preguntaLey) throws ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.jdbc.Driver");
		
		Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/generadorTest?user=root");
		
		PreparedStatement sentencia = conexion.prepareStatement("update pregLegislacion set idAutor=?, ley=?, enunciado=?, art=?, disposicion=?  where idLegislacion = ?");
		
		sentencia.setInt(1, preguntaLey.getAutor().getIdAutor());
		sentencia.setString(2, preguntaLey.getLey().getNombre());
		sentencia.setString(3, preguntaLey.getEnunciado());
		sentencia.setInt(4, preguntaLey.getArt());
		sentencia.setBoolean(5, preguntaLey.isDisposicion());
		sentencia.setInt(6, preguntaLey.getIdPregunta());
		
		if(sentencia.executeUpdate() == 1) {
			
		}else {
			return false;
		}
		
		for(Respuesta respuesta : preguntaLey.getListaRespuestas()) {
			
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

	public boolean insertarMuchasPreguntas(List<PregLegislacion> listaPregLegislacion) throws SQLException, ClassNotFoundException {
		
		Class.forName("com.mysql.jdbc.Driver");
		
		Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/generadorTest?user=root");
		
		for(PregLegislacion preguntaLey : listaPregLegislacion) {

			PreparedStatement sentencia = conexion.prepareStatement("insert into pregLegislacion(fecha, ley, art, disposicion, enunciado, idAutor) values (?,?,?,?,?,?)");
			
			int idLegislacion = 0;
			
			sentencia.setDate(1, preguntaLey.getFecha());
			sentencia.setString(2, preguntaLey.getLey().getNombre());
			sentencia.setInt(3, preguntaLey.getArt());
			sentencia.setBoolean(4, preguntaLey.isDisposicion());
			sentencia.setString(5, preguntaLey.getEnunciado());
			sentencia.setInt(6, preguntaLey.getAutor().getIdAutor());
			

			if(sentencia.executeUpdate() == 1) {
				ResultSet resultado = sentencia.getGeneratedKeys();
				
				if(resultado.next()) {
					idLegislacion = resultado.getInt(1);
					
				}else {
					
					return false;
				}
			}else {
				
				return false;
				
			}

			for(Respuesta respuesta : preguntaLey.getListaRespuestas()) {
				
				PreparedStatement sentencia2 = conexion.prepareStatement("insert into respuestas(texto, correcta, idLegislacion) values(?,?,?)");
				
				sentencia2.setString(1, respuesta.getTexto());
				sentencia2.setBoolean(2, respuesta.isEsCorrecta());
				sentencia2.setInt(3, idLegislacion);
				
				if(sentencia2.executeUpdate() == 1) {
					
				}else {
					
					return false;
				}
			}
			
		}
		return true;
	}
}
		
		



