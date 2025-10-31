public class CuentaCorriente extends Cuenta {
    // Usamos double para valores monetarios y private según el UML
    private double limiteGiroDescubierto;

    // Constructor CORREGIDO
    public CuentaCorriente(int numCuenta, Cliente cliente, double limiteGiroDescubierto) {
        // Llamada a super con el cliente ya creado y tipo fijo
        super(numCuenta, "CuentaCorriente", cliente);
        this.limiteGiroDescubierto = limiteGiroDescubierto;
    }

    // Método debitar: Implementación consistente con el método abstracto 'protected'
    @Override
    protected void debitar(double monto) {
        if (this.saldo - monto >= -this.limiteGiroDescubierto) {
            this.saldo -= monto;
        } else {
            System.out.println("Débito excede el límite de giro descubierto de $" + this.limiteGiroDescubierto);
        }
    }

    // Método cobrarMantenimiento (public, según UML)
    public void cobrarMantenimiento() {
        double cuotaMantenimiento = 50.0; // Ejemplo
        this.debitar(cuotaMantenimiento);
    }

    // Getters y Setters
    public double getLimiteGiroDescubierto() {
        return limiteGiroDescubierto;
    }

    public void setLimiteGiroDescubierto(double limiteGiroDescubierto) {
        this.limiteGiroDescubierto = limiteGiroDescubierto;
    }
}