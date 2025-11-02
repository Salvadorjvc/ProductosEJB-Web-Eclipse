<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, pe.productos.model.Usuario" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lista de Usuarios</title>
</head>
<body>

<h2>Lista de Usuarios</h2>

<%
    List<Usuario> usuarios = (List<Usuario>) request.getAttribute("usuarios");
%>

<p><a href="Usuario?accion=nuevo">➕ Nuevo usuario</a></p>

<p style="color:red;">${mensajeError}</p>

<form method="GET" action="Usuario">
    <input type="hidden" name="accion" value="buscar"/>
    <label for="q">Buscar por nombre:</label>
    <input type="text" name="q" value="${param.q != null ? param.q : ""}" />
    <input type="submit" value="Buscar"/>
</form>

<br/>

<table border="1" width="100%" cellpadding="5" cellspacing="0">
    <tr style="background-color: #f2f2f2;">
        <th>ID</th>
        <th>Nombre</th>
        <th>Apellido</th>
        <th>Email</th>
        <th>Fecha Creación</th>
        <th>Opciones</th>
    </tr>
<%
    if (usuarios != null && !usuarios.isEmpty()) {
        for (Usuario u : usuarios) {
%>
    <tr>
        <td><%= u.getIdUsuario() %></td>
        <td><%= u.getNombre() %></td>
        <td><%= u.getApellido() %></td>
        <td><%= u.getEmail() %></td>
        <td><%= u.getFechaCreacion() != null ? u.getFechaCreacion() : "-" %></td>
        <td>
            <a href="Usuario?accion=editar&idUsuario=<%= u.getIdUsuario() %>">✏️ Editar</a> |
            <a href="Usuario?accion=eliminar&idUsuario=<%= u.getIdUsuario() %>"
               onclick="return confirm('¿Seguro que deseas eliminar este usuario?');">🗑️ Eliminar</a>
        </td>
    </tr>
<%
        }
    } else {
%>
    <tr>
        <td colspan="6" style="text-align:center;">No se encontraron usuarios.</td>
    </tr>
<%
    }
%>
</table>

<p style="color:green;">${mensaje}</p>

</body>
</html>
