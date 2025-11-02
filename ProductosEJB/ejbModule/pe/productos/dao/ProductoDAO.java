package pe.productos.dao;

import java.sql.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import javax.sql.DataSource;
import pe.productos.model.Producto;

public class ProductoDAO {

    private final DataSource ds;

    public ProductoDAO(DataSource ds) {
        this.ds = ds;
    }

    // -------------------------------------------------------------
    // Insertar producto
    // -------------------------------------------------------------
    public int insertar(Producto producto) {
        String query = "{call dbo.sp_insertar_producto(?, ?, ?, ?, ?)}";
        try (Connection conexion = ds.getConnection();
             CallableStatement cstmt = conexion.prepareCall(query)) {

            cstmt.setString(1, producto.getNombre());
            cstmt.setString(2, producto.getDescripcion());
            cstmt.setBigDecimal(3, producto.getPrecio());
            cstmt.setInt(4, producto.getStock());
            cstmt.setString(5, producto.getCategoria());

            return cstmt.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    // -------------------------------------------------------------
    // Actualizar producto
    // -------------------------------------------------------------
    public int actualizar(Producto producto) {
        String query = "{call dbo.sp_actualizar_producto(?, ?, ?, ?, ?, ?)}";
        try (Connection conexion = ds.getConnection();
             CallableStatement cstmt = conexion.prepareCall(query)) {

            cstmt.setInt(1, producto.getIdProducto());
            cstmt.setString(2, producto.getNombre());
            cstmt.setString(3, producto.getDescripcion());
            cstmt.setBigDecimal(4, producto.getPrecio());
            cstmt.setInt(5, producto.getStock());
            cstmt.setString(6, producto.getCategoria());

            return cstmt.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    // -------------------------------------------------------------
    // Eliminar producto
    // -------------------------------------------------------------
    public int eliminar(int idProducto) {
        String query = "{call dbo.sp_eliminar_producto(?)}";
        try (Connection conexion = ds.getConnection();
             CallableStatement cstmt = conexion.prepareCall(query)) {

            cstmt.setInt(1, idProducto);
            return cstmt.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    // -------------------------------------------------------------
    // Listar productos
    // -------------------------------------------------------------
    public List<Producto> listar() {
        List<Producto> lista = new ArrayList<>();
        String query = "{call dbo.sp_listar_productos()}";

        try (Connection conexion = ds.getConnection();
             CallableStatement cstmt = conexion.prepareCall(query);
             ResultSet rs = cstmt.executeQuery()) {

            while (rs.next()) {
                Producto producto = new Producto();
                producto.setIdProducto(rs.getInt("id_producto"));
                producto.setNombre(rs.getString("nombre"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setPrecio(rs.getBigDecimal("precio"));
                producto.setStock(rs.getInt("stock"));
                producto.setCategoria(rs.getString("categoria"));

                Timestamp ts = rs.getTimestamp("fecha_creacion");
                if (ts != null) {
                    producto.setFechaCreacion(ts.toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDateTime());
                }

                lista.add(producto);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return lista;
    }

    // -------------------------------------------------------------
    // Buscar productos por campo
    // -------------------------------------------------------------
    public List<Producto> buscarPorCampo(String campo, String valor) {
        List<Producto> lista = new ArrayList<>();

        // Validar campo permitido para evitar SQL Injection
        List<String> camposPermitidos = Arrays.asList("nombre", "descripcion", "categoria");
        if (!camposPermitidos.contains(campo)) {
            throw new IllegalArgumentException("Campo no permitido para búsqueda.");
        }

        String sql = "SELECT * FROM dbo.Productos WHERE " + campo + " LIKE ?";

        try (Connection conexion = ds.getConnection();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, "%" + valor + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Producto producto = new Producto();
                producto.setIdProducto(rs.getInt("id_producto"));
                producto.setNombre(rs.getString("nombre"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setPrecio(rs.getBigDecimal("precio"));
                producto.setStock(rs.getInt("stock"));
                producto.setCategoria(rs.getString("categoria"));

                Timestamp ts = rs.getTimestamp("fecha_creacion");
                if (ts != null) {
                    producto.setFechaCreacion(ts.toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDateTime());
                }

                lista.add(producto);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return lista;
    }
    
 // -------------------------------------------------------------
 // Obtener producto por ID
 // -------------------------------------------------------------
    public Producto obtenerPorId(int idProducto) {
        Producto p = null;
        String sql = "SELECT id_producto, nombre, descripcion, precio, stock, categoria FROM Productos WHERE id_producto = ?";

        try (Connection con = ds.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idProducto);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                p = new Producto();
                p.setIdProducto(rs.getInt("id_producto"));
                p.setNombre(rs.getString("nombre"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setPrecio(rs.getBigDecimal("precio"));
                p.setStock(rs.getInt("stock"));
                p.setCategoria(rs.getString("categoria"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return p;
    }



}
