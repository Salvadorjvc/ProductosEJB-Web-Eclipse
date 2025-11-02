package pe.productos.model;

import java.math.BigDecimal; //para trabajar con números decimales de alta precisión
import java.time.LocalDateTime; // para manejar fechas y horas

public class Producto {
	
	// definiendo las variables
	private int idProducto;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private int stock;
    private String categoria;
    private LocalDateTime fechaCreacion;

    // Getters y Setters
    public int getIdProducto() {
    	return idProducto;
    }
    public void setIdProducto(int idProducto) {
    	this.idProducto = idProducto; 
    }

    public String getNombre() { 
    	return nombre;
    }
    public void setNombre(String nombre) { 
    	this.nombre = nombre; 
    }

    public String getDescripcion() {
    	return descripcion; 
    }
    public void setDescripcion(String descripcion) { 
    	this.descripcion = descripcion; 
    }

    public BigDecimal getPrecio() {
    	return precio; 
    }
    public void setPrecio(BigDecimal precio) {
    	this.precio = precio; 
    }

    public int getStock() { 
    	return stock; 
    }
    public void setStock(int stock) {
    	this.stock = stock; 
    }

    public String getCategoria() {
    	return categoria; 
    }
    public void setCategoria(String categoria) {
    	this.categoria = categoria;
    }

    public LocalDateTime getFechaCreacion() { 
    	return fechaCreacion; 
    }
    public void setFechaCreacion(LocalDateTime fechaCreacion) {
    	this.fechaCreacion = fechaCreacion;
    }
    
    public Producto() {
        // vacío
    }
    // Constructor
    public Producto(int idProducto, String nombre, String descripcion,
            BigDecimal precio, int stock, String categoria, LocalDateTime fechaCreacion) {
                this.idProducto = idProducto;
               this.nombre = nombre;
            this.descripcion = descripcion;
            this.precio = precio;
           this.stock = stock;
           this.categoria = categoria;
          this.fechaCreacion = fechaCreacion;
        }

}