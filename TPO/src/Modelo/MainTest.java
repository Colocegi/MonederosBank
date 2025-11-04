package Modelo; // (Lo dejamos en Modelo, porque ahí está tu archivo)

// 1. Importamos la Vista que creamos en el Paso 5
import Vista.FormAcceso;

/**
 * ¡Este es el NUEVO punto de entrada principal del programa!
 * Su único trabajo es iniciar la pantalla de login.
 * (Esta lógica es la que tenías en tu archivo Principal (1).java)
 */
public class MainTest {

    public static void main(String[] args) {

        // 2. Crea una instancia de la ventana de login
        FormAcceso formulario = new FormAcceso();

        // 3. La hace visible
        formulario.setVisible(true);
    }
}