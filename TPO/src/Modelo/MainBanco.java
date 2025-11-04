package Modelo;

import Vista.FormInicio;
import javax.swing.*;

public class MainBanco {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            FormInicio inicio = new FormInicio();
            inicio.setVisible(true);
        });
    }
}
