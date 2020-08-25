package generador.controlador;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import generador.modelo.Fachada;
import generador.modelo.Grupo;
import generador.modelo.Ley;
import generador.modelo.PregLegislacion;
import generador.modelo.Pregunta;
import generador.modelo.Tema;

/**
 * Servlet implementation class GenerarExamenServlet
 */
@WebServlet("/GenerarExamen")
public class GenerarExamenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GenerarExamenServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Fachada fachada = new Fachada();
		List<Pregunta> listaPreguntas = new ArrayList<Pregunta>();
		
		String genTematico = request.getParameter("genTematico");
		String grupoNombre = request.getParameter("grupo");
		
		String numPreguntasStr = request.getParameter("numPreguntas");
		int numPreguntas = Integer.parseInt(numPreguntasStr);
		
		if(genTematico.equals("general")) {
			
			String porcentaje = request.getParameter("aleatorioPorcentaje");
			
			if(porcentaje.equals("aleatorio")) {
				
				try {
					
					listaPreguntas = fachada.generarExamenGenAleatorio(grupoNombre,numPreguntas);
					
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}else {
				List<Grupo> listaGrupos = (List<Grupo>)request.getSession().getServletContext().getAttribute("listaGrupos");
				Grupo grupo = null;
				for(Grupo grupoBucle : listaGrupos) {
					if(grupoBucle.getNombreOposicion().equals(grupoNombre)) {
						grupo = grupoBucle;
					}
				}
				List<TemaPorcentajeAux> listaPorcentajes =  new ArrayList<TemaPorcentajeAux>();
				for(Tema tema : grupo.getListaTemas()) {
					int porcentajeNum = Integer.parseInt(request.getParameter(tema.getNombre()));
					TemaPorcentajeAux aux = new TemaPorcentajeAux(tema.getNombre(),porcentajeNum );
					listaPorcentajes.add(aux);
				}
				for(Ley ley : grupo.getListaLey()) {
					
					int porcentajeNum = Integer.parseInt(request.getParameter(ley.getNombre()));
					TemaPorcentajeAux aux = new TemaPorcentajeAux(ley.getNombre(),porcentajeNum );
					listaPorcentajes.add(aux);
				}
				
				try {
					listaPreguntas = fachada.generarExamenGenPorcentajes(grupoNombre,numPreguntas,listaPorcentajes);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
			
		}else {
			
			String tipo = request.getParameter("legisTemas");
			
			if(tipo.equals("temas")) {
				
				String temaStr = request.getParameter("tema");
				int idTema = Integer.parseInt(temaStr);
				String subtema = request.getParameter("subtema");
				Tema tema = new Tema("", idTema);
				tema.anhadirSubtema(subtema);
				try {
					listaPreguntas = fachada.generarExamenPorTema(tema,numPreguntas);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}else {
				String nombreLey = request.getParameter("ley");
				Ley ley = new Ley(nombreLey, 0);
				String minArtStr = request.getParameter("artMin");
				int minArt = Integer.parseInt(minArtStr);
				String maxArtStr = request.getParameter("artMax");
				int maxArt = Integer.parseInt(maxArtStr);
				try {
					listaPreguntas = fachada.generarExamenporLey(ley,minArt,maxArt,numPreguntas);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
	
		GeneradorWord generador = new GeneradorWord();
		try {
			generador.generarWord(listaPreguntas);
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.setContentType("application/msword");
		FileInputStream fis = new FileInputStream("D:/ProyectosWeb/proyectos/generador_Test/WebContent/examen.docx");
		ServletOutputStream salida =  response.getOutputStream();
		
		int tamanoTotal = 0;
		byte[] buffer = new byte[256];
		int bytesLeidos = fis.read(buffer);
		while (bytesLeidos != -1) {
			tamanoTotal += bytesLeidos;
			if (bytesLeidos < buffer.length) {
				salida.write(buffer, 0, bytesLeidos);
			} else {
				salida.write(buffer);
			}
			bytesLeidos = fis.read(buffer);
		}
		fis.close();
		salida.close();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
