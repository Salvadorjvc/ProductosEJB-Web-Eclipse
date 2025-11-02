<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="pe.productos.model.Usuario" %>

<%
    Usuario objUsuario = (Usuario) request.getAttribute("usuario");
    boolean estaEditando = objUsuario != null && objUsuario.getIdUsuario() > 0;
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><%= estaEditando ? "Editar usuario" : "Nuevo usuario" %></title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
 rel="stylesheet" crossorigin="anonymous">
</head>
<body class="bg-light">

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card shadow">
                <div class="card-header text-center bg-primary text-white">
                    <h4><%= estaEditando ? "Editar usuario" : "Registrar nuevo usuario" %></h4>
                </div>
                <div class="card-body">
                    <form method="POST" action="Usuario?accion=<%= estaEditando ? "editar" : "insertar" %>">
                        
                        <% if (estaEditando) { %>
                        <input type="hidden" name="id" value="<%= objUsuario.getIdUsuario() %>">
                        <% } %>

                        <div class="mb-3">
                            <label for="nombre" class="form-label">Nombre:</label>
                            <input type="text" class="form-control" name="nombre" id="nombre"
                                   value="<%= objUsuario != null ? objUsuario.getNombre() : "" %>" required>
                        </div>

                        <div class="mb-3">
                            <label for="apellido" class="form-label">Apellido:</label>
                            <input type="text" class="form-control" name="apellido" id="apellido"
                                   value="<%= objUsuario != null ? objUsuario.getApellido() : "" %>" required>
                        </div>

                        <div class="mb-3">
                            <label for="email" class="form-label">Correo electrónico:</label>
                            <input type="email" class="form-control" name="email" id="email"
                                   value="<%= objUsuario != null ? objUsuario.getEmail() : "" %>">
                        </div>

                        <div class="mb-3">
                            <label for="password" class="form-label">Contraseña:</label>
                            <input type="password" class="form-control" name="password" id="password"
                                   value="<%= objUsuario != null ? objUsuario.getPassword() : "" %>" required>
                        </div>

                        <div class="d-grid">
                            <button type="submit" class="btn btn-success">Guardar</button>
                            <a href="Usuario" class="btn btn-secondary mt-2">Cancelar</a>
                        </div>
                    </form>

                    <c:if test="${not empty mensajeError}">
                        <div class="alert alert-danger mt-3 text-center">
                            ${mensajeError}
                        </div>
                    </c:if>
                    <c:if test="${not empty mensaje}">
                        <div class="alert alert-success mt-3 text-center">
                            ${mensaje}
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
</body>
</html>
