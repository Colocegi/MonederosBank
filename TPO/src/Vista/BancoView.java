package Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BancoView {
    private JPanel mainPanel;
    private JPanel homePanel;
    private JPanel debitoPanel;
    private JPanel depositoPanel;
    private JPanel transferenciaPanel;
    private JPanel pfPanel;
    private JPanel servicioPanel;
    private JButton DebitarButton;
    private JButton AcreditarButton;
    private JButton TransferenciaButton;
    private JButton PlazoFijoButton;
    private JButton ServicioButton;
    private JLabel tituloHomeLabel;
    private JLabel nombreClienteHomeLabel;
    private JLabel tipoCuentaHomeLabel;
    private JLabel numCuentaHomeLabel;
    private JLabel saldoActualHomeLabel;
    private JTextField montoAOperarDebitoTextField;
    private JLabel tituloDebitoLbl;
    private JButton confirmarDebitoButton;
    private JLabel resultadoDebitoLbl;
    private JButton volverDebitoBtn;
    private JLabel tituloTransfLbl;
    private JTextField montoTransfTxt;
    private JTextField destinoTransfTxt;
    private JButton confirmarTransfButton1;
    private JLabel resultTransfLbl;
    private JButton volverTransfBtn;
    private JTextField montoPfTxt;
    private JTextField diasPfTxt;
    private JButton confirmarPfBtn;
    private JButton volverPfBtn;
    private JTextField idServTxt;
    private JButton pagarServBtn;
    private JButton backServBtn;
    private JTextField montoADepositarTextField;
    private JButton confirmarDepositoBtn;
    private JLabel resultadoDepositoPnl;
    private JButton volverDepositoBtn;
    private JLabel bienvenida;
    private JLabel tituloDeposito;
    private JLabel tituloPf;
    private JLabel tituloServ;


    public BancoView() {
        volverDebitoBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
    }

    // Método para que el Controlador obtenga el monto que el usuario ingresó
    public String getMontoAcreditar() {
        return montoAOperarDebitoTextField.getText();
    }

    // Método para que el Controlador muestre el resultado de la operación
    public void mostrarResultadoAcreditacion(String mensaje, boolean esError) {
        resultadoDebitoLbl.setText(mensaje);
        // Para que sea visible, configuramos el color
        if (esError) {
            resultadoDebitoLbl.setForeground(Color.RED); // Asegúrate de importar java.awt.Color
        } else {
            resultadoDebitoLbl.setForeground(Color.BLUE);
        }
    }




}
