package Controlador;

import Modelo.GestorUsuario;
import Modelo.Usuario;

// Esta clase es el intermediario entre la Vista (pantalla de login)
// y el Modelo (GestorUsuario).
public class ControladorUsuario {

    // 1. El controlador tiene una referencia a la clase del Modelo
    private GestorUsuario gestor;

    // Cuando se crea el controlador, automáticamente crea su instancia de GestorUsuario
    public ControladorUsuario() {
        this.gestor = new GestorUsuario();
    }

    // 2. Este es el método principal que llamará la Vista (la pantalla)
    // Recibe los strings de la pantalla, los valida y pide al gestor que autentique.
    public String validarIngreso(String nombreUsuario, String clave) {

        // 3. Primera validación (lógica de negocio simple)
        if (nombreUsuario == null || nombreUsuario.trim().isEmpty() ||
                clave == null || clave.trim().isEmpty()) {
            return "ERROR: Usuario y clave no pueden estar vacíos.";
        }

        // 4. Crea el objeto del Modelo
        Usuario usuario = new Usuario(nombreUsuario, clave);

        // 5. Llama al Modelo (GestorUsuario) para que haga el trabajo de leer el archivo
        if (gestor.autenticar(usuario)) {
            return "ACCESO_CONCEDIDO"; // Éxito
        } else {
            return "ERROR: Credenciales incorrectas."; // Fallo
        }
    }
}