public class Transferencia extends Transaccion {
   
    protected String idDestinatario;
    private Cuenta cuentaOrigen;

    public Transferencia(String idTransaccion, double monto, String fecha, Cuenta cuentaOrigen, String idDestinatario) {
        super(idTransaccion, monto, fecha);
        this.cuentaOrigen = cuentaOrigen; 
        this.idDestinatario = idDestinatario;
    }

    // Getters y Setters...
    public String getIdDestinatario() { return idDestinatario; }
    public void setIdDestinatario(String idDestinatario) { this.idDestinatario = idDestinatario; }
    public Cuenta getCuentaOrigen() { return cuentaOrigen; }
    public void setCuentaOrigen(Cuenta cuentaOrigen) { this.cuentaOrigen = cuentaOrigen; }
    
    // --- Implementación de métodos abstractos ---
    @Override
    protected void operar() {
        System.out.println("===== PROCESANDO TRANSFERENCIA =====");
        System.out.println("ID Transacción: " + this.idTransaccion);
        System.out.println("Monto: $" + this.monto);
        System.out.println("Destinatario: " + this.idDestinatario);
        System.out.println("Fecha: " + this.fecha);

        if (this.cuentaOrigen != null) {
            
            double saldoAnterior = this.cuentaOrigen.getSaldo();
            System.out.println("Saldo Origen (antes): $" + saldoAnterior);

            try {
                // LLAMADA ÚNICA: Intentamos debitar el monto
                this.cuentaOrigen.debitar(this.monto); 
                
                // Si llegamos aquí, el débito fue exitoso
                System.out.println("DEBITO EXITOSO: Se debitaron $" + this.monto + " de la cuenta " + this.cuentaOrigen.getNumCuenta());
                System.out.println("Nuevo Saldo: $" + this.cuentaOrigen.getSaldo());
                System.out.println("Muevo fondos a Destinatario: " + this.idDestinatario);
                
            } catch (FalloTransaccionException e) {
                // CAPTURA EL ERROR y no permite que se debite dos veces
                System.out.println("TRANSFERENCIA FALLIDA: " + e.getMessage());
            }

        } else {
            System.out.println("ERROR: No se especificó una cuenta de origen para la transferencia.");
        }
        System.out.println("====================================");
    }

    @Override
    public void ejecutar() {
        this.operar();
    }

    @Override
    public void cancelar() {
        System.out.println("Cancelando transferencia " + this.idTransaccion);
    }
    
}