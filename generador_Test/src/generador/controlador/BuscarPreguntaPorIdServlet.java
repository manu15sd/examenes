package generador.controlador;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import generador.modelo.Autor;
import generador.modelo.Fachada;
import generador.modelo.PregLegislacion;
import generador.modelo.PregTemas;
import generador.modelo.Pregunta;

/**
 * Servlet implementation class BuscarPreguntaPorId
 */
@WebServlet("/BuscarPreguntaPorId")
public class BuscarPreguntaPorIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuscarPreguntaPorIdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Fachada fachada = new Fachada();
		String idStr = request.getParameter("idPregunta");
		int id = Integer.parseInt(idStr);
		String tipo = request.getParameter("tipo");
		
		try {
			List<Autor> listaAutor = fachada.obtenerAutor();
			request.setAttribute("listaAutor", listaAutor);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(tipo.equals("ley")) {
			
			try {
				PregLegislacion pregLey = fachada.buscarPreguntaLeyPorId(id);
				System.out.println(pregLey.getListaRespuestas());
				request.setAttribute("pregunta", pregLey);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			try {
				PregTemas pregTemas = fachada.buscarPreguntaTemasPorId(id);
				request.setAttribute("pregunta", pregTemas);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		request.getRequestDispatcher("editarPreguntaFormulario.jsp").forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
