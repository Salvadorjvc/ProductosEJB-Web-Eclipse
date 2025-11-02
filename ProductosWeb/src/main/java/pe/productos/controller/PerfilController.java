package pe.productos.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

import jakarta.ejb.EJB;
import pe.productos.model.Usuario;
import pe.productos.service.UsuarioServiceLocal;

@WebServlet("/perfil")
public class PerfilController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    private UsuarioServiceLocal usuarioService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        HttpSession sesion = request.getSession(false);

        // Validar sesión
        Usuario usuario = (sesion != null) ? (Usuario) sesion.getAttribute("usuario") : null;
        if (usuario == null) {
            response.sendRedirect(request.getContextPath() + "/Login");
            return;
        }

        if ("editar".equalsIgnoreCase(action)) {
            // Ir a la vista para cambiar contraseña
            request.getRequestDispatcher("perfil_editar.jsp").forward(request, response);
        } else {
            // Mostrar perfil actual
            request.setAttribute("usuario", usuario);
            request.getRequestDispatcher("perfil_ver.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession sesion = request.getSession(false);
        Usuario usuario = (sesion != null) ? (Usuario) sesion.getAttribute("usuario") : null;

        if (usuario == null) {
            response.sendRedirect(request.getContextPath() + "/Login");
            return;
        }

        String actual = request.getParameter("passwordActual");
        String nueva = request.getParameter("passwordNueva");
        String confirmar = request.getParameter("passwordConfirmar");

        // Validaciones
        if (actual == null || nueva == null || confirmar == null ||
            actual.isEmpty() || nueva.isEmpty() || confirmar.isEmpty()) {
            request.setAttribute("mensajeError", "Todos los campos son obligatorios.");
            request.getRequestDispatcher("perfil_editar.jsp").forward(request, response);
            return;
        }

        if (!nueva.equals(confirmar)) {
            request.setAttribute("mensajeError", "La nueva contraseña y su confirmación no coinciden.");
            request.getRequestDispatcher("perfil_editar.jsp").forward(request, response);
            return;
        }

        // Ejecutar SP
        int filas = usuarioService.cambiarContrasena(usuario.getIdUsuario(), actual, nueva);

        if (filas > 0) {
            usuario.setPassword(nueva);
            sesion.setAttribute("usuario", usuario);
            request.setAttribute("mensajeExito", "Contraseña actualizada correctamente.");
            request.setAttribute("usuario", usuario);
            request.getRequestDispatcher("perfil_ver.jsp").forward(request, response);
        } else {
            request.setAttribute("mensajeError", "La contraseña actual es incorrecta o no se pudo actualizar.");
            request.getRequestDispatcher("perfil_editar.jsp").forward(request, response);
        }
    }
}
