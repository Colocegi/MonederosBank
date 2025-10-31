import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;

public class GestorReportes implements IReportable {
    private final Banco banco;

    public GestorReportes(Banco banco) {
        this.banco = banco;
    }

    // ====== Clases internas de formato ======
    private interface ReportFormatter {
        String header(Banco banco);
        String seccionClientesTitulo();
        String seccionCuentasTitulo();
        String cliente(Cliente c);
        String cuenta(Cuenta cta);
    }

    private static class TextReportFormatter implements ReportFormatter {
        @Override public String header(Banco b) {
            return "Banco: " + b.getNombre() + " (" + b.getCodigo() + ")";
        }
        @Override public String seccionClientesTitulo() { return "=== CLIENTES ==="; }
        @Override public String seccionCuentasTitulo()  { return "=== CUENTAS  ==="; }
        @Override public String cliente(Cliente c) {
            return "ID: " + c.getIdCliente() + " | " + c.getNombre() + " " + c.getApellido();
        }
        @Override public String cuenta(Cuenta c) {
            return "Cuenta #" + c.getNumCuenta() + " | Saldo: $" + c.getSaldo();
        }
    }

    private static class CsvReportFormatter implements ReportFormatter {
        @Override public String header(Banco b) {
            return "BANCO;" + b.getNombre() + ";" + b.getCodigo();
        }
        @Override public String seccionClientesTitulo() { return "CLIENTES"; }
        @Override public String seccionCuentasTitulo()  { return "CUENTAS"; }
        @Override public String cliente(Cliente c) {
            return c.getIdCliente() + ";" + c.getNombre() + ";" + c.getApellido();
        }
        @Override public String cuenta(Cuenta c) {
            return c.getNumCuenta() + ";" + c.getSaldo();
        }
    }
    // =========================================

    private ReportFormatter elegirFormatter(ReportFormat formato) {
        if (formato == ReportFormat.CSV) return new CsvReportFormatter();
        return new TextReportFormatter();
    }

    @Override
    public String generarReporte(ReportFormat formato) {
        ReportFormatter f = elegirFormatter(formato);
        StringBuilder sb = new StringBuilder();

        sb.append(f.header(banco)).append("\n");

        sb.append(f.seccionClientesTitulo()).append("\n");
        for (Cliente c : banco.getListaClientes()) {
            sb.append(f.cliente(c)).append("\n");
        }

        sb.append(f.seccionCuentasTitulo()).append("\n");
        for (Cuenta cta : banco.getListaCuentas()) {
            sb.append(f.cuenta(cta)).append("\n");
        }

        return sb.toString();
    }

    public void escribirReporteAArchivo(String ruta, ReportFormat formato) {
        String contenido = generarReporte(formato);
        try (PrintWriter writer = new PrintWriter(new FileWriter(ruta))) {
            writer.println(contenido);
        } catch (IOException e) {
            System.err.println("Error de escritura en el archivo: " + ruta);
        }
    }
}
