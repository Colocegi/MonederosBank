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

    @Override
    public void generarReporte() {
        System.out.println("========== REPORTE DEL BANCO ==========");
        System.out.println("Banco: " + banco.getNombre());
        System.out.println("Código: " + banco.getCodigo());
        System.out.println("=======================================\n");

        System.out.println("CLIENTES REGISTRADOS: " + banco.getListaClientes().size());
        for (Cliente cliente : banco.getListaClientes()) {
            System.out.println("- " + cliente.getNombre() + " " + cliente.getApellido());
        }

        System.out.println("\nCUENTAS REGISTRADAS: " + banco.getListaCuentas().size());
        for (Cuenta cuenta : banco.getListaCuentas()) {
            System.out.println("- Cuenta #" + cuenta.getNumCuenta() + " | Saldo: $" + cuenta.getSaldo());
        }
    }
}