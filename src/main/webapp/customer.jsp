<%@page import="model.Customer"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cliente</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

	<div class="container">
	<h1>Registrar Cliente</h1>
	<div class="row">
		<div class="col-6 col-sm-4">
			<form action="CustomerServlet?type=registrar" method="post">
				<div class="form-group">
					<label>Codigo</label>
					<input class="form-control" type="text" name="txtCode" id="txtCode" readonly="readonly" value="${customer.id}">
				</div>
				<div class="form-group">
					<label>Nombre</label>
					<input class="form-control" type="text" name="txtName" id="txtName" value="${customer.name}"> 
				</div>
				<div class="form-group">
					<label>Tipo de Documento</label>
					<select class="form-select" name="selectDocument" id="selectDocument">
						<option>Seleccione</option>
						<option value="DNI">DNI</option>
						<option value="CE">CE</option>
					</select>
				</div>
				<div class="form-group">
					<label>Numero de documento</label>
					<input class="form-control" type="text" name="txtDocument" id="txtDocument" value="${customer.documentNumber}"> 
				</div>
				<div class="form-group">
					<label>Telefono</label>
					<input class="form-control" type="text" name="txtPhone" id="txtPhone" value="${customer.phone}"> 
				</div>
				<div class="form-group">
					<label>Direccion</label>
					<input class="form-control" type="text" name="txtAddress" id="txtAddress" value="${customer.address}"> 
				</div>
				<div class="form-group">
					<label>Razon Social</label>
					<input class="form-control" type="text" name="txtBusiness" id="txtBusiness" value="${customer.businessName}"> 
				</div>
				<br>
				<input type="submit" class="btn btn-primary" value="Enviar Datos">
			</form>
		</div>
		<div class="col-6 col-sm-4">
			<table class="table">
				<thead>
					<tr>
						<th>Id</th>
						<th>Nombre</th>
						<th>Tipo Documento</th>
						<th>Numero Documento</th>
						<th>Telefono</th>
						<th>Direccion</th>
						<th>Razon Social</th>
						<th>Acciones</th>
					</tr>
				</thead>
				<tbody>
				<% List<Customer> listCustomer = (List<Customer>) request.getAttribute("customers"); 
					if(listCustomer != null){
						for(Customer c: listCustomer){
				%>
					<tr>
						<td><%=c.getId() %></td>
						<td><%=c.getName() %></td>
						<td><%=c.getDocumentType() %></td>
						<td><%=c.getDocumentNumber() %></td>
						<td><%=c.getPhone() %></td>
						<td><%=c.getAddress() %></td>
						<td><%=c.getBusinessName() %></td>
						<td>
							<a href="CustomerServlet?type=obtener&code=<%=c.getId() %>" class="btn btn-primary">Editar</a>
							<a href="CustomerServlet?type=eliminar&code=<%=c.getId() %>" class="btn btn-primary">Eliminar</a>
						</td>
					</tr>
					<%	}
					} %>
				</tbody>
			</table>
		</div>
	
	</div>
	</div>

</body>
</html>