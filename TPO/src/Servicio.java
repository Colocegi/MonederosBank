public class Servicio implements IOperacionBancaria {

    private String idServicio;
    private String fechaPago;

    public Servicio(String idServicio, String fechaPago) {
        this.idServicio = idServicio;
        this.fechaPago = fechaPago;
    }

    @Override
    public void ejecutar() {
        System.out.println("Ejecutando pago del servicio con ID: " + idServicio);
    }

    @Override
    public void cancelar() {
        System.out.println("Cancelando pago del servicio con ID: " + idServicio);
    }

    public String getIdServicio() {
        return idServicio;
    }

    public String getFechaPago() {
        return fechaPago;
    }
    public void setIdServicio(String idServicio) {
        this.idServicio = idServicio;
    }
    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    @Override
    public String toString() {
        return "Servicio:" + "idServicio='" + idServicio + ", fechaPago='" + fechaPago + '\'';
    }
}
