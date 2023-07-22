package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlConexion {

	public static Connection getConnection() {
		Connection con = null;
		try {
		Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
		String url = "jdbc:mysql://localhost:3306/PUNTOVENTA?"
				+ "useSSL=false&useTimezone=true&serverTimezone=UTC";
		String usr = "root";
		String psw = "1234";
	
		con = DriverManager.getConnection(url, usr, psw);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return con;
	}
	
}
