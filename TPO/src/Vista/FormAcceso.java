package Vista;

// --- IMPORTACIONES ---
// Necesitamos al Controlador (para validar) y la Simulación (para iniciarla)
import Controlador.ControladorUsuario;
import Modelo.SimulacionBanco;

// Estas son las librerías de Java para hacer ventanas (se llaman Swing)
import javax.swing.*; // Para la ventana (JFrame), botones (JButton), etc.
import java.awt.GridLayout; // Para organizar las cosas en la ventana
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; // Para "escuchar" el clic del botón

// Nuestra clase "es" una Ventana (por eso 'extends JFrame')
public class FormAcceso extends JFrame {

    // --- 1. DECLARAR LOS COMPONENTES ---
    // Guardamos los componentes visuales como variables de la clase
    private JTextField txtUsuario;
    private JPasswordField passClave;
    private JButton btnIngresar;

    // La Vista tiene una "conexión" al Controlador
    private ControladorUsuario controlador;

    // --- 2. EL CONSTRUCTOR (Se ejecuta cuando creamos la ventana) ---
    public FormAcceso() {

        // Creamos la instancia del Controlador que hicimos en el Paso 4
        this.controlador = new ControladorUsuario();

        // --- Configuración básica de la ventana ---
        setTitle("Acceso al Simulador Bancario"); // Título
        setSize(350, 150); // Tamaño (ancho, alto)
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Que el programa termine si cerramos esta ventana
        setLocationRelativeTo(null); // Centrarla en la pantalla

        // --- Organización (Layout) ---
        // Le decimos a la ventana cómo ordenar las cosas:
        // En una "grilla" de 3 filas y 2 columnas
        setLayout(new GridLayout(3, 2, 10, 10)); // (filas, cols, espacio horiz, espacio vert)

        // --- 3. CREAR Y AÑADIR LOS COMPONENTES ---

        // Fila 1:
        add(new JLabel("  Usuario:")); // Etiqueta
        txtUsuario = new JTextField(); // Campo de texto
        add(txtUsuario);

        // Fila 2:
        add(new JLabel("  Clave:")); // Etiqueta
        passClave = new JPasswordField(); // Campo de clave (oculta los caracteres)
        add(passClave);

        // Fila 3:
        btnIngresar = new JButton("Ingresar"); // Botón
        add(new JLabel()); // Un espacio en blanco (para que el botón quede a la derecha)
        add(btnIngresar);

        // --- 4. LA ACCIÓN DEL BOTÓN (¡La parte clave!) ---
        // Le decimos al botón qué hacer cuando le hagan clic
        btnIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // a. Obtenemos el texto que el usuario escribió
                String nombre = txtUsuario.getText();
                String clave = new String(passClave.getPassword()); // Se obtiene así del campo de clave

                // b. Le pasamos los datos al Controlador (Paso 4)
                String resultado = controlador.validarIngreso(nombre, clave);

                // c. Vemos qué nos respondió el Controlador
                if (resultado.equals("ACCESO_CONCEDIDO")) {

                    // SI ES CORRECTO:
                    // 1. Cerramos esta ventana de login
                    dispose();

                    // 2. Creamos e iniciamos la Simulación (Paso 1)
                    SimulacionBanco simulacion = new SimulacionBanco();
                    simulacion.iniciar();

                } else {
                    // SI ES INCORRECTO:
                    // Mostramos una ventana emergente con el error
                    JOptionPane.showMessageDialog(null, resultado);
                }
            }
        });
    }
}