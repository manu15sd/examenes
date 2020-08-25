package generador.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AutorDAO {

	public List<Autor> obtenerAutor() throws ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.jdbc.Driver");
		
		Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/generadorTest?user=root");
		
		PreparedStatement sentencia = conexion.prepareStatement("select * from Autor");
		
		ResultSet resultado = sentencia.executeQuery();
		List<Autor> lista = new ArrayList<Autor>();
		
		while(resultado.next()) {
			String nombre = resultado.getString("nombre");
			int idAutor = resultado.getInt("idAutor");
			Autor autor = new Autor(nombre, idAutor);
			lista.add(autor);
		}
		return lista;
	}

}
