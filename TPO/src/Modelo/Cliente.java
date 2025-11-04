package Modelo;

import java.util.ArrayList;
import java.util.List;

public class Cliente implements ICliente {
    private int idCliente;
    private String nombre;
    private String apellido;
    private List<Cuenta> cuentas; 

    public Cliente(int idCliente, String nombre, String apellido) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cuentas = new ArrayList<>();
    }

    public void agregarCuenta(Cuenta cuenta) {
        if (cuenta.getCliente() == this) {
            this.cuentas.add(cuenta);
        } else {
            System.err.println("Error: La cuenta no pertenece a este cliente.");
        }
    }

    @Override
    public double obtenerSaldoTotal() {
        double total = 0.0;
        for (Cuenta c : cuentas) {
            total += c.getSaldo();
        }
        return total;
    }

    // Getters para el GestorReportes
    public int getIdCliente() { return idCliente; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public List<Cuenta> getCuentas() { return cuentas; }
}