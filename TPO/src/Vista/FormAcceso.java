package Vista;

import Modelo.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileReader;

public class FormAcceso extends JFrame {

    private JTextField txtUsuario;
    private JPasswordField passClave;
    private JButton btnIngresar;
    private JButton btnSalir;

    private Banco banco;

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

        // Crear banco y cargar usuarios desde archivo
        banco = new Banco("MonederosBank", "MB001");
        banco.cargarClientesDesdeArchivo("C:\\Users\\leanc\\OneDrive\\Documentos\\GitHub\\MonederosBank\\TPO\\src\\datos\\usuario.txt");

        btnIngresar.addActionListener(this::loginAction);
        btnSalir.addActionListener(e -> System.exit(0));
    }

    private void loginAction(ActionEvent e) {
        String usuario = txtUsuario.getText().trim();
        String clave = new String(passClave.getPassword()).trim();

        if (validarCredenciales(usuario, clave)) {
            Cliente clienteActual = banco.buscarClientePorNombre(usuario);
            if (clienteActual != null) {
                dispose();
                BancoView vista = new BancoView();
                vista.setBanco(banco);
                vista.setClienteActual(clienteActual);

                JFrame frame = new JFrame("MonederosBank");
                frame.setContentPane(vista.getMainPanel());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Cliente no encontrado.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.");
        }
    }

    private boolean validarCredenciales(String nombre, String clave) {
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\leanc\\OneDrive\\Documentos\\GitHub\\MonederosBank\\TPO\\src\\datos\\usuario.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 2) {
                    String usuarioArchivo = partes[0].trim();
                    String claveArchivo = partes[1].trim();
                    if (usuarioArchivo.equalsIgnoreCase(nombre) && claveArchivo.equals(clave)) {
                        return true;
                    }
                }
            }
        } catch (Exception ex) {
            System.err.println("Error validando usuarios: " + ex.getMessage());
        }
        return false;
    }
}