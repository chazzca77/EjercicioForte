package appinit;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class InicializacionContextListener
 *
 */
@WebListener
public class InicializacionContextListener implements ServletContextListener {
	public void contextInitialized(ServletContextEvent event) {

		try {
			ServletContext app = event.getServletContext();
			//String strDirectorioPublicacion = app.getRealPath("/");
			String host= "localhost";
			String port= "8080";

			//---------------- Carga de atributos de aplicacion -------------

			app.setAttribute("host", host);
			app.setAttribute("port", port);

			//---------------------------------------------------------------
		} catch (Throwable e) {
			e.printStackTrace();		
		}
	}
	public void contextDestroyed(ServletContextEvent event) {
	}
	
}
