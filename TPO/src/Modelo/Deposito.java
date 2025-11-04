package Modelo;

public class Deposito extends Transaccion {

    public Depositar(String idTransaccion, double montoDeposito, String fecha) {
        super(idTransaccion, montoDeposito, fecha);
    }

    @Override
    protected void operar() {
        // Método abstracto implementado
        // La lógica principal está en ejecutar()
    }

    @Override
    public void ejecutar(Cuenta cuenta) {
        if (this.monto <= 0) {
            System.out.println("Error: El monto a depositar debe ser mayor a 0");
            return;
        }

        cuenta.acreditar(this.monto);
        cuenta.listaTransacciones.add(this);

        System.out.println("Depósito exitoso de $" + this.monto);
        System.out.println("Nuevo saldo: $" + cuenta.getSaldo());
    }

    @Override
    public String toString() {
        return "Depositar: " + super.toString();
    }
}