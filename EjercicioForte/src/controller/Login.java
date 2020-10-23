package controller;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.UserSession;
import model.dao.UserDAO;


/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(Login.class.getName());

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String user = !request.getParameter("user").isEmpty() ? request.getParameter("user").toString().trim() : "1";
		String pass = !request.getParameter("pass").isEmpty() ? request.getParameter("pass").toString().trim() : "2";

		UserDAO userDAO = new UserDAO(); 
		UserSession valid = null;

		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(3000);
		valid = userDAO.getLogin(user, pass);
		
		//valid = new UserSession("- Admin  -",2);

		if (valid != null) {
			log.info("Usuario válido " + valid);
			session.setAttribute("user", valid);
			response.sendRedirect("./inicio");
		}else{
			log.info("Usuario no encontrado o inválido");
			log.info(getServletContext().getContextPath() + "/?errorLogin=1");
			response.sendRedirect(getServletContext().getContextPath() + "/?errorLogin=1");
		}
	}


	public static void main(String[] args) {
		
	}

}
