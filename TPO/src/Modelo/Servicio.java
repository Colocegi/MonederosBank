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

    // Getters y Setters...
    public String getIdServicio() { return idServicio; }
    public String getFechaPago() { return fechaPago; }
    public double getMonto() { return monto; }
    public Cuenta getCuentaPagadora() { return cuentaPagadora; }
    public void setIdServicio(String idServicio) { this.idServicio = idServicio; }
    public void setFechaPago(String fechaPago) { this.fechaPago = fechaPago; }
    public void setMonto(double monto) { this.monto = monto; }
    public void setCuentaPagadora(Cuenta cuentaPagadora) { this.cuentaPagadora = cuentaPagadora; }

    @Override
    public void ejecutar() {
        System.out.println("===== PAGANDO SERVICIO =====");
        System.out.println("Servicio ID: " + this.idServicio);
        System.out.println("Monto: $" + this.monto);
        System.out.println("Fecha Límite: " + this.fechaPago);

        if (this.cuentaPagadora != null) {
            System.out.println("Pagando desde Cuenta: " + this.cuentaPagadora.getNumCuenta());
            System.out.println("Saldo (antes): $" + this.cuentaPagadora.getSaldo());

            try {
                // LLAMADA ÚNICA: Intentamos debitar el monto
                this.cuentaPagadora.debitar(this.monto); 
                
                // Si llega aquí, el débito fue exitoso
                System.out.println("PAGO EXITOSO. Nuevo Saldo: $" + this.cuentaPagadora.getSaldo());

            } catch (FalloTransaccionException e) {
                // Si debitar() falla, capturamos el error
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