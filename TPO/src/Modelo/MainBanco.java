package Modelo;

import Vista.FormAcceso;
import javax.swing.*;
import java.text.Normalizer;

public class MainBanco {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            FormAcceso inicio = new FormAcceso();
            inicio.setVisible(true);
        });
    }
}
