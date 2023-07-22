package service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;

import config.MysqlConexion;
import service.UserRolService;

public class UserRolServiceImpl implements UserRolService{

	@Override
	public int addUserRol(int rolId, int userId) {
		int value = 0;
		PreparedStatement psmt = null;
		Connection cn = null;
		try {
			cn = MysqlConexion.getConnection();
			String query = "INSERT INTO user_rol (user_id, rol_id) VALUES(?, ?)";
			psmt = cn.prepareStatement(query);
			psmt.setInt(1, userId);
			psmt.setInt(2, rolId);
			value = psmt.executeUpdate();
			
		}catch (Exception e) {
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
	public int deleteUserRol(int rolId, int userId) {
		int value = 0;
		PreparedStatement psmt = null;
		Connection cn = null;
		try {
			cn = MysqlConexion.getConnection();
			String query = "DELETE FROM user_rol WHERE user_id=? AND rol_id=?";
			psmt = cn.prepareStatement(query);
			psmt.setInt(1, userId);
			psmt.setInt(2, rolId);
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
