package pe.productos.model;
import java.sql.Timestamp;

public class Usuario {
    private int idUsuario;
    private String nombre;
    private String apellido;
    private String email;
    private String password;
    private Timestamp fechaCreacion;
    
   // Constructor vacío
    public Usuario() {
		// TODO Auto-generated constructor stub
	}
    
    //Constructor para el PerfilController
    public Usuario(String email, String password) {
        this.email = email;
        this.password = password;
    }

 // Getters y Setters

	public int getIdUsuario() {
        return idUsuario;
    }
	
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Timestamp fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
