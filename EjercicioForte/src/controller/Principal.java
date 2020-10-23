package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/principal")
public class Principal  extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Principal() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		request.getRequestDispatcher("/principal.jsp").forward(request, response);

//		UserSession user = (UserSession) session.getAttribute("user");
//		if (user != null) {
//			ConfiguracionMapa configuracionMapa = new ConfiguracionMapa(16.866319403426395, -99.87430572509766, 14, 0, false);
//			ConfiguracionInterfaz configuracionInterfaz = new ConfiguracionInterfaz("monitoreo", false, 0, "Monitoreo de Vehículos", "", configuracionMapa);
//			session.setAttribute("interfaz", configuracionInterfaz);
//			request.getRequestDispatcher("/monitoreoTR.jsp").forward(request, response);
//		} else {
//			response.sendRedirect(getServletContext().getContextPath() + "/");
//		}
	}

}
