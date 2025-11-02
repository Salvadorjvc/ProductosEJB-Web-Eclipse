package pe.productos.controller;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import pe.productos.model.Usuario;
import pe.productos.service.UsuarioServiceLocal;

import java.io.IOException;

@WebServlet("/Login")
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    private UsuarioServiceLocal usuarioService;

    public LoginController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Muestra el formulario de login
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("usuario");
        String password = request.getParameter("clave");
        String mensaje = "";

        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setPassword(password);

        boolean valido = usuarioService.validarUsuario(usuario);

        if (valido) {
            int idUsuario = usuarioService.obtenerIdUsuario(usuario);
            Usuario usuarioCompleto = usuarioService.obtenerPorId(idUsuario);

            HttpSession sesion = request.getSession(true);
            sesion.setAttribute("usuario", usuarioCompleto);

            // Redirige correctamente (PRG)
            response.sendRedirect("dashboard.jsp");

        } else {
            mensaje = "Usuario o contraseña incorrectos.";
            request.setAttribute("mensajeError", mensaje);
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
