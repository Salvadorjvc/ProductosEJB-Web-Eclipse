<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>Cambiar Contraseña</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
      rel="stylesheet" crossorigin="anonymous">
</head>
<body class="bg-light">
<div class="container mt-5 col-md-5">
  <div class="card shadow p-4">
    <h2 class="text-center text-primary mb-4">Cambiar Contraseña</h2>

    <c:if test="${not empty mensajeError}">
      <div class="alert alert-danger">${mensajeError}</div>
    </c:if>

    <form method="post" action="perfil">
      <input type="hidden" name="action" value="actualizarClave">

      <div class="mb-3">
        <label class="form-label">Contraseña actual:</label>
        <input type="password" class="form-control" name="passwordActual" required>
      </div>

      <div class="mb-3">
        <label class="form-label">Nueva contraseña:</label>
        <input type="password" class="form-control" name="passwordNueva" required>
      </div>

      <div class="mb-3">
        <label class="form-label">Confirmar nueva contraseña:</label>
        <input type="password" class="form-control" name="passwordConfirmar" required>
      </div>

      <div class="d-flex justify-content-between">
        <button type="submit" class="btn btn-primary">Guardar cambios</button>
        <a href="perfil" class="btn btn-secondary">Cancelar</a>
      </div>
    </form>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"
        crossorigin="anonymous"></script>
</body>
</html>
