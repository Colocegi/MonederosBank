package Modelo;

import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
// IMPORTACIONES NUEVAS REQUERIDAS
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GestorReportes implements IReportable {
    private final Banco banco;

    public GestorReportes(Banco banco) {
        this.banco = banco;
    }

    // ... (Las clases internas TextReportFormatter y CsvReportFormatter quedan igual) ...

    private interface ReportFormatter {
        String header(Banco banco);
        String seccionClientesTitulo();
        String seccionCuentasTitulo();
        String cliente(Cliente c);
        String cuenta(Cuenta cta);
    }

    private static class TextReportFormatter implements ReportFormatter {
        @Override public String header(Banco b) {
            return "Modelo.Banco: " + b.getNombre() + " (" + b.getCodigo() + ")";
        }
        @Override public String seccionClientesTitulo() { return "=== CLIENTES (Ordenados por Nombre) ==="; } // Título actualizado
        @Override public String seccionCuentasTitulo()  { return "=== CUENTAS (Ordenadas por N°) ==="; } // Título actualizado
        @Override public String cliente(Cliente c) {
            return "ID: " + c.getIdCliente() + " | " + c.getNombre() + " " + c.getApellido();
        }
        @Override public String cuenta(Cuenta c) {
            return "Modelo.Cuenta #" + c.getNumCuenta() + " | Saldo: $" + c.getSaldo();
        }
    }

    private static class CsvReportFormatter implements ReportFormatter {
        @Override public String header(Banco b) {
            return "BANCO;" + b.getNombre() + ";" + b.getCodigo();
        }
        @Override public String seccionClientesTitulo() { return "CLIENTES (Ordenados por Nombre)"; } // Título actualizado
        @Override public String seccionCuentasTitulo()  { return "CUENTAS (Ordenadas por N°)"; } // Título actualizado
        @Override public String cliente(Cliente c) {
            return c.getIdCliente() + ";" + c.getNombre() + ";" + c.getApellido();
        }
        @Override public String cuenta(Cuenta c) {
            return c.getNumCuenta() + ";" + c.getSaldo();
        }
    }

    private ReportFormatter elegirFormatter(ReportFormat formato) {
        if (formato == ReportFormat.CSV) return new CsvReportFormatter();
        return new TextReportFormatter();
    }
    // =========================================================
    // AQUÍ ESTÁ LA MODIFICACIÓN (MÉTODO generarReporte)
    // =========================================================
    @Override
    public String generarReporte(ReportFormat formato) {
        ReportFormatter f = elegirFormatter(formato);
        StringBuilder sb = new StringBuilder();

        sb.append(f.header(banco)).append("\n");

        sb.append(f.seccionClientesTitulo()).append("\n");

        // --- INICIO DE CAMBIO (CLIENTES) ---
        // 1. Obtenemos la lista original
        List<Cliente> clientes = banco.getListaClientes();

        // 2. Creamos una NUEVA lista ordenada alfabéticamente por nombre
        List<Cliente> clientesOrdenados = clientes.stream()
                .sorted(Comparator.comparing(Cliente::getNombre))
                .collect(Collectors.toList());

        // 3. Iteramos sobre la lista ordenada
        for (Cliente c : clientesOrdenados) {
            sb.append(f.cliente(c)).append("\n");
        }
        // --- FIN DE CAMBIO (CLIENTES) ---


        sb.append(f.seccionCuentasTitulo()).append("\n");

        // --- INICIO DE CAMBIO (CUENTAS) ---
        // 1. Obtenemos la lista original
        List<Cuenta> cuentas = banco.getListaCuentas();

        // 2. Creamos una NUEVA lista ordenada por número de cuenta
        List<Cuenta> cuentasOrdenadas = cuentas.stream()
                .sorted(Comparator.comparing(Cuenta::getNumCuenta))
                .collect(Collectors.toList());

        // 3. Iteramos sobre la lista ordenada
        for (Cuenta cta : cuentasOrdenadas) {
            sb.append(f.cuenta(cta)).append("\n");
        }
        // --- FIN DE CAMBIO (CUENTAS) ---

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