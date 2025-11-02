package pe.productos.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import pe.productos.model.Usuario;

public class UsuarioDAO {

    private final DataSource ds;

    // Constructor recibe el DataSource (inyección de dependencia)
    public UsuarioDAO(DataSource ds) {
        this.ds = ds;
    }

    //Validar usuario (login)
    public boolean validarUsuario(String email, String password) {
        boolean existe = false;
        String query = "{call dbo.sp_validar_usuario(?, ?)}";

        try (Connection conexion = ds.getConnection();
             CallableStatement cstmt = conexion.prepareCall(query)) {

            cstmt.setString(1, email);
            cstmt.setString(2, password);
            ResultSet rs = cstmt.executeQuery();

            if (rs.next()) {
                existe = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return existe;
    }


    //Obtener ID del usuario (según email y password)
    public int obtenerIdUsuario(Usuario usuario) {
        int id = -1;

        try (Connection conexion = ds.getConnection();
             PreparedStatement pstmt = conexion.prepareStatement(
                     "SELECT id_usuario FROM dbo.Usuarios WHERE email=? AND password=?")) {

            pstmt.setString(1, usuario.getEmail());
            pstmt.setString(2, usuario.getPassword());
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                id = rs.getInt("id_usuario");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return id;
    }

    //Obtener usuario por ID
    public Usuario obtenerPorId(int idUsuario) {
        Usuario u = null;

        try (Connection conexion = ds.getConnection();
             PreparedStatement pstmt = conexion.prepareStatement(
                     "SELECT * FROM dbo.Usuarios WHERE id_usuario=?")) {

            pstmt.setInt(1, idUsuario);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                u = new Usuario();
                u.setIdUsuario(rs.getInt("id_usuario"));
                u.setNombre(rs.getString("nombre"));
                u.setApellido(rs.getString("apellido"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                u.setFechaCreacion(rs.getTimestamp("fecha_creacion"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return u;
    }

    //Insertar usuario (usa procedimiento almacenado)
    public int insertar(Usuario usuario) {
        String query = "{call dbo.sp_insertar_usuario(?, ?, ?, ?)}";

        try (Connection conexion = ds.getConnection();
             CallableStatement cstmt = conexion.prepareCall(query)) {

            cstmt.setString(1, usuario.getNombre());
            cstmt.setString(2, usuario.getApellido());
            cstmt.setString(3, usuario.getEmail());
            cstmt.setString(4, usuario.getPassword());

            return cstmt.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return -1;
    }

    //Actualizar usuario
    public int actualizar(Usuario usuario) {
        String query = "{call dbo.sp_actualizar_usuario(?, ?, ?, ?, ?)}";

        try (Connection conexion = ds.getConnection();
             CallableStatement cstmt = conexion.prepareCall(query)) {

            cstmt.setInt(1, usuario.getIdUsuario());
            cstmt.setString(2, usuario.getNombre());
            cstmt.setString(3, usuario.getApellido());
            cstmt.setString(4, usuario.getEmail());
            cstmt.setString(5, usuario.getPassword());

            return cstmt.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return -1;
    }

    //Eliminar usuario
    public int eliminar(int idUsuario) {
        String query = "{call dbo.sp_eliminar_usuario(?)}";

        try (Connection conexion = ds.getConnection();
             CallableStatement cstmt = conexion.prepareCall(query)) {

            cstmt.setInt(1, idUsuario);
            return cstmt.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return -1;
    }

    //Listar usuarios
    public List<Usuario> listar() {
        List<Usuario> lista = new ArrayList<>();
        String query = "{call dbo.sp_listar_usuarios()}";

        try (Connection conexion = ds.getConnection();
             CallableStatement cstmt = conexion.prepareCall(query);
             ResultSet rs = cstmt.executeQuery()) {

            while (rs.next()) {
                Usuario u = new Usuario();
                u.setIdUsuario(rs.getInt("id_usuario"));
                u.setNombre(rs.getString("nombre"));
                u.setApellido(rs.getString("apellido"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                u.setFechaCreacion(rs.getTimestamp("fecha_creacion"));
                lista.add(u);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
 // Cambiar contraseña
    public int cambiarContrasena(int idUsuario, String passwordActual, String passwordNueva) {
        String query = "{call dbo.sp_cambiar_contraseña(?, ?, ?)}";
        try (Connection conexion = ds.getConnection();
             CallableStatement cstmt = conexion.prepareCall(query)) {

            cstmt.setInt(1, idUsuario);
            cstmt.setString(2, passwordActual);
            cstmt.setString(3, passwordNueva);

            return cstmt.executeUpdate(); // devuelve 1 si se actualizó, 0 si no

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return -1;
    }

}
