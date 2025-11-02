package pe.productos.service;

import java.util.List;

import javax.sql.DataSource;

import jakarta.annotation.Resource;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import pe.productos.dao.UsuarioDAO;
import pe.productos.model.Usuario;

/**
 * Session Bean implementation class UsuarioService
 */
@Stateless
@LocalBean
public class UsuarioService implements UsuarioServiceLocal {

    @Resource(lookup = "java:app/jdbc/DAE1DS")
    private DataSource ds;

    public UsuarioService() {
        // Constructor vacío por defecto
    }

    //Validar usuario (email + password)
    public boolean validarUsuario(Usuario usuario) {
        UsuarioDAO usuarioDAO = new UsuarioDAO(ds);
        return usuarioDAO.validarUsuario(
                usuario.getEmail(),
                usuario.getPassword()
        );
    }

    //Obtener ID por email y password
    public int obtenerIdUsuario(Usuario usuario) {
        UsuarioDAO usuarioDAO = new UsuarioDAO(ds);
        return usuarioDAO.obtenerIdUsuario(usuario);
    }

    //Obtener por ID
    public Usuario obtenerPorId(int idUsuario) {
        UsuarioDAO usuarioDAO = new UsuarioDAO(ds);
        return usuarioDAO.obtenerPorId(idUsuario);
    }

    //Insertar usuario (con validaciones de negocio)
    public int insertar(Usuario usuario) {
        UsuarioDAO usuarioDAO = new UsuarioDAO(ds);

        if (usuario.getPassword() == null || usuario.getPassword().length() < 6) {
            return -11; // Error: contraseña demasiado corta
        }

        if (!tieneUnaLetraYNumero(usuario.getPassword())) {
            return -12; // Error: contraseña no cumple requisitos
        }

        return usuarioDAO.insertar(usuario);
    }
    
    //Actualizar contraseña 
    public int cambiarContrasena(int idUsuario, String passwordActual, String passwordNueva) {
        UsuarioDAO usuarioDAO = new UsuarioDAO(ds);
        return usuarioDAO.cambiarContrasena(idUsuario, passwordActual, passwordNueva);
    }

    // Actualizar usuario (con validaciones de negocio)
    public int actualizar(Usuario usuario) {
        UsuarioDAO usuarioDAO = new UsuarioDAO(ds);

        if (usuario.getPassword() == null || usuario.getPassword().length() < 6) {
            return -11;
        }

        if (!tieneUnaLetraYNumero(usuario.getPassword())) {
            return -12;
        }

        return usuarioDAO.actualizar(usuario);
    }

    //Eliminar usuario
    public int eliminar(int idUsuario) {
        UsuarioDAO usuarioDAO = new UsuarioDAO(ds);
        return usuarioDAO.eliminar(idUsuario);
    }

    //Listar todos los usuarios
    public List<Usuario> listar() {
        UsuarioDAO usuarioDAO = new UsuarioDAO(ds);
        return usuarioDAO.listar();
    }

    //Auxiliar: validar que la contraseña tenga al menos una letra y un número
    private boolean tieneUnaLetraYNumero(String password) {
        boolean tieneLetra = false;
        boolean tieneNumero = false;

        for (char c : password.toCharArray()) {
            if (Character.isLetter(c)) {
                tieneLetra = true;
            } else if (Character.isDigit(c)) {
                tieneNumero = true;
            }

            if (tieneLetra && tieneNumero) break;
        }

        return tieneLetra && tieneNumero;
    }
}
