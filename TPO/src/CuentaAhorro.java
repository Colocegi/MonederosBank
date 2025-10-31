public class CuentaAhorro extends Cuenta{
    private double tasaIntereses;

    public double getTasaIntereses() {
        return tasaIntereses;
    }

    public CuentaAhorro(int numCuenta, Cliente cliente, double tasaIntereses) {
        super(numCuenta, "CuentaAhorro", cliente);
        this.tasaIntereses = tasaIntereses;
    }

    public void setTasaIntereses(double tasaIntereses) {
        this.tasaIntereses = tasaIntereses;
    }

    public void calcularIntereses(){
        double intereses = this.saldo * this.tasaIntereses / 100;
        this.acreditar(intereses);
        System.out.println("Intereses acreditados: $" + intereses);
    }

    protected void debitar(double monto) {
        if(this.saldo >= monto){
            this.saldo -= monto;
        } else {
            System.out.println("Saldo insuficiente para debitar $" + monto);
        }
    }
}
