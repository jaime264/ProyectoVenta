package service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import config.MysqlConexion;
import model.Rol;
import service.RolService;

public class RolServiceImpl implements RolService{

	@Override
	public List<Rol> getRols() {
		List<Rol> roles = new ArrayList<Rol>();
		PreparedStatement psmt = null;
		Connection cn = null;
		ResultSet rs = null;
		try {
			cn = MysqlConexion.getConnection();
			String query = "SELECT * FROM rol";
			psmt = cn.prepareStatement(query);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				Rol r = new Rol();
				r.setId(rs.getInt("id"));
				r.setName(rs.getString("name"));
				r.setDescription(rs.getString("description"));
				roles.add(r);
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
		
		return roles;
	}

	

}
