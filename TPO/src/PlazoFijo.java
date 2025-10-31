public class PlazoFijo {
    // Atributos protected
    protected int idPlazo;
    protected double monto;
    protected int plazoDias;

    // Constructor
    public PlazoFijo() {
    }

    // Constructor con parámetros
    public PlazoFijo(int idPlazo, double monto, int plazoDias) {
        this.idPlazo = idPlazo;
        this.monto = monto;
        this.plazoDias = plazoDias;
    }

    // Metodo invertir
    public void invertir() {
        System.out.println("Inversión realizada:");
        System.out.println("ID Plazo: " + idPlazo);
        System.out.println("Monto: $" + monto);
        System.out.println("Plazo: " + plazoDias + " días");
    }

    // Getters y Setters
    public int getIdPlazo() {
        return idPlazo;
    }

    public void setIdPlazo(int idPlazo) {
        this.idPlazo = idPlazo;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public int getPlazoDias() {
        return plazoDias;
    }

    public void setPlazoDias(int plazoDias) {
        this.plazoDias = plazoDias;
    }
}