<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="pe.productos.model.Producto" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    Producto objProducto = (Producto) request.getAttribute("producto");
    boolean estaEditando = objProducto != null && objProducto.getIdProducto() > 0;
%>

<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title><%= estaEditando ? "Editar" : "Nuevo" %> Producto</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" 
      rel="stylesheet" crossorigin="anonymous">
</head>
<body class="bg-light">

<div class="container mt-5 col-md-6">
  <div class="card shadow p-4">
    <h2 class="text-center text-primary mb-4"><%= estaEditando ? "Editar" : "Registrar" %> Producto</h2>

    <!-- MENSAJES -->
    <c:if test="${not empty correcto}">
      <div class="alert alert-success">${correcto}</div>
    </c:if>

    <c:if test="${not empty mensajeError}">
      <div class="alert alert-danger">${mensajeError}</div>
    </c:if>

    <form method="post" action="productos">

      <% if (estaEditando) { %>
      <div class="mb-3">
        <label class="form-label">ID:</label>
        <input type="text" class="form-control" 
               value="<%= objProducto.getIdProducto() %>" readonly>
        <input type="hidden" name="idProducto" 
               value="<%= objProducto.getIdProducto() %>">
      </div>
      <% } %>

      <div class="mb-3">
        <label class="form-label">Nombre:</label>
        <input type="text" class="form-control" name="nombre" id="nombre"
               value="<%= objProducto != null ? objProducto.getNombre() : "" %>" required>
      </div>

      <div class="mb-3">
        <label class="form-label">Descripción:</label>
        <input type="text" class="form-control" name="descripcion" id="descripcion"
               value="<%= objProducto != null ? objProducto.getDescripcion() : "" %>">
      </div>

      <div class="mb-3">
        <label class="form-label">Precio:</label>
        <input type="number" class="form-control" step="0.01" name="precio" id="precio"
               value="<%= objProducto != null && objProducto.getPrecio() != null ? objProducto.getPrecio() : "" %>" required>
      </div>

      <div class="mb-3">
        <label class="form-label">Stock:</label>
        <input type="number" class="form-control" name="stock" id="stock"
               value="<%= objProducto != null ? objProducto.getStock() : "" %>" required>
      </div>

      <div class="mb-3">
        <label class="form-label">Categoría:</label>
        <input type="text" class="form-control" name="categoria" id="categoria"
               value="<%= objProducto != null ? objProducto.getCategoria() : "" %>">
      </div>

      <div class="d-flex justify-content-between mt-4">
        <button type="submit" class="btn btn-primary">Guardar</button>
        <a href="productos" class="btn btn-secondary">Cancelar</a>
      </div>

    </form>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" 
        crossorigin="anonymous"></script>
</body>
</html>
