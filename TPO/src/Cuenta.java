public abstract class Cuenta {
    protected int numCuenta;
    protected double saldo;
    protected Cliente cliente;
    private Cuenta tipo;

    public Cuenta(int numCuenta, Cliente cliente) {
        this.numCuenta = numCuenta;
        this.cliente = new cliente c1 = new Cliente();
        this.saldo = 0.0;
    }

    public int getNumCuenta() {
        return numCuenta;
    }

    public void setNumCuenta(int numCuenta) {
        this.numCuenta = numCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Cuenta getTipo() {
        return tipo;
    }

    public void setTipo(Cuenta tipo) {
        this.tipo = tipo;
    }

    
}
