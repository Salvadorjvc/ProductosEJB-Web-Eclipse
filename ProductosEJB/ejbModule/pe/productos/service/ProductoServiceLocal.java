package pe.productos.service;

import java.util.List;
import pe.productos.model.Producto;

import jakarta.ejb.Local;

@Local
public interface ProductoServiceLocal {
    int insertar(Producto producto);
    int actualizar(Producto producto);
    int eliminar(int idProducto);
    Producto obtenerPorId(int idProducto);
    List<Producto> listar();
	List<Producto> buscarPorCampo(String campo, String filtro);
}
