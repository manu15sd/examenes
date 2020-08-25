package generador.controlador;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import generador.modelo.Autor;
import generador.modelo.Fachada;
import generador.modelo.Ley;
import generador.modelo.PregLegislacion;
import generador.modelo.PregTemas;
import generador.modelo.Respuesta;
import generador.modelo.Tema;

/**
 * Servlet implementation class InsertarPreguntaServlet
 */
@WebServlet("/InsertarPregunta")
public class InsertarPreguntaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertarPreguntaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Fachada fachada = new Fachada();
		boolean error = false;
		
		String idStr = request.getParameter("nombreAutor");
		int idAutor = Integer.parseInt(idStr);
		Autor autor = new Autor("",idAutor);
		
		String enunciado = request.getParameter("enunciado");
		
		long milis = System.currentTimeMillis();
		Date fecha = new Date(milis);
		
		String respCorrectaStr = request.getParameter("respuestaCorrecta");
		Respuesta respCorrecta = new Respuesta(respCorrectaStr, true);
		
		String resp2Str = request.getParameter("respuesta2");
		Respuesta resp2 = new Respuesta(resp2Str, false);
		
		String resp3Str = request.getParameter("respuesta3");
		Respuesta resp3 = new Respuesta(resp3Str, false);
		
		String resp4Str = request.getParameter("respuesta4");
		Respuesta resp4 = new Respuesta(resp4Str, false);
		
		String tipo = request.getParameter("legisTemas");
		
		if(tipo.equals("temas")) {
			
			String temaStr = request.getParameter("tema");
			String subtema = request.getParameter("subtema");
			int idTema = Integer.parseInt(temaStr);
			Tema tema = new Tema("",idTema);
			tema.anhadirSubtema(subtema);
			PregTemas preguntaTema = new PregTemas(enunciado, autor, fecha, "", tema);
			preguntaTema.anhadirListaRespuestas(respCorrecta);
			preguntaTema.anhadirListaRespuestas(resp2);
			preguntaTema.anhadirListaRespuestas(resp3);
			preguntaTema.anhadirListaRespuestas(resp4);
			
			try {
				error = fachada.insertarPregTemas(preguntaTema);
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
			String tipoArt = request.getParameter("tipoArt");
			PregLegislacion preguntaLey = null;
			
			if(tipoArt.equals("art")) {
				
				String artStr = request.getParameter("art");
				int art = Integer.parseInt(artStr);
	
				preguntaLey = new PregLegislacion(enunciado, autor, fecha, "", ley, art, false);
				preguntaLey.anhadirListaRespuestas(respCorrecta);
				preguntaLey.anhadirListaRespuestas(resp2);
				preguntaLey.anhadirListaRespuestas(resp3);
				preguntaLey.anhadirListaRespuestas(resp4);
						
			}else {
				String dispStr = request.getParameter("dispo");
				int disp = Integer.parseInt(dispStr);
				
				preguntaLey = new PregLegislacion(enunciado, autor, fecha, "", ley, disp, true);
			}
			
			try {
				error = fachada.insertarPregLey(preguntaLey);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		request.setAttribute("error", error);
		request.getRequestDispatcher("anhadirPregunta.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
