package factory;

import service.CustomerService;
import service.ProductService;
import service.RolService;
import service.UserRolService;
import service.UserService;
import service.impl.CustomerServiceImpl;
import service.impl.ProductServiceImpl;
import service.impl.RolServiceImpl;
import service.impl.UserRolServiceImpl;
import service.impl.UserServiceImpl;

public class MysqlDAOFactory extends DAOFactory{

	@Override
	public CustomerService getCustomer() {
		return new CustomerServiceImpl();
	}

	@Override
	public UserService getUser() {
		return new UserServiceImpl();
	}

	@Override
	public RolService getRol() {
		return new RolServiceImpl();
	}

	@Override
	public UserRolService getUserRol() {
		return new UserRolServiceImpl();
	}

	@Override
	public ProductService getProduct() {
		return new ProductServiceImpl();
	}

}
