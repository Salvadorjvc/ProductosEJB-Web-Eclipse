<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Panel Principal - Sistema CRUD</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
      rel="stylesheet" crossorigin="anonymous">
</head>
<body class="bg-light">

<!-- Encabezado -->
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
  <div class="container-fluid">
    <span class="navbar-brand mb-0 h1">Dashboard - Sistema CRUD</span>
    <div class="d-flex align-items-center">
      <span class="text-white me-3">
        Bienvenido, ${usuario.nombre} ${usuario.apellido}
      </span>
      <a href="perfil" class="btn btn-warning btn-sm me-2">Mi Perfil</a>
      <a href="Logout" class="btn btn-danger btn-sm">Cerrar sesión</a>
    </div>
  </div>
</nav>

<!-- Contenido principal -->
<div class="container mt-5">
  <div class="text-center mb-4">
    <h2>Bienvenido al Panel Principal</h2>
    <p>Selecciona una opción para continuar:</p>
  </div>

  <div class="row justify-content-center">
    <!-- Card Productos -->
    <div class="col-md-3">
      <div class="card text-center shadow-sm">
        <div class="card-body">
          <h5 class="card-title">Gestión de Productos</h5>
          <p class="card-text">Crea, edita o elimina productos fácilmente.</p>
          <a href="productos" class="btn btn-primary">Ir a Productos</a>
        </div>
      </div>
    </div>

    <!-- Card Perfil -->
    <div class="col-md-3">
      <div class="card text-center shadow-sm">
        <div class="card-body">
          <h5 class="card-title">Mi Perfil</h5>
          <p class="card-text">Consulta o cambia tu información personal.</p>
          <a href="perfil" class="btn btn-secondary">Ir a Perfil</a>
        </div>
      </div>
    </div>

    <!-- (Opcional) Card Reportes -->
    <div class="col-md-3">
      <div class="card text-center shadow-sm">
        <div class="card-body">
          <h5 class="card-title">Reportes</h5>
          <p class="card-text">Consulta estadísticas o informes del sistema.</p>
          <a href="#" class="btn btn-success disabled">Próximamente</a>
        </div>
      </div>
    </div>
  </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        crossorigin="anonymous"></script>

</body>
</html>
