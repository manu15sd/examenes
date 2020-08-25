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
@WebServlet("/ActualizarPregunta")
public class ActualizarPreguntaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActualizarPreguntaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Fachada fachada = new Fachada();
		Respuesta resp2 = null; 
		Respuesta resp3 = null;
		Respuesta resp4 = null;
		boolean error = false;
		
		String idStr = request.getParameter("nombreAutor");
		int idAutor = Integer.parseInt(idStr);
		Autor autor = new Autor("",idAutor);
		
		String enunciado = request.getParameter("enunciado");
		
		String idPreguntaStr = request.getParameter("idPregunta");
		int idPregunta = Integer.parseInt(idPreguntaStr);
		
		long milis = System.currentTimeMillis();
		Date fecha = new Date(milis);
		
		String respCorrectaStr = request.getParameter("respuestaCorrecta");
		String idRespCorrectaStr = request.getParameter("idRespuestaCorrecta");
		int idRespuestaCorrecta = Integer.parseInt(idRespCorrectaStr);
		Respuesta respCorrecta = new Respuesta(respCorrectaStr, true);
		respCorrecta.setIdRespuesta(idRespuestaCorrecta);
		System.out.println(respCorrecta);
		
		if(request.getParameter("respuesta2") != null && request.getParameter("respuesta2")!="") {
			String resp2Str = request.getParameter("respuesta2");
			String idResp2Str = request.getParameter("idRespuesta2");
			int idResp2 = Integer.parseInt(idResp2Str);
			resp2 = new Respuesta(resp2Str, false);
			resp2.setIdRespuesta(idResp2);
			System.out.println(resp2);
		}
		
		if(request.getParameter("respuesta3") != null && request.getParameter("respuesta3")!="") {
			String resp3Str = request.getParameter("respuesta3");
			String idResp3Str = request.getParameter("idRespuesta3");
			int idResp3 = Integer.parseInt(idResp3Str);
			System.out.println(idResp3);
			resp3 = new Respuesta(resp3Str, false);
			resp3.setIdRespuesta(idResp3);
			System.out.println(resp3);
		}
		
		if(request.getParameter("respuesta4") != null && request.getParameter("respuesta4")!="") {
			String resp4Str = request.getParameter("respuesta4");
			String idResp4Str = request.getParameter("idRespuesta4");
			int idResp4 = Integer.parseInt(idResp4Str);
			resp4 = new Respuesta(resp4Str, false);
			resp4.setIdRespuesta(idResp4);
			System.out.println(resp4);
		}
		
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
			preguntaTema.setIdPregunta(idPregunta);
			
			try {
				error = fachada.actualizarPregTemas(preguntaTema);
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
			
			preguntaLey.setIdPregunta(idPregunta);
			try {
				error = fachada.actualizarPregLey(preguntaLey);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		request.setAttribute("error", error);
		request.getRequestDispatcher("editarPregunta.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
