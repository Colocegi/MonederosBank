package Modelo;

/**
 * Clase principal (Main) para ejecutar la simulación bancaria.
 * Pone a prueba la creación de Cuentas, Clientes, y Operaciones.
 */
public class MainTest {

    public static void main(String[] args) {

        System.out.println("🏁 ==============================================");
        System.out.println("        🏦 INICIANDO SIMULADOR BANCARIO 🏦");
        System.out.println("==============================================\n");

        // --- 1. CREACIÓN DE CLIENTES Y CUENTAS ---
        System.out.println("👤 Creando Clientes y Cuentas...");
        
        // Cliente 1: Ana
        Cliente clienteAna = new Cliente(101, "Ana", "García");
        CuentaCorriente ccAna = new CuentaCorriente(1001, clienteAna, 500.0); // Con $500 de giro
        clienteAna.agregarCuenta(ccAna);

        // Cliente 2: Juan
        Cliente clienteJuan = new Cliente(102, "Juan", "Martínez");
        CuentaAhorro caJuan = new CuentaAhorro(2001, clienteJuan, 3.5); // Tasa del 3.5%
        clienteJuan.agregarCuenta(caJuan);

        // --- 2. CREACIÓN DEL BANCO ---
        Banco miBanco = new Banco("Banco JAVA", "BJX001");
        miBanco.agregarCliente(clienteAna);
        miBanco.agregarCliente(clienteJuan);
        System.out.println("🏦 Banco '" + miBanco.getNombre() + "' creado con " + miBanco.getListaClientes().size() + " clientes.\n");


        // --- 3. OPERACIONES BÁSICAS (Depósito e Intereses) ---
        System.out.println("--- 3. Depósitos y Mantenimiento ---");

        // PRUEBA DE DEPOSITO (Usando Deposito.java CORREGIDO)
        Deposito dep1 = new Deposito("D001", 1500.0, "2025-11-04", ccAna);
        dep1.ejecutar(); 
        
        Deposito dep2 = new Deposito("D002", 800.0, "2025-11-04", caJuan);
        dep2.ejecutar(); 

        // Mantenimiento y Cómputo de Intereses
        caJuan.calcularIntereses();
        System.out.println("-> Intereses calculados para Juan. Nuevo Saldo CA: $" + caJuan.getSaldo());
        
        // Cobrar mantenimiento
        try {
            ccAna.cobrarMantenimiento(); // Asumamos que cuesta $50
            System.out.println("-> Mantenimiento cobrado a Ana. Nuevo Saldo CC: $" + ccAna.getSaldo());
        } catch (FalloTransaccionException e) {
            System.err.println("❌ ERROR MANTENIMIENTO: " + e.getMessage());
        }
        System.out.println("");
        // Saldo CC Ana (aprox): $1500 - $50 = $1450.0
        // Saldo CA Juan (aprox): $800 + $28 = $828.0


        // --- 4. PRUEBA DE TRANSFERENCIA (Usando Transferencia.java CORREGIDO) ---
        System.out.println("--- 4. Prueba de Transferencia (Exitosa) ---");
        // Ana ($1450) transfiere $450 a Juan
        Transferencia t1 = new Transferencia("T001", 450.0, "2025-11-05", ccAna, "ID-JUAN-2001");
        t1.ejecutar(); 
        
        System.out.println("\n--- 4. Prueba de Transferencia (Fallida - Sin Fondos) ---");
        // Ana (Saldo $1000) intenta transferir $1600. Límite $1500.
        Transferencia t2 = new Transferencia("T002", 1600.0, "2025-11-05", ccAna, "ID-JUAN-2001");
        t2.ejecutar(); 
        System.out.println("");
        // Saldo CC Ana (aprox): $1450 - $450 = $1000.0
        // Saldo CA Juan (aprox): $828.0 (No hay acreditación implementada para simular el fallo, pero el débito de Ana fue exitoso)


        // --- 5. PRUEBA DE PAGO DE SERVICIO (Usando Servicio.java CORREGIDO) ---
        System.out.println("--- 5. Prueba de Pago de Servicio (Exitoso) ---");
        // Ana ($1000) paga $150 de luz
        Servicio s1 = new Servicio("LUZ-001", "2025-11-10", 150.0, ccAna);
        s1.ejecutar(); 
        System.out.println("");
        // Saldo CC Ana (aprox): $1000 - $150 = $850.0


        // --- 6. PRUEBA DE PLAZO FIJO (PlazoFijo.java) ---
        System.out.println("--- 6. Prueba de Inversión (Plazo Fijo) ---");
        PlazoFijo pf1 = new PlazoFijo(901, 500.0, 30);
        pf1.invertir(); 
        System.out.println("");

        // --- 7. PRUEBA DE REPORTES (Usando sintaxis de enum CORREGIDA) ---
        System.out.println("--- 7. Generación de Reportes Finales ---");
        GestorReportes gestor = new GestorReportes(miBanco);

        // Reporte en TEXTO (Sintaxis Corregida: IReportable.ReportFormat.TEXTO)
        String reporteTexto = gestor.generarReporte(IReportable.ReportFormat.TEXTO);
        System.out.println("\n--- REPORTE TEXTO ---");
        System.out.println(reporteTexto);

        // Reporte en CSV (Sintaxis Corregida: IReportable.ReportFormat.CSV)
        String reporteCsv = gestor.generarReporte(IReportable.ReportFormat.CSV);
        System.out.println("\n--- REPORTE CSV ---");
        System.out.println(reporteCsv);

        System.out.println("\n🏁 ==============================================");
        System.out.println("        🏦 SIMULACIÓN BANCARIA FINALIZADA 🏦");
        System.out.println("==============================================");
    }
}