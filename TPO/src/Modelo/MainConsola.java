package Modelo;


import Controlador.ControladorUsuario;
import Modelo.Banco;
import Modelo.Cliente;
import Modelo.FalloTransaccionException;
import Modelo.Cuenta;
import Modelo.CuentaCorriente;
import Modelo.CuentaAhorro;
import Modelo.Deposito;
import Modelo.GestorReportes;
import Modelo.IReportable;
import Modelo.IReportable.ReportFormat;
import Modelo.Transferencia;
import Modelo.Servicio;
import Modelo.PlazoFijo;
import java.util.Scanner;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Simulación de la aplicación
 * Permite iniciar sesión, ver cuentas, operar y generar reportes.
 */
public class MainConsola {

    private static final Scanner scanner = new Scanner(System.in);
    private static final ControladorUsuario controlador = new ControladorUsuario();
    private static Cliente clienteActual = null;
    private static final AtomicInteger contadorTransacciones = new AtomicInteger(100);

    public static void main(String[] args) {
        mostrarMenuPrincipal();
    }

    /**
     * Muestra el menú principal (Login) o el menú de cliente si ya está logueado.
     */
    private static void mostrarMenuPrincipal() {
        boolean salir = false;
        while (!salir) {
            System.out.println("\n===== MONEDEROS BANK CONSOLA =====");
            if (clienteActual == null) {
                System.out.println("1. Iniciar Sesión");
                System.out.println("2. Generar Reporte de Banco (ADMIN)");
                System.out.println("0. Salir");
                System.out.print("Seleccione una opción: ");
                String opcion = scanner.nextLine();

                switch (opcion) {
                    case "1":
                        iniciarSesion();
                        break;
                    case "2":
                        generarReporte(controlador.getBanco());
                        break;
                    case "0":
                        salir = true;
                        break;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                }
            } else {
                mostrarMenuCliente();
            }
        }
        System.out.println("¡Gracias por usar Monederos Bank! Adiós.");
    }

    /**
     * Pide credenciales y usa el ControladorUsuario para validar.
     */
    private static void iniciarSesion() {
        System.out.println("\n--- INICIO DE SESIÓN ---");
        System.out.print("Nombre de Usuario (ej: dante, thiago, ana): ");
        String nombre = scanner.nextLine();
        System.out.print("Clave (ej: 111, 222, 444): ");
        String clave = scanner.nextLine();

        clienteActual = controlador.validarIngreso(nombre, clave); //

        if (clienteActual != null) {
            System.out.println("\n✅ Ingreso Exitoso. ¡Bienvenido, " + clienteActual.getNombre() + "!");
        } else {
            System.out.println("\n❌ Error de Autenticación. Nombre de usuario o clave incorrectos.");
        }
    }

    /**
     * Muestra las operaciones disponibles para el cliente logueado.
     */
    private static void mostrarMenuCliente() {
        boolean cerrarSesion = false;
        while (!cerrarSesion) {
            System.out.println("\n===== MENÚ DE CLIENTE: " + clienteActual.getNombre() + " =====");
            System.out.println("1. Ver Cuentas y Saldos");
            System.out.println("2. Realizar Depósito");
            System.out.println("3. Realizar Débito/Retiro");
            System.out.println("4. Aplicar Operaciones Especiales (Intereses/Mantenimiento)");
            System.out.println("5. Realizar Transferencia"); // <-- NUEVO
            System.out.println("6. Pagar Servicio");         // <-- NUEVO
            System.out.println("7. Constituir Plazo Fijo"); // <-- NUEVO
            System.out.println("0. Cerrar Sesión");
            System.out.print("Seleccione una opción: ");
            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    verCuentas();
                    break;
                case "2":
                    realizarDeposito();
                    break;
                case "3":
                    realizarDebito();
                    break;
                case "4":
                    aplicarOperacionesMantenimiento();
                    break;
                case "5":
                    realizarTransferencia(); // <-- NUEVO
                    break;
                case "6":
                    pagarServicio(); // <-- NUEVO
                    break;
                case "7":
                    crearPlazoFijo(); // <-- NUEVO
                    break;
                case "0":
                    cerrarSesion = true;
                    clienteActual = null;
                    System.out.println("\nSesión cerrada.");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    /**
     * Muestra las cuentas del cliente actual.
     */
    private static void verCuentas() {
        System.out.println("\n--- CUENTAS DE " + clienteActual.getNombre().toUpperCase() + " ---");
        List<Cuenta> cuentas = clienteActual.getListaCuentas();
        if (cuentas.isEmpty()) {
            System.out.println("El cliente no tiene cuentas asociadas.");
            return;
        }

        for (Cuenta c : cuentas) {
            String tipo = (c instanceof CuentaCorriente) ? "Corriente" : "Ahorro";
            String detalle = "";
            if (c instanceof CuentaCorriente cc) {
                detalle = " | Límite Descubierto: $" + cc.getLimiteGiroDescubierto();
            } else if (c instanceof CuentaAhorro ca) {
                detalle = " | Tasa Interés: " + ca.getTasaIntereses() + "%";
            }
            System.out.printf("  [%-10s] #%d | Saldo: $%.2f%s\n", tipo, c.getNumCuenta(), c.getSaldo(), detalle);
        }
    }

    /**
     * Procesa un depósito en una cuenta seleccionada.
     */
    private static void realizarDeposito() {
        System.out.println("\n--- REALIZAR DEPÓSITO ---");
        Cuenta cuenta = seleccionarCuenta("Seleccione la cuenta de destino (Num Cuenta): ");
        if (cuenta == null) return;

        try {
            System.out.print("Ingrese el monto a depositar: $");
            double monto = Double.parseDouble(scanner.nextLine());

            if (monto <= 0) {
                System.out.println("❌ Error: El monto debe ser positivo.");
                return;
            }

            String idTransaccion = "DEP" + contadorTransacciones.incrementAndGet();
            Deposito deposito = new Deposito(idTransaccion, monto, "HOY", cuenta);
            deposito.ejecutar(); //

        } catch (NumberFormatException e) {
            System.out.println("❌ Error: Monto ingresado no válido.");
        }
    }

    /**
     * Procesa un débito (retiro) de una cuenta seleccionada.
     */
    private static void realizarDebito() {
        System.out.println("\n--- REALIZAR DÉBITO/RETIRO ---");
        Cuenta cuenta = seleccionarCuenta("Seleccione la cuenta a debitar (Num Cuenta): ");
        if (cuenta == null) return;

        try {
            System.out.print("Ingrese el monto a debitar: $");
            double monto = Double.parseDouble(scanner.nextLine());

            if (monto <= 0) {
                System.out.println("❌ Error: El monto debe ser positivo.");
                return;
            }

            cuenta.debitar(monto); // Llama a debitar de Ahorro o Corriente

            System.out.println("✅ Débito/Retiro de $" + monto + " exitoso de cuenta #" + cuenta.getNumCuenta());
            System.out.println("Nuevo Saldo: $" + cuenta.getSaldo());

        } catch (NumberFormatException e) {
            System.out.println("❌ Error: Monto ingresado no válido.");
        } catch (FalloTransaccionException e) { //
            System.out.println("❌ FALLO TRANSACCIÓN: " + e.getMessage());
        }
    }

    /**
     * Ejecuta las operaciones específicas de cada tipo de cuenta.
     */
    private static void aplicarOperacionesMantenimiento() {
        System.out.println("\n--- APLICAR OPERACIONES ESPECIALES ---");
        Cuenta cuenta = seleccionarCuenta("Seleccione la cuenta (Num Cuenta): ");
        if (cuenta == null) return;

        try {
            if (cuenta instanceof CuentaAhorro ca) {
                System.out.println("Acreditando intereses a Cuenta de Ahorro #" + ca.getNumCuenta());
                ca.calcularIntereses(); //
                System.out.println("✅ Intereses aplicados. Nuevo Saldo: $" + ca.getSaldo());

            } else if (cuenta instanceof CuentaCorriente cc) {
                System.out.println("Cobrando cuota de mantenimiento a Cuenta Corriente #" + cc.getNumCuenta());
                cc.cobrarMantenimiento(); //
                System.out.println("✅ Cuota de mantenimiento cobrada. Nuevo Saldo: $" + cc.getSaldo());

            } else {
                System.out.println("Esta cuenta no tiene operaciones especiales aplicables.");
            }
        } catch (FalloTransaccionException e) {
            System.out.println("❌ FALLO OPERACIÓN: " + e.getMessage());
        }
    }

    /**
     * Realiza una transferencia desde una cuenta de origen.
     */
    private static void realizarTransferencia() {
        System.out.println("\n--- REALIZAR TRANSFERENCIA ---");
        Cuenta cuentaOrigen = seleccionarCuenta("Seleccione la cuenta de ORIGEN (Num Cuenta): ");
        if (cuentaOrigen == null) return;

        try {
            System.out.print("Ingrese el monto a transferir: $");
            double monto = Double.parseDouble(scanner.nextLine());

            if (monto <= 0) {
                System.out.println("❌ Error: El monto debe ser positivo.");
                return;
            }

            System.out.print("Ingrese el CBU/Alias/ID del destinatario: ");
            String idDestinatario = scanner.nextLine();

            // Creamos la transacción
            String idTransaccion = "TRA" + contadorTransacciones.incrementAndGet();
            Transferencia trans = new Transferencia(idTransaccion, monto, "HOY", cuentaOrigen, idDestinatario);

            // La clase Transferencia maneja la lógica de debitar y la excepción
            trans.ejecutar(); //

        } catch (NumberFormatException e) {
            System.out.println("❌ Error: Monto ingresado no válido.");
        }
    }

    /**
     * Paga un servicio desde una cuenta.
     */
    private static void pagarServicio() {
        System.out.println("\n--- PAGO DE SERVICIO ---");
        Cuenta cuentaPagadora = seleccionarCuenta("Seleccione la cuenta PAGADORA (Num Cuenta): ");
        if (cuentaPagadora == null) return;

        try {
            System.out.print("Ingrese el ID o Nombre del Servicio (ej: Luz, Agua): ");
            String idServicio = scanner.nextLine();

            System.out.print("Ingrese el monto a pagar: $");
            double monto = Double.parseDouble(scanner.nextLine());

            if (monto <= 0) {
                System.out.println("❌ Error: El monto debe ser positivo.");
                return;
            }

            // Creamos el objeto Servicio
            Servicio serv = new Servicio(idServicio, "HOY", monto, cuentaPagadora);

            // La clase Servicio maneja la lógica de debitar y la excepción
            serv.ejecutar(); //

        } catch (NumberFormatException e) {
            System.out.println("❌ Error: Monto ingresado no válido.");
        }
    }

    /**
     * Constituye un Plazo Fijo, debitando de una cuenta.
     */
    private static void crearPlazoFijo() {
        System.out.println("\n--- CONSTITUIR PLAZO FIJO ---");
        Cuenta cuentaOrigen = seleccionarCuenta("Seleccione la cuenta de ORIGEN (Num Cuenta): ");
        if (cuentaOrigen == null) return;

        try {
            System.out.print("Ingrese el monto a invertir: $");
            double monto = Double.parseDouble(scanner.nextLine());

            System.out.print("Ingrese el plazo en días (ej: 30, 60): ");
            int plazoDias = Integer.parseInt(scanner.nextLine());

            if (monto <= 0 || plazoDias <= 0) {
                System.out.println("❌ Error: El monto y los días deben ser positivos.");
                return;
            }

            // El PlazoFijo.java no debita.
            // Lo debitamos aquí ANTES de crearlo.
            System.out.println("Intentando debitar $" + monto + " para constituir el plazo fijo...");
            cuentaOrigen.debitar(monto);

            // Si debitar() fue exitoso (no lanzó excepción):
            System.out.println("✅ Débito exitoso. Constituyendo Plazo Fijo...");

            int idPlazo = 500 + contadorTransacciones.incrementAndGet();
            PlazoFijo pf = new PlazoFijo(idPlazo, monto, plazoDias); //
            pf.invertir(); //

            System.out.println("Nuevo Saldo Cuenta #" + cuentaOrigen.getNumCuenta() + ": $" + cuentaOrigen.getSaldo());


        } catch (NumberFormatException e) {
            System.out.println("❌ Error: Monto o días ingresados no válidos.");
        } catch (FalloTransaccionException e) {
            // Capturamos si el débito falla
            System.out.println("❌ FALLO: No se pudo constituir el Plazo Fijo. " + e.getMessage());
        }
    }


    /**
     * Helper para que el usuario elija una de sus cuentas.
     */
    private static Cuenta seleccionarCuenta(String prompt) {
        List<Cuenta> cuentas = clienteActual.getListaCuentas();
        if (cuentas.isEmpty()) {
            System.out.println("El cliente no tiene cuentas.");
            return null;
        }

        System.out.println("Cuentas disponibles: " + cuentas.stream()
                .map(c -> "#" + c.getNumCuenta())
                .collect(Collectors.joining(", ")));

        System.out.print(prompt);
        try {
            // AHORA SÍ: El usuario debe ingresar el NÚMERO DE CUENTA (ej: 1001)
            int numCuenta = Integer.parseInt(scanner.nextLine());

            for (Cuenta c : cuentas) {
                if (c.getNumCuenta() == numCuenta) {
                    return c;
                }
            }
            // Si el bucle termina, no encontró la cuenta
            System.out.println("❌ Número de cuenta no encontrado."); //
            return null;
        } catch (NumberFormatException e) {
            System.out.println("❌ Entrada inválida. Debe ingresar un número.");
            return null;
        }
    }

    /**
     * Genera un reporte del banco usando GestorReportes.
     */
    private static void generarReporte(Banco banco) {
        System.out.println("\n--- GENERACIÓN DE REPORTE DE BANCO ---");

        GestorReportes gestorReportes = new GestorReportes(banco); //

        System.out.println("Seleccione formato:");
        System.out.println("1. Texto (TXT)");
        System.out.println("2. CSV");
        System.out.print("Opción: ");
        String opcion = scanner.nextLine();

        // Corrección según IReportable.java
        ReportFormat formato = ReportFormat.TEXTO;
        if (opcion.equals("2")) {
            formato = ReportFormat.CSV;
        }

        System.out.println("Generando reporte...");
        String contenido = gestorReportes.generarReporte(formato); //

        System.out.println("\n==================================");
        System.out.println("           REPORTE BANCO          ");
        System.out.println("==================================");
        System.out.println(contenido);
        System.out.println("==================================");

        String rutaArchivo = "reporte." + (formato == ReportFormat.CSV ? "csv" : "txt");
        gestorReportes.escribirReporteAArchivo(rutaArchivo, formato); //
        System.out.println("✅ Reporte generado y escrito en el archivo: " + rutaArchivo);
    }
}