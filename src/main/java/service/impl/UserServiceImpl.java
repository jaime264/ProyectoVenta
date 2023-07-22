package service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import config.MysqlConexion;
import model.Rol;
import model.User;
import service.UserService;

public class UserServiceImpl implements UserService{

	@Override
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		List<User> listUser = new ArrayList<User>();
		PreparedStatement psmt = null;
		Connection cn = null;
		ResultSet rs = null;
		try {
			cn = MysqlConexion.getConnection();
			String query = "select u.id as user_id, u.name as user_name,u.password, r.id as rol_id, r.name as rol_name from `user` u "
					+ "inner join user_rol ur on u.id = ur.user_id "
					+ "inner join rol r on r.id = ur.rol_id";
			psmt = cn.prepareStatement(query);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				User u = new User();
				u.setId(rs.getInt("user_id"));
				u.setName(rs.getString("user_name"));
				Rol r = new Rol();
				r.setId(rs.getInt("rol_id"));
				r.setName(rs.getString("rol_name"));
				u.setRol(r);
				listUser.add(u);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(psmt != null) psmt.close();
				if(cn != null) cn.close();
				if(rs != null) rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return listUser;
	}

	@Override
	public User getUser(int id) {
		User user = new User();
		PreparedStatement psmt = null;
		Connection cn = null;
		ResultSet rs = null;
		try {
			cn = MysqlConexion.getConnection();
			String query = "select u.id as user_id, u.name as user_name,u.password, r.id as rol_id, r.name as rol_name from `user` u "
					+ "inner join user_rol ur on u.id = ur.user_id "
					+ "inner join rol r on r.id = ur.rol_id where u.id = ?";
			psmt = cn.prepareStatement(query);
			psmt.setInt(1, id);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				user.setId(rs.getInt("user_id"));
				user.setName(rs.getString("user_name"));
				user.setPassword(rs.getString("password"));
				Rol r = new Rol();
				r.setId(rs.getInt("rol_id"));
				r.setName(rs.getString("rol_name"));
				user.setRol(r);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(psmt != null) psmt.close();
				if(cn != null) cn.close();
				if(rs != null) rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return user;
	}

	@Override
	public int addUser(User u) {
		int value = 0;
		String columnNames[] = new String[] {"id"};
		PreparedStatement psmt = null;
		Connection cn = null;
		int primaryKey= 0;
		try {
			cn = MysqlConexion.getConnection();
			String query = "INSERT INTO user "
					+ "(name, password) VALUES(?, ?)";
			psmt = cn.prepareStatement(query, columnNames);
			psmt.setString(1, u.getName());
			psmt.setString(2, u.getPassword());
			value = psmt.executeUpdate();
			if(value >0) {
				ResultSet rs = psmt.getGeneratedKeys();
				if(rs.next()) {
					primaryKey = rs.getInt(1);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(psmt != null) psmt.close();
				if(cn != null) psmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return primaryKey;
	}

	@Override
	public int updateUser(User u) {
		int value = 0;
		PreparedStatement psmt = null;
		Connection cn = null;
		try {
			cn = MysqlConexion.getConnection();
			String query = "UPDATE user SET name=?, password=? WHERE id=?";
			psmt = cn.prepareStatement(query);
			psmt.setString(1, u.getName());
			psmt.setString(2, u.getPassword());
			psmt.setInt(3, u.getId());
			value = psmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(psmt != null) psmt.close();
				if(cn != null) psmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return value;
	}

	@Override
	public int deleteUser(int id) {
		// TODO Auto-generated method stub
		int value = 0;
		PreparedStatement psmt = null;
		Connection cn = null;
		try {
			cn = MysqlConexion.getConnection();
			String query = "DELETE FROM user WHERE id=?";
			psmt = cn.prepareStatement(query);
			psmt.setInt(1, id);		
			value = psmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(psmt != null) psmt.close();
				if(cn != null) psmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return value;
	}

}
