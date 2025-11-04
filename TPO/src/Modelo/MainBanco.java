package Modelo;

import Modelo.*;
import Vista.BancoView;
import javax.swing.*;

public class MainBanco {
    public static void main(String[] args) {
        // --- Crear el banco y cliente ---
        Banco banco = new Banco("Banco JAVA", "BJX001");
        Cliente cliente = new Cliente(1, "Ana", "García");

        // --- Crear una cuenta corriente con límite de giro ---
        CuentaCorriente cuenta = new CuentaCorriente(1001, cliente, 500.0);

        // 💰 Asignar saldo inicial
        cuenta.acreditar(2000.0); // Ahora el cliente empieza con $2000

        // --- Asociar al cliente y al banco ---
        cliente.agregarCuenta(cuenta);
        banco.agregarCliente(cliente);

        // --- Crear la vista ---
        BancoView vista = new BancoView();
        vista.setBanco(banco);
        vista.setClienteActual(cliente);

        // --- Mostrar la interfaz ---
        JFrame frame = new JFrame("MonederosBank");
        frame.setContentPane(vista.getMainPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
