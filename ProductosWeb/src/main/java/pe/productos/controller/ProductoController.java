package pe.productos.controller;

import pe.productos.model.Producto;
import pe.productos.service.ProductoService;

import jakarta.ejb.EJB;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet("/productos")
public class ProductoController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    //  Inyecta el servicio (en lugar de usar el DAO directamente)
    @EJB
    private ProductoService productoService;

    // ===========================================================
    // MÉTODO GET
    // ===========================================================
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action") == null ? "list" : req.getParameter("action");

        switch (action) {
            case "new":
                req.setAttribute("producto", new Producto());
                req.getRequestDispatcher("producto_form.jsp").forward(req, resp);
                break;

            case "edit":
                try {
                    int id = Integer.parseInt(req.getParameter("id"));
                    Producto p = productoService.obtenerPorId(id);
                    if (p == null) {
                        req.setAttribute("mensajeError", "El producto con ID " + id + " no existe.");
                        listarProductos(req, resp);
                    } else {
                        req.setAttribute("producto", p);
                        req.getRequestDispatcher("producto_form.jsp").forward(req, resp);
                    }
                } catch (NumberFormatException e) {
                    req.setAttribute("mensajeError", "ID inválido.");
                    listarProductos(req, resp);
                }
                break;

            case "delete":
                try {
                    int idDel = Integer.parseInt(req.getParameter("id"));
                    int filas = productoService.eliminar(idDel);
                    if (filas > 0) {
                        req.setAttribute("correcto", "Producto eliminado correctamente.");
                    } else {
                        req.setAttribute("mensajeError", "No se pudo eliminar el producto.");
                    }
                } catch (NumberFormatException e) {
                    req.setAttribute("mensajeError", "ID inválido.");
                }
                listarProductos(req, resp);
                break;

            default:
                listarProductos(req, resp);
                break;
        }
    }

    // ===========================================================
    // LISTAR
    // ===========================================================
    private void listarProductos(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String campo = req.getParameter("campo");
        String filtro = req.getParameter("q");

        List<Producto> lista;

        if (filtro != null && !filtro.trim().isEmpty() && campo != null && !campo.trim().isEmpty()) {
            lista = productoService.buscarPorCampo(campo, filtro);
        } else {
            lista = productoService.listar();
        }

        req.setAttribute("lista", lista);
        req.getRequestDispatcher("productos_list.jsp").forward(req, resp);
    }

    // ===========================================================
    // MÉTODO POST
    // ===========================================================
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            int id = req.getParameter("idProducto") == null || req.getParameter("idProducto").isEmpty()
                    ? 0
                    : Integer.parseInt(req.getParameter("idProducto"));

            String nombre = req.getParameter("nombre");
            String descripcion = req.getParameter("descripcion");
            String categoria = req.getParameter("categoria");
            String precioStr = req.getParameter("precio");
            String stockStr = req.getParameter("stock");

            if (nombre == null || nombre.trim().isEmpty()
                    || precioStr == null || precioStr.trim().isEmpty()
                    || stockStr == null || stockStr.trim().isEmpty()) {

                req.setAttribute("mensajeError", "Complete los campos obligatorios (*)");
                Producto p = new Producto(id, nombre, descripcion,
                        precioStr != null && !precioStr.isEmpty() ? new BigDecimal(precioStr) : null,
                        stockStr != null && !stockStr.isEmpty() ? Integer.parseInt(stockStr) : 0,
                        categoria, null);
                req.setAttribute("producto", p);
                req.getRequestDispatcher("producto_form.jsp").forward(req, resp);
                return;
            }

            BigDecimal precio = new BigDecimal(precioStr);
            int stock = Integer.parseInt(stockStr);

            Producto p = new Producto();
            p.setIdProducto(id);
            p.setNombre(nombre);
            p.setDescripcion(descripcion);
            p.setPrecio(precio);
            p.setStock(stock);
            p.setCategoria(categoria);

            int filas;
            if (id == 0) {
                filas = productoService.insertar(p);
                if (filas > 0)
                    req.setAttribute("correcto", "Producto creado correctamente.");
                else
                    req.setAttribute("mensajeError", "Error al crear el producto.");
            } else {
                filas = productoService.actualizar(p);
                if (filas > 0)
                    req.setAttribute("correcto", "Producto actualizado correctamente.");
                else
                    req.setAttribute("mensajeError", "Error al actualizar el producto.");
            }

            listarProductos(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("mensajeError", "Error en los datos enviados.");
            req.getRequestDispatcher("producto_form.jsp").forward(req, resp);
        }
    }
}
