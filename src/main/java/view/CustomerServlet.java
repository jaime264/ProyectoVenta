package view;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Customer;
import service.CustomerService;

import java.io.IOException;
import java.util.List;

import factory.DAOFactory;

public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	DAOFactory daoFactory = DAOFactory.getDaoFactory(DAOFactory.MYSQL);
	CustomerService cusService = daoFactory.getCustomer();
       
    
    public CustomerServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		
		switch (type) {
		case "registrar":
			String codigo = request.getParameter("txtCode");
			if(codigo != null && !codigo.isEmpty()) {
				updateCustomer(request, response);
			}else {
				addCustomer(request, response);
			}
			break;
		case "obtener":
			getCustomer(request,response);		
			break;
		case "eliminar":
			deleteCustomer(request,response);
			break;
		case "listar":
			getCustomers(request,response);
			break;
		case "limpiar":
			clean(request,response);
			break;

		default:
			break;
		}
		
	}


	private void clean(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Customer c = new Customer();
		
		request.setAttribute("customer", c);
		getCustomers(request, response);
		
	}

	private void getCustomers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Customer> customers = cusService.listCustomer();
		
		if(customers != null) {
			request.setAttribute("customers", customers);
			request.getRequestDispatcher("customer.jsp").forward(request, response);
		}else {
			request.setAttribute("mensaje", "Error al listar");
			request.getRequestDispatcher("customer.jsp").forward(request, response);
		}
		
	}

	private void deleteCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = request.getParameter("code");
		int value = cusService.deleteCustomer(Integer.parseInt(code));
		
		if(value == 1) {
			getCustomers(request, response);
		}else {
			request.setAttribute("mensaje", "Error al eliminar");
			request.getRequestDispatcher("customer.jsp").forward(request, response);
		}
	}

	private void getCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = request.getParameter("code");
		Customer c = cusService.getCustomer(Integer.parseInt(code));
		if(c != null) {
			request.setAttribute("customer", c);
			getCustomers(request, response);
		}else {
			request.setAttribute("mensaje", "Error al obtener cliente");
			request.getRequestDispatcher("customer.jsp").forward(request, response);
		}
	}

	private void addCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("txtName");
		String documentType = request.getParameter("selectDocument");
		String documentNumber = request.getParameter("txtDocument");
		String phone = request.getParameter("txtPhone");
		String address = request.getParameter("txtAddress");
		String business = request.getParameter("txtBusiness");
		
		Customer c = new Customer();
		c.setName(name);
		c.setDocumentType(documentType);
		c.setDocumentNumber(documentNumber);
		c.setPhone(phone);
		c.setAddress(address);
		c.setBusinessName(business);
		
		int value = cusService.addCustomer(c);
		
		if(value == 1) {
			getCustomers(request, response);
		}else {
			request.setAttribute("mensaje", "Error al registrar");
			request.getRequestDispatcher("customer.jsp").forward(request, response);
		}
		
	}

	private void updateCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = request.getParameter("txtCode");
		String name = request.getParameter("txtName");
		String documentType = request.getParameter("selectDocument");
		String documentNumber = request.getParameter("txtDocument");
		String phone = request.getParameter("txtPhone");
		String address = request.getParameter("txtAddress");
		String business = request.getParameter("txtBusiness");
		
		Customer c = new Customer();
		c.setId(Integer.parseInt(code));
		c.setName(name);
		c.setDocumentType(documentType);
		c.setDocumentNumber(documentNumber);
		c.setPhone(phone);
		c.setAddress(address);
		c.setBusinessName(business);
		
		int value = cusService.updateCustomer(c);
		
		if(value == 1) {
			getCustomers(request, response);
		}else {
			request.setAttribute("mensaje", "Error al actualizar");
			request.getRequestDispatcher("customer.jsp").forward(request, response);
		}
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
