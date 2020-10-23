package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public interface GenericDAO<T> {

	public int save(T entity);

	public boolean delete(T entity);

	public T findById(int id);

	public ArrayList<T> findAll();

	public default void closeAll(ResultSet rs, Statement s, PreparedStatement ps, Connection conn) {
		try {
			if (rs != null) {
				Logger.getLogger(GenericDAO.class.getName()).info("Cerrando el objeto ResultSet");
				rs.close();
			}
			if (s != null) {
				Logger.getLogger(GenericDAO.class.getName()).info("Cerrando el objeto Statement");
				s.close();
			}
			if (ps != null) {
				Logger.getLogger(GenericDAO.class.getName()).info("Cerrando el objeto PreparedStatement");
				ps.close();
			}
			if (conn != null) {
				Logger.getLogger(GenericDAO.class.getName()).info("Cerrando el objeto Connection");
				conn.close();
			}
		} catch (SQLException e) {
			Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE,
					"Ocurrió un error al intentar cerrar un elemento de la conexición", e);
		}
	}
}