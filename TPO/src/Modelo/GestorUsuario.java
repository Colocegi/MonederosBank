package Modelo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException; // Necesitamos estas 3 para leer archivos

// Esta clase se encarga de leer el .txt y validar al usuario.
// Es "prima" de tu GestorReportes, pero para usuarios.
public class GestorUsuario {

    // 1. Definimos dónde estará el archivo .txt
    private String rutaArchivo = "datos/usuario.txt";

    // Este es el método principal que usará el Controlador.
    // Recibe el objeto Usuario (con nombre y clave) y dice si es válido o no.
    public boolean autenticar(Usuario usuario) {

        // 2. Preparamos el String exacto que queremos encontrar en el archivo
        String credencialesABuscar = usuario.getNombre() + "," + usuario.getClave();

        // 3. Usamos BufferedReader y FileReader (el concepto de tu profe)
        // Este "try-with-resources" abre el archivo y se asegura de cerrarlo
        // automáticamente, incluso si hay un error.
        try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))) {

            String linea;

            // 4. Leemos el archivo línea por línea
            while ((linea = reader.readLine()) != null) {

                // 5. Comparamos la línea actual del archivo con la que buscamos
                if (linea.equals(credencialesABuscar)) {
                    return true; // ¡Coincide! El usuario es válido.
                }
            }

        } catch (IOException e) {
            // 6. Si hay un error (ej: no se encuentra 'datos/usuario.txt')
            System.err.println("Error al leer el archivo de usuarios: " + e.getMessage());
            return false; // Por seguridad, no damos acceso
        }

        // 7. Si se leyó todo el archivo y no se encontró al usuario
        return false;
    }
}