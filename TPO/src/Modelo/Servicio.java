package Modelo;

public class Servicio implements IOperacionBancaria {

    private String idServicio;
    private String fechaPago; 
    private double monto;
    private Cuenta cuentaPagadora; 

    public Servicio(String idServicio, String fechaPago, double monto, Cuenta cuentaPagadora) {
        this.idServicio = idServicio;
        this.fechaPago = fechaPago;
        this.monto = monto;
        this.cuentaPagadora = cuentaPagadora;
    }

    public String getIdServicio() {
        return idServicio;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public double getMonto() {
        return monto;
    }

    public Cuenta getCuentaPagadora() {
        return cuentaPagadora;
    }

    public void setIdServicio(String idServicio) {
        this.idServicio = idServicio;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public void setCuentaPagadora(Cuenta cuentaPagadora) {
        this.cuentaPagadora = cuentaPagadora;
    }

    @Override
    public void ejecutar() {
        System.out.println("===== PAGANDO SERVICIO =====");
        System.out.println("Modelo.Servicio ID: " + this.idServicio);
        System.out.println("Monto: $" + this.monto);
        System.out.println("Fecha Límite: " + this.fechaPago);

        if (this.cuentaPagadora != null) {
            double saldoAnterior = this.cuentaPagadora.getSaldo();
            System.out.println("Pagando desde Modelo.Cuenta: " + this.cuentaPagadora.getNumCuenta());
            System.out.println("Saldo (antes): $" + saldoAnterior);

            // Llamamos a 'debitar' de la cuenta (p.ej. Modelo.CuentaCorriente)
            this.cuentaPagadora.debitar(this.monto);

            // Verificamos si el débito fue exitoso
            if (this.cuentaPagadora.getSaldo() < saldoAnterior) {
                System.out.println("PAGO EXITOSO: Se debitaron $" + this.monto);
                System.out.println("Nuevo Saldo: $" + this.cuentaPagadora.getSaldo());
            } else {
                System.out.println("PAGO FALLIDO: Saldo insuficiente o excede el límite de giro.");
            }

            try {
            this.cuentaPagadora.debitar(this.monto); 
            System.out.println("PAGO EXITOSO. Nuevo Saldo: $" + this.cuentaPagadora.getSaldo());

            } catch (FalloTransaccionException e) {
                System.out.println("PAGO FALLIDO: " + e.getMessage());
            }

        } else {
            System.out.println("ERROR: No se especificó una cuenta para pagar el servicio.");
        }
        System.out.println("=============================");
    }

    @Override
    public void cancelar() {
        System.out.println("Cancelando pago del servicio con ID: " + idServicio);
    }

   
}