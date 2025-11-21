package Modelo;

public class Deposito extends Transaccion {

    private Cuenta cuentaDestino; // Atributo necesario

    // CONSTRUCTOR CORREGIDO: Se llama Deposito y recibe la cuenta
    public Deposito(String idTransaccion, double monto, String fecha, Cuenta cuentaDestino) {
        super(idTransaccion, monto, fecha);
        this.cuentaDestino = cuentaDestino;
    }

    @Override
    protected void operar() {
        System.out.println("===== PROCESANDO DEPÓSITO =====");
        System.out.println("ID Transacción: " + this.idTransaccion);
        System.out.println("Monto: $" + this.monto);

        if (this.cuentaDestino == null) {
            System.out.println("ERROR: No se especificó una cuenta de destino.");
            return;
        }

        System.out.println("Depositando en Cuenta: #" + this.cuentaDestino.getNumCuenta());
        System.out.println("Saldo (antes): $" + this.cuentaDestino.getSaldo());

        if (this.monto <= 0) {
            System.out.println("DEPÓSITO FALLIDO: El monto a depositar debe ser mayor a $0.");
            return;
        }

        // Usamos el método acreditar() de la cuenta
        this.cuentaDestino.acreditar(this.monto);

        System.out.println("DEPÓSITO EXITOSO.");
        System.out.println("Nuevo Saldo: $" + this.cuentaDestino.getSaldo());
        System.out.println("=================================");
    }

    // EJECUTAR CORREGIDO: Sin parámetros, llama a operar()
    @Override
    public void ejecutar() {
        this.operar();
    }

    @Override
    public void cancelar() {
        System.out.println("Cancelando depósito " + this.idTransaccion);
    }
}