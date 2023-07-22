package service;

import java.util.List;

import model.Product;

public interface ProductService {

	public int addProduct(Product p);
	public int updateProduct(Product p);
	public List<Product> getProducts();
	public Product getProduct(int id);
	public int deleteProduct(int id);
	
}
