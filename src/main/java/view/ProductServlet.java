package view;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Product;
import service.ProductService;

import java.io.IOException;
import java.util.List;

import factory.DAOFactory;

public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DAOFactory daoFactory = DAOFactory.getDaoFactory(DAOFactory.MYSQL);
	ProductService productService = daoFactory.getProduct();

	public ProductServlet() {
		super();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String type = request.getParameter("type");

		switch (type) {
		case "registrar":
			String code = request.getParameter("txtId");
			if(code != null && !code.equals("0")) 
				updateProduct(request, response);
			else
				addProduct(request, response);
			break;
		case "listar":
			getProducts(request, response);
			break;
		case "editar":
			obtenerProduct(request, response);
			break;
		case "nuevo":
			cleanProduct(request, response);
			break;
		case "eliminar":
			deleteProduct(request, response);
			break;
		default:
			break;
		}
	}

	private void cleanProduct(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		Product p = new Product();
		request.setAttribute("product", p);
		getProducts(request, response);		
	}

	private void updateProduct(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		String id = request.getParameter("txtId");
		String code = request.getParameter("txtCode");
		String name = request.getParameter("txtName");
		String provider = request.getParameter("txtProvider");
		String price = request.getParameter("txtPrice");
		String quantity = request.getParameter("txtQuantity");
		String image = request.getParameter("txtImage");

		Product p = new Product();
		p.setId(Integer.parseInt(id));
		p.setCode(code);
		p.setName(name);
		p.setProvider(provider);
		p.setPrice(Double.parseDouble(price));
		p.setQuantity(Integer.parseInt(quantity));
		p.setImage(image);
		
		int value = productService.updateProduct(p);
		
		if(value == 1) {
			getProducts(request, response);			
		}else {
			request.setAttribute("mensaje", "Error al actualizar");
			request.getRequestDispatcher("product.jsp").forward(request, response);
		}
		
	}

	private void obtenerProduct(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		String id = request.getParameter("code");
		Product p = productService.getProduct(Integer.parseInt(id));
		
		if(p != null) {
			request.setAttribute("product", p);
			getProducts(request, response);			
		}else {
			request.setAttribute("mensaje", "Error al obtener");
			request.getRequestDispatcher("product.jsp").forward(request, response);
		}
		
	}

	private void deleteProduct(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String id = request.getParameter("code");
		int value = productService.deleteProduct(Integer.parseInt(id));
		
		if(value == 1) {
			getProducts(request, response);			
		}else {
			request.setAttribute("mensaje", "Error al obtener");
			request.getRequestDispatcher("product.jsp").forward(request, response);
		}
	}

	private void addProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String code = request.getParameter("txtCode");
		String name = request.getParameter("txtName");
		String provider = request.getParameter("txtProvider");
		String price = request.getParameter("txtPrice");
		String quantity = request.getParameter("txtQuantity");
		String image = request.getParameter("txtImage");

		Product p = new Product();
		p.setCode(code);
		p.setName(name);
		p.setProvider(provider);
		p.setPrice(Double.parseDouble(price));
		p.setQuantity(Integer.parseInt(quantity));
		p.setImage(image);

		int value = productService.addProduct(p);

		if (value == 1) {
			getProducts(request, response);
		} else {
			request.setAttribute("mensaje", "Error al listar");
			request.getRequestDispatcher("product.jsp").forward(request, response);
		}

	}

	private void getProducts(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Product> products = productService.getProducts();

		if (products != null) {
			request.setAttribute("products", products);
			request.getRequestDispatcher("product.jsp").forward(request, response);
		} else {
			request.setAttribute("mensaje", "Error al listar");
			request.getRequestDispatcher("product.jsp").forward(request, response);
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
