public class GestorReportes implements IReportable {
    private Banco banco;

    // Constructor
    public GestorReportes(Banco banco) {
        this.banco = banco;
    }

    // Getter
    public Banco getBanco() {
        return banco;
    }

    // Método público listaClientes
    public void listaClientes() {
        System.out.println("=== LISTA DE CLIENTES ===");
        System.out.println("Banco: " + banco.getNombre());
        System.out.println("-------------------------");

        for (Cliente cliente : banco.getListaClientes()) {
            System.out.println("ID: " + cliente.getIdCliente());
            System.out.println("Nombre: " + cliente.getNombre() + " " + cliente.getApellido());
            System.out.println("-------------------------");
        }
    }

    // Implementación de IReportable
    @Override
    public String generarReporte() {
        String reporte = "";

        reporte += "========== REPORTE DEL BANCO ==========\n";
        reporte += "Banco: " + banco.getNombre() + "\n";
        reporte += "Código: " + banco.getCodigo() + "\n";
        reporte += "=======================================\n\n";

        reporte += "CLIENTES REGISTRADOS: " + banco.getListaClientes().size() + "\n";
        for (Cliente cliente : banco.getListaClientes()) {
            reporte += "- " + cliente.getNombre() + " " + cliente.getApellido() + "\n";
        }

        reporte += "\nCUENTAS REGISTRADAS: " + banco.getListaCuentas().size() + "\n";
        for (Cuenta cuenta : banco.getListaCuentas()) {
            reporte += "- Cuenta #" + cuenta.getNumCuenta() + " | Saldo: $" + cuenta.getSaldo() + "\n";
        }

        return reporte;
    }
}