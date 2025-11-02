<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login - Productos</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
</head>
<body class="bg-light">

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-4">
            <div class="card shadow">
                <div class="card-header text-center bg-success text-white">
                    <h4>Ingreso al Sistema</h4>
                </div>
                <div class="card-body">
                    <form action="Login" method="POST">
                        <div class="mb-3">
                            <label for="usuario" class="form-label">Correo:</label>
                            <input type="text" name="usuario" id="usuario" class="form-control" required>
                        </div>
                        <div class="mb-3">
                            <label for="clave" class="form-label">Contraseña:</label>
                            <input type="password" name="clave" id="clave" class="form-control" required>
                        </div>
                        <div class="d-grid">
                            <button type="submit" class="btn btn-success">Ingresar</button>
                        </div>
                    </form>

                    <!-- Mensajes de error o éxito -->
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
