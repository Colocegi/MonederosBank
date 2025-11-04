package Modelo;

public class Cliente implements ICliente{
    private int idCliente;
    private String nombre;
    private String apellido;
    private Cuenta cuenta; // referencia a su cuenta

    public Cliente(int idCliente, String nombre, String apellido, Cuenta cuenta) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cuenta = cuenta;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    // Este setter podría omitirse si querés que sea inmutable
    protected void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public double obtenerSaldoTotal() {
        return cuenta != null ? cuenta.getSaldo() : 0.0;
    }
}
