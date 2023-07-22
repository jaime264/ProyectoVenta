package service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import config.MysqlConexion;
import model.Customer;
import service.CustomerService;

public class CustomerServiceImpl implements CustomerService{

	@Override
	public List<Customer> listCustomer() {
		List<Customer> listCustomer = new ArrayList<Customer>();
		PreparedStatement psmt = null;
		Connection cn = null;
		ResultSet rs = null;
		try {
			cn = MysqlConexion.getConnection();
			String query = "{ CALL getCustomers() }";
			psmt = cn.prepareStatement(query);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				Customer c = new Customer();
				c.setId(rs.getInt("id"));
				c.setDocumentType(rs.getString("document_type"));
				c.setDocumentNumber(rs.getString("document_number"));
				c.setName(rs.getString("name"));
				c.setPhone(rs.getString("phone"));
				c.setAddress(rs.getString("address"));
				c.setBusinessName(rs.getString("business_name"));
				listCustomer.add(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(psmt != null) psmt.close();
				if(cn != null) cn.close();
				if(rs != null) rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return listCustomer;
	}

	@Override
	public int addCustomer(Customer c) {
		int value = 0;
		PreparedStatement psmt = null;
		Connection cn = null;
		try {
			cn = MysqlConexion.getConnection();
			String query = "{ CALL addCustomer(?, ?, ?, ?, ?, ?) }";
			psmt = cn.prepareStatement(query);
			psmt.setString(1, c.getDocumentType());
			psmt.setString(2, c.getDocumentNumber());
			psmt.setString(3, c.getName());
			psmt.setString(4, c.getPhone());
			psmt.setString(5, c.getAddress());
			psmt.setString(6, c.getBusinessName());
			value = psmt.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(psmt != null) psmt.close();
				if(cn != null) cn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return value;
	}

	@Override
	public int updateCustomer(Customer c) {
		int value = 0;
		PreparedStatement psmt = null;
		Connection cn = null;
		try {
			cn = MysqlConexion.getConnection();
			String query = "{ CALL updateCustomer(?, ?, ?, ?, ?, ?, ?) }";
			psmt = cn.prepareStatement(query);
			psmt.setInt(1, c.getId());
			psmt.setString(2, c.getDocumentType());
			psmt.setString(3, c.getDocumentNumber());
			psmt.setString(4, c.getName());
			psmt.setString(5, c.getPhone());
			psmt.setString(6, c.getAddress());
			psmt.setString(7, c.getBusinessName());
			value = psmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(psmt != null) psmt.close();
				if(cn != null) cn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return value;
	}

	@Override
	public Customer getCustomer(int id) {
		Customer c = null;
		PreparedStatement psmt = null;
		Connection cn = null;
		ResultSet rs = null;
		try {
			cn = MysqlConexion.getConnection();
			String query = "{ CALL getCustomer(?) }";
			psmt = cn.prepareStatement(query);
			psmt.setInt(1, id);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				c = new Customer();
				c.setId(rs.getInt("id"));
				c.setDocumentType(rs.getString("document_type"));
				c.setDocumentNumber(rs.getString("document_number"));
				c.setName(rs.getString("name"));
				c.setPhone(rs.getString("phone"));
				c.setAddress(rs.getString("address"));
				c.setBusinessName(rs.getString("business_name"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(psmt != null) psmt.close();
				if(cn != null) cn.close();
				if(rs != null) rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return c;
	}

	@Override
	public int deleteCustomer(int id) {
		int value = 0;
		PreparedStatement psmt = null;
		Connection cn = null;
		try {
			cn = MysqlConexion.getConnection();
			String query = "{ CALL deleteCustomer(?) }";
			psmt = cn.prepareStatement(query);
			psmt.setInt(1, id);
			value = psmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(psmt != null) psmt.close();
				if(cn != null) cn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return value;
	}


}
