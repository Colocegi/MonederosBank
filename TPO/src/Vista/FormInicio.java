package Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Página inicial del sistema bancario.
 * Muestra el nombre del banco y permite ir al formulario de acceso.
 */
public class FormInicio extends JFrame {

    private JPanel mainPanel;
    private JLabel tituloLabel;
    private JLabel subtituloLabel;
    private JButton btnIniciarSesion;
    private JLabel logoLabel;

    public FormInicio() {
        setTitle("Banco Digital - Bienvenido");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Panel principal
        mainPanel = new JPanel();
        mainPanel.setBackground(new Color(240, 248, 255)); // azul claro suave
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.CENTER;

        // --- TÍTULO ---
        tituloLabel = new JLabel("MONEDEROS BANK");
        tituloLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        tituloLabel.setForeground(new Color(0, 51, 102));

        // --- SUBTÍTULO ---
        subtituloLabel = new JLabel("Tu dinero, siempre con vos");
        subtituloLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        subtituloLabel.setForeground(new Color(50, 50, 50));

        // --- BOTÓN ---
        btnIniciarSesion = new JButton("Iniciar sesión");
        btnIniciarSesion.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btnIniciarSesion.setBackground(new Color(0, 102, 204));
        btnIniciarSesion.setForeground(Color.BLACK);
        btnIniciarSesion.setFocusPainted(false);
        btnIniciarSesion.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnIniciarSesion.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Acción del botón
        btnIniciarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirLogin();
            }
        });

        // --- AGREGAR COMPONENTES ---
        gbc.gridy = 0;
        mainPanel.add(tituloLabel, gbc);

        gbc.gridy = 1;
        mainPanel.add(subtituloLabel, gbc);

        gbc.gridy = 2;
        mainPanel.add(btnIniciarSesion, gbc);

        setContentPane(mainPanel);
    }

    private void abrirLogin() {
        dispose(); // cierra esta ventana
        SwingUtilities.invokeLater(() -> {
            FormAcceso login = new FormAcceso();
            login.setVisible(true);
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

}