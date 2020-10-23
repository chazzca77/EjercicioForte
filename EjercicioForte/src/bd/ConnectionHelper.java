package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class ConnectionHelper {
	Logger log = Logger.getLogger(ConnectionHelper.class.getName());

	/**
	 * Obtiene una conexión desde el origen de datos configurado en el servidor
	 * de aplicaciones
	 * 
	 * @return un objeto Connection o null si no se puede obtener
	 * 
	 */
	public Connection getConnectionFormDS() {
		Connection connection = null;
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource) context.lookup("java:jboss/datasources/MySQLDS");
			connection = ds.getConnection();
		} catch (NamingException | SQLException e) {
			log.log(Level.SEVERE, "Ocurrió un error al intentar obtener una conexión a la BD desde origen de datos", e);
		}
		return connection;
	}

	/**
	 * Obtiene una conexión desde el origen de datos configurado en el servidor
	 * de aplicaciones
	 * 
	 * @return un objeto Connection o null si no se puede obtener
	 * 
	 */
	public Connection getConnectionFormJDBC() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");


			String url = "jdbc:mysql://localhost:3306/forte_chismografo";
			String user = "root";
			String password = "";
			connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException | ClassNotFoundException e) {
			log.log(Level.SEVERE, "Ocurrió un error al intentar obtener una conexión a la BD desde JDBC", e);
		}
		return connection;
	} 
	

}
