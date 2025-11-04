package Modelo;

/**
 * Representa al usuario que intenta iniciar sesión.
 * Es un modelo de datos simple (POJO).
 */
public class Usuario {

    private String nombre;
    private String clave;

    public Usuario(String nombre, String clave) {
        this.nombre = nombre;
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public String getClave() {
        return clave;
    }
}