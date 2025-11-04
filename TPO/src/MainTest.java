import Modelo.*;

public class MainTest {

    public static void main(String[] args) {

        System.out.println("--- 🏦 INICIO DE PRUEBAS DE CUENTAS ---");

        // 1. Simular la creación de un Modelo.Cliente (Implementación de Modelo.ICliente)
        // Necesitas una instancia de Modelo.Cliente para cumplir con el constructor de Modelo.Cuenta.

        // Creamos la Modelo.Cuenta Corriente primero
        CuentaCorriente cc = new CuentaCorriente(1001, null, 500.0);
        Cliente cliente1 = new Cliente(1, "Ana", "García", cc);
        cc = new CuentaCorriente(1001, cliente1, 500.0); // Se recrea con el Modelo.ICliente/Modelo.Cliente correcto

        // Creamos la Modelo.Cuenta de Ahorro
        CuentaAhorro ca = new CuentaAhorro(2002, null, 2.5);
        Cliente cliente2 = new Cliente(2, "Juan", "Pérez", ca);
        ca = new CuentaAhorro(2002, cliente2, 2.5); // Se recrea con el Modelo.ICliente/Modelo.Cliente correcto

        // --- PRUEBAS DE OPERACIONES ---

        // 2. Acreditación (Método público en Modelo.Cuenta)
        System.out.println("\n--- 1. Prueba de Acreditación (ÉXITO y Registro) ---");
        cc.acreditar(1500.0);
        ca.acreditar(800.0);
        System.out.println("Saldo CC (Ana): $" + cc.getSaldo());
        System.out.println("Saldo CA (Juan): $" + ca.getSaldo());

        // 3. Débito Exitoso (Lógica de Modelo.CuentaAhorro)
        System.out.println("\n--- 2. Prueba de Débito Ahorro (ÉXITO) ---");
        intentarDebito(ca, 300.0); // Debería restar el saldo y registrar el Retiro
        System.out.println("Nuevo Saldo CA: $" + ca.getSaldo()); // 500.0

        // 4. Débito Fallido (Lógica de Modelo.CuentaAhorro - Sin sobregiro)
        System.out.println("\n--- 3. Prueba de Débito Ahorro (FALLO SRP) ---");
        intentarDebito(ca, 600.0); // Debería lanzar Modelo.FalloTransaccionException

        // 5. Débito con Sobregiro (Lógica de Modelo.CuentaCorriente)
        System.out.println("\n--- 4. Prueba de Débito Corriente (Con Sobregiro) ---");
        intentarDebito(cc, 1800.0); // Saldo: 1500. Sobregira 300 (Límite 500)
        System.out.println("Nuevo Saldo CC: $" + cc.getSaldo()); // -300.0

        // 6. Débito Fallido (Lógica de Modelo.CuentaCorriente - Excede límite)
        System.out.println("\n--- 5. Prueba de Débito Corriente (Excede Límite) ---");
        // Saldo -300. Intenta debitar 300. Nuevo saldo -600 (Límite -500)
        intentarDebito(cc, 300.0);

        System.out.println("\n--- 🏦 FIN DE PRUEBAS DE CUENTAS ---");
    }

    /**
     * Método auxiliar que simula el controlador/Modelo.Banco manejando el débito y la excepción.
     * Este método cumple el Principio de Responsabilidad Única (SRP).
     */
    public static void intentarDebito(Cuenta cuenta, double monto) {
        try {
            // Lógica de negocio (llama al método protegido debitar)
            // Se usa un casting para acceder al método protected desde un método helper fuera del paquete
            // En un entorno real, el método debitar podría ser publico o accesible vía un service/banco.

            // Nota: Para que este código funcione, debitar() DEBE ser public o el helper debe estar en el mismo paquete.
            // Si debitar() es protected, tendrías que exponer un método public en Modelo.Cuenta, por ejemplo:
            // cuenta.realizarDebito(monto);

            // Asumiendo que has hecho debitar() public o estás en el mismo paquete
            cuenta.debitar(monto);

        } catch (FalloTransaccionException e) {
            // Manejo de errores (Presentación/UI)
            System.err.println("❌ FALLO DE TRANSACCIÓN: " + e.getMessage());
        }
    }
}