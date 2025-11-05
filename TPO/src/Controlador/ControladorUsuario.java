package Controlador;

import Modelo.Banco;
import Modelo.Cliente;
import Modelo.GestorUsuario;
import Modelo.Usuario;

// Esta clase es el intermediario entre la Vista (pantalla de login)
// y el Modelo (GestorUsuario).
public class ControladorUsuario {

    // 1. El controlador tiene una referencia a la clase del Modelo
    private GestorUsuario gestor;
    private Banco banco; // <-- AÑADIDO: El controlador conoce al Banco

    // Cuando se crea el controlador, automáticamente crea su instancia de GestorUsuario
    public ControladorUsuario() {
        this.gestor = new GestorUsuario();
        this.banco = new Banco("MonederosBank", "MB001"); // <-- AÑADIDO

        // AÑADIDO: Cargar todos los clientes en el momento que se crea el controlador
        // Usamos la ruta relativa (que GestorUsuario también usa)
        this.banco.cargarClientesDesdeArchivo("datos/usuario.txt");
    }

    /**
     * Este es el método principal que llamará la Vista (la pantalla)
     * Recibe los strings de la pantalla, los valida y pide al gestor que autentique.
     *
     * CAMBIADO: Ya no devuelve un String. Devuelve el Cliente real o null.
     */
    public Cliente validarIngreso(String nombreUsuario, String clave) {

        // 3. Primera validación (lógica de negocio simple)
        if (nombreUsuario == null || nombreUsuario.trim().isEmpty() ||
                clave == null || clave.trim().isEmpty()) {
            return null; // Fallo
        }

        // 4. Crea el objeto del Modelo
        Usuario usuario = new Usuario(nombreUsuario, clave);

        // 5. Llama al Modelo (GestorUsuario) para que haga el trabajo de leer el archivo
        if (gestor.autenticar(usuario)) {
            // Éxito: Si el login es válido, busca el Cliente pre-cargado
            return banco.buscarClientePorNombre(nombreUsuario);
        } else {
            return null; // Fallo: Credenciales incorrectas
        }
    }

    // AÑADIDO: Un getter para que la Vista (FormAcceso) pueda obtener el banco
    public Banco getBanco() {
        return banco;
    }
}