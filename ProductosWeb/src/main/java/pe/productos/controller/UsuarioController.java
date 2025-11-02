package pe.productos.controller;

import java.io.IOException;
import java.util.List;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.productos.model.Usuario;
import pe.productos.service.UsuarioServiceLocal;

@WebServlet("/usuarios")
public class UsuarioController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    private UsuarioServiceLocal usuarioService;

    public UsuarioController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if (accion == null) {
            listarUsuarios(request, response);
            return;
        }

        switch (accion) {
            case "nuevo":
                request.getRequestDispatcher("usuario_form.jsp").forward(request, response);
                break;

            case "editar":
                int idEditar = Integer.parseInt(request.getParameter("idUsuario"));
                Usuario usuarioEditar = usuarioService.obtenerPorId(idEditar);
                request.setAttribute("usuario", usuarioEditar);
                request.getRequestDispatcher("usuario_form.jsp").forward(request, response);
                break;

            case "eliminar":
                int idEliminar = Integer.parseInt(request.getParameter("idUsuario"));
                int resultado = usuarioService.eliminar(idEliminar);

                if (resultado > 0) {
                    request.setAttribute("mensaje", "Usuario eliminado correctamente.");
                } else {
                    request.setAttribute("mensajeError", "No se pudo eliminar el usuario.");
                }
                listarUsuarios(request, response);
                break;

            default:
                listarUsuarios(request, response);
                break;
        }
    }

    private void listarUsuarios(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Usuario> lista = usuarioService.listar();
        request.setAttribute("usuarios", lista);

        if (lista == null) {
            request.setAttribute("mensajeError", "Error obteniendo los datos de usuarios.");
        }

        request.getRequestDispatcher("usuario.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if (accion == null) {
            listarUsuarios(request, response);
            return;
        }

        switch (accion) {
            case "insertar":
                insertarUsuario(request, response);
                break;

            case "editar":
                actualizarUsuario(request, response);
                break;

            default:
                listarUsuarios(request, response);
                break;
        }
    }

    private void insertarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Usuario u = new Usuario();
        u.setNombre(nombre);
        u.setApellido(apellido);
        u.setEmail(email);
        u.setPassword(password);

        if (nombre.isEmpty() || apellido.isEmpty() || email.isEmpty() || password.isEmpty()) {
            request.setAttribute("mensajeError", "Todos los campos son obligatorios.");
            request.setAttribute("usuario", u);
            request.getRequestDispatcher("usuario_form.jsp").forward(request, response);
            return;
        }

        int resultado = usuarioService.insertar(u);

        switch (resultado) {
            case -11:
                request.setAttribute("mensajeError", "La contraseña debe tener al menos 6 caracteres.");
                break;
            case -12:
                request.setAttribute("mensajeError", "La contraseña debe tener al menos una letra y un número.");
                break;
            case -1:
                request.setAttribute("mensajeError", "Error al insertar el usuario en la base de datos.");
                break;
            default:
                request.setAttribute("mensaje", "Usuario insertado correctamente.");
                listarUsuarios(request, response);
                return;
        }

        request.setAttribute("usuario", u);
        request.getRequestDispatcher("usuario_form.jsp").forward(request, response);
    }

    private void actualizarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Usuario u = new Usuario();
        u.setIdUsuario(idUsuario);
        u.setNombre(nombre);
        u.setApellido(apellido);
        u.setEmail(email);
        u.setPassword(password);

        if (nombre.isEmpty() || apellido.isEmpty() || email.isEmpty() || password.isEmpty()) {
            request.setAttribute("mensajeError", "Todos los campos son obligatorios.");
            request.setAttribute("usuario", u);
            request.getRequestDispatcher("usuario_form.jsp").forward(request, response);
            return;
        }

        int resultado = usuarioService.actualizar(u);

        switch (resultado) {
            case -11:
                request.setAttribute("mensajeError", "La contraseña debe tener al menos 6 caracteres.");
                break;
            case -12:
                request.setAttribute("mensajeError", "La contraseña debe tener al menos una letra y un número.");
                break;
            case -1:
                request.setAttribute("mensajeError", "Error al actualizar el usuario.");
                break;
            default:
                request.setAttribute("mensaje", "Usuario actualizado correctamente.");
                listarUsuarios(request, response);
                return;
        }

        request.setAttribute("usuario", u);
        request.getRequestDispatcher("usuario_form.jsp").forward(request, response);
    }
}
