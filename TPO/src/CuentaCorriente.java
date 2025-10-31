public class CuentaCorriente extends Cuenta {
    private int limiteGiroDescubierto;

    public CuentaCorriente(int numCuenta, String tipo, int idCliente, String nombre, String apellido, int limiteGiroDescubierto) {
        super(numCuenta, tipo, idCliente, nombre, apellido);
        this.limiteGiroDescubierto = limiteGiroDescubierto;
    }

    public int getLimiteGiroDescubierto() {
        return limiteGiroDescubierto;
    }

    public void setLimiteGiroDescubierto(int limiteGiroDescubierto) {
        this.limiteGiroDescubierto = limiteGiroDescubierto;
    }

    public void debitar(double monto) {
        if (this.saldo - monto >= -limiteGiroDescubierto) {
            this.saldo -= monto;
        } else {
            System.out.println("Débito excede el límite de giro descubierto.");
        }
    }

    public void cobrarMantenimiento(){
        double cuotaMantenimiento = 50.0; // Ejemplo de cuota fija
        this.debitar(cuotaMantenimiento);
    }
}
