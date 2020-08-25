package generador.controlador;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.fabric.Response;

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
@WebServlet("/InsertarDoc")
public class InsertarDocServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertarDocServlet() {
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
		
		long milis = System.currentTimeMillis();
		Date fecha = new Date(milis);
		
		String tamanhoStr = request.getParameter("tamanho");
		int tamanho = Integer.parseInt(tamanhoStr);
		
		
		String enunciado = "";
		Respuesta resp1 = null;
		Respuesta resp2 = null;
		Respuesta resp3 = null;
		Respuesta resp4 = null;
		
		String tipo = request.getParameter("legisTemas");
		
		if(tipo.equals("temas")) {
			
			String temaStr = request.getParameter("tema");
			String subtema = request.getParameter("subtema");
			int idTema = Integer.parseInt(temaStr);
			Tema tema = new Tema("",idTema);
			tema.anhadirSubtema(subtema);
			
			List<PregTemas> listaPregTemas = new ArrayList<PregTemas>();
			
			for(int i = 0; i< tamanho; i++) {
				enunciado = request.getParameter("enunciado"+i);
				
				String respRadioStr = request.getParameter("respRadio"+i);
				boolean resp1Radio = false;
				boolean resp2Radio = false;
				boolean resp3Radio = false;
				boolean resp4Radio = false;
				switch(respRadioStr) {
					case "resp0":
						resp1Radio = true;
						break;
					case "resp1":
						resp2Radio = true;
						break;
					case "resp2":
						resp3Radio = true;
						break;
					case "resp3":
						resp4Radio = true;
						break;
				}
				
				String resp1Str = request.getParameter("resp"+i);
				resp1 = new Respuesta(resp1Str, resp1Radio);
				
				String resp2Str = request.getParameter("resp"+i+"1");
				resp2 = new Respuesta(resp2Str, resp2Radio);
				
				String resp3Str = request.getParameter("resp"+i+"2");
				resp3 = new Respuesta(resp3Str, resp3Radio);
				
				String resp4Str = request.getParameter("resp"+"3");
				resp4 = new Respuesta(resp4Str, resp4Radio);
				
				PregTemas preguntaTema = new PregTemas(enunciado, autor, fecha, "", tema);
				preguntaTema.anhadirListaRespuestas(resp1);
				preguntaTema.anhadirListaRespuestas(resp2);
				preguntaTema.anhadirListaRespuestas(resp3);
				preguntaTema.anhadirListaRespuestas(resp4);
				listaPregTemas.add(preguntaTema);
			}
			
			try {
				error = fachada.insertarMuchasPregTemas(listaPregTemas);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else {
			
			String nombreLey = request.getParameter("ley");
			System.out.println(nombreLey);
			Ley ley = new Ley(nombreLey, 0);
			PregLegislacion preguntaLey = null;
			
			List<PregLegislacion> listaPregLegislacion = new ArrayList<PregLegislacion>();
			
			for(int i = 0; i< tamanho; i++) {
				enunciado = request.getParameter("enunciado"+i);
				
				String respRadioStr = request.getParameter("respRadio"+i);
				boolean resp1Radio = false;
				boolean resp2Radio = false;
				boolean resp3Radio = false;
				boolean resp4Radio = false;
				switch(respRadioStr) {
					case "resp0":
						resp1Radio = true;
						break;
					case "resp1":
						resp2Radio = true;
						break;
					case "resp2":
						resp3Radio = true;
						break;
					case "resp3":
						resp4Radio = true;
						break;
				}
				
				String resp1Str = request.getParameter("resp"+i);
				resp1 = new Respuesta(resp1Str, resp1Radio);
				
				String resp2Str = request.getParameter("resp"+i+"1");
				resp2 = new Respuesta(resp2Str, resp2Radio);
				
				String resp3Str = request.getParameter("resp"+i+"2");
				resp3 = new Respuesta(resp3Str, resp3Radio);
				
				String resp4Str = request.getParameter("resp"+"3");
				resp4 = new Respuesta(resp4Str, resp4Radio);
				
				String artStr = request.getParameter("art"+i);
				int art = Integer.parseInt(artStr);
				
				String disposicionStr = request.getParameter("check"+i);
				boolean disposicion;
				disposicion = disposicionStr != null ? true :  false;
				
				preguntaLey = new PregLegislacion(enunciado, autor, fecha, "", ley, art, disposicion);
				preguntaLey.anhadirListaRespuestas(resp1);
				preguntaLey.anhadirListaRespuestas(resp2);
				preguntaLey.anhadirListaRespuestas(resp3);
				preguntaLey.anhadirListaRespuestas(resp4);
				listaPregLegislacion.add(preguntaLey);
			}
			try {
				error = fachada.insertarMuchasPregLey(listaPregLegislacion);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		request.setAttribute("error", error);
		request.getRequestDispatcher("anhadirDoc.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
