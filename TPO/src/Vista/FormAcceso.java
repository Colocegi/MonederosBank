package Vista;

import Controlador.ControladorUsuario;
import Modelo.*;
import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Ventana de inicio de sesión del sistema bancario.
 * Valida las credenciales del usuario y, si son correctas,
 * abre la interfaz principal del banco (BancoView).
 */
public class FormAcceso extends JFrame {

    private JTextField txtUsuario;
    private JPasswordField passClave;
    private JButton btnIngresar;

    private ControladorUsuario controlador;

    public FormAcceso() {
        this.controlador = new ControladorUsuario();

        // Configuración general de la ventana
        setTitle("Acceso al Sistema Bancario");
        setSize(350, 160);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        setLayout(new GridLayout(3, 2, 10, 10));

        // --- Componentes ---
        add(new JLabel("  Usuario:"));
        txtUsuario = new JTextField();
        add(txtUsuario);

        add(new JLabel("  Clave:"));
        passClave = new JPasswordField();
        add(passClave);

        btnIngresar = new JButton("Ingresar");
        add(new JLabel()); // espacio vacío
        add(btnIngresar);

        // --- Acción del botón ---
        btnIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String nombre = txtUsuario.getText().trim();
                String clave = new String(passClave.getPassword()).trim();

                String resultado = controlador.validarIngreso(nombre, clave);

                if (resultado.equals("ACCESO_CONCEDIDO")) {
                    dispose(); // cerrar login

                    SwingUtilities.invokeLater(() -> {
                        try {
                            // --- Crear la vista principal ---
                            BancoView bancoView = new BancoView();

                            // --- Crear cliente logueado ---
                            Cliente clienteDemo = new Cliente(1, nombre, "");

                            // --- Crear cuenta corriente y asociarla al cliente ---
                            CuentaCorriente cuentaDemo = new CuentaCorriente(1001, clienteDemo, 20000);
                            // 1001 = número de cuenta, 20000 = límite de giro descubierto
                            cuentaDemo.acreditar(50000); // saldo inicial

                            clienteDemo.agregarCuenta(cuentaDemo);

                            // --- Cargar cliente en la vista ---
                            bancoView.setClienteActual(clienteDemo);

                            // --- Mostrar ventana principal ---
                            JFrame ventanaPrincipal = new JFrame("Banco Digital - Panel Principal");
                            ventanaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            ventanaPrincipal.setContentPane(bancoView.getMainPanel());
                            ventanaPrincipal.pack();
                            ventanaPrincipal.setSize(800, 600);
                            ventanaPrincipal.setLocationRelativeTo(null);
                            ventanaPrincipal.setVisible(true);

                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null,
                                    "Error al cargar la vista principal: " + ex.getMessage(),
                                    "Error", JOptionPane.ERROR_MESSAGE);
                            ex.printStackTrace();
                        }
                    });

                } else {
                    JOptionPane.showMessageDialog(null, resultado);
                }
            }
        });
    }
}
