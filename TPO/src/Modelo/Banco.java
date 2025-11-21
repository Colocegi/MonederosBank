package Modelo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.InputStream;
import java.io.InputStreamReader;


public class Banco {
    private String nombre;
    private String codigo;
    private List<Cliente> listaClientes;

    public Banco(String nombre, String codigo) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.listaClientes = new ArrayList<>();
    }

    public void agregarCliente(Cliente c) {
        listaClientes.add(c);
    }

    public List<Cliente> getListaClientes() {
        return listaClientes;
    }

    /**
     * Carga los clientes desde un archivo de texto.
     * Cada línea del archivo debe tener formato: nombre,clave
     * Alterna entre cuentas corrientes y de ahorro.
     */
    public void cargarClientesDesdeArchivo(String rutaArchivo) {
        // Usamos el ClassLoader para una ruta relativa
        InputStream inputStream = Banco.class.getClassLoader().getResourceAsStream(rutaArchivo);

        if (inputStream == null) {
            System.err.println("Error: No se pudo encontrar el archivo de recurso: " + rutaArchivo);
            return;
        }

        // Usamos InputStreamReader en lugar de FileReader
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String linea;
            int id = 1;
            boolean cuentaCorriente = true;

            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 2) {
                    String nombre = partes[0].trim();

                    String claveComoApellido = partes[1].trim();

                    Cliente cliente = new Cliente(id, nombre, claveComoApellido); // Usamos la clave como apellido de demo

                    if (cuentaCorriente) {
                        CuentaCorriente cuenta = new CuentaCorriente(1000 + id, cliente, 10000);
                        cuenta.acreditar(40000);
                        cliente.agregarCuenta(cuenta);
                    } else {
                        CuentaAhorro cuenta = new CuentaAhorro(2000 + id, cliente, 3.5);
                        cuenta.acreditar(25000);
                        cliente.agregarCuenta(cuenta);
                    }

                    agregarCliente(cliente);
                    id++;
                    cuentaCorriente = !cuentaCorriente;
                }
            }
            System.out.println("Clientes cargados correctamente: " + listaClientes.size());
        } catch (IOException e) {
            System.err.println("Error al leer archivo:" + e.getMessage());
        }
    }

    public Cliente buscarClientePorNombre(String nombre) {
        for (Cliente c : listaClientes) {
            if (c.getNombre().equalsIgnoreCase(nombre)) {
                return c;
            }
        }
        return null;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCodigo() {
        return codigo;
    }
    public List<Cuenta> getListaCuentas() {
        List<Cuenta> todas = new ArrayList<>();
        for (Cliente c : listaClientes) {
            todas.addAll(c.getListaCuentas());
        }
        return todas;
    }

}