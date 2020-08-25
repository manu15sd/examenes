package generador.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class GrupoDAO {

	public List<Grupo> obtenerGrupos() throws ClassNotFoundException, SQLException {
		
		
		Class.forName("com.mysql.jdbc.Driver");
		
		Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/generadorTest?user=root");
		
		PreparedStatement sentencia = conexion.prepareStatement("select * from Grupo");
		
		ResultSet resultado = sentencia.executeQuery();
		List<Grupo> lista = new ArrayList<Grupo>();

		
		while(resultado.next()) {
			int idOposicion = resultado.getInt("idGrupo");
			String nombreOposicion = resultado.getString("nombreOposicion");
			Grupo grupo = new Grupo(idOposicion, nombreOposicion);
			List<Ley> listaLey = new ArrayList<Ley>();
			List<Tema> listaTema = new ArrayList<Tema>();
			
			PreparedStatement sentencia2 = conexion.prepareStatement("select * from grupoLey join ley on grupoLey.nombreLey = ley.nombreLey where idGrupo = ?");
			sentencia2.setInt(1, grupo.getIdOposicion());
			ResultSet resultado2 = sentencia2.executeQuery();
			while(resultado2.next()) {
			
				int maxArt = resultado2.getInt("numMaxArticulo");
				String nombre = resultado2.getString("nombreLey");
				Ley ley = new Ley(nombre, maxArt);
				listaLey.add(ley);
				
			}
			
			PreparedStatement sentencia3 = conexion.prepareStatement("select * from grupoTema join Tema on grupoTema.idTema = tema.idTema where idGrupo = ? group by nombreTema");
			sentencia3.setInt(1, grupo.getIdOposicion());
			ResultSet resultado3 = sentencia3.executeQuery();
			
			
			while(resultado3.next()) {
			
				String nombreTema = resultado3.getString("nombreTema");
				int idTema = resultado3.getInt("idTema");

				Tema tema = new Tema(nombreTema, idTema);
				PreparedStatement sentencia4 = conexion.prepareStatement("select subtema from Tema where nombreTema = ?");
				sentencia4.setString(1, nombreTema);
				ResultSet resultado4 = sentencia4.executeQuery();
				List<String> listaSubtema = new ArrayList<String>();
				
					while(resultado4.next()) {
						
						String subtema = resultado4.getString("subtema");
					
						listaSubtema.add(subtema);
					}
				
				tema.setSubTema(listaSubtema);
				listaTema.add(tema);
			}

			grupo.setListaLey(listaLey);
			grupo.setListaTemas(listaTema);
			lista.add(grupo);

		}
	
		return lista;
	}

}
