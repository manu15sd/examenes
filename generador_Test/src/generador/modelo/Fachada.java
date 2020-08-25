package generador.modelo;

import java.sql.SQLException;
import java.util.List;

import generador.controlador.TemaPorcentajeAux;
import generador.controlador.TerminoValorAux;

public class Fachada {
	
	public List<Grupo> obtenerGrupos() throws ClassNotFoundException, SQLException {
		GrupoDAO dao = new GrupoDAO();
		return dao.obtenerGrupos();
	}
	
	public List<Autor> obtenerAutor() throws ClassNotFoundException, SQLException{
		AutorDAO dao = new AutorDAO();
		return dao.obtenerAutor();
	}

	public boolean insertarPregTemas(PregTemas preguntaTema) throws ClassNotFoundException, SQLException {
		
		pregTemasDAO dao = new pregTemasDAO();
		return dao.insertarPregTemas(preguntaTema);
		
	}

	public boolean insertarPregLey(PregLegislacion preguntaLey) throws ClassNotFoundException, SQLException {
		pregLegislacionDAO dao = new pregLegislacionDAO();
		return dao.insertarPregLey(preguntaLey);
		
	}

	public List<Pregunta> generarExamenGenAleatorio(String grupo, int numPreguntas) throws ClassNotFoundException, SQLException {
		
		PreguntaDAO dao = new PreguntaDAO();
		return dao.generarExamenGenAleatorio(grupo,numPreguntas);
		
	}

	public List<Pregunta> generarExamenGenPorcentajes(String grupoNombre, int numPreguntas,
			List<TemaPorcentajeAux> listaPorcentajes) throws ClassNotFoundException, SQLException {
		PreguntaDAO dao = new PreguntaDAO();
		return dao.generarExamenGenPorcentajes(grupoNombre,numPreguntas,listaPorcentajes);
	}

	public List<Pregunta> generarExamenPorTema(Tema tema, int numPreguntas) throws ClassNotFoundException, SQLException {
		
		pregTemasDAO dao = new pregTemasDAO();
		return dao.generarExamenPorTemas(tema, numPreguntas);
	}

	public List<Pregunta> generarExamenporLey(Ley ley, int minArt, int maxArt, int numPreguntas) throws ClassNotFoundException, SQLException {

		pregLegislacionDAO dao = new pregLegislacionDAO();
		return dao.generarExamenPorLey(ley, minArt, maxArt, numPreguntas);
		
	}

	public List<Pregunta> buscarPreguntas(List<TerminoValorAux> listaParametros) throws ClassNotFoundException, SQLException {
		PreguntaDAO dao = new PreguntaDAO();
		return dao.buscarPreguntas(listaParametros);

	}

	public PregLegislacion buscarPreguntaLeyPorId(int id) throws ClassNotFoundException, SQLException {
		pregLegislacionDAO dao = new pregLegislacionDAO();
		return dao.buscarPreguntaPorId(id);
	}

	public PregTemas buscarPreguntaTemasPorId(int id) throws ClassNotFoundException, SQLException {
		pregTemasDAO dao = new pregTemasDAO();
		return dao.buscarPreguntaPorId(id);
	}

	public boolean actualizarPregLey(PregLegislacion preguntaLey) throws ClassNotFoundException, SQLException {
		pregLegislacionDAO dao = new pregLegislacionDAO();
		return dao.actualizarPregLey(preguntaLey);
		
	}

	public boolean actualizarPregTemas(PregTemas preguntaTema) throws ClassNotFoundException, SQLException {
		pregTemasDAO dao = new pregTemasDAO();
		return dao.actualizarPregTemas(preguntaTema);
		
	}

	public boolean insertarMuchasPregTemas(List<PregTemas> listaPregTemas) throws ClassNotFoundException, SQLException {
		pregTemasDAO dao = new pregTemasDAO();
		return dao.insertarMuchasPreguntas(listaPregTemas);
	
	}

	public boolean insertarMuchasPregLey(List<PregLegislacion> listaPregLegislacion) throws ClassNotFoundException, SQLException {
		pregLegislacionDAO dao = new pregLegislacionDAO();
		return dao.insertarMuchasPreguntas(listaPregLegislacion);
	}


}
