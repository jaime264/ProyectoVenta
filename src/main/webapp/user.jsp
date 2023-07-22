<%@page import="model.Rol"%>
<%@page import="model.User"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Usuario</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

	<div class="container">
		<h1>Registrar usuario</h1>
		<div class="row">
			<div class="col-6 col-sm-4">
				<form action="UserServlet?type=registrar" method="post"
					name="RegistrarUsuario">
					<div class="form-group">
						<label>Codigo</label> <input class="form-control" type="text"
							name="txtCode" readonly="readonly" value="${user.id}">
					</div>
					 <input class="form-control" type="hidden"
							name="txtRol"  value="${user.rol.id}" >
					
					<div class="form-group">
						<label>Nombre de Usuario</label> <input class="form-control"
							type="text" name="txtName" value="${user.name}">
					</div>
					<div class="form-group">
						<label>Contraseña</label> <input class="form-control"
							type="password" name="txtPassword" value="${user.password}">
					</div>
					<div class="form-group">
					<label>Rol</label>
						<select class="form-select" name="selectRol" id="selectRol">
							<option>Seleccione</option>
							<% List<Rol> listRol = (List<Rol>) request.getAttribute("roles"); 
								if(listRol != null){
									for(Rol r : listRol){
							%>
							<option value="<%=r.getId()%>"><%=r.getName() %></option>
							<%	}
							} %>
						</select>
					</div>
					<br> <input type="submit" class="btn btn-primary"
						value="Enviar Datos">
				</form>
				<br>
				<a href="UserServlet?type=limpiar" class="btn btn-primary">Nuevo</a>
			</div>
			<div class="col-6 col-sm-4">
				<table class="table">
					<thead>
						<tr>
							<th>Codigo</th>
							<th>Usuario</th>
							<th>Rol</th>
							<th>Acciones</th>
						</tr>
					</thead>
					<tbody>
						<%
						List<User> listUer = (List<User>) request.getAttribute("users");
						if (listUer != null) {
							for (User u : listUer) {
						%>
						<tr>
							<td><%=u.getId() %></td>
							<td><%=u.getName() %></td>
							<td><%=u.getRol().getName() %></td>							
							<td>
							<a href="UserServlet?type=obtener&code=<%=u.getId() %>" class="btn btn-primary">Editar</a>
							<a href="UserServlet?type=eliminar&userId=<%=u.getId()%>&rolId=<%=u.getRol().getId()%>" class="btn btn-primary">Eliminar</a>
						</td>
						</tr>
						<%
							}
						}
						%>
					</tbody>
				</table>
			</div>
		</div>
	</div>

</body>
</html>