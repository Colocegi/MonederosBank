package Modelo;

public class CuentaCorriente extends Cuenta {
    // Usamos double para valores monetarios y private según el UML
    private double limiteGiroDescubierto;

    // Constructor CORREGIDO
    public CuentaCorriente(int numCuenta, ICliente cliente, double limiteGiroDescubierto) {
        // Llamada a super con el cliente ya creado y tipo fijo
        super(numCuenta, "Modelo.CuentaCorriente", cliente);
        this.limiteGiroDescubierto = limiteGiroDescubierto;
    }

    // Método debitar: Implementación consistente con el método abstracto 'protected'
    @Override
    public void debitar(double monto) {
        if (this.saldo - monto >= -this.limiteGiroDescubierto) {
            this.saldo -= monto;
            // Aquí iría el registro de la Modelo.Transaccion si fuera necesario
        } else {
            // Reemplazo del print por el lanzamiento de la excepción
            throw new FalloTransaccionException(
                    "Débito excede el límite de giro descubierto de $" + this.limiteGiroDescubierto + ". Máximo permitido: $" + (this.saldo + this.limiteGiroDescubierto));
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