package Modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa a un cliente del banco.
 * Contiene sus datos personales y una lista de sus cuentas.
 */
public class Cliente implements ICliente {

    // --- Atributos ---
    private int idCliente;
    private String nombre;
    private String apellido;

    // Un cliente "tiene" una lista de cuentas
    private List<Cuenta> listaCuentas;

    // --- Constructor ---
    // Este es el constructor que usa en MainTest
    public Cliente(int idCliente, String nombre, String apellido) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellido = apellido;

        // Importante: Inicializamos la lista para poder agregar cuentas
        this.listaCuentas = new ArrayList<>();
    }

    // --- Métodos ---

    /**
     * Añade una cuenta (Corriente o Ahorro) a la lista de cuentas del cliente.
     * Este método lo usas en MainTest.
     */
    public void agregarCuenta(Cuenta cuenta) {
        this.listaCuentas.add(cuenta);
    }


    //  métodos que usa GestorReportes

    public int getIdCliente() {
        return idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    /**
     * Devuelve la lista de cuentas que le pertenecen a este cliente.
     */
    public List<Cuenta> getListaCuentas() {
        return listaCuentas;
    }

    public Cuenta[] getCuentas() {
        return new Cuenta[0];
    }

    @Override
    public double obtenerSaldoTotal() {
        return 0;
    }
}