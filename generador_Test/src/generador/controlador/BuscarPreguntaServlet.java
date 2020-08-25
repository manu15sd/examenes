package generador.controlador;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import generador.modelo.Fachada;
import generador.modelo.Pregunta;

/**
 * Servlet implementation class BuscarPreguntaServlet
 */
@WebServlet("/BuscarPregunta")
public class BuscarPreguntaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuscarPreguntaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		Fachada fachada = new Fachada();
		List<TerminoValorAux> listaParams = new ArrayList<TerminoValorAux>();
		List<Pregunta> listaPregunta = new ArrayList<Pregunta>();
		
		if(request.getParameter("termino1")!=null) {
			String termino = request.getParameter("termino1");
			String valor = request.getParameter("valor1");
			TerminoValorAux param1 = new TerminoValorAux(termino, valor);
			listaParams.add(param1);
		}
		
		if(request.getParameter("termino2")!=null) {
			String termino2 = request.getParameter("termino2");
			String valor2 = request.getParameter("valor2");
			TerminoValorAux param2 = new TerminoValorAux(termino2, valor2);
			listaParams.add(param2);
		}
		
		if(request.getParameter("termino3")!=null) {
			String termino3 = request.getParameter("termino3");
			String valor3 = request.getParameter("valor3");
			TerminoValorAux param3 = new TerminoValorAux(termino3, valor3);
			listaParams.add(param3);
			
		}
		
		try {
			listaPregunta = fachada.buscarPreguntas(listaParams);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("listaPreguntas", listaPregunta);
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
