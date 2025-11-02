<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    // Si el usuario no ha iniciado sesión, redirigir al login
    if (session == null || session.getAttribute("usuario") == null) {
        response.sendRedirect("Login");
        return;
    }
%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>Inicio - CRUD Productos</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" 
      rel="stylesheet" crossorigin="anonymous">
</head>
<body class="bg-light">
  <div class="container text-center mt-5">
    <div class="card shadow-lg p-5">
      <h1 class="text-primary mb-4">Bienvenido al Sistema CRUD</h1>
      <p class="lead">Gestione los productos de la tienda de forma sencilla.</p>

      <a href="productos" class="btn btn-success btn-lg mt-3">Ir a Gestión de Productos</a>

      <form action="Logout" method="post" class="mt-3">
        <button type="submit" class="btn btn-outline-danger">Cerrar sesión</button>
      </form>
    </div>
  </div>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"
          crossorigin="anonymous"></script>
</body>
</html>
