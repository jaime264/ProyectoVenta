package view;

import java.io.IOException;
import java.util.List;

import factory.DAOFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Rol;
import model.User;
import service.RolService;
import service.UserRolService;
import service.UserService;

public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DAOFactory daoFactory = DAOFactory.getDaoFactory(DAOFactory.MYSQL);
	UserService userService = daoFactory.getUser();
	RolService rolService = daoFactory.getRol();
	UserRolService userRolService = daoFactory.getUserRol();

	public UserServlet() {
		super();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String type = request.getParameter("type");

		switch (type) {
		case "registrar":
			String codigo = request.getParameter("txtCode");
			if (codigo != null && !codigo.isEmpty() && !codigo.equals("0")) {
				updateUser(request, response);
			} else {
				addUser(request, response);
			}
			break;
		case "obtener":
			getUser(request, response);
			break;
		case "eliminar":
			deleteUser(request, response);
			break;
		case "listar":
			getUsers(request, response);
			break;
		case "limpiar":
			clean(request, response);
			break;

		default:
			break;
		}
	}

	private void getUsers(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<User> users = userService.getUsers();
		List<Rol> roles = rolService.getRols();

		if (users != null) {
			request.setAttribute("roles", roles);
			request.setAttribute("users", users);
			request.getRequestDispatcher("user.jsp").forward(request, response);
		} else {
			request.setAttribute("mensaje", "Error al listar los usuarios");
			request.getRequestDispatcher("user.jsp").forward(request, response);
		}

	}

	private void addUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("txtName");
		String password = request.getParameter("txtPassword");
		String rolId = request.getParameter("selectRol");

		User u = new User();
		u.setName(name);
		u.setPassword(password);

		int primaryKey = userService.addUser(u);

		if (primaryKey > 0) {
			int value = userRolService.addUserRol(Integer.parseInt(rolId), primaryKey);

			if (value == 1) {
				getUsers(request, response);
			} else {
				request.setAttribute("mensaje", "Error al registrar");
				request.getRequestDispatcher("user.jsp").forward(request, response);
			}
		}

	}

	private void updateUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String code = request.getParameter("txtCode");
		String name = request.getParameter("txtName");
		String password = request.getParameter("txtPassword");
		String rolId = request.getParameter("selectRol");
		String rolIdPrev = request.getParameter("txtRol");

		User u = new User();
		u.setId(Integer.parseInt(code));
		u.setName(name);
		u.setPassword(password);

		int delRol = userRolService.deleteUserRol(Integer.parseInt(rolIdPrev), Integer.parseInt(code));
		int addRol = userRolService.addUserRol(Integer.parseInt(rolId), Integer.parseInt(code));
		if (delRol == 1 && addRol == 1) {
			int value = userService.updateUser(u);
			if (value == 1) {
				getUsers(request, response);
			} else {
				request.setAttribute("mensaje", "Error al registrar");
				request.getRequestDispatcher("user.jsp").forward(request, response);
			}
		} else {
			request.setAttribute("mensaje", "Error al registrar");
			request.getRequestDispatcher("user.jsp").forward(request, response);
		}
	}

	private void getUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String code = request.getParameter("code");
		User user = userService.getUser(Integer.parseInt(code));

		if (user != null) {
			request.setAttribute("user", user);
			getUsers(request, response);
		} else {
			request.setAttribute("mensaje", "Error al registrar");
			request.getRequestDispatcher("user.jsp").forward(request, response);
		}

	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String codeUser = request.getParameter("userId");
		String codeRol = request.getParameter("rolId");

		int value = userRolService.deleteUserRol(Integer.parseInt(codeRol), Integer.parseInt(codeUser));

		if (value == 1) {
			int value2 = userService.deleteUser(Integer.parseInt(codeUser));
			if (value2 == 1) {
				getUsers(request, response);
			} else {
				request.setAttribute("mensaje", "Error al registrar");
				request.getRequestDispatcher("user.jsp").forward(request, response);
			}
		} else {
			request.setAttribute("mensaje", "Error al registrar");
			request.getRequestDispatcher("user.jsp").forward(request, response);
		}

	}

	private void clean(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User u = new User();
		request.setAttribute("user", u);
		getUsers(request, response);
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
