package services;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.google.gson.JsonObject;
import com.mysql.jdbc.Statement;
import Utils.EncrypterHelper;
import bd.ConnectionHelper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;  

@Path("/appChisme")
public class ChismeServicios extends Application{
	private static Logger log = Logger.getLogger(ChismeServicios.class.getName());

	
	@POST
	@Path("/getPreguntas")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPreguntas() throws IOException {
		
		JSONObject respuesta=new JSONObject(); //create a JSON Object obj.		
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
	
		String sql = "SELECT * FROM pregunta;";
		try {
			connection = getConnection();
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();	
			
			JSONArray jArray = new JSONArray(); 
			while (rs.next()) {
				JSONObject job=new JSONObject(); //create a JSON Object obj.
				
				int Id =  rs.getInt(1);
				String Descripcion = rs.getString(2);

				
				job.put("Id", String.valueOf(Id));
		        job.put("Descripcion", Descripcion);

		        jArray.add(job);

			}
		respuesta.put("success", 1);
		respuesta.put("content", jArray);	
		} catch (SQLException e) {
			log.log(Level.SEVERE, "Ocurrió un error al intentar recuperar la(s) fila(s) de la base de datos", e);
			respuesta.put("success", 0);
		} 

		log.log(Level.SEVERE, "Ocurrió un error: "+respuesta);
		return Response.ok(respuesta.toString()).build();
	}
	
	
	@POST
	@Path("/getLogin")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getLogin(@FormParam("usuario") String usuario,@FormParam("password") String password) throws IOException {
		
		String hayDatos = "0";
		JSONObject respuesta=new JSONObject(); 	
		String passEn = EncrypterHelper.getMD5(password);
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
	
		String sql = "SELECT *" + 
				" FROM usuario" + 
				" WHERE Username = '"+usuario+"' AND Pass = '"+passEn+"'";
		
		log.log(Level.SEVERE, "sql "+sql);

		
		try {
			connection = getConnection();
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();	
			
			JSONArray jArray = new JSONArray(); 
			while (rs.next()) {
				JSONObject job=new JSONObject(); //create a JSON Object obj.
				
				int Id =  rs.getInt(1);
				String Username = rs.getString(2);
				String Email = rs.getString(3);
			
				job.put("Id", String.valueOf(Id));
		        job.put("Username", Username);
		        job.put("Email", Email);

		        jArray.add(job);
		        hayDatos = "1";
			}
		
		respuesta.put("success", hayDatos);
		respuesta.put("res", jArray);	
		} catch (SQLException e) {
			log.log(Level.SEVERE, "Ocurrió un error al intentar recuperar la(s) fila(s) de la base de datos", e);
			respuesta.put("success", 0);
		} 
		
		log.log(Level.SEVERE, "Respuesta "+respuesta);
		return Response.ok(respuesta.toString()).build();
	}
	
	
	@POST
	@Path("/setRegistro")
	@Produces(MediaType.APPLICATION_JSON)
	public Response setRegistro(@FormParam("nombre") String nombre, 
			@FormParam("pass") String pass,@FormParam("email") String email,@FormParam("usuario") String usuario) throws IOException {
		
		JsonObject respuesta = new JsonObject();

	    String[] nombreCompleto = nombre.split(" ");
	    String nombres = nombreCompleto[0];
	    String app = nombreCompleto.length>1?nombreCompleto[1]:"" ;
	    String apm = nombreCompleto.length>2?nombreCompleto[2]:"" ;
		String passEn = EncrypterHelper.getMD5(pass);
		
		int respuestaInsert = setRegistroAct(nombres,app,apm , passEn, email, usuario);
		
		if(respuestaInsert == 1) {
			respuesta.addProperty("success", 1);
			
		}else {
			respuesta.addProperty("success", 0);
		}
		
		log.log(Level.SEVERE, "Ocurrió un error: "+respuesta);
		return Response.ok(respuesta.toString()).build();
	}
	
	public int setRegistroAct(String nombre,String app,String apm,String passEn,String email, String usuario) {
		int respuesta = 0;

		Connection connection = null;
		ResultSet rs = null;

		PreparedStatement ps = null;
		String sql = "INSERT INTO usuario (Username,Nombre,ApellidoPaterno,ApellidoMaterno,Email,Pass) VALUES "
				+ "('"+usuario+"','"+nombre+"','"+app+"','"+apm+"','"+email+"', '"+passEn+"');";
		
		try {
			connection = getConnection();
			ps = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			ps.execute();		
			rs = ps.getGeneratedKeys();
			
			if(rs.next()){
				respuesta = 1;
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			log.log(Level.SEVERE, "Ocurrió un error al intentar recuperar la(s) fila(s) de la base de datos", e);
		} 
		
		return respuesta;
		
	}
	
	
	@POST
	@Path("/setRespuesta")
	@Produces(MediaType.APPLICATION_JSON)
	public Response setRespuesta(@FormParam("idPregunta") String idPregunta, 
			@FormParam("idUsuario") String idUsuario,@FormParam("respuesta") String respuesta) throws IOException {
		
		JsonObject res = new JsonObject();
		
		int respuestaInsert = setRespuestaAct(idPregunta,idUsuario,respuesta);
		
		if(respuestaInsert == 1) {
			res.addProperty("success", 1);
			
		}else {
			res.addProperty("success", 0);
		}
		
		log.log(Level.SEVERE, "Ocurrió un error: "+respuesta);
		return Response.ok(res.toString()).build();
	}
	
	public int setRespuestaAct(String idPregunta,String idUsuario,String respuestaf) {
		int respuesta = 0;

		Connection connection = null;
		ResultSet rs = null;

		PreparedStatement ps = null;
		String sql = "INSERT INTO respuesta (IdUsuario,IdPregunta,Descripcion) VALUES "
				+ "('"+idUsuario+"','"+idPregunta+"','"+respuestaf+"');";
		
		try {
			connection = getConnection();
			ps = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			ps.execute();		
			rs = ps.getGeneratedKeys();
			
			if(rs.next()){
				respuesta = 1;
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			log.log(Level.SEVERE, "Ocurrió un error al intentar recuperar la(s) fila(s) de la base de datos", e);
		} 
		
		return respuesta;
		
	}
	
	@POST
	@Path("/deleteRespuestas")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteRespuestas(@FormParam("idUsuario") String idUsuario) throws IOException {
		
		JsonObject respuesta = new JsonObject();
		
		int respuestaInsert = setDelIn(idUsuario);
		
		if(respuestaInsert == 1) {
			respuesta.addProperty("success", 1);
			
		}else {
			respuesta.addProperty("success", 0);
		}
		
		log.log(Level.SEVERE, "Ocurrió un error: "+respuesta);
		return Response.ok(respuesta.toString()).build();
	}
	
	
	
	public int setDelIn(String idUsuario) {
		int respuesta = 0;

		Connection connection = null;
		ResultSet rs = null;

	
		String sql = "DELETE FROM respuesta WHERE idUsuario = '"+idUsuario+"';";			
		try {
			connection = getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			if(!ps.execute()) {
				respuesta = 1;
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, "Ocurrió un error al intentar recuperar la(s) fila(s) de la base de datos", e);
		} 
		return respuesta;
		
	}
	
	
	@POST
	@Path("/getPreguntasResp")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPreguntasResp(@FormParam("idUsuario") String idUsuario) throws IOException {
		
		JSONObject respuesta=new JSONObject(); //create a JSON Object obj.		
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
	
		String sql = "SELECT * FROM respuesta WHERE idUsuario = '"+idUsuario+"';";
		
		
		try {
			connection = getConnection();
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();	
			
			int rsCount = 0; 

			while (rs.next()) {
				rsCount = rsCount + 1;
			}
			
			if(rsCount > 0) {
				respuesta.put("success", 1);
			}else {
				respuesta.put("success", 0);
				respuesta.put("msg_error", "No se encontró ninguna respuesta "+rsCount + " "+sql );
			}
			
		
		} catch (SQLException e) {
			log.log(Level.SEVERE, "Ocurrió un error al intentar recuperar la(s) fila(s) de la base de datos", e);
			respuesta.put("success", 0);
			respuesta.put("msg_error", "No se encontró ninguna respuesta");
		} 

		log.log(Level.SEVERE, "Ocurrió un error: "+respuesta);
		return Response.ok(respuesta.toString()).build();
	}
	 
	
	@POST
	@Path("/editRespuesta")
	@Produces(MediaType.APPLICATION_JSON)
	public Response editRespuesta(@FormParam("respuesta") String respuesta,@FormParam("idUsuario") String idUsuario,
			@FormParam("idPregunta") String idPregunta) throws IOException {
		
		JsonObject res = new JsonObject();
		
		int respuestaInsert = editRespuestaIn(respuesta,idUsuario,idPregunta);
		
		if(respuestaInsert == 1) {
			res.addProperty("success", "1");
			
		}else {
			res.addProperty("success", "0");
		}
		
		log.log(Level.SEVERE, "Ocurrió un error: "+res);
		return Response.ok(res.toString()).build();
	}
	
	
	
	public int editRespuestaIn(String respuesta,String idUsuario,String idPregunta) {
		int res = 0;

		Connection connection = null;
		ResultSet rs = null;

	
		String sql = "UPDATE respuesta set Descripcion ='"+respuesta+"' "
				+ "WHERE idUsuario = '"+idUsuario+"' AND idPregunta = '"+idPregunta+"';";			
		try {
			connection = getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			if(ps.executeUpdate() > 0) {
				res = 1;
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, "Ocurrió un error al intentar recuperar la(s) fila(s) de la base de datos", e);
		} 
		return res;
		
	}
	
	
	@POST
	@Path("/getListadoChisme")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getListadoChisme() throws IOException {
		
		JSONObject respuesta=new JSONObject(); //create a JSON Object obj.		
		
		Connection connection = null;
		PreparedStatement ps = null , ps2 = null;
		ResultSet rs = null, rs2 = null;
	
		String sql = "SELECT * FROM usuario";
		try {
			connection = getConnection();
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();	
						
			JSONArray contentpricipal=new JSONArray();
			while (rs.next()) {
				JSONObject aux = new JSONObject(); 
				JSONArray datosPreguntas = new JSONArray(); 
				
				int Id =  rs.getInt(1);
				String idStr = String.valueOf(Id);
				String Username = rs.getString(2);

				aux.put("Id", idStr);
				aux.put("Username", Username);

				String sql2 = "SELECT p.Id as 'IdPregunta', p.Descripcion AS 'pregunta', r.Descripcion AS 'respuesta'"
						+ "  FROM respuesta r"
						+ "  INNER JOIN pregunta p ON r.IdPregunta = p.Id"
						+ "  WHERE IdUsuario = '"+idStr+"';";
				
				ps2 = connection.prepareStatement(sql2);
				rs2 = ps2.executeQuery();	
				
				while (rs2.next()) {
					JSONObject aux2 = new JSONObject();
					int IdPregunta =  rs2.getInt(1);
					String IdPreguntaStr = String.valueOf(IdPregunta);
					String pregunta = rs2.getString(2);
					String respuesta2 = rs2.getString(3);
					
					aux2.put("IdPregunta",IdPreguntaStr);
					aux2.put("pregunta",pregunta);
					aux2.put("respuesta",respuesta2);
					
					datosPreguntas.add(aux2);
				}
				
				aux.put("preguntas",datosPreguntas);
				contentpricipal.add(aux);
			}
		respuesta.put("success", 1);
		respuesta.put("content", contentpricipal);	
		} catch (SQLException e) {
			log.log(Level.SEVERE, "Ocurrió un error al intentar recuperar la(s) fila(s) de la base de datos", e);
			respuesta.put("success", 0);
		} 

		log.log(Level.SEVERE, "Ocurrió un error: "+respuesta);
		return Response.ok(respuesta.toString()).build();
	}
	
	
	

		
	private Connection getConnection() {
		ConnectionHelper connectionHelper = new ConnectionHelper();
		return /*connectionHelper.getConnectionFormDS() != null ? connectionHelper.getConnectionFormDS()
				: */connectionHelper.getConnectionFormJDBC();
	}
	
}
