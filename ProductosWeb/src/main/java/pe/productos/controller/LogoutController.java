package pe.productos.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/Logout")
public class LogoutController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Obtener la sesión actual si existe (sin crear una nueva)
        HttpSession sesion = request.getSession(false);

        if (sesion != null) {
            // Opción: limpiar atributos específicos antes de invalidar
            sesion.removeAttribute("usuario");

            // Invalida toda la sesión
            sesion.invalidate();
        }

        // Prevenir el cacheo del login anterior
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0
        response.setDateHeader("Expires", 0); // Proxies

        // Redirigir al login
        response.sendRedirect(request.getContextPath() + "/Login");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
