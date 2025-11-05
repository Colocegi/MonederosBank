package Modelo; // (O donde esté tu MainBanco)

import Vista.FormInicio; // <-- CAMBIADO
import javax.swing.*;

public class MainBanco {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            // AHORA INICIA EN LA BIENVENIDA
            FormInicio inicio = new FormInicio(); // <-- CAMBIADO
            inicio.setVisible(true);
        });
    }
}