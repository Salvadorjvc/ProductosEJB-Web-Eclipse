package pe.productos.service;

import java.util.List;
import javax.sql.DataSource;

import jakarta.annotation.Resource;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;

import pe.productos.dao.ProductoDAO;
import pe.productos.model.Producto;

/**
 * Session Bean implementation class ProductoService
 */
@Stateless
@LocalBean
public class ProductoService implements ProductoServiceLocal {

    // Vincula el DataSource configurado en tu servidor (igual que en UsuarioService)
    @Resource(lookup = "java:app/jdbc/DAE1DS")
    private DataSource ds;

    public ProductoService() {
        // Constructor vacío
    }

    // -------------------------------------------------------------
    // Insertar Producto
    // -------------------------------------------------------------
    @Override
    public int insertar(Producto producto) {
        ProductoDAO productoDAO = new ProductoDAO(ds);

        // Validaciones de negocio (ejemplo)
        if (producto.getNombre() == null || producto.getNombre().isEmpty()) {
            return -10; // Error: nombre vacío
        }

        if (producto.getPrecio() == null || producto.getPrecio().compareTo(java.math.BigDecimal.ZERO) <= 0) {
            return -11; // Error: precio no válido
        }

        if (producto.getStock() < 0) {
            return -12; // Error: stock negativo
        }

        return productoDAO.insertar(producto);
    }

    // -------------------------------------------------------------
    // Actualizar Producto
    // -------------------------------------------------------------
    @Override
    public int actualizar(Producto producto) {
        ProductoDAO productoDAO = new ProductoDAO(ds);

        if (producto.getIdProducto() <= 0) {
            return -13; // Error: ID inválido
        }

        return productoDAO.actualizar(producto);
    }

    // -------------------------------------------------------------
    // Eliminar Producto
    // -------------------------------------------------------------
    @Override
    public int eliminar(int idProducto) {
        ProductoDAO productoDAO = new ProductoDAO(ds);
        return productoDAO.eliminar(idProducto);
    }

 
    // -------------------------------------------------------------
    // istar todos los productos
    // -------------------------------------------------------------
    @Override
    public List<Producto> listar() {
        ProductoDAO productoDAO = new ProductoDAO(ds);
        return productoDAO.listar();
    }
    
    //Luego tengo que modificarlo TODO
    @Override
    public Producto obtenerPorId(int idProducto) {
        ProductoDAO productoDAO = new ProductoDAO(ds);
        return productoDAO.obtenerPorId(idProducto);
    }

    @Override
    public List<Producto> buscarPorCampo(String campo, String filtro) {
        ProductoDAO productoDAO = new ProductoDAO(ds);
        return productoDAO.buscarPorCampo(campo, filtro);
    }

}
