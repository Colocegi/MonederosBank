package Vista;

import Modelo.*;

import javax.swing.*;

/**
 * Vista principal del banco. Permite operar sobre la cuenta del cliente actual.
 * Compatible con CuentaCorriente y CuentaAhorro.
 */
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
    private JButton volverDebitoBtn;
    private JLabel tituloTransfLbl;
    private JTextField montoTransfTxt;
    private JTextField destinoTransfTxt;
    private JButton confirmarTransfButton1;
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
    private JButton volverDepositoBtn;
    private JLabel bienvenida;
    private JLabel tituloDeposito;
    private JLabel tituloPf;
    private JLabel tituloServ;
    private JLabel montoAOperarDebitoLbl;
    private JLabel montoADepositarLbl;
    private JLabel montoTransfLbl;
    private JLabel CuentaDestinoTransfLbl;
    private JLabel montoInvertirPfLbl;
    private JLabel PlazoDiasPfLbl;
    private JLabel idServicioLbl;

    // --- Modelo asociado ---
    private Banco banco;
    private Cliente clienteActual;
    private Cuenta cuentaActual;

    public BancoView() {
        // Mostrar panel principal por defecto
        mostrarPanel(homePanel);

        // === Navegación principal ===
        DebitarButton.addActionListener(e -> mostrarPanel(debitoPanel));
        AcreditarButton.addActionListener(e -> mostrarPanel(depositoPanel));
        TransferenciaButton.addActionListener(e -> mostrarPanel(transferenciaPanel));
        PlazoFijoButton.addActionListener(e -> mostrarPanel(pfPanel));
        ServicioButton.addActionListener(e -> mostrarPanel(servicioPanel));

        // === Botones Volver ===
        volverDebitoBtn.addActionListener(e -> mostrarPanel(homePanel));
        volverDepositoBtn.addActionListener(e -> mostrarPanel(homePanel));
        volverTransfBtn.addActionListener(e -> mostrarPanel(homePanel));
        volverPfBtn.addActionListener(e -> mostrarPanel(homePanel));
        backServBtn.addActionListener(e -> mostrarPanel(homePanel));

        // === DEBITAR ===
        confirmarDebitoButton.addActionListener(e -> {
            if (cuentaActual == null) return;
            try {
                double monto = Double.parseDouble(montoAOperarDebitoTextField.getText());
                // Llamar al método concreto (CuentaCorriente o CuentaAhorro)
                if (cuentaActual instanceof CuentaCorriente cc) {
                    cc.debitar(monto);
                } else if (cuentaActual instanceof CuentaAhorro ca) {
                    ca.debitar(monto);
                } else {
                    throw new RuntimeException("Tipo de cuenta no soportado para débito.");
                }
                JOptionPane.showMessageDialog(null, "Débito realizado correctamente.");
                actualizarDatosCuenta();
                montoAOperarDebitoTextField.setText("");
            } catch (FalloTransaccionException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al debitar: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // === DEPOSITAR ===
        confirmarDepositoBtn.addActionListener(e -> {
            if (cuentaActual == null) return;
            try {
                double monto = Double.parseDouble(montoADepositarTextField.getText());
                Deposito dep = new Deposito("DEP-" + System.currentTimeMillis(), monto, "2025-11-04", cuentaActual);
                dep.ejecutar();
                JOptionPane.showMessageDialog(null, "Depósito realizado correctamente.");
                actualizarDatosCuenta();
                montoADepositarTextField.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error en el depósito: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // === TRANSFERENCIA ===
        confirmarTransfButton1.addActionListener(e -> {
            if (cuentaActual == null) return;
            try {
                double monto = Double.parseDouble(montoTransfTxt.getText());
                String idDestinatario = destinoTransfTxt.getText();
                Transferencia t = new Transferencia("T-" + System.currentTimeMillis(), monto, "2025-11-04",
                        cuentaActual, idDestinatario);
                t.ejecutar();
                JOptionPane.showMessageDialog(null, "Transferencia procesada (ver consola para detalles).");
                actualizarDatosCuenta();
                montoTransfTxt.setText("");
                destinoTransfTxt.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error en la transferencia: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // === PLAZO FIJO ===
        confirmarPfBtn.addActionListener(e -> {
            try {
                int id = (int) (Math.random() * 10000);
                double monto = Double.parseDouble(montoPfTxt.getText());
                int dias = Integer.parseInt(diasPfTxt.getText());
                PlazoFijo pf = new PlazoFijo(id, monto, dias);
                pf.invertir();
                JOptionPane.showMessageDialog(null, "Plazo fijo creado correctamente (ver consola).");
                montoPfTxt.setText("");
                diasPfTxt.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error en el plazo fijo: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // === SERVICIO ===
        pagarServBtn.addActionListener(e -> {
            if (cuentaActual == null) return;
            try {
                String idServ = idServTxt.getText();
                double monto = Double.parseDouble(JOptionPane.showInputDialog("Ingrese monto del servicio:"));
                Servicio serv = new Servicio(idServ, "2025-11-10", monto, cuentaActual);
                serv.ejecutar();
                JOptionPane.showMessageDialog(null, "Pago de servicio ejecutado (ver consola).");
                actualizarDatosCuenta();
                idServTxt.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al pagar servicio: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    // --------- Métodos públicos para vincular modelo ---------
    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public void setClienteActual(Cliente cliente) {
        this.clienteActual = cliente;
        if (cliente != null && !cliente.getListaCuentas().isEmpty()) {
            this.cuentaActual = cliente.getListaCuentas().get(0);
        }
        actualizarDatosCuenta();
    }

    // --------- Métodos de utilidad ---------
    private void mostrarPanel(JPanel panel) {
        homePanel.setVisible(false);
        debitoPanel.setVisible(false);
        depositoPanel.setVisible(false);
        transferenciaPanel.setVisible(false);
        pfPanel.setVisible(false);
        servicioPanel.setVisible(false);
        panel.setVisible(true);
    }

    private void actualizarDatosCuenta() {
        if (clienteActual != null && cuentaActual != null) {
            nombreClienteHomeLabel.setText("Cliente: " + clienteActual.getNombre() + " " + clienteActual.getApellido());
            tipoCuentaHomeLabel.setText("Tipo: " + cuentaActual.getTipo());
            numCuentaHomeLabel.setText("N° Cuenta: " + cuentaActual.getNumCuenta());
            saldoActualHomeLabel.setText(String.format("Saldo: $%.2f", cuentaActual.getSaldo()));
        } else {
            nombreClienteHomeLabel.setText("Cliente: -");
            tipoCuentaHomeLabel.setText("Tipo: -");
            numCuentaHomeLabel.setText("N° Cuenta: -");
            saldoActualHomeLabel.setText("Saldo: -");
        }
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
