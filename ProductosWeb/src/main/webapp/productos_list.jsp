<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, pe.productos.model.Producto" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gestión de Productos</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
 rel="stylesheet" 
 crossorigin="anonymous">
</head>
<body class="bg-light">

<div class="container mt-4">
  <!-- Encabezado con nombre y acciones -->
  <div class="d-flex justify-content-between align-items-center mb-3">
      <h2>Gestión de Productos</h2>

      <div>
          <span>Bienvenido, ${usuario.nombre} ${usuario.apellido}</span>
          <a href="perfil" class="btn btn-primary btn-sm ms-2">Mi Perfil</a>
          <a href="Logout" class="btn btn-danger btn-sm ms-2">Cerrar sesión</a>
      </div>
  </div>

  <!-- Botón para crear -->
  <div class="mb-3">
      <a href="productos?action=new" class="btn btn-success">+ Nuevo Producto</a>
  </div>

  <!-- Formulario de búsqueda -->
  <form method="get" action="productos" class="d-flex mb-3">
      <select name="campo" class="form-select w-auto me-2">
          <option value="nombre">Nombre</option>
          <option value="categoria">Categoría</option>
          <option value="descripcion">Descripción</option>
      </select>
      <input type="text" name="q" class="form-control me-2" placeholder="Buscar...">
      <button type="submit" class="btn btn-outline-primary">Buscar</button>
  </form>

  <!-- Mensajes -->
  <c:if test="${not empty correcto}">
      <div class="alert alert-success">${correcto}</div>
  </c:if>

  <c:if test="${not empty mensajeError}">
      <div class="alert alert-danger">${mensajeError}</div>
  </c:if>

  <!-- Tabla -->
  <table class="table table-bordered table-striped align-middle text-center">
      <thead class="table-dark">
          <tr>
              <th>ID</th>
              <th>Nombre</th>
              <th>Descripción</th>
              <th>Precio</th>
              <th>Stock</th>
              <th>Categoría</th>
              <th>Acciones</th>
          </tr>
      </thead>
      <tbody>
      <c:forEach var="p" items="${lista}">
          <tr>
              <td>${p.idProducto}</td>
              <td>${p.nombre}</td>
              <td>${p.descripcion}</td>
              <td>${p.precio}</td>
              <td>${p.stock}</td>
              <td>${p.categoria}</td>
              <td>
                  <a href="productos?action=edit&id=${p.idProducto}" class="btn btn-warning btn-sm">Editar</a>
                  <a href="productos?action=delete&id=${p.idProducto}" class="btn btn-danger btn-sm"
                     onclick="return confirm('¿Seguro que deseas eliminar este producto?')">Eliminar</a>
              </td>
          </tr>
      </c:forEach>
      </tbody>
  </table>
</div>

<!-- Bootstrap -->
<script 
  src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" 
  crossorigin="anonymous"></script>

</body>
</html>
