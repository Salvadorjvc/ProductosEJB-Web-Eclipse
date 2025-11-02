<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="pe.productos.model.Usuario"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>Perfil de Usuario</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
	rel="stylesheet" crossorigin="anonymous">
</head>

<body class="bg-light">
	<div class="container mt-5">
		<div class="card shadow-lg col-md-6 offset-md-3 p-4">
			<h2 class="text-center text-primary mb-4">Mi Perfil</h2>

			<!-- ✅ Mensaje de éxito -->
			<c:if test="${not empty mensajeExito}">
				<div class="alert alert-success">${mensajeExito}</div>
			</c:if>

			<!-- ✅ Mensaje de error -->
			<c:if test="${not empty mensajeError}">
				<div class="alert alert-danger">${mensajeError}</div>
			</c:if>

			<table class="table table-bordered mb-4">
				<tr>
					<th>Nombre</th>
					<td>${usuario.nombre}</td>
				</tr>
				<tr>
					<th>Apellido</th>
					<td>${usuario.apellido}</td>
				</tr>
				<tr>
					<th>Email</th>
					<td>${usuario.email}</td>
				</tr>
				<tr>
					<th>Fecha de Creación</th>
					<td>${usuario.fechaCreacion}</td>
				</tr>
			</table>

			<div class="d-flex justify-content-between">
				<a href="perfil?action=editar" class="btn btn-warning"> Cambiar
					Contraseña </a> <a href="productos" class="btn btn-secondary">
					Volver </a>
			</div>
		</div>
	</div>

	<!-- Bootstrap JS -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"
		crossorigin="anonymous">
		
	</script>
</body>
</html>
