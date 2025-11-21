package Vista;

// Importa el controlador
import Controlador.ControladorUsuario;
import Modelo.Banco;
import Modelo.Cliente;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

// BORRAMOS: java.io.BufferedReader
// BORRAMOS: java.io.FileReader

public class FormAcceso extends JFrame {

    private JTextField txtUsuario;
    private JPasswordField passClave;
    private JButton btnIngresar;
    private JButton btnSalir;

    // BORRAMOS: private Banco banco;
    private ControladorUsuario controlador; // <-- AÑADIDO: La Vista conoce al Controlador

    public FormAcceso() {
        setTitle("Acceso - MonederosBank");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panelAcceso = new JPanel(new GridLayout(4, 2, 10, 10));
        panelAcceso.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblUsuario = new JLabel("Usuario:");
        txtUsuario = new JTextField();

        JLabel lblClave = new JLabel("Contraseña:");
        passClave = new JPasswordField();

        btnIngresar = new JButton("Ingresar");
        btnSalir = new JButton("Salir");

        panelAcceso.add(lblUsuario);
        panelAcceso.add(txtUsuario);
        panelAcceso.add(lblClave);
        panelAcceso.add(passClave);
        panelAcceso.add(new JLabel());
        panelAcceso.add(new JLabel());
        panelAcceso.add(btnIngresar);
        panelAcceso.add(btnSalir);

        setContentPane(panelAcceso);

        // BORRAMOS: Las dos líneas de new Banco() y banco.cargarClientes...

        // AÑADIDO: La Vista crea su controlador
        this.controlador = new ControladorUsuario();

        btnIngresar.addActionListener(this::loginAction);
        btnSalir.addActionListener(e -> System.exit(0));
    }

    private void loginAction(ActionEvent e) {
        String usuario = txtUsuario.getText().trim();
        String clave = new String(passClave.getPassword()).trim();

        // CAMBIADO: Ya no llama a validarCredenciales local
        // Llama al controlador para que haga el trabajo
        Cliente clienteActual = controlador.validarIngreso(usuario, clave);

        if (clienteActual != null) { // Si el controlador devuelve un cliente Cierra la ventana de Login
            dispose();
            BancoView vista = new BancoView();
            vista.setBanco(controlador.getBanco()); // Pide el banco al controlador
            vista.setClienteActual(clienteActual);  // Pasa el cliente validado

            JFrame frame = new JFrame("MonederosBank");
            frame.setContentPane(vista.getMainPanel());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 500);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

        } else {
            // El controlador devolvió null (falló el login)
            JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.");
        }
    }

    // BORRAMOS: Todo el método private boolean validarCredenciales(...)
    // Ya no es responsabilidad de esta clase.
}