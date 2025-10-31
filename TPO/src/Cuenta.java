import java.util.ArrayList;
import java.util.List;

public abstract class Cuenta {
    protected int numCuenta;
    protected double saldo;
    protected String tipo;
    protected List<Transaccion> listaTransacciones;
    protected Cliente cliente;

    public Cuenta(int numCuenta, String tipo, int idCliente, String nombre, String apellido) {
        this.numCuenta = numCuenta;
        this.tipo = tipo;
        this.saldo = 0.0;
        this.listaTransacciones = new ArrayList<>();
        // Creación del cliente dentro de la cuenta
        this.cliente = new Cliente(idCliente, nombre, apellido, this);
    }

    public int getNumCuenta() {
        return numCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getTipo() {
        return tipo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    protected void acreditar(double monto) {
        this.saldo += monto;
    }

    protected abstract void debitar(double monto);
}
