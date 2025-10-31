import java.util.ArrayList;
import java.util.List;

public abstract class Cuenta {
    // Atributos del UML (protected)
    protected int numCuenta;
    protected double saldo;
    protected String tipo;
    protected List<Transaccion> listaTransacciones;
    protected Cliente cliente; // Referencia al cliente

    // Constructor CORREGIDO: Recibe la instancia de Cliente.
    public Cuenta(int numCuenta, String tipo, Cliente cliente) {
        this.numCuenta = numCuenta;
        this.tipo = tipo;
        this.saldo = 0.0;
        this.listaTransacciones = new ArrayList<>();
        this.cliente = cliente;
    }

    // Método acreditar CORREGIDO: De protected a public (según UML)
    public void acreditar(double monto) {
        this.saldo += monto;
    }

    // Método abstracto debitar (lo dejamos protected para que las subclases lo implementen)
    protected abstract void debitar(double monto);

    // Getters
    public int getNumCuenta() { return numCuenta; }
    public double getSaldo() { return saldo; }
    public String getTipo() { return tipo; }
    public Cliente getCliente() { return cliente; }
}