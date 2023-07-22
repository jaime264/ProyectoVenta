package service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import config.MysqlConexion;
import model.Product;
import service.ProductService;

public class ProductServiceImpl implements ProductService{

	@Override
	public int addProduct(Product p) {
		int value = 0;
		PreparedStatement psmt = null;
		Connection cn = null;
		try {
			cn = MysqlConexion.getConnection();
			String query = "INSERT INTO product "
					+ "(codigo, name, provider, price, quantity, image) "
					+ "VALUES(?, ?, ?, ?, ?, ?)";
			psmt = cn.prepareStatement(query);
			psmt.setString(1, p.getCode());
			psmt.setString(2, p.getName());
			psmt.setString(3, p.getProvider());
			psmt.setDouble(4, p.getPrice());
			psmt.setInt(5, p.getQuantity());
			psmt.setString(6, p.getImage());
			value = psmt.executeUpdate();			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(psmt != null) psmt.close();
				if(cn != null) cn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return value;
	}
	
	@Override
	public List<Product> getProducts() {
		List<Product> products = new ArrayList<Product>();
		PreparedStatement psmt = null;
		Connection cn = null;
		ResultSet rs = null;
		try {
			cn = MysqlConexion.getConnection();
			String query = "SELECT * FROM product";
			psmt = cn.prepareStatement(query);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				Product p = new Product();
				p.setId(rs.getInt("id"));
				p.setCode(rs.getString("codigo"));
				p.setName(rs.getString("name"));
				p.setProvider(rs.getString("provider"));
				p.setPrice(rs.getDouble("price"));
				p.setQuantity(rs.getInt("quantity"));
				p.setImage(rs.getString("image"));
				products.add(p);
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
		return products;
	}

	@Override
	public Product getProduct(int id) {
		Product product = null;
		PreparedStatement psmt = null;
		Connection cn = null;
		ResultSet rs = null;
		try {
			cn = MysqlConexion.getConnection();
			String query = "SELECT * FROM product WHERE id = ?";
			psmt = cn.prepareStatement(query);
			psmt.setInt(1, id);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				product = new Product();
				product.setId(rs.getInt("id"));
				product.setCode(rs.getString("codigo"));
				product.setName(rs.getString("name"));
				product.setProvider(rs.getString("provider"));
				product.setPrice(rs.getDouble("price"));
				product.setQuantity(rs.getInt("quantity"));
				product.setImage(rs.getString("image"));
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
		
		
		return product;
	}
	
	@Override
	public int updateProduct(Product p) {
		int value = 0;
		PreparedStatement psmt = null;
		Connection cn = null;
		try {
			cn = MysqlConexion.getConnection();
			String query = "UPDATE product "
					+ "SET codigo=?, name=?, provider=?, price=?, quantity=?, image=? "
					+ "WHERE id=?";
			psmt = cn.prepareStatement(query);
			psmt.setString(1, p.getCode());
			psmt.setString(2, p.getName());
			psmt.setString(3, p.getProvider());
			psmt.setDouble(4, p.getPrice());
			psmt.setInt(5, p.getQuantity());
			psmt.setString(6, p.getImage());
			psmt.setInt(7, p.getId());
			value = psmt.executeUpdate();			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(psmt != null) psmt.close();
				if(cn != null) cn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return value;
	}

	@Override
	public int deleteProduct(int id) {
		int value = 0;
		PreparedStatement psmt = null;
		Connection cn = null;
		try {
			cn = MysqlConexion.getConnection();
			String query = "DELETE FROM product WHERE id=?";
			psmt = cn.prepareStatement(query);
			psmt.setInt(1, id);
			value = psmt.executeUpdate();			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(psmt != null) psmt.close();
				if(cn != null) cn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return value;
	}

}
