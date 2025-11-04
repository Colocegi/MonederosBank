package Modelo;

public class CuentaAhorro extends Cuenta{
    private double tasaIntereses;

    public double getTasaIntereses() {
        return tasaIntereses;
    }

    public CuentaAhorro(int numCuenta, ICliente cliente, double tasaIntereses) {
        super(numCuenta, "Modelo.CuentaAhorro", cliente);
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

    @Override
    protected void debitar(double monto) {
        if(this.saldo >= monto){
            this.saldo -= monto;
            // Aquí iría el registro de la Modelo.Transaccion si fuera necesario
        } else {
            // Reemplazo del print por el lanzamiento de la excepción
            throw new FalloTransaccionException("Saldo insuficiente para debitar $" + monto + " de la Modelo.Cuenta de Ahorro.");
        }
    }
    }

