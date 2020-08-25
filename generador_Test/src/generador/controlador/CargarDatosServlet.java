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
import generador.modelo.Grupo;

/**
 * Servlet implementation class anhadirPreguntaServlet
 */
@WebServlet("/cargarDatos")
public class CargarDatosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CargarDatosServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Fachada fachada = new Fachada();
		
		try {
			List<Grupo> listaGrupos = fachada.obtenerGrupos();
			List<Autor> listaAutor = fachada.obtenerAutor();
			request.getSession().getServletContext().setAttribute("listaGrupos", listaGrupos);
			request.getSession().getServletContext().setAttribute("listaAutor", listaAutor);
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		switch(request.getParameter("nombre")) {
			case "generar":
				request.getRequestDispatcher("generadorTest.jsp").forward(request, response);
				break;
			
			case "anhadir":
				request.getRequestDispatcher("anhadirPregunta.jsp").forward(request, response);
				break;
				
			case "editar":
				request.getRequestDispatcher("editarPregunta.jsp").forward(request, response);
				break;
			
			case "anhadirDoc":
				request.getRequestDispatcher("anhadirDoc.jsp").forward(request, response);
				break;
		}
	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
