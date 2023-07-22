package factory;

import service.CustomerService;
import service.ProductService;
import service.RolService;
import service.UserRolService;
import service.UserService;

public abstract class DAOFactory {

	public static final int MYSQL = 1;
	public static final int SQLSERVER = 2;
	public static final int ORACLE = 3;
	
	public abstract CustomerService getCustomer();
	public abstract UserService getUser();
	public abstract RolService getRol();
	public abstract UserRolService getUserRol();
	public abstract ProductService getProduct();

	public static DAOFactory getDaoFactory(int tipo) {
		switch (tipo) {
		case MYSQL:
			return new MysqlDAOFactory();
		case SQLSERVER:
			return null;
		case ORACLE:
			return null;
		default:
			return null;
		}
	}

}
