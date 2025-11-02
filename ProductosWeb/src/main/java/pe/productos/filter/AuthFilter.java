package pe.productos.filter;

import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;
import pe.productos.model.Usuario; // 

@WebFilter({"/perfil", "/productos", "/dashboard.jsp", "/index.jsp"}) //  protege las rutas de la app
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession sesion = req.getSession(false); // false: no crea una nueva sesión si no existe

        Usuario usuario = (sesion != null) ? (Usuario) sesion.getAttribute("usuario") : null;
        boolean estaLogueado = usuario != null;

        if (!estaLogueado) {
            // Redirige al login si no hay sesión activa
            resp.sendRedirect(req.getContextPath() + "/Login");
            return;
        }

        // Si el usuario está logueado, continúa con la cadena
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Nada que inicializar
    }

    @Override
    public void destroy() {
        // Nada que limpiar
    }
}
