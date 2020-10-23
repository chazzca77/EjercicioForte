package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import bd.ConnectionHelper;
import model.User;
import model.UserSession;


public class UserDAO implements GenericDAO<User> {
	private static Logger log = Logger.getLogger(UserDAO.class.getName());

	public ArrayList<User> findByName(String name) {
		ArrayList<User> users = new ArrayList<>();
		log.info("Buscando usuario por nombre : " + name);
		String query = "select * from usuario where username = ?";
		Connection connection = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, name);
			rs = ps.executeQuery();
			User user = null;
			while (rs.next()) {
				user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6));
				users.add(user);
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, "Ocurrió un error al intentar recuperar la(s) fila(s) de la base de datos", e);

		} finally {
			closeAll(rs, null, ps, connection);
		}
		return users;
	}

	@Override
	public int save(User entity) {

		String sql = "INSERT INTO usuario (nombre, apellidos, username, PASSWORD(password), altaUsuario) VALUES (?, ?, ?, ?, ?, CURRENT_TIMESTAMP())";
		boolean update = false;
		
		User u = findById(entity.getId());
		if (u != null) {
			sql = "UPDATE usuario SET nombre = ?, apellidos = ?, username =?, password = ? WHERE idUsuario = ?";
			log.info("Se actualizará el usuario con id =" + entity.getId());
			update = true;
		}
		Connection connection = getConnection();
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, entity.getNombre());
			ps.setString(2, entity.getApellidos());
			ps.setString(3, entity.getUsername());
			ps.setString(4, entity.getPassword());
			
			if (update) {
				ps.setInt(5, entity.getId());
			}
			int rows = ps.executeUpdate();
			if (rows > 0) {
				if (update) {
					log.info("Se han actualizado " + rows + " fila(s)");
				} else {
					log.info("Se han insertado " + rows + " fila(s)");
				}
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, "Ocurrió un error al intentar guardar la(s) fila(s) de la base de datos", e);
		} finally {
			closeAll(null, null, ps, connection);
		}
		return -0;
	}

	@Override
	public boolean delete(User entity) {
		// TODO Auto-generated method stub
		
		String queryG = "delete from grupo_usuario where idUsuario = ?";
		String queryU = "delete from usuario where idUsuario = ?";
		Connection connection = null;
		
		PreparedStatement ps = null;
		boolean ex = false;
		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			ps = connection.prepareStatement(queryG);
			ps.setInt(1, entity.getId());
			ex = ps.execute();
			
			ps.close();
			
			ps = connection.prepareStatement(queryU);
			ps.setInt(1, entity.getId());
			ex = ps.execute();
			connection.commit();
		} catch (SQLException e) {
			log.log(Level.SEVERE, "Ocurrió un error al intentar eliminar usuario de la(s) fila(s) de la base de datos", e);
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				log.log(Level.SEVERE, "Ocurrió un error al intentar eliminar usuario de la(s) fila(s) de la base de datos", e1);
			}
		} finally {
			log.info("Cerrando los objetos usados en la conexión.");
			closeAll(null, null, ps, connection);
		}
		return ex;
	}

	@Override
	public User findById(int id) {
		User user = null;
		log.info("Buscando usuario por id : " + id);
		String query = "select * from usuario where idUsuario = ?";
		Connection connection = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				log.info("Se ha encontrado el usuario con id : " + id);
				user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6));
			} else {
				log.info("No existe un usuario con id : " + id);
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, "Ocurrió un error al intentar recuperar la(s) fila(s) de la base de datos", e);
		} finally {
			log.info("Cerrando los objetos usados en la conexión.");
			closeAll(rs, null, ps, connection);
		}
		return user;
	}

	@Override
	public ArrayList<User> findAll() {
		ArrayList<User> users = new ArrayList<>();
		log.info("Obteniendo todos los usuarios");
		String query = "select * from usuario";
		Connection connection = getConnection();
		Statement s = null;
		ResultSet rs = null;
		try {
			s = connection.createStatement();
			rs = s.executeQuery(query);
			User user = null;
			while (rs.next()) {
				user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6));
				users.add(user);
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, "Ocurrió un error al intentar recuperar la(s) fila(s) de la base de datos", e);
		} finally {
			closeAll(rs, s, null, connection);
		}
		return users;
	}

	
	/**
	 * 
	 * @param username
	 * @param password
	 * @return Si no existe el usuario regresa NULL
	 */
	public UserSession getLogin(String username, String password){
		UserSession usuario = null;
		String queryLogin = "SELECT u.usuario, u.idUsuario,u.nivel,c.nombre FROM usuarios u "
				+ "INNER JOIN usuario_coordinador uc ON u.idUsuario = uc.usuario "
				+ "INNER JOIN coordinadores c ON uc.coordinador = c.idCoor "
				+ "WHERE u.usuario = '"+username+"' AND u.pass= password('"+password+"') AND u.activo = 1;";
		
		log.log(Level.SEVERE, queryLogin, "string");
		
		//String queryLogin = "SELECT u.nombre, u.apellidos, gu.idGrupo AS tipo FROM usuarios u, grupo_usuario gu "
		//		+ "WHERE u.username = password('admin') AND u.password = password('admin') AND u.idUsuario = gu.idUsuario AND gu.activo = 1;";
		PreparedStatement ps  = null;
		ResultSet rs = null;
		Connection connection = getConnection();
		try {
			ps = connection.prepareStatement(queryLogin);
//			ps.setString(1, username);
//			ps.setString(2, password);
			rs = ps.executeQuery();
			if(rs.next()){
				usuario = new UserSession(rs.getString("usuario"),rs.getInt("idUsuario"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.log(Level.SEVERE, "Ocurrió un error al intentar logueo a la base de datos", e);
		}finally {
			closeAll(rs, null, ps, connection);
		}
		
		return usuario;
	}

	private Connection getConnection() {
		ConnectionHelper connectionHelper = new ConnectionHelper();
		return /*connectionHelper.getConnectionFormDS() != null ? connectionHelper.getConnectionFormDS()
				: */connectionHelper.getConnectionFormJDBC();
	}
}
