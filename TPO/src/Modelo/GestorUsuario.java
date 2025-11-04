package Modelo;

import java.io.BufferedReader;
// Importa estas clases en lugar de FileReader
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;

public class GestorUsuario {

    // 1. Esta ruta ahora es relativa A LA RAÍZ de 'src'
    private String rutaArchivo = "datos/usuario.txt"; // (No pongas 'src/' aquí)

    public boolean autenticar(Usuario usuario) {
        String credencialesABuscar = usuario.getNombre() + "," + usuario.getClave();

        // 2. Usamos el ClassLoader para obtener el archivo como un "recurso"
        // Esto busca 'datos/usuario.txt' dentro de 'src' (o del .jar compilado)
        InputStream inputStream = GestorUsuario.class.getClassLoader().getResourceAsStream(rutaArchivo);

        // 3. Verificamos si el archivo se encontró
        if (inputStream == null) {
            System.err.println("Error: No se pudo encontrar el archivo de recurso: " + rutaArchivo);
            return false;
        }

        // 4. Usamos 'InputStreamReader' para leer el flujo de datos (en lugar de FileReader)
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (linea.equals(credencialesABuscar)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo de usuarios: " + e.getMessage());
            return false;
        }

        return false;
    }
}