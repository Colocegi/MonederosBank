import java.util.ArrayList;
import java.util.List;

public class Banco {
    private String nombre;
    private String codigo;
    private List<Cliente> listaClientes;
    private List<Cuenta> listaCuentas;

    public Banco(String nombre, String codigo) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.listaClientes = new ArrayList<>();
        this.listaCuentas = new ArrayList<>();
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public List<Cliente> getListaClientes() { return new ArrayList<>(listaClientes); }
    public List<Cuenta> getListaCuentas() { return new ArrayList<>(listaCuentas); }

    public void agregarCliente(Cliente cliente) {
        this.listaClientes.add(cliente);
    }

    public void agregarCuenta(Cuenta cuenta) {
        this.listaCuentas.add(cuenta);
    }
}
