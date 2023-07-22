<%@page import="model.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Product</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

	<div class="container">
		<h1>Registrar Producto</h1>
		<div class="row">
			<div class="col-6 col-sm-4">
				<form action="ProductServlet?type=registrar" method="post">
					<input class="form-control" type="text" style="display: none"
							name="txtId" readonly="readonly" value="${product.id }"/>
					<div class="form-group">
						<label>Codigo</label> <input class="form-control" type="text"
							name="txtCode" value="${product.code }"/>
					</div>
					<div class="form-group">
						<label>Nombre</label> <input class="form-control" type="text"
							name="txtName" value="${product.name }"/>
					</div>
					<div class="form-group">
						<label>Proveedor</label> <input class="form-control" type="text"
							name="txtProvider" value="${product.provider }"/>
					</div>
					<div class="form-group">
						<label>Precio</label> <input class="form-control" type="text"
							name="txtPrice" value="${product.price }" />
					</div>
					<div class="form-group">
						<label>Cantidad</label> <input class="form-control" type="text"
							name="txtQuantity" value="${product.quantity }" />
					</div>
					<div class="form-group">
						<label>Imagen</label> <input class="form-control" type="text"
							name="txtImage" value="${product.image }"/>
					</div>
					<br> <input class="btn btn-primary" type="submit"
						value="Enviar datos" />
					<br>
					<br>
					<a class="btn btn-primary" href="ProductServlet?type=nuevo">Nuevo Registro</a>
				</form>
			</div>
			<div class="col-6 col-sm-4">
				<table class="table">
					<thead>
						<tr>
							<th>Id</th>
							<th>Codigo</th>
							<th>Nombre</th>
							<th>Proveedor</th>
							<th>Precio</th>
							<th>Catidad</th>
							<th>Total</th>
							<th>Imagen</th>
							<th>Acciones</th>
						</tr>
					</thead>
					<tbody>
						<%
						List<Product> products = (List<Product>) request.getAttribute("products");
						if (products != null) {
							for (Product p : products) {
						%>
						<tr>
							<td><%=p.getId()%></td>
							<td><%=p.getCode()%></td>
							<td><%=p.getName()%></td>
							<td><%=p.getProvider()%></td>
							<td><%=p.getPrice()%></td>
							<td><%=p.getQuantity()%></td>
							<td><%=p.getTotal()%></td>
							<td><img width="40px" alt="" src="<%=p.getImage()%>"></td>
							<td><a href="ProductServlet?type=editar&code=<%=p.getId()%>" style="margin-right: 8px">
								<img width="20px" src="img/lapiz.png"></a> 
								<a href="ProductServlet?type=eliminar&code=<%=p.getId()%>">
								<img width="20px" src="img/boton-x.png"></a>
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