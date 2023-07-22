package service;

import java.util.List;

import model.Customer;

public interface CustomerService {

	public List<Customer> listCustomer();
	public int addCustomer(Customer c);
	public int updateCustomer(Customer c);
	public Customer getCustomer(int id);
	public int deleteCustomer(int id);
	
}
