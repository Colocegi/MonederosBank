package Modelo;

public abstract class Transaccion implements IOperacionBancaria {

    protected String idTransaccion;
    protected double monto;
    protected String fecha;

    public Transaccion(String idTransaccion, double monto, String fecha) {
        this.idTransaccion = idTransaccion;
        this.monto = monto;
        this.fecha = fecha;
    }

    protected abstract void operar();

    public String getIdTransaccion() {
        return idTransaccion;
    }

    public double getMonto() {
        return monto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setIdTransaccion(String idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }
    
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }


}

